package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 图片处理工具
 * ARGB == 0 : Java生成的图片的透明像素点的argb值<br>
 * ARGB == 16777215 : 外部PNG图片的透明像素点的argb值<br>
 */
public class ImageUtil{
	
	
	/**
	 * 获取指定源图上指定位置的像素(ARGB)
	 * @param srcImg 源图
	 * @param x x坐标
	 * @param y y坐标
	 */
	public static int getARGB(BufferedImage srcImg, int x, int y){
		return srcImg.getRGB(x, y);
	}
	
	/**
	 * 获取指定源图上指定位置的像素(RGB)
	 * @param srcImg 源图
	 * @param x x坐标
	 * @param y y坐标
	 */
	public static int getRGB(BufferedImage srcImg, int x, int y){
		int ARGB = getARGB(srcImg, x, y);
		return argb2RGB(ARGB);
	}
	
	/**
	 * 将ARGB颜色值转换成RGB颜色值
	 * @param ARGB argb颜色值
	 */
	public static int argb2RGB( int ARGB ){
		int r = (ARGB >> 16) & 0xff;
		int g = (ARGB >>  8) & 0xff;
		int b = (ARGB >>  0) & 0xff;
		int rgb = (r << 16) | (g << 8) | b ;
		return rgb;
	}
	
	/**
	 * 将字符串颜色转换成ARGB值
	 * @param color RGB或ARGB颜色值( #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000  )
	 * @return ARGB颜色值
	 */
	public static int str2ARGB( String color ){
		String colorStr = color.replace("#", "")
							   .replace("0x", "")
							   .replace("0X", "");
//		return Integer.parseUnsignedInt(colorStr, 16);//JDK1.8支持
		if( colorStr.length() != 6 && colorStr.length() != 8 )
			throw new RuntimeException("非法颜色值");
		String aStr = "FF";
		String rStr = "00";
		String gStr = "00";
		String bStr = "00";
		if( colorStr.length() == 6 ){
			rStr = colorStr.substring(0, 2);
			gStr = colorStr.substring(2, 4);
			bStr = colorStr.substring(4, 6);
		}else if( colorStr.length() == 8 ){
			aStr = colorStr.substring(0, 2);
			rStr = colorStr.substring(2, 4);
			gStr = colorStr.substring(4, 6);
			bStr = colorStr.substring(6, 8);
		}
		return 	(Integer.parseInt(aStr, 16) << 24) | 
				(Integer.parseInt(rStr, 16) << 16) | 
				(Integer.parseInt(gStr, 16) <<  8) | 
				 Integer.parseInt(bStr, 16);
	}
	
	
	/**
	 * 获取ARGB值中的R值
	 * @param ARGB
	 * @return
	 */
	public static int getRed( int ARGB ){
		return (ARGB >> 16) & 0xff;
	}
	
	
	/**
	 * 获取ARGB值中的G值
	 * @param ARGB
	 * @return
	 */
	public static int getGreen( int ARGB ){
		return (ARGB >>  8) & 0xff;
	}
	
	/**
	 * 获取ARGB值中的B值
	 * @param ARGB
	 * @return
	 */
	public static int getBlue( int ARGB ){
		return (ARGB >>  0) & 0xff;
	}
	
	/**
	 * 在源图中查找第一个匹配的像素
	 * @param srcImg 源图
	 * @param color 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 第一个匹配的像素信息("idx":数组中哪个索引下的像素被匹配; "x":x坐标,"y":y坐标)
	 */
	public static int[] findPx( BufferedImage srcImg, String color ){
		return findPx(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), color);
	}
	
	/**
	 * 在源图中查找第一个匹配的像素
	 * @param srcImg 源图
	 * @param x x起始坐标
	 * @param y y起始坐标
	 * @param w 宽度
	 * @param h 高度
	 * @param color 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 第一个匹配的像素信息("idx":数组中哪个索引下的像素被匹配; "x":x坐标,"y":y坐标)
	 */
	public static int[] findPx( BufferedImage srcImg, int x, int y, int w, int h, String color ){
		for( int sx = x ; sx < x + w; sx++ ){
			for( int sy = y ; sy < y + h; sy++ ){
				int sARGB = getARGB(srcImg, sx, sy);
				int tARGB = str2ARGB(color);
				if( tARGB == sARGB )
					return new int[]{sx, sy};
			}
		}
		return null;
	}
	
	/**
	 * 在源图中查找第一个匹配的像素
	 * @param srcImg 源图
	 * @param colors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 第一个匹配的像素信息("idx":数组中哪个索引下的像素被匹配; "x":x坐标,"y":y坐标)
	 */
	public static Map<String, Object> findPx( BufferedImage srcImg, String[] colors ){
		return findPx(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), colors);
	}
	
	/**
	 * 在源图中查找第一个匹配的像素
	 * @param srcImg 源图
	 * @param x x起始坐标
	 * @param y y起始坐标
	 * @param w 宽度
	 * @param h 高度
	 * @param colors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 第一个匹配的像素信息("idx":数组中哪个索引下的像素被匹配; "x":x坐标,"y":y坐标)
	 */
	public static Map<String, Object> findPx( BufferedImage srcImg, int x, int y, int w, int h, String[] colors ){
		for( int sx = x ; sx < x + w; sx++ ){
			for( int sy = y ; sy < y + h; sy++ ){
				int sARGB = getARGB(srcImg, sx, sy);
				for( int idx = 0 ; idx < colors.length; idx++ ){
					String color = colors[idx];
					int tARGB = str2ARGB(color);
					if( sARGB == tARGB ){
						Map<String, Object> rs = new HashMap<String, Object>();
						rs.put("idx", idx);
						rs.put("x", sx);
						rs.put("y", sy);
						return rs;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImg(BufferedImage srcImg, BufferedImage targetImg){
		return findImg(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, null, null);
	}
	
	/**
	 * 在源图中查找目标图且与子图透明像素位置对于的源图位置上的像素不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImg(BufferedImage srcImg, BufferedImage targetImg, String neqColor){
		return findImg(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标图且与子图透明像素位置对于的源图位置上的像素不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImg(BufferedImage srcImg, BufferedImage targetImg, String[] neqColors){
		return findImg(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg ){
		return findImg(srcImg, x, y, w, h, targetImg, null, null);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String neqColor ){
		return findImg(srcImg, x, y, w, h, targetImg, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String[] neqColors ){
		return findImg(srcImg, x, y, w, h, targetImg, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	private static int[] findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String neqColor, String[] neqColors){
		int tImgW = targetImg.getWidth();
		int tImgH = targetImg.getHeight();
		
		//获取目标图左上角像素值
		int tBaseARGB = getARGB(targetImg, 0 , 0);
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x + w) - tImgW  ; sx++ ){
			b:for( int sy = y ; sy <= (y + h) - tImgH; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
					for( int tx = 0 ; tx < tImgW; tx++ ){
						for( int ty = 0 ; ty < tImgH; ty++){
							sARGB = getARGB(srcImg, sx + tx, sy + ty);
							int tARGB = getARGB(targetImg, tx, ty);
							if( tARGB == 0 || tARGB == 16777215 ){
								if( !Validator.isNull(neqColor) ){
									int neqARGB = str2ARGB(neqColor);
									if( neqARGB == sARGB ){
//										System.out.println("src:" + ( sx + tx ) + "," + ( sy + ty ));
//										System.out.println("t:" + tx + "," + ty );
										continue b;//不是当前子图,对下一个子图进行基点对比
									}
								}else if( !Validator.isEmpty(neqColors) ){
									for( String neqColorTmp : neqColors ){
										int neqARGB = str2ARGB(neqColorTmp);
										if( neqARGB == sARGB ){
//											System.out.println("src:" + ( sx + tx ) + "," + ( sy + ty ));
//											System.out.println("t:" + tx + "," + ty );
											continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
							}
							if( tARGB != 0 && tARGB != 16777215 && sARGB != tARGB ){
//								System.out.println("src:" + ( sx + tx ) + "," + ( sy + ty ));
//								System.out.println("t:" + tx + "," + ty );
								continue b;//寻找下一基点
							}
						}
					}
					//找到子图,返回子图所在源图的坐标
					return new int[]{sx, sy};
				}
			}
		}
		return null;
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImgV(BufferedImage srcImg, BufferedImage targetImg){
		return findImgV(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, null, null);
	}
	
	/**
	 * 在源图中查找目标图且与子图透明像素位置对于的源图位置上的像素不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImgV(BufferedImage srcImg, BufferedImage targetImg, String neqColor){
		return findImgV(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标图且与子图透明像素位置对于的源图位置上的像素不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImgV(BufferedImage srcImg, BufferedImage targetImg, String[] neqColors){
		return findImgV(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg ){
		return findImg(srcImg, x, y, w, h, targetImg, null, null);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String neqColor ){
		return findImg(srcImg, x, y, w, h, targetImg, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String[] neqColors ){
		return findImgV(srcImg, x, y, w, h, targetImg, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标图(从上往下扫描)
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	private static int[] findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String neqColor, String[] neqColors){
		int tImgW = targetImg.getWidth();
		int tImgH = targetImg.getHeight();
		
		//获取目标图左上角像素值
		int tBaseARGB = getARGB(targetImg, 0 , 0);
		
		//在源图中寻找比对基点
		for( int sy = y ; sy <= (y + h) - tImgH; sy++){
			b:for( int sx = x ; sx <= (x + w) - tImgW  ; sx++ ){
				int sARGB = getARGB(srcImg, sx, sy);
				if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
					for( int tx = 0 ; tx < tImgW; tx++ ){
						for( int ty = 0 ; ty < tImgH; ty++){
							sARGB = getARGB(srcImg, sx + tx, sy + ty);
							int tARGB = getARGB(targetImg, tx, ty);
							if( tARGB == 0 || tARGB == 16777215 ){
								if( !Validator.isNull(neqColor) ){
									int neqARGB = str2ARGB(neqColor);
									if( neqARGB == sARGB )
										continue b;//不是当前子图,对下一个子图进行基点对比
								}else if( !Validator.isEmpty(neqColors) ){
									for( String neqColorTmp : neqColors ){
										int neqARGB = str2ARGB(neqColorTmp);
										if( neqARGB == sARGB )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}
								}
							}
							if( tARGB != 0 && tARGB != 16777215 && sARGB != tARGB ){
								continue b;//寻找下一基点
							}
						}
					}
					//找到子图,返回子图所在源图的坐标
					return new int[]{sx, sy};
				}
			}
		}
		return null;
	}
	
	/**
	 * 在指定的源图中指定的范围内X方向的中间开始向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @return 目标图的左上角坐标
	 */
	public static int[] findImg4XCenter( BufferedImage sImg, int x, int y, int w, int h, BufferedImage tImg, int offsetX, int offsetY ){
		return findImg4X(sImg, x, y, w, h, x + w/2, tImg, offsetX, offsetY , null, null);
	}
	
	/**
	 * 在指定的源图中指定的范围内X方向的中间开始向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的左上角坐标
	 */
	public static int[] findImg4XCenter( BufferedImage sImg, int x, int y, int w, int h, BufferedImage tImg, int offsetX, int offsetY, String neqColor ){
		return findImg4X(sImg, x, y, w, h, x + w/2, tImg, offsetX, offsetY , neqColor, null);
	}
	
	/**
	 * 在指定的源图中指定的范围内X方向的中间开始向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的左上角坐标
	 */
	public static int[] findImg4XCenter( BufferedImage sImg, int x, int y, int w, int h, BufferedImage tImg, int offsetX, int offsetY, String[] neqColors ){
		return findImg4X(sImg, x, y, w, h, x + w/2, tImg, offsetX, offsetY, null, neqColors);
	}
	
	/**
	 * 在指定的源图中指定的范围内从指定的x开始在x方向上向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param startX 搜寻起始点的x坐标
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @param targetImg 目标图
	 * @return 目标图的左上角坐标
	 */
	public static int[] findImg4X( BufferedImage sImg, int x, int y, int w, int h, int startX, BufferedImage tImg, int offsetX, int offsetY){
		return findImg4X(sImg, x, y, w, h, startX, tImg, offsetX, offsetY, null, null);
	}
	
	/**
	 * 在指定的源图中指定的范围内从指定的x开始在x方向上向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param startX 搜寻起始点的x坐标
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的左上角坐标
	 */
	public static int[] findImg4X( BufferedImage sImg, int x, int y, int w, int h, int startX, BufferedImage tImg, int offsetX, int offsetY, String neqColor){
		return findImg4X(sImg, x, y, w, h, startX, tImg, offsetX, offsetY, neqColor, null);
	}
	
	/**
	 * 在指定的源图中指定的范围内从指定的x开始在x方向上向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param startX 搜寻起始点的x坐标
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @param targetImg 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的左上角坐标
	 */
	public static int[] findImg4X( BufferedImage sImg, int x, int y, int w, int h, int startX, BufferedImage tImg, int offsetX, int offsetY, String[] neqColors){
		return findImg4X(sImg, x, y, w, h, startX, tImg, offsetX, offsetY, null, neqColors);
	}
	
	/**
	 * 在指定的源图中指定的范围内从指定的x开始在x方向上向两边寻找第一次出现的目标图
	 * @param sImg 源图
	 * @param x 寻找范围的x坐标起始点(必须大于等于0)
	 * @param y 寻找范围的y坐标起始点(必须大于等于0)
	 * @param w 寻找范围的宽度(必须小于等于源图宽度)
	 * @param h 寻找范围的高度(必须小于等于源图高度)
	 * @param startX 搜寻起始点的x坐标
	 * @param tImg 目标图
	 * @param offsetX 目标图定位点相对于左上角点的偏移量X
	 * @param offsetY 目标图定位点相对于左上角点的偏移量Y
	 * @param targetImg 目标图
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的左上角坐标
	 */
	private static int[] findImg4X( BufferedImage sImg, int x, int y, int w, int h, int startX, BufferedImage tImg, int offsetX, int offsetY, String neqColor, String[] neqColors ){
		if( x < 0 || y < 0 )
			throw new RuntimeException("范围起始点(x,y)不能小于0");
		if( startX < x  )
			throw new RuntimeException("搜寻起始点x坐标不能小于范围起始点");
		//获取源图宽高
		int sImgW = sImg.getWidth();
		int sImgH = sImg.getHeight();
		if( w > sImgW || h > sImgH )
			throw new RuntimeException("查找范围宽高不能超出源图大小");
		
		//获取目标图宽高
		int tImgW = tImg.getWidth();
		int tImgH = tImg.getHeight();
		
		//获取目标图左上角ARGB像素值
		int tBaseARGB = getARGB(tImg, 0 , 0);
		
		//在源图中寻找最近子图的比对基点
		int[] startPoint = new int[2];
		startPoint[0] = startX;
		startPoint[1] = y;
		int len = 0;
		boolean isLeftOver = false;//左边是否扫描完毕
		boolean isRightOver = false;//右边是否扫描完毕
		for( int sx = startPoint[0] ; (isLeftOver || sx >= x) && (isRightOver || sx <= (x + w) - tImgW - 1) ;  ){
			n:for( int sy = startPoint[1] ; sy >= y && sy <= (y + h) - tImgH - 1 ; sy++ ){
				int sARGB = getARGB(sImg, sx, sy);
				if( tBaseARGB != 0 && tBaseARGB != 16777215 && tBaseARGB != sARGB )
					continue n;//在原地此位置上为发现子图, 结束比对并开始下一基点的寻找
				for( int tx = 0 ; tx < tImgW ; tx++ ){
					for( int ty = 0 ; ty < tImgH ; ty++){
						sARGB = getARGB(sImg, sx + tx, sy + ty);
						int tARGB = getARGB(tImg, tx, ty);
						if( tARGB == 0 || tARGB == 16777215 ){
							if( !Validator.isNull(neqColor) ){
								int neqARGB = str2ARGB(neqColor);
								if( neqARGB == sARGB )
									continue n;//不是当前子图,对下一个子图进行基点对比
							}else if( !Validator.isEmpty(neqColors) ){
								for( String neqColorTmp : neqColors ){
									int neqARGB = str2ARGB(neqColorTmp);
									if( neqARGB == sARGB )
										continue n;//不是当前子图,对下一个子图进行基点对比
								}
							}
						}
						if( tARGB != 0 && tARGB != 16777215 && tARGB != sARGB )
							continue n;//在原地此位置上为发现子图, 结束比对并开始下一基点的寻找
					}
				}
				//找到最近子图
//				if( sx < startX ){//目标图在左边
//					return new int[]{sx + tImg.getWidth() - offsetX, sy + offsetY};
//					
//				}else{//目标图在右边
//					return new int[]{sx + offsetX, sy + offsetY};
//				}
				return new int[]{sx + offsetX, sy + offsetY};
			}
			if( len >= 0 && !isRightOver && !isLeftOver ){
				len = -1 * (Math.abs(len) + 1);
				sx+=len;
				if( sx < x ){
					isLeftOver = true;
					sx += Math.abs(len) + 1;
				}
			}else if( len < 0 && !isRightOver && !isLeftOver ){
				len = Math.abs(len) + 1;
				sx+=len;
				if( sx > (x + w) - 1 ){
					isRightOver = true;
					sx += -1 * (Math.abs(len) + 1) ;
				}
			}else if( isLeftOver ){
				sx++;
			}else if( isRightOver ){
				sx--;
			}
		}
		return null;
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs){
		return findImg(srcImg, x, y, w, h, tImgs, null, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 *  @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String neqColor){
		return findImg(srcImg, x, y, w, h, tImgs, neqColor, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String[] neqColors){
		return findImg(srcImg, x, y, w, h, tImgs, null, neqColors);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	private static Map<String, Object> findImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String neqColor, String[] neqColors){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x+w) - 1; sx++ ){
			for( int sy = y ; sy <= (y+h) - 1; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( int idx = 0; idx < tImgs.length; idx++ ){
					BufferedImage tImg = tImgs[idx];//当前子图
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取当前子图的基点像素
					int tBaseARGB = getARGB(tImg, 0, 0);
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
						for( int tx = 0 ; tx < tImgW; tx++ ){
							for( int ty = 0 ; ty < tImgH; ty++){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int neqARGB = str2ARGB(neqColor);
										if( neqARGB == sARGB_tmp )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}else if( !Validator.isEmpty(neqColors) ){
										for( String neqColorTmp : neqColors ){
											int neqARGB = str2ARGB(neqColorTmp);
											if( neqARGB == sARGB_tmp )
												continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
								if( tARGB != 0 && tARGB != 16777215 && tARGB != sARGB_tmp )
									continue b;//不是当前子图,对下一个子图进行基点对比
							}
						}
						//找到子图,保存子图所在源图的坐标
						Map<String, Object> rs = new HashMap<String, Object>();
						rs.put("idx", idx);
						rs.put("x", sx);
						rs.put("y", sy);
						return rs;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中所有的子图
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static List<Map<String, Object>> findImgs(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs){
		return findImgs(srcImg, x, y, w, h, tImgs, null, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给定目标图数组中所有的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 *  @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static List<Map<String, Object>> findImgs(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String neqColor){
		return findImgs(srcImg, x, y, w, h, tImgs, neqColor, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中所有的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static List<Map<String, Object>> findImgs(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String[] neqColors){
		return findImgs(srcImg, x, y, w, h, tImgs, null, neqColors);
	}
	
	/**
	 * 在源图上指定的范围中查找给定目标图数组中所有的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	private static List<Map<String, Object>> findImgs(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String neqColor, String[] neqColors){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		List<Map<String, Object>> imgs = null;
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x+w) - 1; sx++ ){
			for( int sy = y ; sy <= (y+h) - 1; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( int idx = 0; idx < tImgs.length; idx++ ){
					BufferedImage tImg = tImgs[idx];//当前子图
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取当前子图的基点像素
					int tBaseARGB = getARGB(tImg, 0, 0);
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
						for( int tx = 0 ; tx < tImgW; tx++ ){
							for( int ty = 0 ; ty < tImgH; ty++){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int neqARGB = str2ARGB(neqColor);
										if( neqARGB == sARGB_tmp )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}else if( !Validator.isEmpty(neqColors) ){
										for( String neqColorTmp : neqColors ){
											int neqARGB = str2ARGB(neqColorTmp);
											if( neqARGB == sARGB_tmp )
												continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
								if( tARGB != 0 && tARGB != 16777215 && tARGB != sARGB_tmp )
									continue b;//不是当前子图,对下一个子图进行基点对比
							}
						}
						
						if( imgs == null )
							imgs = new ArrayList<Map<String, Object>>();
						//找到子图,保存子图所在源图的坐标
						Map<String, Object> rs = new HashMap<String, Object>();
						rs.put("idx", idx);
						rs.put("x", sx);
						rs.put("y", sy);
						imgs.add(rs);
					}
				}
			}
		}
		return imgs;
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs){
		return findImgV(srcImg, x, y, w, h, tImgs, null, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 *  @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String neqColor){
		return findImgV(srcImg, x, y, w, h, tImgs, neqColor, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String[] neqColors){
		return findImgV(srcImg, x, y, w, h, tImgs, null, neqColors);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	private static Map<String, Object> findImgV(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, String neqColor, String[] neqColors){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		//在源图中寻找比对基点
		for( int sy = y ; sy <= (y+h) - 1; sy++){
			for( int sx = x ; sx <= (x+w) - 1; sx++ ){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( int idx = 0; idx < tImgs.length; idx++ ){
					BufferedImage tImg = tImgs[idx];//当前子图
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取当前子图的基点像素
					int tBaseARGB = getARGB(tImg, 0, 0);
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
						for( int ty = 0 ; ty < tImgH; ty++){
							for( int tx = 0 ; tx < tImgW; tx++ ){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int neqARGB = str2ARGB(neqColor);
										if( neqARGB == sARGB_tmp )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}else if( !Validator.isEmpty(neqColors) ){
										for( String neqColorTmp : neqColors ){
											int neqARGB = str2ARGB(neqColorTmp);
											if( neqARGB == sARGB_tmp )
												continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
								if( tARGB != 0 && tARGB != 16777215 && tARGB != sARGB_tmp )
									continue b;//不是当前子图,对下一个子图进行基点对比
							}
						}
						//找到子图,保存子图所在源图的坐标
						Map<String, Object> rs = new HashMap<String, Object>();
						rs.put("idx", idx);
						rs.put("x", sx);
						rs.put("y", sy);
						return rs;
					}
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * 生成文字对应的图片
	 * @param word 文字字符
	 * @param fontName 字体名称
	 * @param fontStyle 字体样式
	 * @param fontSize 字体大小
	 * @param fontColor 字体颜色
	 */
	public static BufferedImage genImg4Word( char word, String fontName, int fontStyle, int fontSize, Color fontColor ){
		BufferedImage tmpImg = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = (Graphics2D)tmpImg.getGraphics();
		Font font = new Font(fontName, fontStyle, fontSize);
		return genImg4Word(g, word, font, fontColor);
	}
	
	/**
	 * 生成文字对应的图片
	 * @param text 文本字符串
	 * @param fontName 字体名称
	 * @param fontStyle 字体样式
	 * @param fontSize 字体大小
	 * @param fontColor 字体颜色
	 */
	public static BufferedImage[] genImg4Text( String text, String fontName, int fontStyle, int fontSize, Color fontColor ){
		BufferedImage tmpImg = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = (Graphics2D)tmpImg.getGraphics();
		Font font = new Font(fontName, fontStyle, fontSize);
		BufferedImage[] imgs = new BufferedImage[text.length()];
		for( int i = 0 ; i < text.length() ; i++ ){
			imgs[i] = genImg4Word(g, text.charAt(i), font, fontColor);
		}
		return imgs;
	}
	
	/**
	 * 生成文字对应的图片
	 * @param Graphics2D 2D绘图画笔
	 * @param word 文字字符
	 * @param fontName 字体名称
	 * @param fontStyle 字体样式
	 * @param fontSize 字体大小
	 * @param fontColor 字体颜色
	 */
	public static BufferedImage genImg4Word( Graphics2D g, char word, Font font, Color fontColor ){
		//文本测量所需的信息容器
		FontRenderContext context = g.getFontRenderContext();
		//设置字体
		g.setFont(font);
		//当前文本字符串
		String wordStr = word+"";
		boolean isSix = false;//是否是6
		boolean isPoint = false;//是否是"."号
		boolean isSpace = false;//是否是空格
		if( "6".equals(wordStr) ){
			wordStr = "9";
			isSix = true;
		}else if( ".".equals(wordStr) ){//是"."号
			wordStr = "1";
			isPoint = true;
		}else if( " ".equals(wordStr) ){//是空格
			isSpace = true;
		}
		//文本测量
		Rectangle2D stringBounds = font.getStringBounds(wordStr, context);
		//新建缓冲图片对象
		BufferedImage newImg = new BufferedImage(
				(int)stringBounds.getWidth(), (int)stringBounds.getHeight(),
				BufferedImage.TYPE_INT_ARGB
		);
		//绘制文字
		g = (Graphics2D)newImg.getGraphics();
		g.setFont(font);
		g.setColor( fontColor );
		g.drawString(wordStr, 0, font.getSize() - 1);
		if( !isSpace ){
			//去掉周围空白像素
			Integer minX = null, minY = null;
			Integer maxX = null, maxY = null;
			for( int x = 0 ; x < newImg.getWidth() ; x++ ){
				for( int y = 0 ; y < newImg.getHeight() ; y++ ){
					int argb = getARGB(newImg, x, y);
					if( argb != 0 && argb != 16777215 ){//非透明
						if( minX == null || x < minX ) minX = x;
						if( maxX == null || x > maxX ) maxX = x;
						
						if( minY == null || y < minY ) minY = y;
						if( maxY == null || y > maxY ) maxY = y;
					}
				}
			}
			newImg = newImg.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
		}
		if( isSix )
			return rotate(newImg, 180);
		if( isPoint ){
			int height = newImg.getHeight();
			//新建缓冲图片对象
			newImg = new BufferedImage( 1, height, BufferedImage.TYPE_INT_ARGB );
			int fontARGB = (fontColor.getAlpha() << 24) | (fontColor.getRed() << 16) | (fontColor.getGreen() << 8) | fontColor.getBlue();
			newImg.setRGB(0, height-1, fontARGB);
		}
		return newImg;
	}
	
	/**
	 * 在中心点上对图片进行旋转指定角度
	 * @param image 待旋转的图片
	 * @param degree 旋转角度
	 * @return
	 */
	public static BufferedImage rotate( BufferedImage image, int degree ){
		BigDecimal w = new BigDecimal( image.getWidth() + "" );
		BigDecimal h = new BigDecimal( image.getHeight() + "" );
		BigDecimal half = new BigDecimal( "2" );
		return rotate(image, w.divide(half).doubleValue(), h.divide(half).doubleValue(), degree);
	}
	
	/**
	 * 在指定旋转点上对图片进行旋转指定角度
	 * @param image 待旋转的图片
	 * @param rX 旋转原点的x坐标
	 * @param rY 旋转原点的y坐标
	 * @param degree 旋转角度
	 */
	public static BufferedImage rotate( BufferedImage image, double rX, double rY, int degree ) { 
		//将角度转换到0-360度之间 
		degree = degree % 360; 
		if (degree < 0) 
			degree = 360 + degree;
		//将角度转为弧度 
		double ang = Math.toRadians(degree);
		/*
		 * 确定旋转后的图象的高度和宽度 
		 */ 
		int iw = image.getWidth();//原始图象的宽度 
		int ih = image.getHeight();//原始图象的高度 
		//转换后的宽高
		int w = 0; 
		int h = 0; 
		if (degree == 180 || degree == 0 || degree == 360) { 
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) { 
			w = ih;
			h = iw;
		} else { 
			int d = iw + ih; 
			w = (int) (d * Math.abs(Math.cos(ang))); 
			h = (int) (d * Math.abs(Math.sin(ang))); 
//			w = (int) (sinVal*ih) + (int) (cosVal*iw); 
//			h = (int) (sinVal*iw) + (int) (cosVal*ih); 
		} 
		//确定原点坐标 
		int x = 0; 
		int y = 0; 
		x = (w / 2) - (iw / 2);
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType()); 
		AffineTransform at = new AffineTransform(); 
		at.rotate(ang, rX, rY);//旋转图象 
		at.translate(x, y); 
		AffineTransformOp op = new AffineTransformOp(at, ((Graphics2D)image.getGraphics()).getRenderingHints()); 
		op.filter(image, rotatedImage); 
		return rotatedImage; 
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findShape(BufferedImage srcImg, BufferedImage targetImg, String color ){
		return findShape(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, color, null, null);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findShape(BufferedImage srcImg, BufferedImage targetImg, String color, String neqColor){
		return findShape(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, color, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串 (#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findShape(BufferedImage srcImg, BufferedImage targetImg, String color, String[] neqColors){
		return findShape(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, color, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findShape(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String color, String neqColor){
		return findShape(srcImg, x, y, w, h, targetImg, color, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串 (#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findShape(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String color, String[] neqColors){
		return findShape(srcImg, x, y, w, h, targetImg, color, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	private static int[] findShape(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String color, String neqColor, String[] neqColors){
		int tImgW = targetImg.getWidth();
		int tImgH = targetImg.getHeight();
		
		int patternARGB = str2ARGB(color);
		
		//获取目标图左上角像素值
		int tBaseARGB = getARGB(targetImg, 0 , 0);
		if( tBaseARGB != 0 && tBaseARGB != 16777215 )//非透明
			tBaseARGB = patternARGB;
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x + w) - tImgW  ; sx++ ){
			b:for( int sy = y ; sy <= (y + h) - tImgH; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
					for( int tx = 0 ; tx < tImgW; tx++ ){
						for( int ty = 0 ; ty < tImgH; ty++){
							sARGB = getARGB(srcImg, sx + tx, sy + ty);
							int tARGB = getARGB(targetImg, tx, ty);
							if( tARGB == 0 || tARGB == 16777215 ){
								if( !Validator.isNull(neqColor) ){
									int neqARGB = str2ARGB(neqColor);
									if( neqARGB == sARGB )
										continue b;//不是当前子图,对下一个子图进行基点对比
								}else if( !Validator.isEmpty(neqColors) ){
									for( String neqColorTmp : neqColors ){
										int neqARGB = str2ARGB(neqColorTmp);
										if( neqARGB == sARGB )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}
								}
							}
							if( tARGB != 0 && tARGB != 16777215 && sARGB != patternARGB ){
								continue b;//寻找下一基点
							}
						}
					}
					//找到子图,返回子图所在源图的坐标
					return new int[]{sx, sy};
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static List<int[]> findShapes(BufferedImage srcImg, BufferedImage targetImg, String color ){
		return findShapes(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, color, null, null);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static List<int[]> findShapes(BufferedImage srcImg, BufferedImage targetImg, String color, String neqColor){
		return findShapes(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, color, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串 (#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static List<int[]> findShapes(BufferedImage srcImg, BufferedImage targetImg, String color, String[] neqColors){
		return findShapes(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, color, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static List<int[]> findShapes(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String color, String neqColor){
		return findShapes(srcImg, x, y, w, h, targetImg, color, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串 (#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static List<int[]> findShapes(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String color, String[] neqColors){
		return findShapes(srcImg, x, y, w, h, targetImg, color, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标形状
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标形状图片
	 * @param color 颜色字符串(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	private static List<int[]> findShapes(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, String color, String neqColor, String[] neqColors){
		int tImgW = targetImg.getWidth();
		int tImgH = targetImg.getHeight();
		
		int patternARGB = str2ARGB(color);
		
		//获取目标图左上角像素值
		int tBaseARGB = getARGB(targetImg, 0 , 0);
		if( tBaseARGB != 0 && tBaseARGB != 16777215 )//非透明
			tBaseARGB = patternARGB;
		
		List<int[]> pos4Shaps = null;
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x + w) - tImgW  ; sx++ ){
			b:for( int sy = y ; sy <= (y + h) - tImgH; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
					for( int tx = 0 ; tx < tImgW; tx++ ){
						for( int ty = 0 ; ty < tImgH; ty++){
							sARGB = getARGB(srcImg, sx + tx, sy + ty);
							int tARGB = getARGB(targetImg, tx, ty);
							if( tARGB == 0 || tARGB == 16777215 ){
								if( !Validator.isNull(neqColor) ){
									int neqARGB = str2ARGB(neqColor);
									if( neqARGB == sARGB )
										continue b;//不是当前子图,对下一个子图进行基点对比
								}else if( !Validator.isEmpty(neqColors) ){
									for( String neqColorTmp : neqColors ){
										int neqARGB = str2ARGB(neqColorTmp);
										if( neqARGB == sARGB )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}
								}
							}
							if( tARGB != 0 && tARGB != 16777215 && sARGB != patternARGB ){
								continue b;//寻找下一基点
							}
						}
					}
					if( pos4Shaps == null )
						pos4Shaps = new ArrayList<int[]>();
					//找到子图
					pos4Shaps.add( new int[]{sx, sy} );
				}
			}
		}
		return pos4Shaps;
	}
	
	
	/**
	 * 解析图片上的文本
	 * @param srcImg 源图
	 * @param x 搜索起始x坐标
	 * @param y 搜索起始y坐标
	 * @param w 搜索宽度
	 * @param h 搜索高度
	 * @param wordLib 当前使用的字库
	 * @param color 字体颜色
	 * @param neqColor 源图对应子图的透明位置上的像素值不能为neqColor颜色(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 源图对应子图的透明位置上的像素值不能为neqColors颜色数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 解析后的文本字符串
	 */
	public static String parseText( BufferedImage srcImg, int x, int y, int w, int h, Map<String, BufferedImage> wordLib, String color, String neqColor, String[] neqColors ){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		String text = "";
		
		//在源图中寻找比对基点
		w:for( int sx = x ; sx <= (x+w) - 1; sx++ ){
			for( int sy = y ; sy <= (y+h) - 1; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( Map.Entry<String, BufferedImage> word2Img : wordLib.entrySet() ){
					BufferedImage tImg = word2Img.getValue();
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取匹配颜色值
					int patternARGB = str2ARGB(color);
					//获取当前子图的基点像素颜色值
					int tBaseARGB = getARGB(tImg, 0, 0);
					if( tBaseARGB != 0 && tBaseARGB != 16777215 )//非透明
						tBaseARGB = patternARGB;
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
						for( int tx = 0 ; tx < tImgW; tx++ ){
							for( int ty = 0 ; ty < tImgH; ty++){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int neqARGB = str2ARGB(neqColor);
										if( neqARGB == sARGB_tmp )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}else if( !Validator.isEmpty(neqColors) ){
										for( String neqColorTmp : neqColors ){
											int neqARGB = str2ARGB(neqColorTmp);
											if( neqARGB == sARGB_tmp )
												continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
								if( tARGB != 0 && tARGB != 16777215 && sARGB_tmp != patternARGB )
									continue b;//不是当前子图,对下一个子图进行基点对比
							}
						}
						//找到子图
						text += word2Img.getKey();
						sx = sx + tImg.getWidth() - 1;//因为接下来要执行循环增量,因此这里减1保证正确
						continue w;//搜寻下一个字
					}
				}
			}
		}
		if( text == null || "".equals(text) )
			return null;
		return text;
	}
	
	
	/**
	 * 解析图片上的文本
	 * @param srcImg 源图
	 * @param x 搜索起始x坐标
	 * @param y 搜索起始y坐标
	 * @param w 搜索宽度
	 * @param h 搜索高度
	 * @param wordLib 当前使用的字库
	 * @param color 字体颜色
	 * @param neqColor 源图对应子图的透明位置上的像素值不能为neqColor颜色(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 源图对应子图的透明位置上的像素值不能为neqColors颜色数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @return 解析后的文本字符串
	 */
	public static String parseText( BufferedImage srcImg, int x, int y, int w, int h, Map<String, BufferedImage> wordLib, String color, String neqColor, String[] neqColors,double degree ){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		if( degree < 0 )
			degree = 0;
		if( degree > 1 )
			degree = 1;
		
		int dValue = (int)(255 * degree);//颜色差值
		
		String text = "";
		
		//在源图中寻找比对基点
		w:for( int sx = x ; sx <= (x+w) - 1; sx++ ){
			for( int sy = y ; sy <= (y+h) - 1; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( Map.Entry<String, BufferedImage> word2Img : wordLib.entrySet() ){
					BufferedImage tImg = word2Img.getValue();
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取匹配颜色值
					int patternARGB = str2ARGB(color);
					//获取当前子图的基点像素颜色值
					int tBaseARGB = getARGB(tImg, 0, 0);
					if( tBaseARGB != 0 && tBaseARGB != 16777215 )//非透明
						tBaseARGB = patternARGB;
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB ){//找到一个基点,开始比对
						for( int tx = 0 ; tx < tImgW; tx++ ){
							for( int ty = 0 ; ty < tImgH; ty++){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int neqARGB = str2ARGB(neqColor);
										if( neqARGB == sARGB_tmp )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}else if( !Validator.isEmpty(neqColors) ){
										for( String neqColorTmp : neqColors ){
											int neqARGB = str2ARGB(neqColorTmp);
											if( neqARGB == sARGB_tmp )
												continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
								if( tARGB != 0 && tARGB != 16777215 && sARGB_tmp != patternARGB )
									continue b;//不是当前子图,对下一个子图进行基点对比
							}
						}
						//找到子图
						text += word2Img.getKey();
						sx = sx + tImg.getWidth() - 1;//因为接下来要执行循环增量,因此这里减1保证正确
						continue w;//搜寻下一个字
					}
				}
			}
		}
		if( text == null || "".equals(text) )
			return null;
		return text;
	}
	
	/**
	 * 解析图片上的文本
	 * @param srcImg 源图
	 * @param x 搜索起始x坐标
	 * @param y 搜索起始y坐标
	 * @param w 搜索宽度
	 * @param h 搜索高度
	 * @param wordLib 当前使用的字库
	 * @param color 字体颜色
	 * @param neqColor 源图对应子图的透明位置上的像素值不能为neqColor颜色(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @return 解析后的文本字符串
	 */
	public static String parseVagueText( BufferedImage srcImg, int x, int y, int w, int h, Map<String, BufferedImage> wordLib, String color, String neqColor, double degree ){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		if( degree < 0 )
			degree = 0;
		if( degree > 1 )
			degree = 1;
		
		int dValue = (int)(255 * degree);//颜色差值
		
		//不等色的R,G,B值
		int neqR = 0, neqG = 0, neqB = 0;
		if( !Validator.isNull(neqColor) ){
			int neqARGB = str2ARGB(neqColor);
			neqR = getRed(neqARGB);
			neqG = getGreen(neqARGB);
			neqB = getBlue(neqARGB);
		}
		
		String text = "";
		
		//在源图中寻找比对基点
		w:for( int sx = x ; sx <= (x+w) - 1; sx++ ){
			for( int sy = y ; sy <= (y+h) - 1; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( Map.Entry<String, BufferedImage> word2Img : wordLib.entrySet() ){
					boolean isGoOn = false;//是否满足差值范围, 默认不满足
					
					BufferedImage tImg = word2Img.getValue();
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取匹配颜色值
					int patternARGB = str2ARGB(color);
					//获取当前子图的基点像素颜色值
					int tBaseARGB = getARGB(tImg, 0, 0);
					if( tBaseARGB != 0 && tBaseARGB != 16777215 )//非透明
						tBaseARGB = patternARGB;
					//基点颜色不等时,是否在差值范围内
					if( tBaseARGB != 0 && tBaseARGB != 16777215 && tBaseARGB != sARGB ){
						int sRed = getRed(sARGB);
						int tRed = getRed(tBaseARGB);
						if( Math.abs(sRed - tRed) > dValue )
							continue b;//寻找下一基点
						
						int sGreen = getGreen(sARGB);
						int tGreen = getGreen(tBaseARGB);
						if( Math.abs(sGreen - tGreen) > dValue )
							continue b;//寻找下一基点
						
						int sBlue = getBlue(sARGB);
						int tBlue = getBlue(tBaseARGB);
						if( Math.abs(sBlue - tBlue) > dValue )
							continue b;//寻找下一基点
						
						isGoOn = true;
					}
					
					//子图基点色为透明或与原图当前点的颜色相同或在颜色差值范围内
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB || isGoOn ){//找到一个基点,开始比对
						for( int tx = 0 ; tx < tImgW; tx++ ){
							for( int ty = 0 ; ty < tImgH; ty++){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int sRed = getRed(sARGB_tmp);
										int sGreen = getGreen(sARGB_tmp);
										int sBlue = getBlue(sARGB_tmp);
										if( Math.abs(sRed - neqR) <= dValue && Math.abs(sBlue - neqB) <= dValue && Math.abs(sGreen - neqG) <= dValue )
											continue b;//寻找下一基点
									}
								}
								//子图当前点颜色不透明且不是匹配色时, 判断是否在色差值范围内
								if( tARGB != 0 && tARGB != 16777215 && sARGB_tmp != patternARGB ){
									int sRed = getRed(sARGB_tmp);
									int tRed = getRed(patternARGB);
									if( Math.abs(sRed - tRed) > dValue )
										continue b;//寻找下一基点
									
									int sGreen = getGreen(sARGB_tmp);
									int tGreen = getGreen(patternARGB);
									if( Math.abs(sGreen - tGreen) > dValue )
										continue b;//寻找下一基点
									
									int sBlue = getBlue(sARGB_tmp);
									int tBlue = getBlue(patternARGB);
									if( Math.abs(sBlue - tBlue) > dValue )
										continue b;//寻找下一基点
								}
							}
						}
						//找到子图
						text += word2Img.getKey();
						sx = sx + tImg.getWidth() - 1;//因为接下来要执行循环增量,因此这里减1保证正确
						continue w;//搜寻下一个字
					}
				}
			}
		}
		if( text == null || "".equals(text) )
			return null;
		return text;
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findVagueImg(BufferedImage srcImg, BufferedImage targetImg, double degree){
		return findVagueImg(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, degree, null, null);
	}
	
	/**
	 * 在源图中查找目标图且与子图透明像素位置对于的源图位置上的像素不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findVagueImg(BufferedImage srcImg, BufferedImage targetImg, double degree, String neqColor){
		return findVagueImg(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, degree, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标图且与子图透明像素位置对于的源图位置上的像素不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findVagueImg(BufferedImage srcImg, BufferedImage targetImg, double degree, String[] neqColors){
		return findVagueImg(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), targetImg, degree, null, neqColors);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, double degree ){
		return findVagueImg(srcImg, x, y, w, h, targetImg, degree, null, null);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, double degree, String neqColor ){
		return findVagueImg(srcImg, x, y, w, h, targetImg, degree, neqColor, null);
	}
	
	/**
	 * 在源图中查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	public static int[] findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, double degree, String[] neqColors ){
		return findVagueImg(srcImg, x, y, w, h, targetImg, degree, null, neqColors);
	}
	
	/**
	 * 在源图中模糊查找目标图
	 * @param srcImg 源图
	 * @param x 搜寻范围起始点的x坐标
	 * @param y 搜寻范围起始点的y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param targetImg 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 目标图的第一个像素的坐标, 没找到则返回null
	 */
	private static int[] findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage targetImg, double degree, String neqColor, String[] neqColors){
		int tImgW = targetImg.getWidth();
		int tImgH = targetImg.getHeight();
		
		if( degree < 0 )
			degree = 0;
		if( degree > 1 )
			degree = 1;
		
		int dValue = (int)(255 * degree);//颜色差值
		
		//获取目标图左上角像素值
		int tBaseARGB = getARGB(targetImg, 0 , 0);
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x + w) - tImgW  ; sx++ ){
			b:for( int sy = y ; sy <= (y + h) - tImgH; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				boolean isGoOn = false;//是否满足差值范围, 默认不满足
				if( tBaseARGB != 0 && tBaseARGB != 16777215 && tBaseARGB != sARGB ){
					int sRed = getRed(sARGB);
					int tRed = getRed(tBaseARGB);
					if( Math.abs(sRed - tRed) > dValue )
						continue b;//寻找下一基点
					
					int sGreen = getGreen(sARGB);
					int tGreen = getGreen(tBaseARGB);
					if( Math.abs(sGreen - tGreen) > dValue )
						continue b;//寻找下一基点
					
					int sBlue = getBlue(sARGB);
					int tBlue = getBlue(tBaseARGB);
					if( Math.abs(sBlue - tBlue) > dValue )
						continue b;//寻找下一基点
					
					isGoOn = true;
				}
				if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB || isGoOn ){//找到一个基点,开始比对
					for( int tx = 0 ; tx < tImgW; tx++ ){
						for( int ty = 0 ; ty < tImgH; ty++){
							sARGB = getARGB(srcImg, sx + tx, sy + ty);
							int tARGB = getARGB(targetImg, tx, ty);
							if( tARGB == 0 || tARGB == 16777215 ){
								if( !Validator.isNull(neqColor) ){
									int neqARGB = str2ARGB(neqColor);
									if( neqARGB == sARGB ){
										continue b;//不是当前子图,对下一个子图进行基点对比
									}
								}else if( !Validator.isEmpty(neqColors) ){
									for( String neqColorTmp : neqColors ){
										int neqARGB = str2ARGB(neqColorTmp);
										if( neqARGB == sARGB ){
											continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
							}
							if( tARGB != 0 && tARGB != 16777215 && sARGB != tARGB ){
								int sRed = getRed(sARGB);
								int tRed = getRed(tARGB);
								if( Math.abs(sRed - tRed) > dValue )
									continue b;//寻找下一基点
								
								int sGreen = getGreen(sARGB);
								int tGreen = getGreen(tARGB);
								if( Math.abs(sGreen - tGreen) > dValue )
									continue b;//寻找下一基点
								
								int sBlue = getBlue(sARGB);
								int tBlue = getBlue(tARGB);
								if( Math.abs(sBlue - tBlue) > dValue )
									continue b;//寻找下一基点
							}
						}
					}
					//找到子图,返回子图所在源图的坐标
					return new int[]{sx, sy};
				}
			}
		}
		return null;
	}
	
	//////////////////////////////////////////////////////
	/////////////////////////////////////////
	//////////////////////////////
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, double degree){
		return findVagueImg(srcImg, x, y, w, h, tImgs, degree, null, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 *  @param neqColor 字符串颜色值(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, double degree, String neqColor){
		return findVagueImg(srcImg, x, y, w, h, tImgs, degree, neqColor, null);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColors 字符串颜色值数组(#ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	public static Map<String, Object> findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, double degree, String[] neqColors){
		return findVagueImg(srcImg, x, y, w, h, tImgs, degree, null, neqColors);
	}
	
	/**
	 * 在源图上指定的范围中查找给点目标图数组中最先出现的子图且目标图上的透明位置不能为指定的RGB颜色
	 * @param srcImg 源图
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 搜寻宽度
	 * @param h 搜寻高度
	 * @param tImgs 目标图
	 * @param degree 模糊度(0:无模糊度,1:最大模糊度)
	 * @param neqColor 字符串颜色值(优先级1: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @param neqColors 字符串颜色值数组(优先级2: #ffffff, 0xFFFFFF, 0Xffffff, FFFFFF, #FF000000, 0xff000000, 0X11000000)
	 * @return 返回最新匹配到的子图索引和坐标("idx":索引, "x":x坐标, "y":y坐标), 没找到则返回null
	 */
	private static Map<String, Object> findVagueImg(BufferedImage srcImg, int x, int y, int w, int h, BufferedImage[] tImgs, double degree, String neqColor, String[] neqColors){
		if( x < 0 || y < 0 )
			return null;
		//获取源图宽高
		int sImgW = srcImg.getWidth();
		int sImgH = srcImg.getHeight();
		if( w > sImgW || h > sImgH )
			return null;
		
		if( degree < 0 )
			degree = 0;
		if( degree > 1 )
			degree = 1;
		
		int dValue = (int)(255 * degree);//颜色差值
		
		//在源图中寻找比对基点
		for( int sx = x ; sx <= (x+w) - 1; sx++ ){
			for( int sy = y ; sy <= (y+h) - 1; sy++){
				int sARGB = getARGB(srcImg, sx, sy);
				b:for( int idx = 0; idx < tImgs.length; idx++ ){
					BufferedImage tImg = tImgs[idx];//当前子图
					int tImgW = tImg.getWidth();
					int tImgH = tImg.getHeight();
					if( sx > sImgW - tImgW || sy > sImgH - tImgH )//当前子图没有被找到
						continue;
					//获取当前子图的基点像素
					int tBaseARGB = getARGB(tImg, 0, 0);
					boolean isGoOn = false;//是否满足差值范围, 默认不满足
					if( tBaseARGB != 0 && tBaseARGB != 16777215 && tBaseARGB != sARGB ){
						int sRed = getRed(sARGB);
						int tRed = getRed(tBaseARGB);
						if( Math.abs(sRed - tRed) > dValue )
							continue b;//寻找下一基点
						
						int sGreen = getGreen(sARGB);
						int tGreen = getGreen(tBaseARGB);
						if( Math.abs(sGreen - tGreen) > dValue )
							continue b;//寻找下一基点
						
						int sBlue = getBlue(sARGB);
						int tBlue = getBlue(tBaseARGB);
						if( Math.abs(sBlue - tBlue) > dValue )
							continue b;//寻找下一基点
						
						isGoOn = true;
					}
					
					if( tBaseARGB == 0 || tBaseARGB == 16777215 || tBaseARGB == sARGB || isGoOn ){//找到一个基点,开始比对
						for( int tx = 0 ; tx < tImgW; tx++ ){
							for( int ty = 0 ; ty < tImgH; ty++){
								int sARGB_tmp = getARGB(srcImg, sx + tx, sy + ty);
								int tARGB = getARGB(tImg, tx, ty);
								if( tARGB == 0 || tARGB == 16777215 ){
									if( !Validator.isNull(neqColor) ){
										int neqARGB = str2ARGB(neqColor);
										if( neqARGB == sARGB_tmp )
											continue b;//不是当前子图,对下一个子图进行基点对比
									}else if( !Validator.isEmpty(neqColors) ){
										for( String neqColorTmp : neqColors ){
											int neqARGB = str2ARGB(neqColorTmp);
											if( neqARGB == sARGB_tmp )
												continue b;//不是当前子图,对下一个子图进行基点对比
										}
									}
								}
								if( tARGB != 0 && tARGB != 16777215 && tARGB != sARGB_tmp ){
									int sRed = getRed(sARGB_tmp);
									int tRed = getRed(tARGB);
									if( Math.abs(sRed - tRed) > dValue )
										continue b;//寻找下一基点
									
									int sGreen = getGreen(sARGB_tmp);
									int tGreen = getGreen(tARGB);
									if( Math.abs(sGreen - tGreen) > dValue )
										continue b;//寻找下一基点
									
									int sBlue = getBlue(sARGB_tmp);
									int tBlue = getBlue(tARGB);
									if( Math.abs(sBlue - tBlue) > dValue )
										continue b;//寻找下一基点
								}
							}
						}
						//找到子图,保存子图所在源图的坐标
						Map<String, Object> rs = new HashMap<String, Object>();
						rs.put("idx", idx);
						rs.put("x", sx);
						rs.put("y", sy);
						return rs;
					}
				}
			}
		}
		return null;
	}
}

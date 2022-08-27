package util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备工具
 * @author waver
 *
 */
public class DeviceUtil{

	private static Robot robot;
	
	/**
	 * 屏幕类
	 */
	public static final class Screen{
		static{
			if( robot == null ){
				try {
					robot = new Robot();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		/**
		 * 截取屏幕(全屏)
		 * @return
		 */
		public static BufferedImage captureScreen(){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			return captureScreen(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		}
		
		/**
		 * 截取屏幕(从0,0点开始截取)
		 * @param w 宽度
		 * @param h 高度
		 * @return
		 */
		public static BufferedImage captureScreen( int w, int h ){
			return captureScreen(0, 0, w, h);
		}
		
		/**
		 * 截取屏幕(从指定点x,y开始截取)
		 * @param x 起始点x坐标
		 * @param y 起始点y坐标
		 * @param w 宽度
		 * @param h 高度
		 * @return
		 */
		public static BufferedImage captureScreen( int x, int y, int w, int h ){
			Rectangle rect = new Rectangle(x, y, w, h);
			return robot.createScreenCapture(rect);
		}
		
		/**
		 * 截取屏幕
		 * @param rect 截屏范围
		 * @return
		 */
		public static BufferedImage captureScreen( Rectangle rect ){
			return robot.createScreenCapture(rect);
		}
	}
	
	
	/**
	 * 鼠标操作类
	 */
	public static class Mouse{
		
		static{
			if( robot == null ){
				try {
					robot = new Robot();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		/** 操作后不移动鼠标位置 */
		public static final int AFTER_NONE = 1;
		/** 操作后移动鼠标位置 */
		public static final int AFTER_MOVE = 2;
		/** 鼠标滚轮向上移动 */
		public static final int WHEEL_UP = -1;
		/** 鼠标滚轮向下移动 */
		public static final int WHEEL_DOWN = 1;
		
		/** 鼠标按键延迟毫秒数 */
		private static final int DELAY = 68;
		/** 鼠标移动延时 */
		private static final int MOVE_DELAY = 68;
		/** 鼠标空闲时摆放的位置坐标 */
		private static final int[] FREE_POS = new int[]{0, 0};
		
		/**
		 * 鼠标移动到指定点
		 * @param x x坐标
		 * @param y y坐标
		 */
		public static void move( int x, int y ){
			robot.mouseMove(x, y);
		}
		
		/**
		 * 按住鼠标左键不放
		 */
		public static void pressLeft(){
			robot.mousePress( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 按住鼠标右键不放
		 */
		public static void pressRight(){
			robot.mousePress( InputEvent.BUTTON3_MASK );
		}
		
		/**
		 * 释放鼠标左键
		 */
		public static void releaseLeft(){
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 释放鼠标右键
		 */
		public static void releaseRight(){
			robot.mouseRelease( InputEvent.BUTTON3_MASK );
		}
		
		/**
		 * 鼠标左键单击当前位置
		 */
		public static void click(){
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 鼠标左键单击当前位置
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void click( int afterType ){
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//需要移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标右键单击当前位置
		 */
		public static void rightClick(){
			robot.mousePress( InputEvent.BUTTON3_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON3_MASK );
		}
		
		/**
		 * 鼠标左键单击当前位置
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void rightClick( int afterType ){
			robot.mousePress( InputEvent.BUTTON3_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON3_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//需要移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标左键单击指定点
		 * @param x x坐标
		 * @param y y坐标
		 */
		public static void click( int x, int y ){
			robot.mouseMove(x, y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 鼠标左键单击指定点
		 * @param Spot 指定点
		 */
		public static void click( Point point ){
			robot.mouseMove(point.x, point.y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
//		/**
//		 * 鼠标左键单击指定区域内的随机一个点
//		 * @param x1 范围的左上角点x坐标
//		 * @param y1 范围的左上角点y坐标
//		 * @param x2 范围的右下角点x坐标
//		 * @param y2 范围的右下角点y坐标
//		 */
//		public static void click( int x1, int y1, int x2, int y2 ){
//			int x = RandomUtil.randomInt(x1, x2);
//			int y = RandomUtil.randomInt(y1, y2);
//			click(x, y);
//		}
		
		/**
		 * 鼠标左键单击指定区域内的随机一个点
		 * @param leftTopPoint 范围的左上角点
		 * @param rightBottomPoint 范围的右下角点
		 */
//		public static void click( Point leftTopPoint, Point rightBottomPoint ){
//			click(leftTopPoint.x, leftTopPoint.y, rightBottomPoint.x, rightBottomPoint.y);
//		}
		
		/**
		 * 鼠标左键单击指定区域内的随机一个点
		 * @param rect 区域范围
		 */
//		public static void click( Rectangle rect ){
//			int x1 = rect.x;
//			int y1 = rect.y;
//			int x2 = rect.x + rect.width;
//			int y2 = rect.y + rect.height;
//			click( x1, y1, x2, y2 );
//		}
		
		/**
		 * 鼠标左键单击指定点
		 * @param x x坐标
		 * @param y y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void click( int x, int y, int afterType ){
			robot.mouseMove(x, y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标左键单击指定点
		 * @param Spot 指定点
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void click( Point point, int afterType ){
			robot.mouseMove(point.x, point.y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标左键单击指定区域内的随机一个点
		 * @param x1 范围的左上角点x坐标
		 * @param y1 范围的左上角点y坐标
		 * @param x2 范围的右下角点x坐标
		 * @param y2 范围的右下角点y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void click( int x1, int y1, int x2, int y2, int afterType ){
//			int x = RandomUtil.randomInt(x1, x2);
//			int y = RandomUtil.randomInt(y1, y2);
//			click(x, y, afterType);
//		}
		
		/**
		 * 鼠标左键单击指定区域内的随机一个点
		 * @param leftTopPoint 范围的左上角点
		 * @param rightBottomPoint 范围的右下角点
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void click( Point leftTopPoint, Point rightBottomPoint, int afterType ){
//			click(leftTopPoint.x, leftTopPoint.y, rightBottomPoint.x, rightBottomPoint.y, afterType);
//		}
		
		/**
		 * 鼠标左键单击指定区域内的随机一个点
		 * @param rect 区域范围
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void click( Rectangle rect, int afterType ){
//			int x1 = rect.x;
//			int y1 = rect.y;
//			int x2 = rect.x + rect.width;
//			int y2 = rect.y + rect.height;
//			click( x1, y1, x2, y2, afterType );
//		}
		
		/**
		 * 鼠标右键单击指定点
		 * @param x x坐标
		 * @param y y坐标
		 */
		public static void rightClick( int x, int y ){
			robot.mouseMove(x, y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON3_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON3_MASK );
		}
		
		/**
		 * 鼠标右键单击指定点
		 * @param x x坐标
		 * @param y y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void rightClick( int x, int y, int afterType ){
			robot.mouseMove(x, y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON3_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON3_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标右键单击指定区域内的随机一个点
		 * @param x1 范围的左上角点x坐标
		 * @param y1 范围的左上角点y坐标
		 * @param x2 范围的右下角点x坐标
		 * @param y2 范围的右下角点y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void rightClick( int x1, int y1, int x2, int y2, int afterType ){
//			int x = RandomUtil.randomInt(x1, x2);
//			int y = RandomUtil.randomInt(y1, y2);
//			rightClick(x, y, afterType);
//		}
		
		/**
		 * 鼠标右键单击指定区域内的随机一个点
		 * @param leftTopPoint 范围的左上角点
		 * @param rightBottomPoint 范围的右下角点
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void rightClick( Point leftTopPoint, Point rightBottomPoint, int afterType ){
//			rightClick(leftTopPoint.x, leftTopPoint.y, rightBottomPoint.x, rightBottomPoint.y, afterType);
//		}
		
		/**
		 * 鼠标右键单击指定区域内的随机一个点
		 * @param rect 区域范围
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void rightClick( Rectangle rect, int afterType ){
//			int x1 = rect.x;
//			int y1 = rect.y;
//			int x2 = rect.x + rect.width;
//			int y2 = rect.y + rect.height;
//			rightClick( x1, y1, x2, y2, afterType );
//		}
		
		/**
		 * 鼠标左键双击当前位置
		 * @param x x坐标
		 * @param y y坐标
		 */
		public static void dbClick(){
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 鼠标左键双击指定点
		 * @param x x坐标
		 * @param y y坐标
		 */
		public static void dbClick( int x, int y ){
			robot.mouseMove(x, y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 鼠标左键双击指定区域内的随机一个点
		 * @param x1 范围的左上角点x坐标
		 * @param y1 范围的左上角点y坐标
		 * @param x2 范围的右下角点x坐标
		 * @param y2 范围的右下角点y坐标
		 */
//		public static void dbClick( int x1, int y1, int x2, int y2 ){
//			int x = RandomUtil.randomInt(x1, x2);
//			int y = RandomUtil.randomInt(y1, y2);
//			dbClick(x, y);
//		}
		
		/**
		 * 鼠标左键双击指定区域内的随机一个点
		 * @param leftTopPoint 范围的左上角点
		 * @param rightBottomPoint 范围的右下角点
		 */
//		public static void dbClick( Point leftTopPoint, Point rightBottomPoint ){
//			dbClick(leftTopPoint.x, leftTopPoint.y, rightBottomPoint.x, rightBottomPoint.y);
//		}
		
		/**
		 * 鼠标左键双击指定区域内的随机一个点
		 * @param rect 区域范围
		 */
//		public static void dbClick( Rectangle rect ){
//			int x1 = rect.x;
//			int y1 = rect.y;
//			int x2 = rect.x + rect.width;
//			int y2 = rect.y + rect.height;
//			dbClick( x1, y1, x2, y2 );
//		}
		
		
		/**
		 * 鼠标左键双击指定点
		 * @param x x坐标
		 * @param y y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void dbClick( int x, int y, int afterType ){
			robot.mouseMove(x, y);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标左键双击指定区域内的随机一个点
		 * @param x1 范围的左上角点x坐标
		 * @param y1 范围的左上角点y坐标
		 * @param x2 范围的右下角点x坐标
		 * @param y2 范围的右下角点y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void dbClick( int x1, int y1, int x2, int y2, int afterType ){
//			int x = RandomUtil.randomInt(x1, x2);
//			int y = RandomUtil.randomInt(y1, y2);
//			dbClick(x, y, afterType);
//		}
		
		/**
		 * 鼠标左键双击指定区域内的随机一个点
		 * @param leftTopPoint 范围的左上角点
		 * @param rightBottomPoint 范围的右下角点
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void dbClick( Point leftTopPoint, Point rightBottomPoint, int afterType ){
//			dbClick(leftTopPoint.x, leftTopPoint.y, rightBottomPoint.x, rightBottomPoint.y, afterType);
//		}
		
		/**
		 * 鼠标左键双击指定区域内的随机一个点
		 * @param rect 区域范围
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
//		public static void dbClick( Rectangle rect, int afterType ){
//			int x1 = rect.x;
//			int y1 = rect.y;
//			int x2 = rect.x + rect.width;
//			int y2 = rect.y + rect.height;
//			dbClick( x1, y1, x2, y2, afterType );
//		}
		
		/**
		 * 鼠标从当前点拖动到指定to
		 * @param toX 结束点的x坐标
		 * @param toY 结束点的y坐标
		 */
		public static void dragTo( int toX, int toY ){
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseMove(toX, toY);
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 鼠标从指定点from拖动到指定to
		 * @param fromX 起始点的x坐标
		 * @param fromY 起始点的y坐标
		 * @param toX 结束点的x坐标
		 * @param toY 结束点的y坐标
		 */
		public static void dragTo( int fromX, int fromY, int toX, int toY ){
			robot.mouseMove(fromX, fromY);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseMove(toX, toY);
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
		}
		
		/**
		 * 鼠标从指定点from拖动到指定to
		 * @param fromX 起始点的x坐标
		 * @param fromY 起始点的y坐标
		 * @param toX 结束点的x坐标
		 * @param toY 结束点的y坐标
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void dragTo( int fromX, int fromY, int toX, int toY, int afterType ){
			robot.mouseMove(fromX, fromY);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(DELAY);
			robot.mouseMove(toX, toY);
			robot.delay(DELAY);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 鼠标从指定点from拖动到指定to
		 * @param fromX 起始点的x坐标
		 * @param fromY 起始点的y坐标
		 * @param toX 结束点的x坐标
		 * @param toY 结束点的y坐标
		 * @param dragDelay 延迟多少秒后再向目标位置进行拖动
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void dragTo( int fromX, int fromY, int toX, int toY, int dragDelay, int afterType ){
			robot.mouseMove(fromX, fromY);
			robot.delay(DELAY);
			robot.mousePress( InputEvent.BUTTON1_MASK );
			robot.delay(dragDelay);
			robot.mouseMove(toX, toY);
			robot.delay(300);
			robot.mouseRelease( InputEvent.BUTTON1_MASK );
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
		
		/**
		 * 滚动鼠标滚轮
		 * @param direction 滚动方向(-1:向上, 1:向下)
		 * @param wheelAmt 滚动滚轮的刻度
		 */
		public static void wheel(int direction, int wheelAmt){
			int step = direction * 1;
			for( int i = 0 ; i < wheelAmt ; i++ ){
				robot.mouseWheel(step);
				if(i != wheelAmt - 1)
					robot.delay(DELAY);
			}
		}
		
		/**
		 * 滚动鼠标滚轮
		 * @param direction 滚动方向(-1:向上, 1:向下)
		 * @param wheelAmt 滚动滚轮的刻度
		 * @param afterType 1:点击完后不移动鼠标, 2:点击完后移动鼠标到默认点
		 */
		public static void wheel(int direction, int wheelAmt, int afterType ){
			int step = direction * 1;
			for( int i = 0 ; i < wheelAmt ; i++ ){
				robot.mouseWheel(step);
				if(i != wheelAmt - 1)
					robot.delay(DELAY);
			}
			if( afterType == Mouse.AFTER_MOVE ){//操作后移动鼠标
				robot.delay(MOVE_DELAY);
				robot.mouseMove(FREE_POS[0], FREE_POS[1]);
			}
		}
	}
	
	
	
	/**
	 * 键盘类
	 */
	public static class Keyboard{
		
		/** 按键延迟毫秒数 */
		private static final int DELAY = 68;
		/** 输入字符命令映射表 */
		private static Map<String, String> inputCommands = new HashMap<String, String>();
		static{
			try {
				//0~9
				inputCommands.put("0", KeyEvent.VK_0 + "");
				inputCommands.put("1", KeyEvent.VK_1 + "");
				inputCommands.put("2", KeyEvent.VK_2 + "");
				inputCommands.put("3", KeyEvent.VK_3 + "");
				inputCommands.put("4", KeyEvent.VK_4 + "");
				inputCommands.put("5", KeyEvent.VK_5 + "");
				inputCommands.put("6", KeyEvent.VK_6 + "");
				inputCommands.put("7", KeyEvent.VK_7 + "");
				inputCommands.put("8", KeyEvent.VK_8 + "");
				inputCommands.put("9", KeyEvent.VK_9 + "");
				//a ~ z
				inputCommands.put("a", KeyEvent.VK_A + "");
				inputCommands.put("b", KeyEvent.VK_B + "");
				inputCommands.put("c", KeyEvent.VK_C + "");
				inputCommands.put("d", KeyEvent.VK_D + "");
				inputCommands.put("e", KeyEvent.VK_E + "");
				inputCommands.put("f", KeyEvent.VK_F + "");
				inputCommands.put("g", KeyEvent.VK_G + "");
				inputCommands.put("h", KeyEvent.VK_H + "");
				inputCommands.put("i", KeyEvent.VK_I + "");
				inputCommands.put("j", KeyEvent.VK_J + "");
				inputCommands.put("k", KeyEvent.VK_K + "");
				inputCommands.put("l", KeyEvent.VK_L + "");
				inputCommands.put("m", KeyEvent.VK_M + "");
				inputCommands.put("n", KeyEvent.VK_N + "");
				inputCommands.put("o", KeyEvent.VK_O + "");
				inputCommands.put("p", KeyEvent.VK_P + "");
				inputCommands.put("q", KeyEvent.VK_Q + "");
				inputCommands.put("r", KeyEvent.VK_R + "");
				inputCommands.put("s", KeyEvent.VK_S + "");
				inputCommands.put("t", KeyEvent.VK_T + "");
				inputCommands.put("u", KeyEvent.VK_U + "");
				inputCommands.put("v", KeyEvent.VK_V + "");
				inputCommands.put("w", KeyEvent.VK_W + "");
				inputCommands.put("x", KeyEvent.VK_X + "");
				inputCommands.put("y", KeyEvent.VK_Y + "");
				inputCommands.put("z", KeyEvent.VK_Z + "");
				//A ~ Z
				inputCommands.put("A", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_A + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("B", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_B + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("C", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_C + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("D", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_D + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("E", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_E + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("F", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_F + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("G", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_G + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("H", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_H + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("I", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_I + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("J", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_J + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("K", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_K + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("L", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_L + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("M", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_M + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("N", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_N + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("O", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_O + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("P", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_P + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("Q", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_Q + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("R", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_R + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("S", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_S + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("T", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_T + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("U", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_U + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("V", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_V + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("W", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_W + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("X", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_X + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("Y", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_Y + " " + KeyEvent.VK_CAPS_LOCK);
				inputCommands.put("Z", KeyEvent.VK_CAPS_LOCK + " " + KeyEvent.VK_Z + " " + KeyEvent.VK_CAPS_LOCK);
				//符号
				inputCommands.put(" ", KeyEvent.VK_SPACE + "");
				inputCommands.put("!", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_1);
				inputCommands.put("@", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_2);
				inputCommands.put("#", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_3);
				inputCommands.put("$", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_4);
				inputCommands.put("%", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_5);
				inputCommands.put("^", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_6);
				inputCommands.put("&", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_7);
				inputCommands.put("*", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_8);
				inputCommands.put("(", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_9);
				inputCommands.put(")", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_0);
				inputCommands.put("[", KeyEvent.VK_OPEN_BRACKET + "");
				inputCommands.put("]", KeyEvent.VK_CLOSE_BRACKET + "");
				inputCommands.put("{", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_OPEN_BRACKET + "");
				inputCommands.put("}", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_CLOSE_BRACKET + "");
				inputCommands.put(";", KeyEvent.VK_SEMICOLON + "");
				inputCommands.put(":", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_SEMICOLON);
				inputCommands.put(",", KeyEvent.VK_COMMA + "");
				inputCommands.put("<", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_COMMA);
				inputCommands.put(".", KeyEvent.VK_PERIOD + "");
				inputCommands.put(">", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_PERIOD);
				inputCommands.put("/", KeyEvent.VK_SLASH + "");
				inputCommands.put("?", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_SLASH);
				inputCommands.put("-", KeyEvent.VK_MINUS + "");
				inputCommands.put("_", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_MINUS);
				inputCommands.put("=", KeyEvent.VK_EQUALS + "");
				inputCommands.put("+", KeyEvent.VK_SHIFT + "+" + KeyEvent.VK_EQUALS);
				
				if( robot == null ){
					robot = new Robot();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 是否是大写状态
		 */
//		public static boolean isCapsLock(){
//			byte[] keyState = new byte[256];
//			User32Ext.INSTANCE.GetKeyboardState( keyState );
//			if( keyState[KeyEvent.VK_CAPS_LOCK] == 0 ){//小写
//				return false;
//			}
//			return true;
//		}
		
		/**
		 * 按下指定键不放
		 * @param keyCode 键值
		 */
		public static void press( int keyCode ){
			robot.keyPress( keyCode );
		}
		
		/**
		 * 按下按键不放,在指定的毫米数后释放,不支持表单输入时的键盘模拟
		 * @param millis 毫米数
		 */
		public static void press( int keyCode, int millis ){
			robot.keyPress( keyCode );
			robot.delay(millis);
			robot.keyRelease( keyCode );
		}
		
		/**
		 * 松开指定键
		 * @param keyCode 键值
		 */
		public static void release( int keyCode ){
			robot.keyRelease( keyCode );
		}
		
		/**
		 * 点击键盘上的指定按键
		 * @param keyCode 按键编码
		 */
		public static void click( int keyCode ){
			robot.keyPress(keyCode);
			robot.delay(DELAY);
			robot.keyRelease(keyCode);
		}
		
		/**
		 * 多次点击键盘上的指定按键,每次间隔指定毫秒数
		 * @param keyCode 按键编码
		 * @param count 点击次数
		 * @param clickDelay 每次点击的间隔毫秒数
		 */
		public static void clicks( int keyCode, int count, int clickDelay ){
			for( int i = 0 ; i < count ; i++ ){
				robot.keyPress(keyCode);
				robot.delay(DELAY);
				robot.keyRelease(keyCode);
				if( i != count - 1 )
					robot.delay(clickDelay);
			}
		}
		
		/**
		 * 多次点击键盘上的指定按键,每次间隔指定毫秒数
		 * @param keyCode 按键编码
		 * @param count 点击次数
		 * @param releaseDelay 每次释放按键的延迟毫秒数
		 * @param clickDelay 每次点击的间隔毫秒数
		 */
		public static void clicks( int keyCode, int count, int releaseDelay, int clickDelay ){
			for( int i = 0 ; i < count ; i++ ){
				robot.keyPress(keyCode);
				robot.delay(releaseDelay);
				robot.keyRelease(keyCode);
				if( i != count - 1 )
					robot.delay(clickDelay);
			}
		}
		
		/**
		 * 在光标处输入一段字符串(仅支持具备ASCII编码的字符)
		 * @param content 输入的内容(仅支持具备ASCII编码的字符)
		 * @param millis 每输入一个字符后的间隔毫米数
		 */
		public static void input( String content, int millis ){
			for( int i = 0 ; i < content.length(); i++ ){
				String chatStr = content.charAt(i) + "";
				String command = inputCommands.get(chatStr);
				if( command == null || "".equals(command.trim()) )
					return;
				if( command.indexOf(" ") != -1 ){//点了再点
					String[] cmds = command.split(" ");
					for( int idx = 0 ; idx < cmds.length ; idx++ ){
						click( Integer.parseInt( cmds[idx] ) );
						robot.delay(millis);
					}
					
				}else if( command.indexOf("+") != -1 ){//组合键
					String[] cmds = command.split("\\+");
					for( int idx = 0 ; idx < cmds.length ; idx++ ){
						press( Integer.parseInt( cmds[idx] ) );
						robot.delay(millis);
					}
					for( int idx = cmds.length - 1 ; idx >= 0 ; idx-- ){
						release( Integer.parseInt( cmds[idx] ) );
						robot.delay(millis);
					}
					
				}else{//单键
					click( Integer.parseInt( command ) );
					robot.delay(millis);
				}
				
				if( i != content.length() - 1 )
					robot.delay(millis);
			}
		}
		
		/**
		 * WinIO的方式实现
		 */
//		public static class WinIo{
//			
//			/**
//			 * winIo.dll映射接口
//			 */
//			private static interface WinIoLib extends Library{
//				
//				WinIoLib INSTANCE = (WinIoLib)Native.loadLibrary("WinIo32", WinIoLib.class, W32APIOptions.DEFAULT_OPTIONS);
//				
//				/**
//				 * 加载WinIO驱动
//				 */
//				Boolean InitializeWinIo();
//				
//				/**
//				 * 卸载WinIO驱动
//				 */
//				Boolean ShutdownWinIo();
//				
//				/**
//				 * 获取端口值
//				 * @param portAddr 端口地址
//				 * @param portVal 端口值(out参数)
//				 * @param size 读入长度
//				 */
//				Boolean GetPortVal(Integer portAddr, IntByReference portVal, Integer size);
//				
//				/**
//				 * 设置端口值
//				 * @param portAddr 端口地址
//				 * @param portVal 端口值
//				 * @param size 写入大小
//				 */
//				Boolean SetPortVal(Integer portAddr, Integer portVal, Integer size);
//			}
//			
//			/** 鼠标左键 */
//			public static final int VK_LButton = 0x1;
//			/** 鼠标右键 */
//			public static final int VK_RButton = 0x2;
//			/** CANCEL键 */
//			public static final int VK_Cancel = 0x3;
//			/** 鼠标中键 */
//			public static final int VK_MButton = 0x4;
//			/** 退格键 */
//			public static final int VK_Back = 0x8;
//			/** TAB键 */
//			public static final int VK_Tab = 0x9;
//			/** CLEAR健 */
//			public static final int VK_Clear = 0xC;
//			/** 回车键 */
//			public static final int VK_ENTER = 0xD;
//			/** SHIFT键 */
//			public static final int VK_Shift = 0x10;
//			/** CTRL键 */
//			public static final int VK_Control = 0x11;
//			/** alt键 */
//			public static final int VK_ALT = 0x12;
//			/** PAUSE键 */
//			public static final int VK_Pause = 0x13;
//			/** CAPS LOCK 键 */
//			public static final int VK_Capital = 0x14;
//			/** ESC键 */
//			public static final int VK_Escape = 0x1B;
//			/** 空格键 */
//			public static final int VK_Space = 0x20;
//			/** Page Up 键 */
//			public static final int VK_PageUp = 0x21;
//			/** Page Down 键 */
//			public static final int VK_PageDown = 0x22;
//			/** END键 */
//			public static final int VK_End = 0x23;
//			/** HOME键 */
//			public static final int VK_Home = 0x24;
//			/** 左键 */
//			public static final int VK_Left = 0x25;
//			/** 上键 */
//			public static final int VK_Up = 0x26;
//			/** 右键 */
//			public static final int VK_Right = 0x27;
//			/** 下键 */
//			public static final int VK_Down = 0x28;
//			/** SELECT键 */
//			public static final int VK_Select = 0x29;
//			/** Print Screen 键 */
//			public static final int VK_Print = 0x2A;
//			/** EXECUTE键 */
//			public static final int VK_Execute = 0x2B;
//			/** SnapShot键 */
//			public static final int VK_Snapshot = 0x2C;
//			/** INSERT键 */
//			public static final int VK_Insert = 0x2D;
//			/** DELETE键 */
//			public static final int VK_Delete = 0x2E;
//			/** HELP键 */
//			public static final int VK_Help = 0x2F;
//			/** NUM LOCK 键 */
//			public static final int VK_Numlock = 0x90;
//			
//			public static final int VK_A = 0x41;//字母A键 
//			public static final int VK_B = 0x42;//字母B键
//			public static final int VK_C = 0x43;//字母C键
//			public static final int VK_D = 0x44;//字母D键
//			public static final int VK_E = 0x45;//字母E键
//			public static final int VK_F = 0x46;//字母F键
//			public static final int VK_G = 0x47;//字母G键
//			public static final int VK_H = 0x48;//字母H键
//			public static final int VK_I = 0x49;//字母I键
//			public static final int VK_J = 0x4A;//字母J键
//			public static final int VK_K = 0x4B;//字母K键
//			public static final int VK_L = 0x4C;//字母L键
//			public static final int VK_M = 0x4D;//字母M键
//			public static final int VK_N = 0x4E;//字母N键
//			public static final int VK_O = 0x4F;//字母O键
//			public static final int VK_P = 0x50;//字母P键
//			public static final int VK_Q = 0x51;//字母Q键
//			public static final int VK_R = 0x52;//字母R键
//			public static final int VK_S = 0x53;//字母S键
//			public static final int VK_T = 0x54;//字母T键
//			public static final int VK_U = 0x55;//字母U键
//			public static final int VK_V = 0x56;//字母V键
//			public static final int VK_W = 0x57;//字母W键
//			public static final int VK_X = 0x58;//字母X键
//			public static final int VK_Y = 0x59;//字母Y键
//			public static final int VK_Z = 0x5A;//字母Z键
//
//			public static final int VK_0 = 0x30;//数字0键
//			public static final int VK_1 = 0x31;//数字1键
//			public static final int VK_2 = 0x32;//数字2键
//			public static final int VK_3 = 0x33;//数字3键
//			public static final int VK_4 = 0x34;//数字4键
//			public static final int VK_5 = 0x35;//数字5键
//			public static final int VK_6 = 0x36;//数字6键
//			public static final int VK_7 = 0x37;//数字7键
//			public static final int VK_8 = 0x38;//数字8键
//			public static final int VK_9 = 0x39;//数字9键
//
//			public static final int VK_F1 = 0x70;//F1功能键
//			public static final int VK_F2 = 0x71;//F2功能键
//			public static final int VK_F3 = 0x72;//F3功能键
//			public static final int VK_F4 = 0x73;//F4功能键
//			public static final int VK_F5 = 0x74;//F5功能键
//			public static final int VK_F6 = 0x75;//F6功能键
//			public static final int VK_F7 = 0x76;//F7功能键
//			public static final int VK_F8 = 0x77;//F8功能键
//			public static final int VK_F9 = 0x78;//F9功能键
//			public static final int VK_F10 = 0x79;//F10功能键
//			public static final int VK_F11 = 0x7A;//F11功能键
//			public static final int VK_F12 = 0x7B;//F12功能键
//			public static final int VK_F13 = 0x7C;//F13功能键
//			public static final int VK_F14 = 0x7D;//F14功能键
//			public static final int VK_F15 = 0x7E;//F15功能键
//			public static final int VK_F16 = 0x7F;//F16功能键
//			
//			public static final int VK_Numpad0 = 0x60;//小键盘0键
//			public static final int VK_Numpad1 = 0x61;//小键盘1键
//			public static final int VK_Numpad2 = 0x62;//小键盘2键
//			public static final int VK_Numpad3 = 0x63;//小键盘3键
//			public static final int VK_Numpad4 = 0x64;//小键盘4键
//			public static final int VK_Numpad5 = 0x65;//小键盘5键
//			public static final int VK_Numpad6 = 0x66;//小键盘6键
//			public static final int VK_Numpad7 = 0x67;//小键盘7键
//			public static final int VK_Numpad8 = 0x68;//小键盘8键
//			public static final int VK_Numpad9 = 0x69;//小键盘9键
//			/** 小键盘*键 */
//			public static final int VK_Multiply = 0x6A;
//			/** 小键盘+键 */
//			public static final int VK_Add = 0x6B;
//			/** 小键盘回车键 */
//			public static final int VK_Separator = 0x6C;
//			/** 小键盘-键 */
//			public static final int VK_Subtract = 0x6D;
//			/** 小键盘.键 */
//			public static final int VK_Decimal = 0x6E;
//			/** 小键盘/键 */
//			public static final int VK_Divide = 0x6F;
//			
//			/** 键盘命令端口号 */
//			private static final int KBC_KEY_CMD = 0x64;
//			/** 键盘数据端口号 */
//			private static final int KBC_KEY_DATA = 0x60;
//			
//			/** 写入命令 */
//			private static final int WRITE_CMD = 0xD2;
//			/** 扩展键标识 */
//			private static final int EXTEND_FLAG = 0xE0;
//			
//			/** 字符-按键码 映射表 */
//			private static Map<String, Integer> charKeyCodes = new HashMap<String, Integer>();
//			
//			static{
//				//0~9
//				charKeyCodes.put("0", KeyEvent.VK_0 );
//				charKeyCodes.put("1", KeyEvent.VK_1 );
//				charKeyCodes.put("2", KeyEvent.VK_2 );
//				charKeyCodes.put("3", KeyEvent.VK_3 );
//				charKeyCodes.put("4", KeyEvent.VK_4 );
//				charKeyCodes.put("5", KeyEvent.VK_5 );
//				charKeyCodes.put("6", KeyEvent.VK_6 );
//				charKeyCodes.put("7", KeyEvent.VK_7 );
//				charKeyCodes.put("8", KeyEvent.VK_8 );
//				charKeyCodes.put("9", KeyEvent.VK_9 );
//				//a ~ z
//				charKeyCodes.put("a", KeyEvent.VK_A );
//				charKeyCodes.put("b", KeyEvent.VK_B );
//				charKeyCodes.put("c", KeyEvent.VK_C );
//				charKeyCodes.put("d", KeyEvent.VK_D );
//				charKeyCodes.put("e", KeyEvent.VK_E );
//				charKeyCodes.put("f", KeyEvent.VK_F );
//				charKeyCodes.put("g", KeyEvent.VK_G );
//				charKeyCodes.put("h", KeyEvent.VK_H );
//				charKeyCodes.put("i", KeyEvent.VK_I );
//				charKeyCodes.put("j", KeyEvent.VK_J );
//				charKeyCodes.put("k", KeyEvent.VK_K );
//				charKeyCodes.put("l", KeyEvent.VK_L );
//				charKeyCodes.put("m", KeyEvent.VK_M );
//				charKeyCodes.put("n", KeyEvent.VK_N );
//				charKeyCodes.put("o", KeyEvent.VK_O );
//				charKeyCodes.put("p", KeyEvent.VK_P );
//				charKeyCodes.put("q", KeyEvent.VK_Q );
//				charKeyCodes.put("r", KeyEvent.VK_R );
//				charKeyCodes.put("s", KeyEvent.VK_S );
//				charKeyCodes.put("t", KeyEvent.VK_T );
//				charKeyCodes.put("u", KeyEvent.VK_U );
//				charKeyCodes.put("v", KeyEvent.VK_V );
//				charKeyCodes.put("w", KeyEvent.VK_W );
//				charKeyCodes.put("x", KeyEvent.VK_X );
//				charKeyCodes.put("y", KeyEvent.VK_Y );
//				charKeyCodes.put("z", KeyEvent.VK_Z );
//				//A ~ Z
//				charKeyCodes.put("A", KeyEvent.VK_A );
//				charKeyCodes.put("B", KeyEvent.VK_B );
//				charKeyCodes.put("C", KeyEvent.VK_C );
//				charKeyCodes.put("D", KeyEvent.VK_D );
//				charKeyCodes.put("E", KeyEvent.VK_E );
//				charKeyCodes.put("F", KeyEvent.VK_F );
//				charKeyCodes.put("G", KeyEvent.VK_G );
//				charKeyCodes.put("H", KeyEvent.VK_H );
//				charKeyCodes.put("I", KeyEvent.VK_I );
//				charKeyCodes.put("J", KeyEvent.VK_J );
//				charKeyCodes.put("K", KeyEvent.VK_K );
//				charKeyCodes.put("L", KeyEvent.VK_L );
//				charKeyCodes.put("M", KeyEvent.VK_M );
//				charKeyCodes.put("N", KeyEvent.VK_N );
//				charKeyCodes.put("O", KeyEvent.VK_O );
//				charKeyCodes.put("P", KeyEvent.VK_P );
//				charKeyCodes.put("Q", KeyEvent.VK_Q );
//				charKeyCodes.put("R", KeyEvent.VK_R );
//				charKeyCodes.put("S", KeyEvent.VK_S );
//				charKeyCodes.put("T", KeyEvent.VK_T );
//				charKeyCodes.put("U", KeyEvent.VK_U );
//				charKeyCodes.put("V", KeyEvent.VK_V );
//				charKeyCodes.put("W", KeyEvent.VK_W );
//				charKeyCodes.put("X", KeyEvent.VK_X );
//				charKeyCodes.put("Y", KeyEvent.VK_Y );
//				charKeyCodes.put("Z", KeyEvent.VK_Z );
//				
//				//卸载驱动
//				WinIoLib.INSTANCE.ShutdownWinIo();
//				//加载驱动
//				WinIoLib.INSTANCE.InitializeWinIo();
//			}
//			
//			/**
//			 * 等待键盘缓冲区为空<br>
//			 * 发送数据前应该先等待键盘缓冲区为空
//			 */
//			private static void KBCWait4IBE(){
//				//int类型的out参数对象
//				IntByReference dwVal = new IntByReference(0);
//				do{
//					WinIoLib.INSTANCE.GetPortVal(KBC_KEY_CMD, dwVal, 1);
//				}while( (dwVal.getValue() & 0x2) != 0  );
//			}
//			
//			/**
//			 * 按下指定按键(非按住不放)
//			 * @param keyCode 按键编号
//			 */
//			public static void press( int keyCode ){
//				//获取指定按键的键盘扫描码
//				Integer btScancode = User32Ext.INSTANCE.MapVirtualKey(keyCode, 0);
//				KBCWait4IBE();//发送数据前应该先等待键盘缓冲区为空
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_CMD, WRITE_CMD, 1);
//				KBCWait4IBE();//发送数据前应该先等待键盘缓冲区为空
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_DATA, btScancode, 1);
//			}
//			
//			/**
//			 * 释放指定按键(不针对按住不放)
//			 * @param keyCode 按键编号
//			 */
//			public static void release( int keyCode ){
//				Integer btScancode = User32Ext.INSTANCE.MapVirtualKey(keyCode, 0);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_CMD, WRITE_CMD, 1);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_DATA, btScancode | 0x80, 1);
//			}
//			
//			/**
//			 * 按下指定的扩展键<br>
//			 * 以下按键将会按住不放:ctrl, alt<br>
//			 * 按住不放的按键必须手动调用releaseEx进行释放
//			 * @param keyCode 扩展键编码
//			 */
//			public static void pressEx( int keyCode ){
//				Integer btScancode = User32Ext.INSTANCE.MapVirtualKey(keyCode, 0);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_CMD, WRITE_CMD, 1);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_DATA, EXTEND_FLAG, 1);
//				
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_CMD, WRITE_CMD, 1);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_DATA, btScancode, 1);
//			}
//			
//			/**
//			 * 释放指定的扩展键<br>
//			 * @param keyCode 扩展键编码
//			 */
//			public static void releaseEx( int keyCode ){
//				Integer btScancode = User32Ext.INSTANCE.MapVirtualKey(keyCode, 0);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_CMD, WRITE_CMD, 1);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_DATA, EXTEND_FLAG, 1);
//				
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_CMD, WRITE_CMD, 1);
//				KBCWait4IBE();
//				WinIoLib.INSTANCE.SetPortVal(KBC_KEY_DATA, btScancode | 0x80, 1);
//				
//			}
//			
//			/**
//			 * 点击指定按键
//			 * @param keyCode 按键编码
//			 */
//			public static void click( int keyCode ){
//				press(keyCode);
//				robot.delay(DELAY);
//				release(keyCode);
//			}
//			
//			/**
//			 * 多次点击键盘上的指定按键,每次间隔指定毫秒数
//			 * @param keyCode 按键编码
//			 * @param count 点击次数
//			 * @param clickDelay 每次点击的间隔毫秒数
//			 */
//			public static void clicks( int keyCode, int count, int clickDelay ){
//				for( int i = 0 ; i < count ; i++ ){
//					click(keyCode);
//					if( i != count - 1 )
//						robot.delay(clickDelay);
//				}
//			}
//			
//			/**
//			 * 在光标处输入一段字符串(仅支持字母和数字)
//			 * @param content 输入的内容(仅支持字母和数字)
//			 * @param millis 每输入一个字符后的间隔毫米数
//			 */
//			public static void input( String content, int millis ){
//				//按键序列
//				List<Integer> keyCodes = new ArrayList<Integer>();
//				//获取当前大小写状态
//				boolean isCapsLock = isCapsLock();
//				boolean initCapsLock = isCapsLock();
//				for( int i = 0 ; i < content.length(); i++ ){
//					char curChar = content.charAt(i);
//					String chatStr = curChar + "";
//					Integer command = charKeyCodes.get(chatStr);
//					if( command == null ){
//						return;
//					}
//					if( Character.isUpperCase(curChar) != isCapsLock ){
//						keyCodes.add( KeyEvent.VK_CAPS_LOCK );
//						isCapsLock = !isCapsLock;
//					}
//					keyCodes.add( command );
//				}
//				if( initCapsLock != isCapsLock ){
//					keyCodes.add( KeyEvent.VK_CAPS_LOCK );
//					isCapsLock = !isCapsLock;
//				}
//				for( int i = 0 ; i < keyCodes.size() ; i++ ){
//					click( keyCodes.get(i) );
//					robot.delay(millis);
//					if( i != keyCodes.size() - 1 )
//						robot.delay(millis);
//				}
//			}
//		}
	}
	
	
	/**
	 * 线程睡眠
	 * @param millis 毫秒
	 * @throws InterruptedException 
	 */
	public static void delay( long millis ){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 设置粘贴板内容
	 * @param str
	 */
	public static void setIntoClipboard(String str) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(str), null);
	}
	
	/**
	 * 计算两张图片同一坐标点色差绝对值
	 * @param img1	图片1
	 * @param img2	图片2
	 * @param x		
	 * @param y
	 * @return	色差绝对值
	 */
	public static int calc2ImgABS(BufferedImage img1, BufferedImage img2, int x, int y){
		int color1 = Math.abs(img1.getRGB(x, y));
		int color2 = Math.abs(img2.getRGB(x, y));
		return Math.abs(color2 - color1);
	}
	
	
	
}

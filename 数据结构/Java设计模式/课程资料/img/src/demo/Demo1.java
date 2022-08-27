package demo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Demo1 {
	public static void main(String[] args) throws AWTException, IOException {
		//Java机器人对象
		Robot robot = new Robot();
		//Java公用工具包  设备工具  对设备操作提供支持
		Toolkit tk = Toolkit.getDefaultToolkit();
		//屏幕信息  Dimension屏幕信息对象
		Dimension di = tk.getScreenSize();
		Rectangle rec = new Rectangle(9, 67, 200, 100);
		//使用robot创建屏幕截图
		BufferedImage screen = robot.createScreenCapture(rec);
		
//		ImageIO.write(screen, "png", new File("E:\\b.png"));
		//获取RGB颜色值,十进制的反值
		//"背景色" -》 c000ff
		int rgb = 0;
		
		StringBuilder strB = new StringBuilder();
		for (int y = 35; y <= 35 + 36; y++) {
			for (int x = 22; x <= 22 + 36; x++) {
				rgb = screen.getRGB(x, y);
				String hexColor = Integer.toHexString(16777215 + 1 + rgb);
				if( "c000ff".equals(hexColor) ){//当前像素和背景色相同
					strB.append("0");
				}else{
					strB.append("1");
				}
			}
		}
		System.out.println(strB);
		
		
		
		
	}
}

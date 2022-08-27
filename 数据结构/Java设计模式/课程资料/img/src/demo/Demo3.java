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
import java.util.Arrays;

import javax.imageio.ImageIO;

import util.DeviceUtil;
import util.ImageUtil;

public class Demo3 {
	public static void main(String[] args) throws AWTException, IOException {
		BufferedImage mb = ImageIO.read(new File("suo.png"));
		BufferedImage screen = DeviceUtil.Screen.captureScreen();
		int[] findImg = ImageUtil.findImg(screen, mb);
		System.out.println(Arrays.toString(findImg));
		
		//首次截取验证码区域
//		BufferedImage firstImg = DeviceUtil.Screen.captureScreen(findImg[0]-277, findImg[1]-153, 261, 116);
//		ImageIO.write(firstImg, "png", new File("e:\\firstImg.png"));
		
		//鼠标点击滑竿，略。。。
		
		//第二次
		BufferedImage secondImg = DeviceUtil.Screen.captureScreen(findImg[0]-277, findImg[1]-153, 261, 116);
		ImageIO.write(secondImg, "png", new File("e:\\secondImg.png"));
		
		
		
		
		
		
	}
}

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

public class Demo2 {
	public static void main(String[] args) throws AWTException, IOException {
//		BufferedImage captureScreen = DeviceUtil.Screen.captureScreen(200, 200);
//		ImageIO.write(captureScreen, "png", new File("e:\\a.png"));
		
		BufferedImage mb = ImageIO.read(new File("mb.png"));
		BufferedImage screen = DeviceUtil.Screen.captureScreen();
		int[] findImg = ImageUtil.findImg(screen, mb);
		System.out.println(Arrays.toString(findImg));
		
		
		
		
		
	}
}

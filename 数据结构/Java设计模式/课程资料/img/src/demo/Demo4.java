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

public class Demo4 {
	public static void main(String[] args) throws AWTException, IOException {
		BufferedImage firstImg = ImageIO.read(new File("firstImg.png"));
		BufferedImage secondImg = ImageIO.read(new File("secondImg.png"));
		
		System.out.println(firstImg.getRGB(8, 68));
		System.out.println(secondImg.getRGB(8, 68));
		
		
		
	}
}

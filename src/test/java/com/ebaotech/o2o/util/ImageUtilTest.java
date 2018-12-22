package com.ebaotech.o2o.util;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author Administrator
 * 2018年9月10日
 */
public class ImageUtilTest {
	public static void main(String[] args) throws IOException {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(basePath);
		Thumbnails.of("F:\\doc\\a.jpg").size(200, 200).watermark(Positions.BOTTOM_CENTER,ImageIO.read(new File("F:\\doc\\watermark.jpg")), 0.25f).outputQuality(0.8f).toFile("F:\\doc\\newa.jpg");
	}
}

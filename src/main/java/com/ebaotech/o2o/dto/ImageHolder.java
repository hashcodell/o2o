package com.ebaotech.o2o.dto;

import java.io.InputStream;

/**
 * @author Administrator
 * 2018年9月10日
 * 图片的dto类
 */
public class ImageHolder {
	
	private String imageName;
	private InputStream image;

	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}
}

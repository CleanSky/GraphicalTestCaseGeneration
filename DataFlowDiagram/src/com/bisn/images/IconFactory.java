package com.bisn.images;

import java.io.InputStream;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Widget;

/**
 * 图标工厂
 */
public class IconFactory {
	/**
	 * This static function returns an ImageDescriptor for a given file
	 * 为给定的图像文件创建图像描述符
	 */
	public static ImageDescriptor getImageDescriptor(String file_name) {
		return ImageDescriptor.createFromFile(IconFactory.class, file_name);
	}

	/**
	 * This function returns an Image loaded from the given image file
	 * 从给定的图像文件返回图像
	 */
	public static Image getImage(Widget widget, String file_name) {
		InputStream input = IconFactory.class.getResourceAsStream(file_name);
		Image image = null;

		try {
			image= new Image(widget.getDisplay(), input); 
		} catch (org.eclipse.swt.SWTException e) {
			return new Image(widget.getDisplay(), 16, 16);
		}

		return image;
	}
}

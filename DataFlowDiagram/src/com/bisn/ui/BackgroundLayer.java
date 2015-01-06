package com.bisn.ui;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;

/**
 * 编辑器的背景颜色
 */
public class BackgroundLayer extends FreeformLayer {
	public static final String BACKGROUND_LAYER = "Background Layer"; //$NON-NLS-1$

	/**
	 * 是否设置编辑器的背景颜色
	 */
	public BackgroundLayer() {
		setOpaque(false);
	}

	/**
	 * 编辑器背景颜色的设置
	 */
	protected void paintFigure(Graphics graphics) {
		if(isOpaque()) {
			graphics.setForegroundColor(ColorConstants.white);
			graphics.setBackgroundColor(ColorConstants.blue);
			graphics.fillGradient(getBounds(), true);
		}
	}
}

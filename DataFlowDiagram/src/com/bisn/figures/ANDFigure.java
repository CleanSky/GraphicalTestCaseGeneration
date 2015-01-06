package com.bisn.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 构建图形
 * 
 * XORFigure是与节点图形，形状为固定的圆形
 */
public class ANDFigure extends Shape {
	/**
	 * 构造方法
	 */
	public ANDFigure() {
		super();
	}

	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
	}
	
	/**
	 * @see Shape#fillShape(Graphics)
	 * 形状填充方法
	 */
	protected void fillShape(Graphics graphics) {
		graphics.pushState();
		Rectangle bound = this.getBounds().getCopy();

		graphics.setBackgroundColor(ColorConstants.lightGreen);
		graphics.fillOval(bound);

		graphics.popState();
	}

	/**
	 * 形状轮廓方法
	 */
	protected void outlineShape(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		r.x = r.x + lineWidth / 2;
		r.y = r.y + lineWidth / 2;
		r.width = r.width - Math.max(1, lineWidth);
		r.height = r.height - Math.max(1, lineWidth);
		graphics.setBackgroundColor(ColorConstants.blue);
		graphics.fillOval(r);
	}
}

package com.bisn.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 构建图形
 * 
 * DataTargetFigure是数据终点图形，形状为椭圆形
 */
public class DataTargetFigure extends Shape {
	private String name;//数据终点名称
	private Label label;//数据终点名称的标签
	
	/**
	 * 构造方法
	 */
	public DataTargetFigure() {
		this.label = new Label();
		this.add(label);
	}
	
	/******************************************************************************************/
	//Getters和Setters方法
	public String getText() {
		return this.label.getText();
	}
	
	public Rectangle getTextBounds() {
		return this.label.getTextBounds();
	}
	
	public void setName(String name) {
		this.name = name;
		this.label.setText(name);
		this.repaint();
	}

	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		this.label.setBounds(rect);
	}
	/******************************************************************************************/
	
	/**
	 * @see Shape#fillShape(Graphics)
	 * 形状填充方法
	 */
	protected void fillShape(Graphics graphics) {
		graphics.pushState();
		Rectangle bound = this.getBounds().getCopy();

		graphics.setBackgroundColor(ColorConstants.black);
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
		graphics.setBackgroundColor(ColorConstants.lightGreen);
		graphics.fillOval(r);
	}
}

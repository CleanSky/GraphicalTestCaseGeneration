package com.bisn.models;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * 构建模型
 * 
 * AND为“与”节点，继承DfdNode
 */
public class AND extends DfdNode {
	public AND() {
		super();
		this.name = "与";
		this.size = new Dimension(20, 20);
	}
}

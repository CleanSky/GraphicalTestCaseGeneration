package com.bisn.models;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * 构建模型
 * 
 * XOR是“异或”节点，继承DfdNode
 */
public class XOR extends DfdNode{
	public XOR() {
		super();
		this.name = "异或";
		this.size = new Dimension(20, 20);
	}
}

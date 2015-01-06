package com.bisn.models;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * 构建模型
 * 
 * OR是“或”节点，集成DfdNode
 */
public class OR extends DfdNode{
	public OR() {
		super();
		this.name = "或";
		this.size = new Dimension(20, 20);
	}
}

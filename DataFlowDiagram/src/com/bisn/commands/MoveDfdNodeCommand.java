package com.bisn.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * MoveDfdNodeCommand是移动数据流图节点的命令
 */
public class MoveDfdNodeCommand extends Command {
	private DfdNode node;//数据流图的节点
	private Point oldPos;//原来的位置
	private Point newPos;//新的位置
	private Dimension oldSize;//原来的大小
    private Dimension newSize;//新的大小

	//Setters
	public void setNode(DfdNode node) {
		this.node = node;
	}
	
	public void setLocation(Point p) {
		oldPos = this.node.getLocation();
		this.newPos = p;
	}

	public void setSize(Dimension d) {
		oldSize = this.node.getSize();
		this.newSize = d;
	}
	
	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		node.setLocation(newPos);
		node.setSize(newSize);
	}

	public String getLabel() {
		return "Move/Resize DfdNode";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		this.node.setLocation(oldPos);
		this.node.setSize(oldSize);
	}
	
	public boolean canExecute() {
		return true;//任何时候都可以移动节点
	}
}

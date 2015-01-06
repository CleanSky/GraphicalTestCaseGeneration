package com.bisn.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdModel;
import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * CreateDfdNodeCommand是创建数据流图节点的命令
 */
public class CreateDfdNodeCommand extends Command {
	protected DfdModel diagram;//数据流图
	protected DfdNode node;//数据流图的节点
	protected Point location;//节点的位置

	//Setters
	public void setDiagram(DfdModel diagram) {
		this.diagram = diagram;
	}

	public void setNode(DfdNode node) {
		this.node = node;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		if (this.location != null) {
			this.node.setLocation(this.location);
		}
		this.diagram.addDfdNode(this.node);
	}

	public String getLabel() {
		return "Create DfdNode";
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		diagram.deleteDfdNode(node);
	}
	
	public boolean canExecute() {
		return true;//任何时候都可以创建节点
	}
}

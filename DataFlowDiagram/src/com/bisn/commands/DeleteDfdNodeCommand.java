package com.bisn.commands;

import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdModel;
import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * DeleteDfdNodeCommand是删除数据流图节点命令
 */
public class DeleteDfdNodeCommand extends Command {
	private DfdModel diagram;//数据流图
	private DfdNode node;//数据流图的节点

	//Setters
	public void setDiagram(DfdModel diagram) {
		this.diagram = diagram;
	}

	public void setNode(DfdNode node) {
		this.node = node;
	}

	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		diagram.deleteDfdNode(node);
	}

	public String getLabel() {
		return "Delete DfdNode";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		diagram.addDfdNode(node);
	}

	public boolean canExecute() {
		return true;//任何时候都可以删除节点
	}
}

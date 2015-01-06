package com.bisn.commands;

import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * RenameDfdNodeCommand是重命名节点名称的命令
 */
public class RenameDfdNodeCommand extends Command {
	private DfdNode node;//数据流图的节点
	private String newName;//新节点名称
	private String oldName;//旧节点名称

	//Setters
	public void setName(String name) {
		this.newName = name;
	}

	public void setNode(DfdNode node) {
		this.node = node;
	}

	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		oldName = this.node.getName();
		this.node.setName(newName);
	}

	public void redo() {
		node.setName(newName);
	}

	public void undo() {
		node.setName(oldName);
	}

	public String getLabel() {
		return "Rename DfdNode";
	}
	
	public boolean canExecute() {
		return true;//任何时候都可以重命名节点的名称
	}
}

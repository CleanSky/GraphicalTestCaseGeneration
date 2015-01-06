package com.bisn.commands;

import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdConnection;
import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * DeleteDfdConnectionCommand是删除数据流的命令
 */
public class DeleteDfdConnectionCommand extends Command {
	private DfdNode source;//起点
	private DfdNode target;//终点
	private DfdConnection connection;//数据流

	//Setters
	public void setConnection(DfdConnection connection) {
		this.connection = connection;
	}

	public void setSource(DfdNode source) {
		this.source = source;
	}

	public void setTarget(DfdNode target) {
		this.target = target;
	}

	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		source.removeOutput(connection);
		target.removeInput(connection);
		connection.setSource(null);
		connection.setTarget(null);
	}

	public String getLabel() {
		return "Delete DfdConnection";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		connection.setSource(source);
		connection.setTarget(target);
		source.addOutput(connection);
		target.addInput(connection);
	}
	
	public boolean canExecute() {
		return true;//任何时候都可以删除数据流
	}
}

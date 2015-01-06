package com.bisn.commands;

import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdConnection;
import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * ReconnectSourceCommand是更改数据流起点的命令
 */
public class ReconnectDfdConnectionSourceCommand extends Command {
	private DfdConnection connection;//数据流
	private DfdNode newSource;//新的起点
	private DfdNode oldSource;//原来的起点
	private DfdNode target;//终点

	//Setters
	public void setConnection(DfdConnection connection) {
		this.connection = connection;
		this.target=this.connection.getTarget();
		this.oldSource=this.connection.getSource();
	}

	public void setSource(DfdNode source) {
		this.newSource = source;
	}

	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		oldSource.removeOutput(connection);
		newSource.addOutput(connection);
		connection.setSource(newSource);
	}

	public String getLabel() {
		return "Reconnect DfdConnectionSource";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		newSource.removeOutput(connection);
		oldSource.addOutput(connection);
		connection.setSource(oldSource);
	}

	public boolean canExecute() {
		return true;//任何时候都可以更改数据流的起点
	}
}

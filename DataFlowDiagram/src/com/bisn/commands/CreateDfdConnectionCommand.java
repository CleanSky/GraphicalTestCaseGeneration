package com.bisn.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.bisn.models.DfdConnection;
import com.bisn.models.DfdNode;

/**
 * 创建命令
 * 
 * CreateDfdConnectionCommand是创建数据流命令
 */
public class CreateDfdConnectionCommand extends Command {
	protected DfdConnection connection;//数据流
	protected DfdNode source;//数据流的起点
	protected DfdNode target;//数据流的终点

	//Setters
	public void setSource(DfdNode source) {
		this.source = source;
	}

	public void setConnection(DfdConnection connection) {
		this.connection = connection;
	}

	public void setTarget(DfdNode target) {
		this.target = target;
	}

	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		connection = new DfdConnection(source, target);
	}

	public String getLabel() {
		return "Create DfdConnection";
	}

	public void redo() {
		this.source.addOutput(this.connection);
		this.target.addInput(this.connection);
	}

	public void undo() {
		this.source.removeOutput(this.connection);
		this.target.removeInput(this.connection);
	}

	public boolean canExecute() {
		//不允许自己连自己
		if (source.equals(target))
			return false;
		// 检查数据流是否已经存在
		List connections = this.source.getOutgoingConnections();
		for (int i = 0; i < connections.size(); i++) {
			if (((DfdConnection) connections.get(i)).getTarget().equals(target))
				return false;
		}
		return true;
	}
}

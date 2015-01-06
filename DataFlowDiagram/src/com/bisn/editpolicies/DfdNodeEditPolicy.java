package com.bisn.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.bisn.commands.DeleteDfdNodeCommand;
import com.bisn.models.DfdModel;
import com.bisn.models.DfdNode;

/**
 * 构建编辑策略
 * 
 * DfdNodeEditPolicy是数据流图节点的编辑策略
 */
public class DfdNodeEditPolicy extends ComponentEditPolicy{
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteDfdNodeCommand deleteCommand=new DeleteDfdNodeCommand();//删除节点命令
		deleteCommand.setDiagram((DfdModel)getHost().getParent().getModel());
		deleteCommand.setNode((DfdNode)getHost().getModel());
		return deleteCommand;
	}
}

package com.bisn.editpolicies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.bisn.commands.CreateDfdNodeCommand;
import com.bisn.commands.MoveDfdNodeCommand;
import com.bisn.models.DfdModel;
import com.bisn.models.DfdNode;

/**
 * 创建编辑策略
 * 
 * DiagramLayoutEditPolicy是整个数据流图的编辑策略，被安放在DfdEditPart中
 */
public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	/**
	 * 节点位置变更
	 */
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
//		if (!(child instanceof DfdNodeEditPart))
//			return null;
//		if (!(constraint instanceof Rectangle))
//			return null;

		MoveDfdNodeCommand cmd = new MoveDfdNodeCommand();//移动节点命令
		cmd.setNode((DfdNode)child.getModel());
		cmd.setLocation(((Rectangle) constraint).getLocation());
		cmd.setSize(((Rectangle) constraint).getSize());
		return cmd;
	}

	/**
	 * 创建数据流图的节点
	 */
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObject() instanceof DfdNode) {
			CreateDfdNodeCommand cmd = new CreateDfdNodeCommand();
			cmd.setDiagram((DfdModel) getHost().getModel());
			cmd.setNode((DfdNode) request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			cmd.setLocation(constraint.getLocation());
			return cmd;
		}
		
		return null;
	}

	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}
}

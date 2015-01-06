package com.bisn.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.bisn.commands.CreateDfdConnectionCommand;
import com.bisn.commands.ReconnectDfdConnectionSourceCommand;
import com.bisn.models.DfdConnection;
import com.bisn.models.DfdNode;

public class DfdNodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CreateDfdConnectionCommand command = (CreateDfdConnectionCommand) request.getStartCommand();
		command.setTarget((DfdNode) getHost().getModel());
		return command;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateDfdConnectionCommand command = new CreateDfdConnectionCommand();
		command.setSource((DfdNode) getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectDfdConnectionSourceCommand cmd = new ReconnectDfdConnectionSourceCommand();
		cmd.setConnection((DfdConnection)request.getConnectionEditPart().getModel());
		cmd.setSource((DfdNode)getHost().getModel());
		return cmd;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return null;
	}
}

package com.bisn.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.bisn.commands.DeleteDfdConnectionCommand;
import com.bisn.models.DfdConnection;

public class DfdConnectionEditPolicy extends ComponentEditPolicy{
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DfdConnection conn=(DfdConnection)getHost().getModel();
		DeleteDfdConnectionCommand cmd=new DeleteDfdConnectionCommand();
		cmd.setConnection(conn);
		cmd.setSource(conn.getSource());
		cmd.setTarget(conn.getTarget());
		return cmd;
	}
}

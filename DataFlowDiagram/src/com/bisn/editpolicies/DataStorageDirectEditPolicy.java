package com.bisn.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.bisn.commands.RenameDfdNodeCommand;
import com.bisn.figures.DataStorageFigure;
import com.bisn.models.DfdNode;

public class DataStorageDirectEditPolicy extends DirectEditPolicy {
	protected Command getDirectEditCommand(DirectEditRequest request) {
		RenameDfdNodeCommand cmd = new RenameDfdNodeCommand();
		cmd.setNode((DfdNode) getHost().getModel());
		cmd.setName((String) request.getCellEditor().getValue());
		return cmd;
	}
	
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((DataStorageFigure) getHostFigure()).setName(value);
	}
}

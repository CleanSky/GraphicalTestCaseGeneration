package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;

import com.bisn.editpolicies.DfdNodeEditPolicy;
import com.bisn.figures.DfdNodeFigure;
import com.bisn.models.DfdNode;

public class DfdNodeEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DfdNode.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DfdNode.PROP_SIZE))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DfdNode.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DfdNode.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(DfdNode.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	protected IFigure createFigure() {
		return new DfdNodeFigure();
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DfdNodeEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableEditPolicy());
	}

	public void activate() {
		super.activate();
		if (isActive()) {
			return;
		}
		
		((DfdNode) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		if (!isActive()) {
			return;
		}
		
		((DfdNode) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		DfdNode node = (DfdNode) getModel();
		Point loc = node.getLocation();
		Dimension size = new Dimension(node.getSize());
		Rectangle rectangle = new Rectangle(loc, size);
		((DfdNodeFigure) this.getFigure()).setName(((DfdNode) this.getModel()).getName());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	//------------------------------------------------------------------------
	// Abstract methods from NodeEditPart
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	protected List getModelSourceConnections() {
		return ((DfdNode) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((DfdNode) this.getModel()).getIncomingConnections();
	}
}

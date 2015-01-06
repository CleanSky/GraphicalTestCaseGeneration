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
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.bisn.editpolicies.DataTargetDirectEditPolicy;
import com.bisn.editpolicies.DfdNodeEditPolicy;
import com.bisn.editpolicies.DfdNodeGraphicalNodeEditPolicy;
import com.bisn.figures.DataTargetFigure;
import com.bisn.models.DataTarget;

/**
 * 构建控制器
 * 
 * DataTargetEditPart是数据终点控制器
 */
public class DataTargetEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	protected DirectEditManager manager;

	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			if (manager == null) {
				DataTargetFigure figure = (DataTargetFigure) getFigure();
				manager = new DataTargetDirectEditManager(this, TextCellEditor.class, new DataTargetCellEditorLocator(figure));
			}
			manager.show();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DataTarget.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataTarget.PROP_SIZE))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataTarget.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataTarget.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(DataTarget.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	protected IFigure createFigure() {
		return new DataTargetFigure();
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DataTargetDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DfdNodeEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DfdNodeGraphicalNodeEditPolicy());
	}

	public void activate() {
		if (isActive()) {
			return;
		}
		
		((DataTarget) getModel()).addPropertyChangeListener(this);
		super.activate();
	}

	public void deactivate() {
		if (!isActive()) {
			return;
		}
		
		((DataTarget) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	protected void refreshVisuals() {
		DataTarget node = (DataTarget) getModel();
		Point loc = node.getLocation();
		Dimension size = new Dimension(node.getSize());
		Rectangle rectangle = new Rectangle(loc, size);
		((DataTargetFigure) this.getFigure()).setName(((DataTarget) this.getModel()).getName());
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
		return ((DataTarget) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((DataTarget) this.getModel()).getIncomingConnections();
	}
}

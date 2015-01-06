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
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.bisn.editpolicies.DataProcessDirectEditPolicy;
import com.bisn.editpolicies.DfdNodeEditPolicy;
import com.bisn.editpolicies.DfdNodeGraphicalNodeEditPolicy;
import com.bisn.figures.DataProcessFigure;
import com.bisn.models.DataProcess;

/**
 * 构建控制器
 * 
 * DataProcessEditPart是数据加工控制器
 */
public class DataProcessEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	protected DirectEditManager manager;

	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			if (manager == null) {
				DataProcessFigure figure = (DataProcessFigure) getFigure();
				manager = new DataProcessDirectEditManager(this, TextCellEditor.class, new DataProcessCellEditorLocator(figure));
			}
			manager.show();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DataProcess.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataProcess.PROP_SIZE))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataProcess.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataProcess.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(DataProcess.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	protected IFigure createFigure() {
		return new DataProcessFigure();
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DataProcessDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DfdNodeEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DfdNodeGraphicalNodeEditPolicy());
	}

	public void activate() {
		if (isActive()) {
			return;
		}
		
		((DataProcess) getModel()).addPropertyChangeListener(this);
		super.activate();
	}

	public void deactivate() {
		if (!isActive()) {
			return;
		}
		
		((DataProcess) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	protected void refreshVisuals() {
		DataProcess node = (DataProcess) getModel();
		Point loc = node.getLocation();
		Dimension size = new Dimension(node.getSize());
		Rectangle rectangle = new Rectangle(loc, size);
		((DataProcessFigure) this.getFigure()).setName(((DataProcess) this.getModel()).getName());
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
		return ((DataProcess) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((DataProcess) this.getModel()).getIncomingConnections();
	}
}

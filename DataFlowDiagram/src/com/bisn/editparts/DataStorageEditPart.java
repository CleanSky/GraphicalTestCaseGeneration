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

import com.bisn.editpolicies.DataStorageDirectEditPolicy;
import com.bisn.editpolicies.DfdNodeEditPolicy;
import com.bisn.editpolicies.DfdNodeGraphicalNodeEditPolicy;
import com.bisn.figures.DataStorageFigure;
import com.bisn.models.DataStorage;

/**
 * 构建控制器
 * 
 * DataStorageEditPart是数据存储控制器
 */
public class DataStorageEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	protected DirectEditManager manager;

	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			if (manager == null) {
				DataStorageFigure figure = (DataStorageFigure) getFigure();
				manager = new DataStorageDirectEditManager(this, TextCellEditor.class, new DataStorageCellEditorLocator(figure));
			}
			manager.show();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DataStorage.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataStorage.PROP_SIZE))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataStorage.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(DataStorage.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(DataStorage.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	protected IFigure createFigure() {
		return new DataStorageFigure();
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DataStorageDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DfdNodeEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DfdNodeGraphicalNodeEditPolicy());
	}

	public void activate() {
		if (isActive()) {
			return;
		}
		
		((DataStorage) getModel()).addPropertyChangeListener(this);
		super.activate();
	}

	public void deactivate() {
		if (!isActive()) {
			return;
		}
		
		((DataStorage) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	protected void refreshVisuals() {
		DataStorage node = (DataStorage) getModel();
		Point loc = node.getLocation();
		Dimension size = new Dimension(node.getSize());
		Rectangle rectangle = new Rectangle(loc, size);
		((DataStorageFigure) this.getFigure()).setName(((DataStorage) this.getModel()).getName());
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
		return ((DataStorage) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((DataStorage) this.getModel()).getIncomingConnections();
	}
}

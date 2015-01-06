package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.bisn.editpolicies.DiagramLayoutEditPolicy;
import com.bisn.models.DfdModel;

public class DfdEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {
	protected List getModelChildren() {
		return ((DfdModel) this.getModel()).getDfdNodes();
	}

	public void activate() {
		super.activate();
		((DfdModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((DfdModel) getModel()).removePropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (DfdModel.PROP_NODE.equals(prop))
			refreshChildren();
	}

	protected IFigure createFigure() {
		Figure figure = new FreeformLayer();
		figure.setLayoutManager(new FreeformLayout());
		return figure;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
	}
}

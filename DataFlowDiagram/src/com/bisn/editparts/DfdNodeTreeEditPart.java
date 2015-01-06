package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.DfdNode;

public class DfdNodeTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	public DfdNodeTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((DfdNode) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((DfdNode) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((DfdNode) getModel()).getName());
	}
}

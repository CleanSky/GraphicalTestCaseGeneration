package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.OR;

public class ORTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	public ORTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((OR) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((OR) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((OR) getModel()).getName());
	}
}

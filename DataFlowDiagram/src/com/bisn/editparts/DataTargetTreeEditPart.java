package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.DataTarget;

public class DataTargetTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	public DataTargetTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((DataTarget) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((DataTarget) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((DataTarget) getModel()).getName());
	}
}

package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.DataProcess;

public class DataProcessTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	public DataProcessTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((DataProcess) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((DataProcess) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((DataProcess) getModel()).getName());
	}
}

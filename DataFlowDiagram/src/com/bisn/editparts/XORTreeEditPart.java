package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.XOR;

public class XORTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	public XORTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((XOR) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((XOR) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((XOR) getModel()).getName());
	}
}

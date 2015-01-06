package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.DataSource;

public class DataSourceTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	public DataSourceTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((DataSource) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((DataSource) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((DataSource) getModel()).getName());
	}
}

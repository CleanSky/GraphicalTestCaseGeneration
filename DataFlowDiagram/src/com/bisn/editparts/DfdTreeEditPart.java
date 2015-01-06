package com.bisn.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.bisn.models.DfdModel;

public class DfdTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{
	public DfdTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshChildren();
	}
	
	public void activate() {
		super.activate();
		((DfdModel) getModel()).addPropertyChangeListener(this);
	}
	
	public void deactivate() {
		super.deactivate();
		((DfdModel) getModel()).removePropertyChangeListener(this);
	}
	
	protected List getModelChildren() {
		return ((DfdModel) getModel()).getDfdNodes();
	}
}

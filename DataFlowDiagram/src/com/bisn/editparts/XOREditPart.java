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
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.bisn.editpolicies.DfdNodeEditPolicy;
import com.bisn.editpolicies.DfdNodeGraphicalNodeEditPolicy;
import com.bisn.figures.XORFigure;
import com.bisn.models.XOR;

/**
 * XOREditPart是异或节点控制器
 */
public class XOREditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	/**
	 * 属性改变处理方法
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(XOR.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(XOR.PROP_SIZE))
			refreshVisuals();
		else if (evt.getPropertyName().equals(XOR.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(XOR.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(XOR.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	/**
	 * 创建图形
	 */
	protected IFigure createFigure() {
		return new XORFigure();
	}

	/**
	 * 添加所需角色对应的EditPolicy
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DfdNodeEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DfdNodeGraphicalNodeEditPolicy());
	}

	/**
	 * 启用EditPart
	 */
	public void activate() {
		if (isActive()) {
			return;
		}
		
		((XOR) getModel()).addPropertyChangeListener(this);
		super.activate();
	}

	/**
	 * 禁用EditPart
	 */
	public void deactivate() {
		if (!isActive()) {
			return;
		}
		
		((XOR) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	/**
	 * 刷新视图
	 */
	protected void refreshVisuals() {
		XOR node = (XOR) getModel();
		Point loc = node.getLocation();
		Dimension size = new Dimension(node.getSize());
		Rectangle rectangle = new Rectangle(loc, size);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	//------------------------------------------------------------------------
	// Abstract methods from NodeEditPart
	// 返回连接到该EditPart连接线的起点和终点锚点
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
		return ((XOR) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((XOR) this.getModel()).getIncomingConnections();
	}
}

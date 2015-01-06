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
import com.bisn.figures.ANDFigure;
import com.bisn.models.AND;

/**
 * 构建控制器
 *
 * 为了让图形元素随着界面操作动起来，首先需要为EditPart添加EditPolicy。
 * EditPolicy是处理GEF请求，创建Command的类。
 * 为了能够实现默认角色Role定义的功能，必须在EditPolicy中添加相应的EditPolicy。
 * 
 * ANDEditPart是与节点控制器
 */
public class ANDEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	/**
	 * 属性改变处理方法
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(AND.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(AND.PROP_SIZE))
			refreshVisuals();
		else if (evt.getPropertyName().equals(AND.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(AND.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(AND.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	/**
	 * 创建图形
	 */
	protected IFigure createFigure() {
		return new ANDFigure();
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
		
		((AND) getModel()).addPropertyChangeListener(this);
		super.activate();//不能放在第一行，否则节点不能移动
	}

	/**
	 * 禁用EditPart
	 */
	public void deactivate() {
		if (!isActive()) {
			return;
		}
		
		((AND) getModel()).removePropertyChangeListener(this);
		super.deactivate();//不能放在第一行，否则节点不能移动
	}

	/**
	 * 刷新视图
	 */
	protected void refreshVisuals() {
		AND node = (AND) getModel();
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
		return ((AND) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((AND) this.getModel()).getIncomingConnections();
	}
}

package com.bisn.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.bisn.tools.STextPropertyDescriptor;

/**
 * 构建模型
 * 
 * DfdNode是所有节点模型的父类，继承DfdBase
 */
public class DfdNode extends DfdBase {
	final public static String PROP_LOCATION = "LOCATION";
	final public static String PROP_GLOBALID = "GLOBALID";
	final public static String PROP_TITLE = "TITLE";
	final public static String PROP_NAME = "NAME";
	final public static String PROP_DESCRIPTION = "DESCRIPTION";
	final public static String PROP_INPUTS = "INPUTS";
	final public static String PROP_OUTPUTS = "OUTPUTS";
	final public static String PROP_SIZE = "SIZE";

	protected Dimension size = new Dimension(150, 40);//节点大小
	protected Point location = new Point(0, 0);//节点位置
	protected String globalId;//图元全局唯一ID
	protected String title;//图元标题
	protected String name;//图元名称
	protected String description;//图元描述
	protected List outputs = new ArrayList();//节点的输出数据流
	protected List inputs = new ArrayList();//节点的输入数据流

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_GLOBALID, "全局ID"),
			new STextPropertyDescriptor(PROP_TITLE, "图元标题"),
			new STextPropertyDescriptor(PROP_NAME, "图元名称"),
			new STextPropertyDescriptor(PROP_DESCRIPTION, "图元描述"),
			new STextPropertyDescriptor(PROP_INPUTS, "输入数据流"),
			new STextPropertyDescriptor(PROP_OUTPUTS, "输出数据流"),
			new STextPropertyDescriptor(PROP_LOCATION, "节点位置")};
	
	/**
	 * 构造方法
	 */
	public DfdNode() {
		this.title = "数据流图节点";
		this.name = "数据流图节点";
	}
	
	/******************************************************************************************/
	//get和set
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point p) {
		if (this.location.equals(p)) {
			return;
		}
		this.location = p;
		firePropertyChange(PROP_LOCATION, null, p);
	}
	
	public String getGlobalId() {
		return this.globalId;
	}

	public void setGlobalId(String globalId) {
		if (this.globalId.equals(globalId)) {
			return;
		}
		this.globalId = globalId;
		firePropertyChange(PROP_GLOBALID, null, globalId);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		if (this.title.equals(title)) {
			return;
		}
		this.title = title;
		firePropertyChange(PROP_TITLE, null, title);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (this.name.equals(name)) {
			return;
		}
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if (this.description.equals(description)) {
			return;
		}
		this.description = description;
		firePropertyChange(PROP_DESCRIPTION, null, description);
	}

	public Dimension getSize() {
		return size;
	}
	
	public void setSize(Dimension d) {
		if (this.size.equals(d)) {
			return;
		}
		this.size = d;
		firePropertyChange(PROP_SIZE, null, d);//发送事件通知EditPart模型已修改
	}
	
	/**
	 * 添加节点的输入数据流
	 */
	public void addInput(DfdConnection connection) {
		this.inputs.add(connection);
		fireStructureChange(PROP_INPUTS, connection);
	}
	
	/**
	 * 添加节点的输出数据流
	 */
	public void addOutput(DfdConnection connection) {
		this.outputs.add(connection);
		fireStructureChange(PROP_OUTPUTS, connection);
	}

	/**
	 * 删除节点的输入数据流
	 */
	public void removeInput(DfdConnection connection) {
		inputs.remove(connection);
		fireStructureChange(PROP_INPUTS, connection);
	}
	
	/**
	 * 删除节点的输出数据流
	 */
	public void removeOutput(DfdConnection connection) {
		outputs.remove(connection);
		fireStructureChange(PROP_OUTPUTS, connection);
	}

	/**
	 * 返回节点的输入数据流
	 */
	public List getIncomingConnections() {
		return this.inputs;
	}
	
	/**
	 * 返回节点的输出数据流
	 */
	public List getOutgoingConnections() {
		return this.outputs;
	}
	/******************************************************************************************/
	
	/******************************************************************************************/
	// Abstract methods from IPropertySource 包含如下代码的模型可以实现标准属性页与模型之间的交互操作
	//返回实现了IPropertySource接口可以被作为标准属性页来源的对象，一般情况下返回模型对象本身
	public Object getEditableValue() {
		return this;
	}

	/**
	 * 返回一个属性描述列表，列表中包含IPropertyDescriptor属性描述对象
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}
	
	/**
	 * 返回参数id对应模型属性的值
	 */
	public Object getPropertyValue(Object id) {
		if (PROP_LOCATION.equals(id))
			return getLocation();
		if (PROP_TITLE.equals(id))
			return getTitle();
		if (PROP_NAME.equals(id))
			return getName();
		if (PROP_DESCRIPTION.equals(id))
			return getDescription();
		if (PROP_INPUTS.equals(id))
			return getIncomingConnections();
		if (PROP_OUTPUTS.equals(id))
			return getOutgoingConnections();
		if (PROP_SIZE.equals(id))
			return getSize();
		return null;
	}
	
	/**
	 * 将属性页中修改的值设置到对应的模型属性
	 */
	public void setPropertyValue(Object id, Object value) {
		if (PROP_LOCATION.equals(id))
			setLocation((Point) value);
		if (PROP_GLOBALID.equals(id))
			setGlobalId((String) value);
		if (PROP_TITLE.equals(id))
			setTitle((String) value);
		if (PROP_NAME.equals(id))
			setName((String) value);
		if (PROP_DESCRIPTION.equals(id))
			setDescription((String) value);
	}

	public boolean isPropertySet(Object id) {
		return true;
	}

	public void resetPropertyValue(Object id) {
		
	}
	/******************************************************************************************/
}

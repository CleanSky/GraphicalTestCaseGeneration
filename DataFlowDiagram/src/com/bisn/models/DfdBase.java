package com.bisn.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.bisn.tools.STextPropertyDescriptor;

/**
 * 构建模型
 * 模型是整个编辑器的基础，也是被编辑的对象。
 * 模型的结构和控制器EditPart是一一对应的，故在创建模型类时必须创建与之对应的EditPart。
 * 一般情况下，各个模型元素会存在重叠的属性和功能，可以将这些部分抽象为抽象类。
 * DfdBase是所有模型的抽象类，DfdNode扩展了DfdBase类，是所有节点模型的抽象类。
 * 
 * DfdBase实现了Cloneable、Serializable和IPropertySource接口。
 * Cloneable和Serializable接口保证了DfdBase可以被克隆和被持久化。
 * IPropertySource使得DfdBase可以作为标准属性框的属性来源。
 */
public abstract class DfdBase implements Cloneable, Serializable, IPropertySource {
	final public static String PROP_NAME= "NAME";
	final public static String PROP_DESCRIPTION = "DESCRIPTION";
	
	protected String name;//图元名称
	protected String description;//图元描述
	
	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_NAME, "名称"),
			new STextPropertyDescriptor(PROP_DESCRIPTION, "描述")};
	
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	/******************************************************************************************/
	/**
	 * 注册属性修改监听器方法
	 * @param l
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	/**
	 * 删除属性修改监听器方法
	 * @param l
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	/**
	 * 发送属性修改事件方法
	 */
	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);//发送事件通知EditPart模型已修改
	}

	/**
	 * 发送属性修改事件方法
	 */
	protected void firePropertyChange(String prop, Object newValue) {
		listeners.firePropertyChange(prop, null, newValue);//发送事件通知EditPart模型已修改
	}
	
	/**
	 * 发送模型结构修改事件方法
	 */
	protected void fireStructureChange(String prop, Object child) {
		listeners.firePropertyChange(prop, null, child);
	}
	/******************************************************************************************/
	
	/******************************************************************************************/
	//get和set方法
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if (this.name.equals(name)) {
			return;
		}
		
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);//发送事件通知EditPart模型已修改
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if (this.description.equals(description)) {
			return;
		}
		
		this.description = description;
		firePropertyChange(PROP_DESCRIPTION, null, description);//发送事件通知EditPart模型已修改
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
		if (PROP_NAME.equals(id))
			return getName();
		if (PROP_DESCRIPTION.equals(id))
			return getDescription();
		return null;
	}

	/**
	 * 将属性页中修改的值设置到对应的模型属性
	 */
	public void setPropertyValue(Object id, Object value) {
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

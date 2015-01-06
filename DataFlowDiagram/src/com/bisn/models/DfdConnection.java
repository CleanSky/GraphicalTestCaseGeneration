package com.bisn.models;

/**
 * 构建模型
 * 
 * DfdConnection为连接线，即数据流，继承DfdBase
 */
public class DfdConnection extends DfdBase {
	private DfdNode source;//起点
	private DfdNode target;//终点
	
	/**
	 * 构造方法
	 */
	public DfdConnection(DfdNode source, DfdNode target) {
		super();
		this.name = "数据流";
		this.source = source;
		this.target = target;
		source.addOutput(this);
		target.addInput(this);
	}

	/******************************************************************************************/
	//get和set方法
	public void setSource(DfdNode source) {
		this.source = source;
	}
	
	public void setTarget(DfdNode target) {
		this.target = target;
	}
	
	public DfdNode getSource() {
		return this.source;
	}
	
	public DfdNode getTarget() {
		return this.target;
	}
	/******************************************************************************************/
}

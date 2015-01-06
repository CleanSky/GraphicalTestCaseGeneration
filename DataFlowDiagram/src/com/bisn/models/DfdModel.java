package com.bisn.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建模型
 * 
 * DfdModel是数据流图模型，继承DfdBase
 */
public class DfdModel extends DfdBase {
	public static String PROP_NODE = "DFDNODE";

	protected List dfdNodes = new ArrayList();//数据流图的节点列表
	
	/**
	 * 添加节点
	 */
	public void addDfdNode(DfdNode dfdNode) {
		dfdNodes.add(dfdNode);
		fireStructureChange(PROP_NODE, dfdNodes);
	}

	/**
	 * 删除节点
	 */
	public void deleteDfdNode(DfdNode dfdNode) {
		dfdNodes.remove(dfdNode);
		fireStructureChange(PROP_NODE, dfdNodes);
	}
	
	/**
	 * 返回数据流图的节点
	 */
	public List getDfdNodes() {
		return dfdNodes;
	}
}

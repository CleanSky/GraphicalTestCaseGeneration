package com.bisn.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.bisn.models.AND;
import com.bisn.models.DataProcess;
import com.bisn.models.DataSource;
import com.bisn.models.DataStorage;
import com.bisn.models.DataTarget;
import com.bisn.models.DfdModel;
import com.bisn.models.DfdNode;
import com.bisn.models.OR;
import com.bisn.models.XOR;

/**
 * 树形控制器工厂
 * 
 * TreePartFactory是树形控制器工厂，用于创建模型对应的树形控制器
 */
public class TreePartFactory implements EditPartFactory{
	/**
	 * 创建模型对应的树形控制器
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof DfdModel) {
			return new DfdTreeEditPart(model);
		} else if (model instanceof DataSource) {
			return new DataSourceTreeEditPart(model);
		} else if (model instanceof DataTarget) {
			return new DataTargetTreeEditPart(model);
		} else if (model instanceof DataProcess) {
			return new DataProcessTreeEditPart(model);
		} else if (model instanceof DataStorage) {
			return new DataStorageTreeEditPart(model);
		} else if (model instanceof AND) {
			return new ANDTreeEditPart(model);
		} else if (model instanceof OR) {
			return new ORTreeEditPart(model);
		} else if (model instanceof XOR) {
			return new XORTreeEditPart(model);
		} else if (model instanceof DfdNode) {
			return new DfdNodeTreeEditPart(model);
		} else {
			return null;
		}
	}
}

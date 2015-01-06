package com.bisn.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.bisn.models.AND;
import com.bisn.models.DataProcess;
import com.bisn.models.DataSource;
import com.bisn.models.DataStorage;
import com.bisn.models.DataTarget;
import com.bisn.models.DfdConnection;
import com.bisn.models.DfdModel;
import com.bisn.models.OR;
import com.bisn.models.XOR;

/**
 * 控制器工厂
 * 
 * PartFactory是控制器工厂，用于创建模型Model对应的控制器EditPart
 */
public class PartFactory implements EditPartFactory {
	/**
	 * 创建模型对应的控制器
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof DfdModel) {
			part = new DfdEditPart();
		} else if (model instanceof DfdConnection)  {
			part = new DfdConnectionEditPart();
		} else if (model instanceof DataSource) {
			part = new DataSourceEditPart();
		} else if (model instanceof DataTarget) {
			part = new DataTargetEditPart();
		} else if (model instanceof DataProcess) {
			part = new DataProcessEditPart();
		} else if (model instanceof DataStorage) {
			part = new DataStorageEditPart();
		} else if (model instanceof AND) {
			part = new ANDEditPart();
		} else if (model instanceof OR) {
			part = new OREditPart();
		} else if (model instanceof XOR) {
			part = new XOREditPart();
		} else {
			part = new DfdNodeEditPart();
		}
		
		part.setModel(model);
		return part;
	}
}

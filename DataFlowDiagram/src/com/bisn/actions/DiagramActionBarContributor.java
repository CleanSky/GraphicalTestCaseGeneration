package com.bisn.actions;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * DiagramActionBarContributor负责注册Action并扩展工具条
 * 在Eclipse现有工具条中添加Undo/Redo按钮
 */
public class DiagramActionBarContributor extends ActionBarContributor {
	/**
	 * 注册Action
	 */
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new DeleteRetargetAction());
    }
    
    protected void declareGlobalActionKeys() {
        // TODO Auto-generated method stub

    }
    
    /**
     * 扩展工具条
     */
    public void contributeToToolBar(IToolBarManager toolBarManager) {
        toolBarManager.add(getAction(ActionFactory.UNDO.getId()));//添加UNDO按钮
        toolBarManager.add(getAction(ActionFactory.REDO.getId()));//添加REDO按钮
    }
}

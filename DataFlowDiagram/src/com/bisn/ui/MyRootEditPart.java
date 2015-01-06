package com.bisn.ui;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

/**
 * 在实现GEF编辑器时，会重写configureGraphicalViewer()方法，在该方法里会为GraphicalViewer设置一个RootEditPart，一般是ScalableFreeformRootEditPart。
 * RootEditPart接口：该接口定义了几个方法，主要用于获取当前的EditPartViewer，在GEF中EditPartViewer才是我们在界面看到的一个个有效单元，而EditPart只是它的一个控制器。
 * FreeformGraphicalRootEditPart：一个GEF默认提供的类，它是一个RootEditPart，默认建立了N个Layer，用分层展示的方式，展示编辑器中的界面元素，所有的layer都放在FreeformViewport里面。
 * ScalableFreeformRootEditPart：是FreeformGraphicalRootEditPart的子类，它里面增加了一个ZoomManager的功能。
 * RulerRootEditPart：为编辑提供游标功能的EditPart，主要被gef默认提供的封装组件RulerComposite使用。
 * ScalableRootEditPart：能够缩放，但是不自由伸展。
 */
public class MyRootEditPart extends ScalableFreeformRootEditPart {
	protected LayeredPane createPrintableLayers() {
		FreeformLayeredPane layeredPane = new FreeformLayeredPane();
		layeredPane.add(new BackgroundLayer(), BackgroundLayer.BACKGROUND_LAYER);
		layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
		layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
		return layeredPane;
	}
}

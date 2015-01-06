package com.bisn.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import com.bisn.factories.ElementFactory;

/**
 * 默认情况下，GEF中要创建一个新的节点都是在Palette上选择一个节点，然后在Editor区域单击实现的。
 * 也有一些是通过拖放Palette中的对象到Editor区域来实现的，GEF默认实现了一组drag-drop listener用来支持拖放实现。
 * TemplateTransferDropTargetListener：因为是要从palette上拖到Editor中，所以此监听事件应放在Editor上，在GEF中就是GraphicalViewer。
 * TemplateTransferDragSourceListener：此事件需要加在palette上，即PaletteViewer上。
 * 
 * 增加Palette的拖放支持，实现拖放功能
 * 图形元素拖动效果：在拖动过程中会产生一个与原图形元素相同大小，并随鼠标移动的阴影以表示拖动后图形元素所占据的位置。
 * 
 * 向EditPartViewer中添加拖放Transfer源监听器TransferDragSourceListener和托放Transfer目的监听器TransferDropTargetListener。
 * TransferDragSourceListener用来定义EditPartViewer作为拖放源响应拖放操作，而TransferDropTargetListener用来定义EditPartViewer作为托放目的响应拖放操作。
 * 在向GEF应用加入拖放功能的时候，拖放监听器的实现必须继承自包含默认实现的GEF中提供的抽象类。
 */
public class DiagramTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	/**
	 * @param viewer
	 */
	public DiagramTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	protected CreationFactory getFactory(Object template) {
		return new ElementFactory(template);
	}
}

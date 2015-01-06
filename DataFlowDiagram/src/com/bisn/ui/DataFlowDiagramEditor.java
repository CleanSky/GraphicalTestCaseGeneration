package com.bisn.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gef.ui.stackview.CommandStackInspectorPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.bisn.dnd.DiagramTemplateTransferDropTargetListener;
import com.bisn.editparts.PartFactory;
import com.bisn.editparts.TreePartFactory;
import com.bisn.factories.PaletteFactory;
import com.bisn.models.DfdModel;

/**
 * 数据流图编辑器
 */
public class DataFlowDiagramEditor extends GraphicalEditorWithFlyoutPalette {
	private DfdModel diagram = new DfdModel();//数据流图
	private PaletteRoot paletteRoot;

	//Getter
	public DfdModel getDiagram() {
		return this.diagram;
	}

	/**
	 * 构造方法
	 */
	public DataFlowDiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));//在Editor创建方法中添加设置EditDomain代码
	}

	/**
	 * 在实现GEF编辑器时，会重写configureGraphicalViewer()方法，在该方法里会为GraphicalViewer设置一个RootEditPart，一般是ScalableFreeformRootEditPart。
	 *
	 * 配置图形视图GraphicalViewer，向视图中设置RootEditPart和EditPart工厂类——PartFactory
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new MyRootEditPart());
		getGraphicalViewer().setEditPartFactory(new PartFactory());
	}

	/**
	 * 初始化GraphicalViewer
	 */
	protected void initializeGraphicalViewer() {
		getGraphicalViewer().setContents(this.diagram);
		getGraphicalViewer().addDropTargetListener(new DiagramTemplateTransferDropTargetListener(getGraphicalViewer()));
	}

	/**
	 * 将编辑后的模型保存到文件，完成保存编辑结果操作
	 */
	public void doSave(IProgressMonitor monitor) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(
					new ByteArrayInputStream(out.toByteArray()), 
					true,  // keep saving, even if IFile is out of sync with the Workspace
					false, // dont keep history
					monitor); // progress monitor
			getCommandStack().markSaveLocation();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (CoreException ce) { 
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(this.diagram);
		oos.close();
	}

	/**
	 * 完成编辑结果另存为操作
	 */
	public void doSaveAs() {
		
	}

	/**
	 * 返回CommandStack状态，判断是否需要保存
	 * 返回CommandStack中是否有保存后执行的Command的布尔值
	 */
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	/**
	 * 设置是否允许另存为功能，这里返回false，不允许另存为
	 */
	public boolean isSaveAsAllowed() {
		return false;//不允许另存为
	}

	/**
	 * 设置编辑器输入，当用户双击模型文件时，调用此方法将模型从文件中读出
	 * 为Editor设置输入，可以在此处加入从文件读取模型操作，实现编辑保存在文件的模型的功能
	 */
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		try {
			IFile file = ((IFileEditorInput) input).getFile();
			ObjectInputStream in = new ObjectInputStream(file.getContents());
			this.diagram = (DfdModel) in.readObject();
			in.close();
			setPartName(file.getName());
		} catch (IOException e) {
			handleLoadException(e);
		} catch (CoreException e) {
			handleLoadException(e);
		} catch (ClassNotFoundException e) {
			handleLoadException(e);
		}
	}

	/**
	 * 处理异常情况
	 */
	private void handleLoadException(Exception e) {
		this.diagram = new DfdModel();
	}

	/**
	 * 设置对应于Eclipse平台中预定义控件的改编器——Adapter
	 * 在此设置各种自定义控件，比如添加自定义大纲视图、自定义属性视图等
	 * 可替换功能
	 */
	public Object getAdapter(Class type) {
		if (type == CommandStackInspectorPage.class)
			return new CommandStackInspectorPage(getCommandStack());
		if (type == ActionRegistry.class)
			return getActionRegistry();
		if (type == IContentOutlinePage.class)
			return new OutlinePage();
		return super.getAdapter(type);
	}
	
	/**
	 * 当CommandStack发生改变时调用此方法，在此方法中添加发送IEditorPart.PROP_DIRTY事件代码以更新界面显示状态
	 */
	public void commandStackChanged(EventObject event) {
		if (isDirty()) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		super.commandStackChanged(event);

	}

	/**
	 * 创建调色板PaletteRoot，在此添加创建调色板PaletteRoot代码
	 * 由调色板工厂创建调色板并返回PaletteRoot
	 * 如果编辑器是直接扩展GraphicalEditorWithPalette或GraphhicalEditorWithFlyoutPalette实现的，就不需要再添加PaletteViewer了。
	 */
	protected PaletteRoot getPaletteRoot() {
		if (this.paletteRoot == null) {
			this.paletteRoot = PaletteFactory.createPalette();
		}
		return this.paletteRoot;
	}

	/**
	 * 大纲视图
	 */
	class OutlinePage extends ContentOutlinePage {
		private Control outline;

		public OutlinePage() {
			super(new TreeViewer());
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();
			String id = IWorkbenchActionConstants.UNDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.REDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.DELETE;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			bars.updateActionBars();
		}

		public void createControl(Composite parent) {
			outline = getViewer().createControl(parent);
			getSelectionSynchronizer().addViewer(getViewer());
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new TreePartFactory());
			getViewer().setContents(getDiagram());
		}

		public Control getControl() {
			return outline;
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			super.dispose();
		}
	}
}

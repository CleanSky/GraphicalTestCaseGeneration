package com.bisn;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 在GEF应用实现过程中，有可能需要获取当前活动状态的WorkbenchShell或WorkbenchWindow。
 * GEF应用开发过程：
 * （1）添加模型——创建GEF应用的用户模型;
 * （2）添加视图——创建用户界面上表现模型的图形视图;
 * （3）添加控制器——创建GEF架构中的协调控制中心EditPart;
 * （4）创建编辑器——创建Eclipse平台编辑器;
 * （5）添加缩放、直接编辑等附属功能;
 * （6）添加编辑策略——向EditPart中添加编辑策略EditPolicy，一般在创建EditPart时一同完成;
 * （7）添加属性页——添加辅助编辑器模型属性的属性页;
 * （8）添加调色板和调色板中工具;
 */
public class Activator extends AbstractUIPlugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "com.bisn.Activator"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;//可以通过它获取当前插件实例
	
	/**
	 * The constructor
	 * 构造方法
	 */
	public Activator() {
		super();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 * 激活插件时调用该方法
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 * 关闭插件时调用该方法
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 返回当前插件实例
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 *
	 * @param path the path
	 * @return the image descriptor
	 * 
	 * 根据传入的路径参数获取ImageDescriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * 获取当前活动的WorkbenchShell
	 * @return
	 */
	public static Shell getActiveWorkbenchShell() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		return null;
	}

	/**
	 * 获取当前活动的WorkbenchWindow
	 * @return
	 */
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}
}

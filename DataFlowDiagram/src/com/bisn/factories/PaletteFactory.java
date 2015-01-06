package com.bisn.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;

import com.bisn.images.IconFactory;
import com.bisn.images.ImageConstants;
import com.bisn.models.AND;
import com.bisn.models.DataProcess;
import com.bisn.models.DataSource;
import com.bisn.models.DataStorage;
import com.bisn.models.DataTarget;
import com.bisn.models.DfdNode;
import com.bisn.models.OR;
import com.bisn.models.XOR;

/**
 * 调色板工厂
 */
public class PaletteFactory {
	public static PaletteRoot createPalette() {//整个工具
		PaletteRoot paletteRoot = new PaletteRoot();
		paletteRoot.addAll(createCategories(paletteRoot));
		
		return paletteRoot;
	}

	private static List createCategories(PaletteRoot root) {//工具分组
		List categories = new ArrayList();
		
		//调色板共分两组，一个是通用工具分组，另一个是自定义可折叠的PaletteDrawer，需要在其中添加对应于节点模型的ToolEntry
		categories.add(createControlGroup(root));//通用工具组
		categories.add(createComponentsDrawer());//自定义可折叠的工具组
		
		return categories;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) {//向通用工具组中添加工具
		PaletteGroup controlGroup = new PaletteGroup("通用工具");
		List entries = new ArrayList();//通用工具组中的工具列表
		
		ToolEntry tool = new SelectionToolEntry();//选择工具
		entries.add(tool);//向通用工具中加入选择工具
		root.setDefaultEntry(tool);//设置选择工具为默认工具
		
		tool = new ConnectionCreationToolEntry("数据流", "创建一条连接节点的数据流", null, null, null);//数据流工具
		ImageDescriptor imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.CONNECTION);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向通用工具中加入数据流工具
		
		controlGroup.addAll(entries);
		return controlGroup;
	}

	private static PaletteContainer createComponentsDrawer() {//向自定义工具组中添加工具
		PaletteDrawer drawer = new PaletteDrawer("节点工具");//工具组的名称
		List entries = new ArrayList();
		
		ToolEntry tool = new CombinedTemplateCreationEntry("数据源点", "创建一个数据源点", DataSource.class, new SimpleFactory(
				DataSource.class), null, null);//数据源点工具
		ImageDescriptor imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.DATASOURCE);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入数据源点工具
		
		tool = new CombinedTemplateCreationEntry("数据终点", "创建一个数据终点", DataTarget.class, new SimpleFactory(
				DataTarget.class), null, null);//数据终点工具
		imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.DATATARGET);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入数据终点工具
		
		tool = new CombinedTemplateCreationEntry("数据加工", "创建一个数据加工节点", DataProcess.class, new SimpleFactory(
				DataProcess.class), null, null);//数据加工工具
		imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.DATAPROCESS);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入数据加工工具
		
		tool = new CombinedTemplateCreationEntry("数据存储", "创建一个数据存储", DataStorage.class, new SimpleFactory(
				DataStorage.class), null, null);//数据存储工具
		imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.DATASTORAGE);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入数据存储工具
		
		tool = new CombinedTemplateCreationEntry("与关系", "创建一个数据流与关系节点", AND.class, new SimpleFactory(
				AND.class), null, null);//与关系工具
		imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.AND);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入与关系工具
		
		tool = new CombinedTemplateCreationEntry("或关系", "创建一个数据流或关系节点", OR.class, new SimpleFactory(
				OR.class), null, null);//或关系工具
		imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.OR);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入或关系工具
		
		tool = new CombinedTemplateCreationEntry("异或关系", "创建一个数据流异或关系节点", XOR.class, new SimpleFactory(
				XOR.class), null, null);//异或关系工具
		imageDescriptor = IconFactory.getImageDescriptor(ImageConstants.XOR);//工具图标
		tool.setSmallIcon(imageDescriptor);//小图标
		tool.setLargeIcon(imageDescriptor);//大图标
		entries.add(tool);//向节点工具组中加入异或关系工具
		
		drawer.addAll(entries);
		return drawer;
	}
}

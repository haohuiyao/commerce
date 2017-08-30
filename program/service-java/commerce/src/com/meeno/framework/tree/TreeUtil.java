package com.meeno.framework.tree;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.meeno.framework.exception.BusinessRuntimeException;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.ChildrenIterator;

public class TreeUtil {
	private static final int maxLevel = 3;

	public static List<TreeEntity> generalStyles(String positionList, String baseStyle) {
		if (positionList == null || baseStyle == null) {
			return null;
		}
		String[] position = positionList.split(",");
		TreeEntity root = getDefaultRootNode();
		TreeEntity parent = null;
		List<TreeEntity> styleList = new ArrayList<TreeEntity>();
		for (int i = 0; i < position.length; i++) {
			String[] ss = position[i].split("-");
			TreeEntity node = new TreeEntity();
			node.setId(Long.valueOf(ss[0]));
			TreeEntity parentNode = new TreeEntity();
			parentNode.setId(Long.valueOf(ss[1]));
			node.setParent(parentNode);
			if (Long.parseLong(ss[1]) == 0) {
				parent = root;
			} else {
				parent = getParent(styleList, node.getParent().getId());
			}
			node.setLevel(parent.getLevel() + 1);
			String maxStyleStr = getMaxStyleInLevel(styleList, parent);
			if (i == 0) {
				node.setStyle(fillZero(baseStyle, 1, TreeConfig.getInstance().getMaxLevel()));
			} else {
				node.setStyle(makeStyle(parent, maxStyleStr, TreeConfig.getInstance().getMaxLevel()));
			}
			styleList.add(node);
		}
		return styleList;

	}

	public static TreeEntity getDefaultRootNode() {
		TreeEntity root = new TreeEntity();
		root.setId(Long.valueOf("0"));
		root.setParent(null);
		root.setStyle(fillZero("00", 1, maxLevel));
		root.setLevel(0);
		return root;
	}

	public static String makeStyle(TreeEntity parent, String maxStyleStr, int maxLevel) {
		int stylePosition = TreeNodeStyle.getNodeOrderNum(maxStyleStr);
		if (stylePosition >= 350) {
			throw new BusinessRuntimeException("Tree style position is bigger than 350!");
			// stylePosition = 98;
		}
		stylePosition = stylePosition + 1;
		String childStyle = parent.getStyle().substring(0, (int) (parent.getLevel() * 2));
		childStyle = childStyle + TreeNodeStyle.nodeStyle[stylePosition];
		childStyle = fillZero(childStyle, (int) parent.getLevel() + 1, maxLevel);
		return childStyle;
	}

	public static String fillZero(final String filledStr, final int level, final int maxLevel) {
		StringBuffer strb = new StringBuffer(filledStr);
		int countLevel = level;
		while (countLevel < maxLevel) {
			strb.append("00");
			countLevel++;
		}
		return strb.toString();
	}

	private static String getMaxStyleInLevel(List<? extends TreeEntity> styleList, TreeEntity parent) {
		int beginIndex = (int) parent.getLevel() * 2;
		String maxStyle = "00";
		for (int i = styleList.size() - 1; i >= 0; i--) {
			TreeEntity node = styleList.get(i);
			if (node.getParent().getId().longValue() == parent.getId().longValue()) {
				maxStyle = node.getStyle().substring(beginIndex, beginIndex + 2);
				break;
			}
		}
		return maxStyle;
	}

	private static TreeEntity getParent(List<? extends TreeEntity> styleList, Long parentId) {
		TreeEntity node = null;
		for (int i = 0; i < styleList.size(); i++) {
			if (styleList.get(i).getId().equals(parentId)) {
				node = (TreeEntity) styleList.get(i);
				break;
			}
		}
		return node;
	}

	@SuppressWarnings("unchecked")
	private static String getNodeStyle(final TreeEntity inputParent, HibernateTemplate hibernateTemplate,
			Class treeSubClass) {
		List<String> list = null;
		TreeEntity parent = inputParent;
		if (parent == null || parent.getId() == null) {
			try {
				parent = (TreeEntity) treeSubClass.newInstance();
			} catch (InstantiationException e) {
				throw new BusinessRuntimeException("InstantiationException", e);
			} catch (IllegalAccessException e) {
				throw new BusinessRuntimeException("IllegalAccessException", e);
			}
			parent.setLevel(0);
			parent.setStyle(fillZero("00", 1, TreeConfig.getInstance().getMaxLevel()));
			String HSQL = "select max(" + TreeConfig.getInstance().getSubStringMethod() + "(t.style,1,2)) from "
					+ treeSubClass.getName() + " t where t.parent.id is null ";
			list = hibernateTemplate.find(HSQL);
		} else {
			int beginIndex = (int) parent.getLevel() * 2 + 1;
			String HSQL = "select max(" + TreeConfig.getInstance().getSubStringMethod() + "(t.style," + beginIndex
					+ ",2)) from " + treeSubClass.getName() + " t where t.parent.id=? ";
			list = hibernateTemplate.find(HSQL, parent.getId());
		}
		if (list.size() == 0 || list.get(0) == null || list.get(0).length() == 0) {
			return makeStyle(parent, "00", TreeConfig.getInstance().getMaxLevel());
		}
		return makeStyle(parent, list.get(0), TreeConfig.getInstance().getMaxLevel());
	}

	public static String getClassName(String instanceName) {
		if (instanceName.indexOf("_$$_") > 0) {
			return instanceName.substring(0, instanceName.indexOf("_$$_"));
		}
		return instanceName;
	}

	public static void setLeveAndStyle(TreeEntity treeNode, HibernateTemplate hibernateTemplate) {
		if (treeNode.getId() != null) {
			TreeEntity oldEntity = (TreeEntity) hibernateTemplate.get(treeNode.getClass(), treeNode.getId());
			if (oldEntity.getParent() == null && treeNode.getParent() == null) {
				return;
			} else if (oldEntity.getParent() != null && treeNode.getParent() != null
					&& oldEntity.getParent().getId().longValue() == treeNode.getParent().getId().longValue()
					&& treeNode.getStyle() != null) {
				return;
			}
			if (treeNode.getParent() != null && treeNode.getStyle() != null) {
				TreeEntity parent = (TreeEntity) hibernateTemplate.get(treeNode.getClass(),
						treeNode.getParent().getId());
				String parentStyle = parent.getStyle();
				String style = treeNode.getStyle().substring(0, treeNode.getLevel() * 2);
				if (parentStyle.startsWith(style)) {
					throw new BusinessRuntimeException("不能选择自己或者自己的下级作为父级节点!");
				}
			}
		}
		TreeEntity parent = treeNode.getParent();
		if (parent != null && parent.getId() != null) {
			parent = (TreeEntity) hibernateTemplate.get(treeNode.getClass(), parent.getId());
			if (treeNode.getId() != null) {
				TreeEntity oldTreeNode = (TreeEntity) hibernateTemplate.get(treeNode.getClass(), treeNode.getId());
				if (oldTreeNode.getParent() != null
						&& oldTreeNode.getParent().getId().longValue() == parent.getId().longValue()
						&& treeNode.getStyle() != null) {
					return;
				}
				if (parent.getStyle().startsWith(oldTreeNode.getStyle().substring(0, 2 * oldTreeNode.getLevel()))) {
					treeNode.setStyle(oldTreeNode.getStyle());
					treeNode.setLevel(oldTreeNode.getLevel());
					return;
				}
			}
			if (parent.getLevel() >= TreeConfig.getInstance().getMaxLevel()) {
				// String[] param = { TreeConfig.getInstance().getMaxLevel()+ ""
				// };
				throw new BusinessRuntimeException("error.over.tree.nodes.limit");
			} else {
				treeNode.setLevel(parent.getLevel() + 1);
			}
		} else {
			treeNode.setLevel(1);
		}
		treeNode.setStyle(getNodeStyle(parent, hibernateTemplate, treeNode.getClass()));
	}

	public static boolean isContain(TreeEntity entity, List<? extends TreeEntity> childList) {
		int level = (int) entity.getLevel() * 2;
		String prefix = entity.getStyle().substring(0, level);
		for (int i = 0; i < childList.size(); i++) {
			TreeEntity childSource = childList.get(i);
			if (childSource.getStyle().substring(0, level).equals(prefix)) {
				return true;
			}
		}
		return false;
	}

	/***
	 * 这个类需要实现数据缓存.
	 * 
	 * @param childSourceList
	 * @param entity
	 * @param hibernateTemplate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getMenuTree(List<? extends TreeEntity> childSourceList, Class entity,
			HibernateTemplate hibernateTemplate) {
		List<TreeEntity> allSourceList = hibernateTemplate
				.find("from " + entity.getName() + " t where t.status='A' order by t.style ASC");
		for (int i = 0; i < allSourceList.size(); i++) {
			TreeEntity source = (TreeEntity) allSourceList.get(i);
			if (!isContain(source, childSourceList)) {
				allSourceList.remove(i);
				i--;
			}
		}
		return allSourceList;
	}
}

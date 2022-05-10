package com.shiyue.superUtil;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 构建树结构，基于栈实现
 *
 * @author wanggl
 * @date 2021-02-07
 */
public class TreeHelper {

    /**
     * 根据头结点和所有下属节点的list向头结点组装树结构
     * <br/>
     * example:<br/>
     * TreeHelper.build(treeListDTO, treeList,<br/>
     * BaseTreeListDTO::getId,<br/>
     * BaseTreeListDTO::getParentId,<br/>
     * BaseTreeListDTO::setChildren);<br/>
     * <p>
     * param head        头结点
     * param allChildren 所有下属节点list
     * param getId       获取当前节点id
     * param getParentId 获取父节点id
     * param setChildren 给指节点设置子节点
     * param <T>         指定节点
     * param <ID>        子节点list
     */
    public static <T, ID> void build(T head, List<T> allChildren,
                                     Function<T, ID> getId,
                                     Function<T, ID> getParentId,
                                     BiConsumer<T, List<T>> setChildren) {
        // 检查参数
        if (null == head || null == allChildren || allChildren.size() == 0) {
            return;
        }

        // 根据父ID分组
        Map<ID, List<T>> childMap = allChildren.parallelStream()
                .collect(Collectors.groupingBy(getParentId));

        // 使用栈结构处理
        Stack<T> stack = new Stack<>();
        // 取头结点的子节点集合
        List<T> children = childMap.get(getId.apply(head));
        if (null == children || children.isEmpty()) {
            return;
        }
        // 关联父子节点，子节点入栈
        setChildren.accept(head, children);
        stack.addAll(children);

        while (!stack.isEmpty()) {
            // 出栈
            T node = stack.pop();
            // 取当前节点的所有子节点
            List<T> nodeChildren = childMap.get(getId.apply(node));
            if (null != nodeChildren && !nodeChildren.isEmpty()) {
                // 关联父子节点，子节点入栈
                setChildren.accept(node, nodeChildren);
                stack.addAll(nodeChildren);
            }
        }
    }

}

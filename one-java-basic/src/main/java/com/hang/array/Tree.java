package com.hang.array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Hang
 * @date 2020-09-08 19:56
 */
public class Tree {
    public static void main(String[] args) {
        int[] postorder = {1,3,2,6,5};
        Tree tree = new Tree();
        System.out.println(tree.verifyPostorder(postorder));
        System.out.println(tree.verifyPostorderAnother(postorder));
    }
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    //  递归方式，判断每一个子树都满足BST的性质
    private boolean recur(int[] post, int i, int j) {
        if (i >= j) return true;
        int p = i;
        // 找到第一个大于p的节点 --- p之前的都是root的左子树
        while (post[p] < post[j]) p++;
        // 记录此时的左子树顶点
        int m = p;
        // 找到第一个等于p的节点 --- 找右子树部分
        while (post[p] > post[j]) p++;
        // 如果最后的p与j相等，代表找到了相应的顶点
        return p == j && recur(post, i, m - 1) && recur(post, m, j - 1);
    }


    // 递归实现
    private boolean verify(int[] postorder, int left, int right){
        if (left >= right) return true; // 当前区域不合法的时候直接返回true就好

        int rootValue = postorder[right]; // 当前树的根节点的值

        int k = left;
        while (k < right && postorder[k] < rootValue){ // 从当前区域找到第一个大于根节点的，说明后续区域数值都在右子树中
            k++;
        }

        for (int i = k; i < right; i++){ // 进行判断后续的区域是否所有的值都是大于当前的根节点，如果出现小于的值就直接返回false
            if (postorder[i] < rootValue) return false;
        }
        // 当前树没问题就检查左右子树
        if (!verify(postorder, left, k - 1)) return false; // 检查左子树
        if (!verify(postorder, k, right - 1)) return false; // 检查右子树

        return true; // 最终都没问题就返回true
    }


    public boolean verifyPostorderAnother(int[] postorder) {
        // 单调栈使用，单调递增的单调栈
        Stack<Integer> stack = new Stack<>();
        int pervElem = Integer.MAX_VALUE;
        // 逆向遍历，就是翻转的先序遍历
        for (int i = postorder.length - 1;i>=0;i--){
            // 左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
            if (postorder[i] > pervElem){
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()){
                // 数组元素小于单调栈的元素了，表示往左子树走了，记录下上个根节点
                // 找到这个左子树对应的根节点，之前右子树全部弹出，不再记录，因为不可能在往根节点的右子树走了
                pervElem = stack.pop();
            }
            // 这个新元素入栈
            stack.push(postorder[i]);
        }
        return true;
    }
}

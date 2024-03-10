package org.khachidze;

import java.util.Iterator;
import java.util.Stack;

class AVLTreeIterator implements Iterator<String> {

    private Node current;
    private Stack<Node> stack;

    public AVLTreeIterator(Node root) {
        stack = new Stack<>();
        current = root;
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public String next() {
        Node node = stack.pop();
        String result = node.data;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }
}

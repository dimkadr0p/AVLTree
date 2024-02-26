package org.khachidze;

import java.util.ArrayList;
import java.util.List;

class AVLTree {
    private Node root;

    public void insert(String data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, String data) {
        if (node == null) {
            return new Node(data);
        }

        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            node.left = insertNode(node.left, data);
        } else if (compareResult > 0) {
            node.right = insertNode(node.right, data);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node rightChildOfX = x.right;
        x.right = y;
        y.left = rightChildOfX;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node leftChildOfY = y.left;
        y.left = x;
        x.right = leftChildOfY;
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        return y;
    }

    public List<String> query(String target, String mode) {
        List<String> result = new ArrayList<>();
        queryHelper(root, target, mode, result);
        return result;
    }

    private void queryHelper(Node node, String target, String mode, List<String> result) {
        if (node == null) {
            return;
        }

        int compareResult = target.compareTo(node.data);

        switch (mode) {
            case "greater":
                if (compareResult < 0) {
                    queryHelper(node.left, target, mode, result);
                    result.add(node.data);
                    queryHelper(node.right, target, mode, result);
                } else {
                    queryHelper(node.right, target, mode, result);
                }
                break;
            case "less":
                if (compareResult > 0) {
                    queryHelper(node.right, target, mode, result);
                    result.add(node.data);
                    queryHelper(node.left, target, mode, result);
                } else {
                    queryHelper(node.left, target, mode, result);
                }
                break;
            case "equal":
                if (target.equals(node.data)) {
                    result.add(node.data);
                }
                if (compareResult < 0) {
                    queryHelper(node.left, target, mode, result);
                } else {
                    queryHelper(node.right, target, mode, result);
                }
                break;
        }
    }

    public void display() {
        root = displayHelper(root);
    }

    private Node displayHelper(Node node) {
        if(node != null) {
            node.left = displayHelper(node.left);
            System.out.println(node.data);
            node.right = displayHelper(node.right);
        }
        return node;
    }


    public Node findFirst() {
        if (root == null) {
            return null;
        }
        return findFirstNode(root);
    }

    private Node findFirstNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return findFirstNode(node.left);
    }

    public Node findLast() {
        if (root == null) {
            return null;
        }
        return findLastNode(root);
    }

    private Node findLastNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return findLastNode(node.right);
    }

}
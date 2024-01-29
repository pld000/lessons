package binary_search_tree;

import java.io.*;
import java.util.*;


class BSTNode<T> {
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T> {
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root;

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        if (Root == null) {
            return new BSTFind<>();
        }
        return _FindNodeByKey(key, Root);
    }

    private BSTFind<T> _FindNodeByKey(int key, BSTNode<T> node) {
        BSTFind<T> findNode = new BSTFind<>();
        findNode.Node = node;

        if (key == node.NodeKey) {
            findNode.NodeHasKey = true;
        } else if (key < node.NodeKey) {
            if (node.LeftChild != null) {
                return _FindNodeByKey(key, node.LeftChild);
            }

            findNode.NodeHasKey = false;
            findNode.ToLeft = true;
        } else {
            if (node.RightChild != null) {
                return _FindNodeByKey(key, node.RightChild);
            }

            findNode.NodeHasKey = false;
            findNode.ToLeft = false;
        }

        return findNode;
    }

    public boolean AddKeyValue(int key, T val) {
        BSTFind<T> findNode = FindNodeByKey(key);

        if (findNode.Node == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }

        if (findNode.NodeHasKey) {
            return false;
        }

        if (findNode.ToLeft) {
            findNode.Node.LeftChild = new BSTNode<>(key, val, findNode.Node);
        } else {
            findNode.Node.RightChild = new BSTNode<>(key, val, findNode.Node);
        }

        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        if (FromNode == null) {
            return null;
        }
        return _FinMinMax(FromNode, FindMax);
    }

    private BSTNode<T> _FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        if (FindMax && FromNode.RightChild == null || !FindMax && FromNode.LeftChild == null) {
            return FromNode;
        }

        return FindMax ? _FinMinMax(FromNode.RightChild, true) : _FinMinMax(FromNode.LeftChild, false);
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> findNode = FindNodeByKey(key);

        if (findNode.Node == null || !findNode.NodeHasKey) {
            return false;
        }

        boolean isLeaf = findNode.Node.LeftChild == null && findNode.Node.RightChild == null;
        if (isLeaf) {
            if (findNode.Node.NodeKey == Root.NodeKey) {
                Root = null;
            } else if (findNode.Node.NodeKey > findNode.Node.Parent.NodeKey) {
                findNode.Node.Parent.RightChild = null;
            } else {
                findNode.Node.Parent.LeftChild = null;
            }

            return true;
        }

        BSTNode<T> minChild = FinMinMax(findNode.Node.RightChild, false);

        if (minChild == null) {
            findNode.Node.Parent.LeftChild = null;
            findNode.Node.Parent = findNode.Node.Parent.Parent;
        } else if (minChild.RightChild == null) {
            minChild.Parent = findNode.Node.Parent;
            minChild.RightChild = findNode.Node.RightChild;
            minChild.LeftChild = findNode.Node.LeftChild;
        } else {
            minChild.RightChild.Parent = minChild.Parent;
            minChild.Parent = findNode.Node.Parent;
            minChild.RightChild = findNode.Node.RightChild;
            minChild.LeftChild = findNode.Node.LeftChild;
        }

        return true;
    }

    public int Count() {
        return 0;
    }

}
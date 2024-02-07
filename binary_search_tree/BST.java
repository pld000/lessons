package binary_search_tree;

import java.io.*;
import java.util.*;

class BSTFind<T> {
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

public class BST<T> {
    public BSTNode<T> Root;

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        if (Root == null) {
            return null;
        }

        ArrayList<BSTNode> nodes = new ArrayList<>();

        if (order == 0) {
            return _DeepInOrder(Root, nodes);
        } else if (order == 1) {
            return _DeepPostOrder(Root, nodes);
        } else if (order == 2) {
            return _DeepPreOrder(Root, nodes);
        }

        return null;
    }

    private ArrayList<BSTNode> _DeepInOrder(BSTNode node, ArrayList<BSTNode> nodes) {
        if (node == null) {
            return nodes;
        }

        nodes = _DeepInOrder(node.LeftChild, nodes);
        nodes.add(node);
        nodes = _DeepInOrder(node.RightChild, nodes);

        return nodes;
    }

    private ArrayList<BSTNode> _DeepPostOrder(BSTNode node, ArrayList<BSTNode> nodes) {
        if (node == null) {
            return nodes;
        }

        nodes = _DeepPostOrder(node.LeftChild, nodes);
        nodes = _DeepPostOrder(node.RightChild, nodes);
        nodes.add(node);

        return nodes;
    }

    private ArrayList<BSTNode> _DeepPreOrder(BSTNode node, ArrayList<BSTNode> nodes) {
        if (node == null) {
            return nodes;
        }

        nodes.add(node);
        nodes = _DeepPreOrder(node.LeftChild, nodes);
        nodes = _DeepPreOrder(node.RightChild, nodes);

        return nodes;
    }

    public ArrayList<BSTNode> WideAllNodes() {
        if (Root == null) {
            return null;
        }

        ArrayList<BSTNode> nodes = new ArrayList<>();
        nodes.add(Root);

        return _WideAllNodes(nodes);
    }

    private ArrayList<BSTNode> _WideAllNodes(ArrayList<BSTNode> nodes) {
        boolean isLevelEmpty = true;
        ArrayList<BSTNode> nextLevelNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            BSTNode leftChild = nodes.get(i).LeftChild;
            BSTNode rightChild = nodes.get(i).RightChild;
            isLevelEmpty = isLevelEmpty && leftChild == null && rightChild == null;

            if (leftChild != null) {
                nextLevelNodes.add(leftChild);
            }

            if (rightChild != null) {
                nextLevelNodes.add(rightChild);
            }
        }

        if (isLevelEmpty) {
            return nodes;
        }

        nodes.addAll(_WideAllNodes(nextLevelNodes));
        return nodes;
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
        BSTFind<T> deletingNode = FindNodeByKey(key);

        if (deletingNode.Node == null || !deletingNode.NodeHasKey) {
            return false;
        }

        boolean isLeaf = deletingNode.Node.LeftChild == null && deletingNode.Node.RightChild == null;
        boolean isRoot = deletingNode.Node.NodeKey == Root.NodeKey;
        if (isLeaf) {
            if (isRoot) {
                Root = null;
            } else if (deletingNode.Node.NodeKey < deletingNode.Node.Parent.NodeKey) {
                deletingNode.Node.Parent.LeftChild = null;
            } else {
                deletingNode.Node.Parent.RightChild = null;
            }

            return true;
        }

        BSTNode<T> childForReplace = deletingNode.Node.RightChild == null ? deletingNode.Node.LeftChild : FinMinMax(deletingNode.Node.RightChild, false);

        if (deletingNode.Node.RightChild == null) {
            childForReplace.Parent = deletingNode.Node.Parent;
        } else if (childForReplace.RightChild == null) {
            if (childForReplace.NodeKey != deletingNode.Node.RightChild.NodeKey) {
                childForReplace.Parent.LeftChild = null;
                childForReplace.RightChild = deletingNode.Node.RightChild;
            }
            childForReplace.Parent = deletingNode.Node.Parent;
            childForReplace.LeftChild = deletingNode.Node.LeftChild;
        } else {
            if (childForReplace.NodeKey != deletingNode.Node.RightChild.NodeKey) {
                childForReplace.RightChild.Parent = childForReplace.Parent;
                childForReplace.Parent.LeftChild = childForReplace.RightChild;
                childForReplace.RightChild = deletingNode.Node.RightChild;
            }
            childForReplace.Parent = deletingNode.Node.Parent;
            childForReplace.LeftChild = deletingNode.Node.LeftChild;
        }

        if (isRoot) {
            Root = childForReplace;
        } else if (deletingNode.Node.NodeKey < deletingNode.Node.Parent.NodeKey) {
            deletingNode.Node.Parent.LeftChild = childForReplace;
        } else {
            deletingNode.Node.Parent.RightChild = childForReplace;
        }

        return true;
    }

    public int Count() {
        if (Root == null) {
            return 0;
        }

        return _getNodesCount(Root, 0);
    }

    private int _getNodesCount(BSTNode<T> node, int count) {
        if (node.Parent == null) {
            count++;
        }

        if (node.LeftChild == null && node.RightChild == null) {
            return count;
        }

        if (node.LeftChild != null) {
            count++;
            count = _getNodesCount(node.LeftChild, count);
        }

        if (node.RightChild != null) {
            count++;
            count = _getNodesCount(node.RightChild, count);
        }

        return count;
    }

    public ArrayList<BSTNode> InvertTree() {
        if (Root == null) {
            return null;
        }

        ArrayList<BSTNode> nodes = new ArrayList<>();
        nodes.add(Root);

        return _InvertTree(nodes);
    }

    private ArrayList<BSTNode> _InvertTree(ArrayList<BSTNode> nodes) {
        boolean isLevelEmpty = true;
        ArrayList<BSTNode> nextLevelNodes = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            BSTNode rightChild = nodes.get(i).RightChild;
            BSTNode leftChild = nodes.get(i).LeftChild;

            if (rightChild != null) {
                nextLevelNodes.add(rightChild);
            }

            if (leftChild != null) {
                nextLevelNodes.add(leftChild);
            }

            isLevelEmpty = isLevelEmpty && leftChild == null && rightChild == null;
        }

        if (isLevelEmpty) {
            return nodes;
        }

        return _addAll(nodes, _InvertTree(nextLevelNodes));
    }

    private ArrayList<BSTNode> _addAll(ArrayList<BSTNode> nodes, ArrayList<BSTNode> othersNodes) {
        nodes.addAll(othersNodes);
        return nodes;
    }
}
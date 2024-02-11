package balanced_bst_2;

import java.util.*;

class BSTNode {
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int Level;

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
        Level = parent == null ? 0 : (parent.Level + 1);
    }
}

public class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);

        Root = _MakeNode(null, a);
    }

    private BSTNode _MakeNode(BSTNode parent, int[] arrayRange) {
        if (arrayRange.length == 0) {
            return null;
        }

        int nodeKeyIndex = arrayRange.length / 2;
        int nodeKey = arrayRange[nodeKeyIndex];

        BSTNode node = new BSTNode(nodeKey, parent);

        int[] leftNodes = Arrays.copyOfRange(arrayRange, 0, nodeKeyIndex);
        node.LeftChild = _MakeNode(node, leftNodes);

        int[] rightNodes = Arrays.copyOfRange(arrayRange, Math.min(nodeKeyIndex + 1, arrayRange.length), arrayRange.length);
        node.RightChild = _MakeNode(node, rightNodes);

        return node;
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) {
            return true;
        }

        return _IsBalanced(root_node);
    }

    private boolean _IsBalanced(BSTNode node) {
        if (node == null) {
            return true;
        }

        int leftHeight = _getTreeHeight(node.LeftChild);
        int rightHeight = _getTreeHeight(node.RightChild);

        leftHeight = Math.max(leftHeight, node.Level);
        rightHeight = Math.max(rightHeight, node.Level);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return _IsBalanced(node.LeftChild) && _IsBalanced(node.RightChild);
    }

    public int _getTreeHeight(BSTNode node) {
        if (node == null) {
            return 0;
        }

        int height = node.Level;

        if (node.LeftChild == null && node.RightChild == null) {
            return height;
        }

        if(node.LeftChild != null) {
            height = Math.max(height, _getTreeHeight(node.LeftChild));
        }

        if(node.RightChild != null) {
            height = Math.max(height, _getTreeHeight(node.RightChild));
        }

        return height;
    }
}
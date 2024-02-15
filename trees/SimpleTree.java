package trees;

import java.util.*;

class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;
    public Integer Level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
        Level = null;
    }
}

public class SimpleTree<T> {
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public ArrayList<T> EvenTrees() {
        if (Count() % 2 > 0) {
            return null;
        }

        ArrayList<T> edges = new ArrayList<>();



        return edges;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (Root == null) {
            return;
        }

        NewChild.Parent = ParentNode == null ? Root : ParentNode;

        if (NewChild.Parent.Children == null) {
            NewChild.Parent.Children = new ArrayList<>();
        }
        NewChild.Parent.Children.add(NewChild);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete == null || NodeToDelete.Parent == null || NodeToDelete.Parent.Children == null) {
            return;
        }

        int deletedIndex = NodeToDelete.Parent.Children.indexOf(NodeToDelete);

        if (deletedIndex >= 0) {
            NodeToDelete.Parent.Children.remove(deletedIndex);

            if (NodeToDelete.Parent.Children.isEmpty()) {
                NodeToDelete.Parent.Children = null;
            }
        }
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        List<SimpleTreeNode<T>> nodesList = new ArrayList<>();

        nodesList.add(Root);
        _addChildrenToList(nodesList, Root);

        return nodesList;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        List<SimpleTreeNode<T>> nodesList = new ArrayList<>();

        _findNodesByValue(val, Root, nodesList);

        return nodesList.size() > 0 ? nodesList : null;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        if (OriginalNode.Parent == null) {
            return;
        }

        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        return _getNodesCount(Root, 0);
    }

    public int LeafCount() {
        return _getLeafsCount(Root, 0);
    }

    private int _getLeafsCount(SimpleTreeNode<T> node, int count) {
        if (node.Children == null) {
            return count + 1;
        }

        for (int i = 0; i < node.Children.size(); i++) {
            count = _getLeafsCount(node.Children.get(i), count);
        }

        return count;
    }

    private int _getNodesCount(SimpleTreeNode<T> node, int count) {
        if (node.Parent == null) {
            count++;
        }

        if (node.Children == null) {
            return count;
        }
        count += node.Children.size();

        for (int i = 0; i < node.Children.size(); i++) {
            count = _getNodesCount(node.Children.get(i), count);
        }

        return count;
    }

    private void _findNodesByValue(T val, SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodesList) {
        if (node.NodeValue == val) {
            nodesList.add(node);
        }

        if (node.Children == null) {
            return;
        }

        for (int i = 0; i < node.Children.size(); i++) {
            _findNodesByValue(val, node.Children.get(i), nodesList);
        }
    }

    private void _addChildrenToList(List<SimpleTreeNode<T>> nodesList, SimpleTreeNode<T> node) {
        if (node.Children == null) {
            return;
        }

        nodesList.addAll(node.Children);

        for (int i = 0; i < node.Children.size(); i++) {
            _addChildrenToList(nodesList, node.Children.get(i));
        }
    }
}

package trees;

import java.util.*;

public class SimpleTree<T> {
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
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
            NodeToDelete.Children = null;
        }
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        List<SimpleTreeNode<T>> nodesList = new ArrayList<>();


        return nodesList;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        return null;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
    }

    public int Count() {
        // количество всех узлов в дереве
        return 0;
    }

    public int LeafCount() {
        // количество листьев в дереве
        return 0;
    }
}

class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

package binary_search_tree;

public class BSTUtils {
    public void InvertTree(BST<Integer> tree) {
        if (tree.Root == null) {
            return;
        }

        _InvertNode(tree.Root);
    }

    private void  _InvertNode(BSTNode<Integer> node) {
        if (node == null) {
            return;
        }

        BSTNode<Integer> transferNode = node.LeftChild;
        node.LeftChild = node.RightChild;
        node.RightChild = transferNode;

        _InvertNode(node.LeftChild);
        _InvertNode(node.RightChild);
    }
}

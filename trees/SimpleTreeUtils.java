package trees;

public class SimpleTreeUtils<T> {
    public void addLevelToTreeNodes(SimpleTree<T> tree) {
        _addLevelToNodes(tree.Root);
    }

    private void _addLevelToNodes(SimpleTreeNode<T> node) {
        node.Level = node.Parent == null ? 0 : node.Parent.Level + 1;

        if (node.Children == null) {
            return;
        }

        for (int i = 0; i < node.Children.size(); i++) {
            _addLevelToNodes(node.Children.get(i));
        }
    }
}

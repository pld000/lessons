package trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeUtilsTest {

    @Test
    void addLevelToTreeNodes() {
        SimpleTreeUtils<Integer> utils = new SimpleTreeUtils<>();
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9999, null));

        int length = 20;
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(tree.Root, simpleNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(j, null);
                tree.AddChild(simpleNode, simpleChildNode1);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(k, null);
                    tree.AddChild(simpleChildNode1, simpleChildNode2);
                }
            }
        }

        utils.addLevelToTreeNodes(tree);

        Assertions.assertEquals(0, tree.Root.Level, "Failed root level");

        Assertions.assertEquals(1, tree.Root.Children.get(0).Level, "Failed root level");
        Assertions.assertEquals(2, tree.Root.Children.get(0).Children.get(3).Level, "Failed root level");
        Assertions.assertEquals(3, tree.Root.Children.get(0).Children.get(3).Children.get(3).Level, "Failed root level");
    }
}
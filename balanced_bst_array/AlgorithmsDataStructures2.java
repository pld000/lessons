package balanced_bst_array;

import java.util.*;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        if (a == null) {
            return null;
        }

        if (a.length == 0) {
            return new int[0];
        }

        Arrays.sort(a);
        int[] bst = new int[a.length];
        _GenerateBBSTArray(bst, a, 0);
        return bst;
    }

    private static void _GenerateBBSTArray(int[] bst, int[] nodeKeys, int nodeIndex) {
        if (nodeKeys.length == 0) {
            return;
        }

        int rootIndex = nodeKeys.length / 2;
        bst[nodeIndex] = nodeKeys[rootIndex];

        if (rootIndex == 0) {
            return;
        }

        int toIndex = Math.min(rootIndex + 1, nodeKeys.length);
        int[] leftNodes = Arrays.copyOfRange(nodeKeys, 0, rootIndex - 1);
        int[] rightNodes = Arrays.copyOfRange(nodeKeys, rootIndex, toIndex);

        _GenerateBBSTArray(bst, leftNodes, 2 * nodeIndex + 1);
        _GenerateBBSTArray(bst, rightNodes, 2 * nodeIndex + 2);
    }
}
package array_bst;

class aBST {
    public Integer Tree[];
    private int _depth;

    public aBST(int depth) {
        _depth = depth;
        int tree_size = _calcTreeSize(depth);
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    private int _calcTreeSize(int depth) {
        return depth <= 0 ? 1 : (int) (Math.pow(2, depth) + _calcTreeSize(depth - 1));
    }

    public Integer FindKeyIndex(int key) {
        return _FindKeyIndex(key, 0, 0);
    }

    private Integer _FindKeyIndex(int searchingKey, int nodeIndex, int currentDepth) {
        if (currentDepth > _depth) {
            return null;
        }

        if (Tree[nodeIndex] == null) {
            return -1 * nodeIndex;
        }

        if (searchingKey == Tree[nodeIndex]) {
            return nodeIndex;
        }

        int nextIndex = 2 * nodeIndex + (searchingKey < Tree[nodeIndex] ? 1 : 2);

        return _FindKeyIndex(searchingKey, nextIndex, currentDepth + 1);
    }

    public int AddKey(int key) {
        Integer index = FindKeyIndex(key);

        if (index == null) {
            return -1;
        }

        index = index < 0 ? -1 * index : index;

        if (Tree[index] == null) {
            Tree[index] = key;
        }

        return index;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }

}

package binary_search_tree;

import java.io.*;
import java.util.*;


class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

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
        // добавляем ключ-значение в дерево
        return false; // если ключ уже есть
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        return null;
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        return false; // если узел не найден
    }

    public int Count() {
        return 0; // количество узлов в дереве
    }

}
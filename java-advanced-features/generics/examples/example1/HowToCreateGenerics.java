package generics.examples.example1;

import javax.swing.text.AbstractDocument.LeafElement;

/**
 * we have defined a interface which represents a node in a binary tree and that
 * node contains a value of some arbitrary type <T>
 */
interface TreeNode<T> {
    T getValue();

    TreeNode<T> getLeft();

    TreeNode<T> getRight();

}

class InnerNode<T> implements TreeNode<T> {
    private T value;
    private final TreeNode<T> left, right;

    public InnerNode(T value, TreeNode<T> left, TreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public TreeNode<T> getLeft() {
        return left;
    }

    @Override
    public TreeNode<T> getRight() {
        return right;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{[%s],{%s,%s}}", value, left, right);
    }
}

class LeafNode<T> implements TreeNode<T> {
    private final T value;

    public LeafNode(T value) {
        this.value = value;
    }

    @Override
    public TreeNode<T> getLeft() {
        return null;
    }

    @Override
    public TreeNode<T> getRight() {
        return null;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("[%s]", value);
    }
}

public class HowToCreateGenerics {
    public static void main(String[] args) {
        LeafNode three = new LeafNode<Integer>(3); // <Integer> is a type argument here to fill in the Type parameter
                                                   // <T>
        LeafNode five = new LeafNode<>(5);

        InnerNode tree = new InnerNode(2, three, five);
        System.out.println(tree);
    }
}

package generics.examples.example3;

interface TreeNode<T extends Comparable<T>> {
    T getValue();

    TreeNode<T> getLeft();

    TreeNode<T> getRight();

}

class LeafNode<T extends Comparable<T>> implements TreeNode<T> {

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
}

class MaxValueInnerNode<T extends Comparable<T>> implements TreeNode<T> {

    private final TreeNode<T> left, right;

    public MaxValueInnerNode(TreeNode<T> left, TreeNode<T> right) {
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
        T leftValue = left.getValue();
        T rightValue = right.getValue();
        int result = leftValue.compareTo(rightValue);
        return result >= 0 ? leftValue : rightValue;
    }

}

public class BoundedTypes {
    public static void main(String[] args) {
        var tree = new MaxValueInnerNode<>(new LeafNode<>(2),
                new MaxValueInnerNode<>(new LeafNode<>(3), new LeafNode<>(5)));
        System.out.println(tree.getValue());
    }
}

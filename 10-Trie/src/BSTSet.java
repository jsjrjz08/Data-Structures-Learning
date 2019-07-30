public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> tree;

    public BSTSet() {
        tree = new BST<>();
    }

    @Override
    public String toString() {
        tree.inOrder();
        return "";
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    @Override
    public void add(E e) {
        tree.add(e);
    }

    @Override
    public void remove(E e) {
        tree.remove(e);
    }

    public static void main(String[] args) {
        BSTSet<Integer> set = new BSTSet<>();

        System.out.println(set.getSize());
        System.out.println(set.isEmpty());

        set.add(50);
        set.add(30);
        set.add(60);
        set.add(80);
        set.add(40);
        set.add(70);
        set.add(20);

        set.add(55);
        set.add(52);
        set.add(57);
        set.add(88);
        set.add(86);
        set.add(90);

        set.add(30);
        System.out.println(set);
        System.out.println(set.getSize());
        System.out.println(set.isEmpty());
        set.remove(-1);
        System.out.println(set);
        set.remove(30);
        System.out.println(set);
        System.out.println(set.contains(34));
        System.out.println(set.contains(30));
        System.out.println(set);
    }
}

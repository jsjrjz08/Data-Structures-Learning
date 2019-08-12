public class AVLTreeSet<E extends Comparable<E>> implements Set<E> {
    private AVLTreeMap<E,Object> avl;

    //构造函数
    public AVLTreeSet() {
        avl = new AVLTreeMap<>();
    }

    @Override
    public String toString() {
        return avl.toString();
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public void add(E e) {
        avl.add(e,null);//Value设置为Null
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    public static void main(String[] args) {
        AVLTreeSet<Integer> set = new AVLTreeSet<>();

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

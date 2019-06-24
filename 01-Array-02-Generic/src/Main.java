public class Main {
    public static void main(String[] args) {

        Array<Integer> arr = new Array<>(15);
        System.out.println(arr.isEmpty());
        System.out.println(arr.getSize());
        System.out.println(arr.getCapacity());

        for (int i=0; i<11;i++) {
            arr.addLast(i);
        }
        System.out.println(arr.isEmpty());
        System.out.println(arr.getSize());
        System.out.println(arr.getCapacity());
        System.out.println("arr1 = " + arr );

        arr.addFirst(-1);
        System.out.println("arr2 = " + arr );

        arr.add(1,8);
        System.out.println("arr3 = " + arr );

        System.out.println(arr.get(11));
        System.out.println(arr.find(11));
        System.out.println(arr.find(8));
        System.out.println(arr.findAll(88));
        System.out.println("findAll="+arr.findAll(8));
        System.out.println(arr.contains(8));
        System.out.println(arr.contains(88));


        arr.set(11,90);
        System.out.println("arr4 = " + arr );

        System.out.println(arr.remove(2));
        System.out.println("arr5 = " + arr );

        System.out.println(arr.removeFirst());
        System.out.println("arr6 = " + arr );

        System.out.println(arr.removeLast());
        System.out.println("arr7 = " + arr );

        arr.removeAllElement(8);
        System.out.println("arr8 = " + arr );

        arr.removeAllElement(33);
        System.out.println("arr9 = " + arr );
    }
}

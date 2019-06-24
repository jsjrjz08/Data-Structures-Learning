public class Main {
    public static void main(String[] args) {

        Array<Integer> arr = new Array<>(20);
        System.out.println(arr.isEmpty());
        System.out.println(arr.getSize());
        System.out.println(arr.getCapacity());

        for (int i=0; i<10;i++) {
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

        //模拟容量震荡，这种情况比较浪费时间、消耗性能
        arr.addLast(8);
        arr.addLast(9);
        System.out.println("arr100 = " + arr );//arr100 = [size=9, capacity=10, data=(1, 2, 3, 4, 5, 6, 7, 8, 9)]
        arr.addLast(10);
        arr.addLast(11);
        System.out.println("arr10 = " + arr );//arr10 = [size=11, capacity=20, data=(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)]
        arr.removeLast();
        arr.removeLast();
        System.out.println("arr11 = " + arr );//arr11 = [size=9, capacity=10, data=(1, 2, 3, 4, 5, 6, 7, 8, 9)]
        arr.addLast(12);
        arr.addLast(13);
        System.out.println("arr12 = " + arr );//arr12 = [size=11, capacity=20, data=(1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 13)]

        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        System.out.println("arr13 = " + arr );//arr13 = [size=4, capacity=10, data=(1, 2, 3, 4)]

    }
}

public class Main {
    public static void main(String[] args) {

        ArrayStack<Integer> stk = new ArrayStack<>();
        System.out.println(stk.isEmpty());
        for(int i=0; i<10; i++) {
            stk.push(i);
        }
        System.out.println(stk);
        System.out.println(stk.isEmpty());

        System.out.println(stk.pop());
        System.out.println(stk);

        System.out.println(stk.peek());

        System.out.println("size="+stk.getSize());
        System.out.println("capacity="+stk.getCapacity());

        stk.push(10);
        System.out.println(stk);
        stk.push(11);//触发resize
        System.out.println(stk);

    }
}

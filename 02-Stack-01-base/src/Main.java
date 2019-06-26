import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //两种栈的实现的效率对比
        //1) 数组：需要动态分配连续内存空间，有时浪费内存
        //2) 链表：需要创建节点对象，然后挂到链表上
        //两者实现的栈复杂度相当，所以，花费时间是相当的！
        //数据量大的时候，链表栈入栈时间会比数组栈时间长，因为创建对象更花费时间!

        int opCount = 1200000;
        System.out.println(" *********************** 数组实现栈 *********************** ");
        testStack(new ArrayStack(),opCount);
        System.out.println(" *********************** 链表实现栈 *********************** ");
        testStack(new LinkedListStack<>(),opCount);
    }

    public static void testStack(Stack<Integer> stack,int opCount) {
        long startTime = System.nanoTime();
        //入栈
        Random rand = new Random();
        for(int i=0;i<opCount; i++) {
            stack.push(rand.nextInt(Integer.MAX_VALUE));
        }

        long middleTime = System.nanoTime();

        //出栈
        for(int i=0;i<opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        System.out.println("opCount="+opCount+",push time ="+(middleTime-startTime)/1000000000.0+"s ,and " +
                "pop time ="+(endTime-middleTime)/1000000000.0+"s.");
    }
}

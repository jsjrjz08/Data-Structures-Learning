import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        String fileName = "D:\\git_repo\\Data-Structures-Learning\\06-Set-base\\src\\pride-and-prejudice.txt";
        String fileName = "D:\\git_repo\\Data-Structures-Learning\\06-Set\\src\\a-tale-of-two-cities.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName,words)) {

            testSet(new BSTSet<>(), words,true);
            testSet(new AVLTreeSet<>(), words,true);
            testSet(new LinkedListSet<>(), words,true);
        }

        //链表本身是可以添加重复元素的，但是Set是不允许重复元素存在的，所以，使用链表实现Set的添加操作时，需要先做一下contains判断，
        //这样一来，就增加了时间开销
        //我们自己实现的BST本事就不允许存储重复元素，所以，使用BST实现Set的添加操作时，不做判断，直接存储
        //可见，不同的底层实现，会有不同的时间开销

        //对于满二叉树而言，添加/删除/查找 时间复杂度为O(log n)
        //对于链表而言，添加/删除/查找 时间复杂度为O(n)
        //n越大，BST比链表性能越优越

        //AVL树比链表性能还好

    }
    private static void testSet(Set<String> set, ArrayList<String> words,boolean isOrdered) {

        if(isOrdered) {
            System.out.println("-------" + set.getClass().getName() + "----排序-----");
            Collections.sort(words);
        } else {
            System.out.println("-------" + set.getClass().getName() + "----不排序-----");
        }

        long startTime = System.nanoTime();

        for(int i=0;i<words.size();i++) {
            set.add(words.get(i));
        }

        for(String word:words) {
            set.contains(word);
        }

        long endTime = System.nanoTime();
        System.out.println("total size is "+words.size()+",and different "+set.getSize()+" words cost "+(endTime-startTime)/1000000000.0+" s.");

    }
}

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        String fileName = "D:\\git_repo\\Data-Structures-Learning\\13-Tree-RedBlackTree\\src\\pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName,words)) {
            testMap(new BSTMap<>(),words,false);

            AVLTree<String,Integer> avl = new AVLTree<>();
            testMap(avl,words,false);
            System.out.println("isBalanced="+avl.isBalanced());
            System.out.println("isBST="+avl.isBST());
            testMap(new RBTree<>(),words,false);

//            testMap(new BSTMap<>(),words,true);
            testMap(new AVLTree<>(),words,true);
            testMap(new RBTree<>(),words,true);
        }


        //数据直接存入AVL和BST，AVL的读写性能更优
        //数据排序后存入AVL和BST，AVL的读写性能更优、更明显

    }
    //词频统计
    private static void testMap(Map<String,Integer> map, ArrayList<String> words,boolean isOrdered) {


        if(isOrdered) {
            Collections.sort(words);
            System.out.println("--------------"+map.getClass().getName()+"----排序----------");
        } else {
            System.out.println("--------------"+map.getClass().getName()+"----不排序----------");
        }


        long startTime = System.nanoTime();

        for(String word : words) {
            if(map.contains(word)) {
                map.set(word,map.get(word)+1);
            } else {
                map.add(word, 1);
            }
        }

        long midTime = System.nanoTime();
        for(String word : words) {
            map.contains(word);
        }

        long endTime = System.nanoTime();
        System.out.println("total size is "+words.size()+", and different "+map.getSize()+" words,total costs "+(endTime-startTime)/1000000000.0+" s.");
        System.out.print("add words cost "+(midTime-startTime)/1000000000.0+" s,");
        System.out.println("find words cost "+(endTime-midTime)/1000000000.0+" s.");
        System.out.println("frequency of pride is "+ map.get("pride"));
        System.out.println("frequency of prejudice is "+ map.get("prejudice"));

    }
}

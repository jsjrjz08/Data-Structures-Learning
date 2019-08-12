import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "D:\\git_repo\\Data-Structures-Learning\\12-Tree-AVLTree\\src\\pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName,words)) {
//            testMap(new BSTMap<>(),words);

            AVLTree<String,Integer> avl = new AVLTree<>();
            testMap(avl,words);
            System.out.println("isBalanced="+avl.isBalanced());
            System.out.println("isBST="+avl.isBST());
        }


        //对于满二叉树而言，添加/删除/查找/改 时间复杂度为O(log n)
        //对于链表而言，添加/删除/查找/改 时间复杂度为O(n)
        //n越大，BST比链表性能越优越

        //二叉树最坏情况下(有序插入元素)，会蜕变成链表，时间复杂度达到链表级别 O(n)


    }
    //词频统计
    private static void testMap(Map<String,Integer> map, ArrayList<String> words) {
        System.out.println("--------------"+map.getClass().getName()+"--------------");

        long startTime = System.nanoTime();

        for(String word : words) {
            if(map.contains(word)) {
                map.set(word,map.get(word)+1);
            } else {
                map.add(word, 1);
            }
        }

        long endTime = System.nanoTime();
        System.out.println("total size is "+words.size()+", and different "+map.getSize()+" words, cost "+(endTime-startTime)/1000000000.0+" s.");
        System.out.println("frequency of pride is "+ map.get("pride"));
        System.out.println("frequency of prejudice is "+ map.get("prejudice"));

    }
}

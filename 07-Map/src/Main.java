import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "D:\\git_repo\\Data-Structures-Learning\\06-Set\\src\\pride-and-prejudice.txt";
//        String fileName = "D:\\git_repo\\Data-Structures-Learning\\06-Set\\src\\a-tale-of-two-cities.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName,words)) {

            testMap(new BSTMap<>(), words);
            testMap(new AVLTreeMap<>(), words);
//          testMap(new LinkedListMap<>(),words);
        }

        //链表本身是可以添加重复元素的，但是Map是不允许重复key存在的，所以，使用链表实现Map的添加操作时，需要先做一下contains判断，
        //这样一来，就增加了时间开销
        //我们自己实现的BST本身就不允许存储重复元素，所以，使用BST实现Map的添加操作时，不做判断，直接存储
        //可见，不同的底层实现，会有不同的时间开销

        //对于满二叉树而言，添加/删除/查找/改 时间复杂度为O(log n)
        //对于链表而言，添加/删除/查找/改 时间复杂度为O(n)
        //n越大，BST比链表性能越优越

        //二叉树最坏情况下(有序插入元素)，会蜕变成链表，时间复杂度达到链表级别 O(n)


    }
    //词频统计
    private static void testMap(Map<String,Integer> map, ArrayList<String> words) {

        System.out.println("-------"+map.getClass().getName()+"---------");
        long startTime = System.nanoTime();

        for(String word : words) {
            if(map.contains(word)) {
                map.set(word,map.get(word)+1);
            } else {
                map.add(word, 1);
            }
        }
        for(String word:words) {
            map.contains(word);
        }

        long endTime = System.nanoTime();
        System.out.println("total size is "+words.size()+",and different "+map.getSize()+" words cost "+(endTime-startTime)/1000000000.0+" s.");
        System.out.println("frequency of pride is "+ map.get("pride"));
        System.out.println("frequency of prejudice is "+ map.get("prejudice"));

    }
}

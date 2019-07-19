import java.util.*;
import java.util.PriorityQueue;

public class Solution_692_优化 {

    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for(String s : words) {
            map.put(s,map.getOrDefault(s,0) + 1);
        }

        int size = map.size();
        PriorityQueue<String> pq = new PriorityQueue<>(size, new Comparator<String>() {
            @Override
            public int compare(String key1, String key2) {//定义高优先级
                if(map.get(key1) == map.get(key2)) {
//                    return -key1.compareTo(key2);//逆序，越小越高
                    return key2.compareTo(key1);//逆序，越小越高
                }
                return map.get(key1) - map.get(key2);//正序，越大越高
            }
        });

        for(String key : map.keySet()) {
            if(pq.size() < k) {
                pq.add(key);
            } else {
                if(pq.comparator().compare(key,pq.peek()) > 0) {
                    pq.remove();
                    pq.add(key);
                }
            }
        }

        String[] res = new String[k];
        for(int i=k-1;i>=0;i--) {
            res[i] = pq.remove();
        }

        return Arrays.asList(res);
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k=2;
//        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
//        int k=4;
        List<String> res = new Solution_692_优化().topKFrequent(words,k);
        for(String s : res) {
            System.out.print(s+",");
        }
        System.out.println();
    }
}

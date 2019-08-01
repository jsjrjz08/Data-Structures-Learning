import java.util.*;
import java.util.PriorityQueue;

public class Solution_692 {

    class WordFreq implements Comparable<WordFreq> {
        public String word;
        public int freq;

        public WordFreq(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public String toString() {
            return String.format(word+":"+freq);
        }

        @Override
        public int compareTo(WordFreq other) {
            if(freq == other.freq) {
                return -word.compareTo(other.word);//逆序
            }
            return freq - other.freq;//正序
        }
    }

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
        PriorityQueue<WordFreq> pq = new PriorityQueue<>(size);

        for(String key : map.keySet()) {
            WordFreq wf = new WordFreq(key,map.get(key));
            if(pq.size() < k) {
                pq.add(wf);
            } else {
                if(wf.compareTo(pq.peek()) > 0) {
                    pq.remove();
                    pq.add(wf);
                }
            }
        }

        String[] res = new String[k];
        for(int i=k-1;i>=0;i--) {
            res[i] = pq.remove().word;
        }
//        while(!pq.isEmpty()) {//顺序错了
//            res.add(pq.remove().word);
//        }

        return Arrays.asList(res);
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k=2;
//        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
//        int k=4;
        List<String> res = new Solution_692().topKFrequent(words,k);
        for(String s : res) {
            System.out.print(s+",");
        }
        System.out.println();
    }
}

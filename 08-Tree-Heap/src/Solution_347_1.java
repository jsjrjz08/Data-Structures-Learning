import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//使用自定义的MinHeap
public class Solution_347_1 {

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 注：
     * 1.你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 2.你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小
     * --------------------------------------------------------------------
     * 1.统计每个元素出现的频率，顺便元素去重，得到含有N个key的map，将map中的元素放到Freq对象中
     * 2.选出频率 top K 的元素。建立含有前k个Freq的MinHeap，由于k<=N，后面遍历到的Freq的值如果比MinMap中的最小值还大，就替换MinHeap中的最小值 -------此处的最大or最小我们是可以自己定义的 compareTo
     * 3.MinHeap中的元素就是k个在map中频率最高的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for(int num:nums) {
            if(map.containsKey(num)) {
                map.put(num,map.get(num)+1);
            } else {
                map.put(num,1);
            }
        }

        int size = map.keySet().size();
        MinHeap<Freq> heap = new MinHeap<>(size);
        for(int key : map.keySet()) {
            int value = map.get(key);
//            System.out.println(heap.getSize());
            if(heap.getSize() < k) {
                heap.add(new Freq(key, value));
            } else {
//                System.out.println(heap);
                if(value > heap.findMin().freq) {
                    heap.replace(new Freq(key,value));
                }
            }
        }

        List<Integer> res = new LinkedList<>();
        while(!heap.isEmpty()) {
            res.add(heap.extractMin().key);
        }

        return res;
    }

    private class Freq implements Comparable<Freq> {
        public int key;
        public int freq;

        public Freq(int key,int freq) {
            this.key = key;
            this.freq = freq;
        }
        @Override
        public int compareTo(Freq o) {
            return this.freq - o.freq;
//            if(this.freq > o.freq) {
//                return 1;
//            } else if(this.freq < o.freq) {
//                return -1;
//            } else {
//                return 0;
//            }

        }

        @Override
        public String toString() {
            return "("+key+","+freq+")";
        }
    }

    public static void main(String[] args) {
        int[] nums = {6,0,1,4,9,7,-3,1,-4,-8,4,-7,-3,3,2,-3,9,5,-4,0};
        int k =6;
        //[-3,-4,4,0,1,9]
        List<Integer> res = new Solution_347_1().topKFrequent(nums,k);
        for(int x:res) {
            System.out.print(x+",");
        }
    }

}

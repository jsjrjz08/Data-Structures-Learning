import java.util.*;
import java.util.PriorityQueue;

//使用Java 内置的PriorityQueue ------小顶堆
public class Solution_347_3_优化2 {//------利用内部类，省掉Freq对象的定义

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 注：
     * 1.你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 2.你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小
     * --------------------------------------------------------------------
     * 1.统计每个元素出现的频率，顺便元素去重，得到含有N个key的map，将map中的元素放到Freq对象中
     * 2.选出频率 top K 的元素。建立含有前k个Freq的PriorityQueue(min)，由于k<=N，后面遍历到的Freq的值如果比PriorityQueue(min)中的最小值还大，就替换PriorityQueue(min)中的最小值 -------此处的最大or最小我们是可以自己定义的 compareTo
     * 3.PriorityQueue(min)中的元素就是k个在map中频率最高的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for(int num:nums) {
            map.put(num, map.getOrDefault(num,0) +1 );
        }

        int size = map.keySet().size();
        //匿名内部类--只使用一次
        //优先队列中方的是map中的key
        PriorityQueue<Integer> pq = new PriorityQueue<>(size, new Comparator<Integer>() {
            //内部类可以访问外部类的各种变量，比如map
            @Override
            public int compare(Integer key1, Integer key2) {
                return map.get(key1) - map.get(key2);
            }
        });//PriorityQueue支持比较器

        for(int key : map.keySet()) {
            int value = map.get(key);
            if(pq.size() < k) {
                pq.add(key);
            } else {
                if( value > map.get(pq.peek()) ) {
                    pq.remove();
                    pq.add(key);
                }
            }
        }

        List<Integer> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.add(pq.remove());
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {6,0,1,4,9,7,-3,1,-4,-8,4,-7,-3,3,2,-3,9,5,-4,0};
        int k =6;
//        int[] nums = {2,1,1,1,1,3,3,4,4,4};
//        int k=2;
        //[-3,-4,4,0,1,9]
        List<Integer> res = new Solution_347_3_优化1().topKFrequent(nums,k);
        for(int x:res) {
            System.out.print(x+",");
        }
    }

}

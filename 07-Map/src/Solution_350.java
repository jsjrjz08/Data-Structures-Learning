import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 求两个数组的交集，并返回可以含有重复元素的交集
 */
public class Solution_350 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        //将nums1去重 --Map
        TreeMap<Integer,Integer> s1 = new TreeMap<>();
        for(int num : nums1) {
            if(s1.containsKey(num)) {
                s1.put(num,s1.get(num) + 1);
            } else {
                s1.put(num,1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();//存储交集元素
        //看nums2中的元素是否存在于nums1中，如果存在，就是交集中的元素
        for(int num : nums2) {
            if(s1.containsKey(num)) {
                list.add(num);
                s1.put(num, s1.get(num)-1);
                //边界值判断
                if(s1.get(num) == 0) {
                    s1.remove(num);
                }
            }
        }

        //结果类型转换
        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            result[i] = list.get(i);
            System.out.println(result[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5,9,4,3};
        int[] nums2 = {9,4,9,8,4};

        intersect(nums1, nums2);
//        System.out.println();
    }
}

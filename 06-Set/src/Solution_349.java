import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 求两个数组的交集，并返回没有重复元素的交集
 */
public class Solution_349 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        //将nums1去重 --集合
        TreeSet<Integer> s1 = new TreeSet<>();
        for(int i=0;i<nums1.length; i++) {
            s1.add(nums1[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();//存储交集元素
        //看nums2中的元素是否存在于nums1中，如果存在，就是交集中的元素
        for(int i=0; i< nums2.length; i++) {
            int cur = nums2[i];
            if(s1.contains(cur)) {
                list.add(cur);
                s1.remove(cur);
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
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {8,9,7,6,3,2,2};

        intersection(nums1, nums2);
//        System.out.println();
    }
}

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * -----------
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 */
class NumArray_303_1 {

    /**
     * 构造函数的时候,对数组进行预处理。
     */

    //sum[x]记录前x个元素之和
    private int[] sum;
    public NumArray_303_1(int[] nums) {
        int len = nums.length;
        if(len > 0) {
            //初始化sum
            sum = new int[len];
            //给sum赋值
            sum[0] = nums[0];
            for(int i=1; i<len; i++) {
                sum[i] = sum[i-1] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if(i>j) {
            int tmp = i;
            i=j;
            j=tmp;
        }
        if(sum == null) {
            throw new IllegalArgumentException("error");
        }
        return i==0? sum[j]:sum[j] - sum[i-1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
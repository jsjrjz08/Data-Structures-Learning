/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 * ------------
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 */

class NumArray_307 {

    private SegmentTree<Integer> segTree;
    public NumArray_307(int[] nums) {
        int len = nums.length;
        if(len > 0) {
            Integer[] data = new Integer[len];
            //int[] 转换成 Integer[]
            for(int i=0; i<len; i++) {
                data[i] = nums[i];
            }
            //初始化线段树
            segTree = new SegmentTree<>(data,(a,b) -> a+b);
        }
    }

    public void update(int i, int val) {
        if(segTree == null) {
            throw new IllegalArgumentException("error");
        }
        if(i<0 || i>=segTree.getSize()) {
            throw new IllegalArgumentException("index is illegal");
        }
        segTree.update(i,val);

    }

    public int sumRange(int i, int j) {
        if(segTree == null) {
            throw new IllegalArgumentException("error");
        }
        if(i>j) {
            int tmp = i;
            i=j;
            j=tmp;
        }
        return segTree.query(i,j);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

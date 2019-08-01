class NumArray_303_2 {

    private SegmentTree<Integer> segTree;

    public NumArray_303_2(int[] nums) {
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

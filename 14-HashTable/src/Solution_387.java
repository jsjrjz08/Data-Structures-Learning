import java.util.Hashtable;

public class Solution_387 {

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1
     *      s = "leetcode" 返回 0.
     *      s = "loveleetcode", 返回 2.
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }

        int len = s.length();
        Hashtable<Character,Integer> ht = new Hashtable<>();
        for(int i=0;i<len;i++) {
            char ch = s.charAt(i);
            if(ht.containsKey(ch)) {
                ht.put(ch,ht.get(ch)+1);
            } else {
                ht.put(ch,1);
            }
        }

        for(int i=0;i<s.length();i++) {
            if(ht.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;

//        int[] freqs = new int[26];
//        for(int i=0;i<len;i++) {
//            freqs[s.charAt(i)-'a'] ++ ;
//        }
//
//        for(int i=0;i<len;i++) {
//            if(freqs[s.charAt(i)-'a'] == 1) {
//                return i;
//            }
//        }
//        return -1;
    }

}

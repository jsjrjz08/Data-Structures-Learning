import java.util.ArrayList;
import java.util.Vector;

/**
 * leecode 804
 * 唯一摩斯密码
 */
public class Solution {

    public static int uniqueMorseRepresentations(Vector<String> words) {
        //摩斯密码字母对照表
        String[] mores = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        //存储翻译后的单词序列
        Set<String> transWords = new BSTSet<>();

        String transWord = "";

        for(String word : words) {
            for(int i=0;i<word.length();i++) {
                transWord += mores[word.charAt(i)-'a'];
            }
            transWords.add(transWord);
            transWord = "";
        }
        return transWords.getSize();
    }

    public static void main(String[] args) {
        Vector<String> words = new Vector<>();//["gin", "zen", "gig", "msg"];
        words.add("gin");
        words.add("zen");
        words.add("gig");
        words.add("msg");
        System.out.println(words +" translates to mores code, it has "+uniqueMorseRepresentations(words)+" different mores code ");
    }
}

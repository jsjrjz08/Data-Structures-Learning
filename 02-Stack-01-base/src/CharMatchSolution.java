public class CharMatchSolution
{
    public static void main(String[] args) {

        String str1 = "[{(,)}]]";
        System.out.println("str1='"+str1+"',"+(new Solution(str1.toCharArray())).isMatch());
        String str2 = "[{(,)}]";
        System.out.println("str2='"+str2+"',"+(new Solution(str2.toCharArray())).isMatch());
        String str3 = "()[]{}";
        System.out.println("str3='"+str3+"',"+(new Solution(str3.toCharArray())).isMatch());
        String str4 = "(,)[,]{,}";
        System.out.println("str4='"+str4+"',"+(new Solution(str4.toCharArray())).isMatch());
    }
}

class Solution {
    char[] chars;

    Solution(char[] chars) {
        this.chars = chars;
    }

    boolean isMatch() {
        Stack<Character> stk = new ArrayStack<>();

        for(int i=0;i<chars.length;i++) {
            Character c = chars[i];
            if(c == '(' || c == '[' || c == '{') {
                stk.push(c);
//                System.out.println("push:"+stk);
            } else if(c == ')' || c == ']' || c == '}') {

                //还有等待匹配的，但栈里已经空了。此处判断很重要，否则，在一个空栈中弹出元素会发生异常
                if(stk.isEmpty()) {
                    return false;
                }

                char topElem = stk.pop();

                if(c==')' && topElem!='(') {
                    return false;
                }

                if(c==']' && topElem!='[') {
                    return false;
                }

                if(c=='}' && topElem!='{') {
                    return false;
                }

            }

        }

        return stk.isEmpty();
    }

}

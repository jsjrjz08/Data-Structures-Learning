import java.util.Hashtable;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestMain {
    public static void main(String[] args) {
        //整型占4字节=32bit(最高一位为符号位)
        int a = 42;
        int b = -42;
//        System.out.println("a:"+((Integer)a).hashCode());
//        System.out.println("b:"+((Integer)b).hashCode());
//        System.out.println("a:"+(((Integer)a).hashCode() & 0x7fffffff));
//        System.out.println("b:"+(((Integer)b).hashCode() & 0x7fffffff));

        //单精度浮点型占4字节=32bit(最高一位为符号位) 0x7fffffff 共31位
        float c = 3.14f;
        float d = 42.0f;
        float e = -42.0f;

//        System.out.println("c:"+((Float)c).hashCode());
//        System.out.println("d:"+((Float)d).hashCode());
//        System.out.println("e:"+((Float)e).hashCode());
//        System.out.println("c:"+(((Float)c).hashCode() & 0x7fffffff));
//        System.out.println("d:"+(((Float)d).hashCode() & 0x7fffffff));
//        System.out.println("e:"+(((Float)e).hashCode() & 0x7fffffff));

//        TreeMap map = new TreeMap();
//        Hashtable ht = new Hashtable();
//        Object aa;

        Student stu1 = new Student(3,2,"BOBO","Liu");
//        System.out.println(stu1.hashCode());//94106972

        Student stu2 = new Student(3,2,"bobo","Liu");
//        System.out.println(stu2.hashCode());

        System.out.println("stu1="+stu1+",hashCode="+stu1.hashCode());
        System.out.println("stu2="+stu2+",hashCode="+stu2.hashCode());
        System.out.println("stu1 == stu2 :"+(stu1 == stu2));
        System.out.println("stu1.equals(stu2) :"+(stu1.equals(stu2)));

        System.out.println(new Solution_387().firstUniqChar("leetcode"));
    }
}

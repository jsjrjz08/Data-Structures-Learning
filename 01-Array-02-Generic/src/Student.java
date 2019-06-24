public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("Student[name=%s, score=%d]",name,score);
    }

    @Override
    public boolean equals(Object obj) {
        Student s = (Student)obj;
        return name.equals(s.getName()) && score == s.getScore();
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + score;
        return result;
    }

    public static void main(String[] args) {
        Array<Student> arr = new Array<>();
        System.out.println(arr.isEmpty());

        arr.addLast(new Student("liyi",77));
        arr.addLast(new Student("zhangsan",89));
        arr.addLast(new Student("lisi",90));
        arr.addLast(new Student("wangwu",66));

        System.out.println(arr);
        System.out.println(arr.isEmpty());

        Student stu = new Student("zhangsan",99);
        System.out.println(arr.contains(stu));
        Student stu2 = new Student("zhangsan",89);
        System.out.println(arr.contains(stu2));
    }
}

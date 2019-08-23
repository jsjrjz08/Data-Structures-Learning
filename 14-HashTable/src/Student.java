public class Student {
    private int grade;
    private int cls;
    private String firstName;
    private String lastName;

    public Student(int grade,int cls,String firstName,String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "grade=" + grade +
                ", cls=" + cls +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        int B = 31;
        hash = hash*B + grade;
        hash = hash*B + cls;
        hash = hash*B + firstName.toLowerCase().hashCode();
        hash = hash*B + lastName.toLowerCase().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(o == null) {
            return false;
        }

        if(o.getClass() != this.getClass()) {
            return false;
        }

        Student other = (Student)o;
        return this.grade == other.grade &&
                this.cls == other.cls &&
                this.firstName.toLowerCase().equals(other.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(other.lastName.toLowerCase());
    }


//    @Override
//    public int hashCode() {
//        int result = grade;
//        result = 31 * result + cls;
//        result = 31 * result + (firstName != null ? firstName.toLowerCase().hashCode() : 0);
//        result = 31 * result + (lastName != null ? lastName.toLowerCase().hashCode() : 0);
//        return result;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Student student = (Student) o;
//
//        if (grade != student.grade) return false;
//        if (cls != student.cls) return false;
//        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
//        return lastName != null ? lastName.equals(student.lastName) : student.lastName == null;
//    }

}

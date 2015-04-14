package gpa;

public class StudentGPA implements Comparable<StudentGPA>
{

    protected int id;
    protected String name;
    protected Double GPA;

    public StudentGPA(int id, String name, double gpa)
    {
        GPA = gpa;
        this.name = name;
        this.id = id;
    }  // end constructor

    public String toString()
    {
        return "(" + id + "," + name + "," + GPA + ")";
    } // end toString

    public int compareTo(StudentGPA stu)
    {
        return GPA.compareTo(stu.GPA);
    }

}

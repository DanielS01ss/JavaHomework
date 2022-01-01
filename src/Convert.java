import java.util.*;

class Convert{
    public static Curs[] convertToCurs(ArrayList<Curs> c)
    {
        Curs[] a = new Curs[c.size()];
        for(int i=0;i<c.size();i++)
        {
            a[i] = c.get(i);
        }

        return a;
    }

    public static Student[] convertToStudent(Set<Student> s)
    {
        Student[] sArray = new Student[s.size()];
        Iterator<Student> myIterator = s.iterator();

        int j = 0;
        while(myIterator.hasNext())
        {
            sArray[j++] = myIterator.next();
        }
        return sArray;
    }

    public static ArrayList<Student> convertToStudentArrayList(Set<Student> s)
    {
        ArrayList<Student> studentList = new ArrayList<Student>();
        for(Student si:s)
        {
            studentList.add(si);
        }
        return studentList;
    }

}
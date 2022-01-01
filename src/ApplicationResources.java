import java.util.*;

public class ApplicationResources {

    static List<Curs> cursuri = new ArrayList<Curs>();
    static List<Profesor> profesori = new ArrayList<Profesor>();
    static List<Student> studenti = new ArrayList<Student>();
    static ManagerCursuri mg = new ManagerCursuri();
    static Profesor loggedProfesor = null;
    static Student loggedStudent = null;

    static void displayStudents()
    {
        for(int i=0;i<studenti.size();i++)
        {
            System.out.println(studenti.get(i));
        }
    }

    static Curs getSpecificCourse(String name)
    {
        System.out.println(studenti);
        for(int i=0;i<cursuri.size();i++)
        {
            if(cursuri.get(i).nume == name)
                return cursuri.get(i);
        }

        return null;
    }

    static Student getSpecificStudent(Student s)
    {
        Student toBeFound = null;
        for(Student si: studenti)
        {
            if(si.nume == s.nume && si.prenume == s.prenume )
            {
               toBeFound = si;
            }
        }
        return toBeFound;
    }
}

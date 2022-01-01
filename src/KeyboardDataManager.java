import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.*;
import java.util.*;

public class KeyboardDataManager implements IDataLoader {
    @Override
    public Student[] createStudentsData() {

        var n = "";
        System.out.println("Introdu numarul de studenti pe care doresti sa-i introduci:");
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int nr=0;

        while(!ok)
        {
            try{
                n = sc.next();
                nr = Integer.parseInt(n);
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("Caracterul introdus nu este un numar!! Va rog introduceti un numar.");
            }

        }
        /* nume, prenume ,grupa*/
        ArrayList<Student> students = new ArrayList<Student>();
        Student[] s = new Student[nr];
        int j = 0;
        System.out.flush();
        for(int i=0;i<nr;i++)
        {
            String nume;
            String prenume;
            int grupa;
            System.out.flush();
            System.out.print("Nume: ");
            nume = sc.next();
            System.out.println();
            System.out.print("Prenume: ");
            prenume = sc.next();
            System.out.println();
            System.out.print("Grupa: ");
            grupa = sc.nextInt();
            System.out.println();

            Student s1 = new Student(nume,prenume,grupa);
            s[j++]= s1;
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        return s;
    }

    @Override
    public Profesor[] createProfesorData() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        var n = "";
        System.out.println("Introdu numarul de profesori pe care doresti sa-i introduci:");
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int nr=0;

        while(!ok)
        {
            try{
                n = sc.next();
                nr = Integer.parseInt(n);
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("Caracterul introdus nu este un numar!! Va rog introduceti un numar.");
            }

        }
        /* nume, prenume */
        System.out.flush();
        Profesor professors[] = new Profesor[nr];
        int j = 0;
        for(int i=0;i<nr;i++)
        {
            String nume;
            String prenume;

            System.out.print("Nume: ");
            nume = sc.next();
            System.out.println();
            System.out.print("Prenume: ");
            prenume = sc.next();
            System.out.println();


            Profesor p = new Profesor(nume,prenume);
            professors[j++] = p;
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        return professors;


    }

    @Override
    public Curs[] createCoursesData() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        var n = "";
        System.out.println("Introdu numarul de cursuri pe care vrei sa le introduci");
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int nr=0;

        while(!ok)
        {
            try{
                n = sc.next();
                nr = Integer.parseInt(n);
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("Caracterul introdus nu este un numar!! Va rog introduceti un numar.");
            }

        }
        /* nume, descriere , profu , Set<Student> students *

         */
        System.out.flush();
        sc.nextLine();
        Curs cursuri[] = new Curs[nr];
        int k = 0;
        for(int i=0;i<nr;i++)
        {
            String nume;
            String descriere;

            System.out.flush();
            System.out.print("Numele Cursului: ");
            nume = sc.next();
            System.out.println();
            sc.nextLine();
            System.out.print("Descrierea Cursului: ");
            descriere = sc.nextLine();
            System.out.flush();
            System.out.println();

            String numeProf;
            String prenumeProf;
            System.out.print("Nume Profesor Titular: ");
            System.out.flush();
            numeProf = sc.next();
            System.out.println();
            System.out.flush();
            System.out.print("Prenume Profesor Titular: ");
            System.out.flush();
            prenumeProf = sc.next();
            System.out.println();
            System.out.flush();
            Profesor p = new Profesor(numeProf,prenumeProf);

            Set<Student> s = new HashSet<Student>();
            Student [] students = createStudentsData();
            for(int j=0;j<students.length;j++)
            {
                s.add(students[i]);
            }

            Curs c = new Curs(nume,descriere,p,s);
            cursuri[k++] = c;
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        return cursuri;
    }
}

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class ConsoleDisplay implements IDisplayManager {
    @Override
    public void displayStudents(Student[] students) {
        for (Student s :
                students) {
            System.out.println(s);
        }
    }

    @Override
    public void displayTeachers(Profesor[] profesors) {

        for(Profesor p:profesors)
        {
            System.out.println(p);
        }

    }

    @Override
    public void displayCourses(Curs[] cursuri) {

        if (cursuri.length == 0)
        {
            System.out.println("Nu exista cursuri!!");
            System.out.println("Apasa orice tasta pentru a continua si apoi enter");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            return;
        }
        for(Curs c:cursuri) {
            System.out.println(c);
        }
        System.out.println();
        System.out.println("Apasa orice tasta pentru a continua si apoi enter");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public void displayStudentsAtACourse(Profesor p)
    {
        Scanner sc = new Scanner(System.in);
        if(p.cursuri.size() == 0)
        {
            System.out.println("Nu aveti cursuri la care sa afisati studenti!");
            System.out.println("Apasa orice tasta pentru a continua si apoi enter");
            sc.nextLine();
            return;
        }
        System.out.println("Introdu numarul cursului la care doresti sa fie afisat studentul:");

        ArrayList<Curs> c = p.cursuri;
        for(int i=0;i<c.size();i++)
        {
            System.out.println(i+1+"."+c.get(i).nume);
        }

        int option=1;
        boolean ok = false;
        while (!ok)
        {
            try{
                String data = sc.nextLine();
                option = Integer.valueOf(data);

            }catch (NumberFormatException e)
            {
                System.out.println("Ati introdus caractere gresite! Va rog reintroduceti numarul!");
                continue;
            }
            if(option<1 && option>c.size())
            {
                System.out.println("Numarul introdus nu este corect!!! Va rog reintroduceti!");
                continue;
            }
            ok = true;
        }

        Curs selectedCourse = c.get(option-1);
        displayStudents( Convert.convertToStudent(selectedCourse.getStudenti()));
        System.out.println("Apasa orice tasta si apoi enter pentru a continua");
        sc.nextLine();
    }

    public void meniuDeNotare(Profesor p)
    {
        ArrayList<Curs> c = p.cursuri;
        Scanner sc = new Scanner(System.in);

        if(c.size() == 0)
        {
            System.out.println("Nu aveti cursuri la care sa puteti nota studentii!!");
            System.out.println("Apasati orice tasta pentru a continua si apoi enter");
            sc.nextLine();
            return;
        }
        System.out.println("Selecteaza cursul la care vrei sa notezi studentul:");
        for(int i= 0;i<c.size();i++)
        {
            System.out.println(i+1+"."+c.get(i).nume);
        }

        int optiune;
        while(true)
        {
            try{
                String line;
                line = sc.nextLine();
                optiune = Integer.parseInt(line);
                if(optiune<0 || optiune > c.size())
                {
                    System.out.println("Numarul cursului introdus de dumneavoastra nu se afla in lista");
                    System.out.println("Va rugam reluati introducerea!");
                    continue;
                }
                break;
            }catch(NumberFormatException e)
            {
                System.out.println("Ati introdus caractere eronate!! Va rog introduceti din nou");
            }
        }

        System.out.println("Introdu datele studentului:");
        System.out.print("Nume:");
        String nume = sc.nextLine();
        System.out.println();
        System.out.println("Prenume:");
        String prenume = sc.nextLine();
        System.out.println("Introdu grupa in care este:");

        int grupa;
        while(true)
        {
            try{
                String line = sc.nextLine();
                grupa = Integer.parseInt(line);
                break;
            }catch(NumberFormatException e)
            {
                System.out.println("Numarul introdus nu este corect!");
                continue;
            }
        }


        Student s = new Student(nume,prenume,grupa);

        int notaStudent;
        System.out.println("Introdu nota studentului te rog!");
        while(true)
        {
            try{
                String line = sc.nextLine();
                notaStudent = Integer.parseInt(line);
                break;
            }catch(NumberFormatException e)
            {
                System.out.println("Numarul introdus nu este corect!!");
            }
        }

        boolean success = false;
        try{
            c.get(optiune-1).noteazaStudent(s,notaStudent);
            success = true;
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        if(success)
        {
            System.out.println("Studentul a fost notat cu succces!");
        }
        System.out.println("Apasati orice tasta pentru a continua si apoi enter");
        sc.nextLine();

    }

    public void afisareNoteStudentLaCurs(Student s)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Curs> cursuriStudent = s.cursuriInscrisi;
        for(Curs c:cursuriStudent)
        {
            System.out.print(c.nume+":");
            c.AfisareNoteStudentCurs(s);
            System.out.println();
        }
        System.out.println("Apasati orice tasta pentru a continua si apoi enter!");
        sc.nextLine();
    }

    public void afisareNumeCursuri(ArrayList<Curs> cursuri)
    {
        Scanner sc = new Scanner(System.in);
        for(Curs c:cursuri)
        {
            c.AfisareNumeCurs();
        }

        System.out.println("Apasati orice tasta pentru a continua si apoi enter!");
        sc.next();
    }

    public void afisareMedieAnUniversitar(Student s)
    {
        int an;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti anul univesitar in care doriti sa vedeti media:");

        while(true)
        {
            try{
                String line = sc.nextLine();
                an = Integer.parseInt(line);
                break;
            }catch(NumberFormatException e) {
                System.out.println("Ati introdus caractere eronate! Va rugam introduceti din nou anul!");
            }
        }

        double medie = s.medieAnUniversitar(s,an);
        if(medie ==-1.)
        {
            System.out.println("Studentul nu a fost notat in anul universitar "+an);

        } else {
            System.out.println("Media studentului din anul universitar "+an+" este:"+medie);
        }

        System.out.println("Apasa orice tasta pentru a continua si apoi enter");
        sc.nextLine();

    }

    public void afisareRestante(Student s)
    {
        Scanner sc = new Scanner(System.in);
        s.afisareRestante();
        System.out.println("Apasati orice tasta pentru a continua si apoi enter");
        sc.nextLine();
    }

}

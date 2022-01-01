import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface implements Runnable{

    public ManagerCursuri m;

    ConsoleInterface(ManagerCursuri mCursuri)
    {
        m = mCursuri;
    }

    @Override
    public void run()
    {
        runConsole();
    }

    public void runConsole()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username = ");
        var username = sc.next();
        System.out.println("Password = ");
        var password = sc.next();

        User currentUser;
        UserAccountType accType = null;
        HashMap<String,String> accHolderInfo = new HashMap<String,String>();

        try {
            Application.getInstance().login(new User(username, password));
            currentUser =  Application.getInstance().currentUser;
            accHolderInfo = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation();
            accType = Application.getInstance().currentUser.menuStrategy.getAccountType();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /* Afisare in functie de tipul de afisare setat! */
        Iterator<Map.Entry<String, String>> iterator = accHolderInfo.entrySet().iterator();
        if(accType == UserAccountType.TEACHER)
        {
            Map.Entry<String,String> value = iterator.next();
            Profesor p = new Profesor(value.getKey(), value.getValue());
            p.cursuri = m.getProfessorCursuri(p);
            System.out.println(Settings.displayType);
            if(Settings.displayType == DISPLAY_TYPE.CONSOLA)
            {
                ConsoleDisplayTeacher cd  = new ConsoleDisplayTeacher(p);
                cd.mainLoop();
            }

        }else if (accType == UserAccountType.STUDENT)
        {

            Map.Entry<String,String> value = iterator.next();
            String nume = value.getKey();
            String prenume = value.getValue();
            value = iterator.next();
            String line2 = value.getValue();
            int grupa = Integer.parseInt(line2);
            Student loggedStudent = new Student(nume,prenume,grupa);
            loggedStudent = m.populateEnrolledCourseStudent(loggedStudent);

            if(Settings.displayType == DISPLAY_TYPE.CONSOLA)
            {
                ConsoleDisplayStudent cDisplayStudent = new ConsoleDisplayStudent(loggedStudent);
                cDisplayStudent.mainLoop();
            }

        }
    }
}

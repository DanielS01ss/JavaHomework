import java.util.HashMap;
import java.util.Arrays;

public class TeacherStrategy implements MenuStrategy {
    public Profesor profesor;
    public TeacherStrategy() { }
    HashMap<DISPLAY_TYPE,IDisplayManager> displayFunction = new HashMap<DISPLAY_TYPE,IDisplayManager>(){{
        put(DISPLAY_TYPE.CONSOLA,new ConsoleDisplay());
    }};

    String [] accountMenuOptions = {"1.Afiseaza toate cursurile","2.Afiseaza studenti inscrisi la un anume curs","3.Noteaza un student",
            "4.Iesi"};
    public int currentMenuOption = 0;

    TeacherStrategy(Profesor p) {
        this.profesor = p;
    }
    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.TEACHER;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(profesor.nume, profesor.prenume);
        }};
    }

    @Override
    public String[] getAccountMenuOptions() {
        return accountMenuOptions;
    }

    @Override
    public void nextMenuOption(int opt) {
            switch (opt)
            {
                case 1:
                    displayFunction.get(Settings.displayType).displayCourses( Convert.convertToCurs(profesor.cursuri));
                    break;
                case 2:
                    ((ConsoleDisplay)displayFunction.get(Settings.displayType)).displayStudentsAtACourse(profesor);
                    break;
                case 3:
                    ((ConsoleDisplay)displayFunction.get(Settings.displayType)).meniuDeNotare(profesor);
                    break;
                case 4:
                    break;
            }
    }

    @Override
    public void previousMenuOption() {

    }



}

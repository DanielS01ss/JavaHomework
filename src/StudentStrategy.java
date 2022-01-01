import java.util.HashMap;

public class StudentStrategy implements MenuStrategy{
    public Student student;
    public ConsoleDisplay cd = new ConsoleDisplay();
    public String [] accountMenuOptions = {
            "1.Arata cursurile la care esti inscris",
            "2.Afiseaza notele de la fiecare curs",
            "3.Calculeaza media dintr-un an universitar",
            "4.Afiseaza restantele",
            "5.Iesire"
    };
    HashMap<DISPLAY_TYPE,IDisplayManager> displayFunction = new HashMap<DISPLAY_TYPE,IDisplayManager>()
    {{
        put(DISPLAY_TYPE.CONSOLA,new ConsoleDisplay());
    }};
    public StudentStrategy() { }
    StudentStrategy(Student student) {
        this.student = student;
    }

    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.STUDENT;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(student.nume, student.prenume);
            put("grupa",Integer.toString(student.grupa));
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
                cd.afisareNumeCursuri(student.cursuriInscrisi);
                break;
            case 2:
                cd.afisareNoteStudentLaCurs(student);
                break;
            case 3:
                cd.afisareMedieAnUniversitar(student);
                break;
            case 4:
                cd.afisareRestante(student);
                break;
            case 5:
                break;
        }

    }

    @Override
    public void previousMenuOption() {

    }
}

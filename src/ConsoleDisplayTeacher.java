import java.util.*;



public class ConsoleDisplayTeacher  {
    TeacherStrategy tSt;
    Profesor p;

    ConsoleDisplayTeacher(Profesor p)
    {
        this.p = p;
        tSt = new TeacherStrategy(p);
    }

    public void mainLoop()
    {
        Scanner sc = new Scanner(System.in);
        int key = 0;
        String [] availableOptions = tSt.getAccountMenuOptions();
        while(key!=4)
        {
            System.out.println("Bine ai venit!");
            for(String s: availableOptions)
            {
                System.out.println(s);
            }
            System.out.println();
            System.out.println("Introdu optiunea dorita");
            String data = sc.nextLine();
            try{
                key = Integer.parseInt(data);
            } catch(NumberFormatException e)
            {
                System.out.println("Ati introdus caractere eronate! Apasati orice tasta pentru a continua si apoi enter si introduceti din nou!!");
                sc.nextLine();
                continue;
            }

            if(key<1 || key >4)
            {
                System.out.println("Numarul introdus de dumneavoastra nu se afla in lista! Introduceti altul! Apasiti orice tasta pentru a relua procesul!");
                sc.nextLine();
                continue;
            }

            tSt.nextMenuOption(key);

        }

    }

}

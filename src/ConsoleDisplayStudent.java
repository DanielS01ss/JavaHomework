import java.util.*;

public class ConsoleDisplayStudent {
    Student s;
    StudentStrategy st;

    ConsoleDisplayStudent(Student s1)
    {
        s = s1;
        st = new StudentStrategy(s1);
    }

    public void mainLoop()
    {

        Scanner sc = new Scanner(System.in);
        int key = 0;
        String [] availableOptions = st.getAccountMenuOptions();
        while(key!=5)
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

            if(key<1 || key >5)
            {
                System.out.println("Numarul introdus de dumneavoastra nu se afla in lista! Introduceti altul! Apasiti orice tasta pentru a relua procesul!");
                sc.nextLine();
                continue;
            }

            st.nextMenuOption(key);

        }
    }

}

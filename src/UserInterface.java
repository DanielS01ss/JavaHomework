import javax.swing.*;
import java.util.Iterator;
import java.util.Map;
import java.util.*;

public class UserInterface implements Runnable{
    int typeOfInterface = 0;
    public  ManagerCursuri m;

    UserInterface(ManagerCursuri manag)
    {
        this.m = manag;
    }

    @Override
    public void run()
    {
            runGui();
    }

    public void runGui()
    {
        JFrame frame = new JFrame("Graphic user interface");
        LoginForm loginForm = new LoginForm(frame);
        frame.setContentPane(loginForm.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



}

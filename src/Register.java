import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Register {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JButton creeazaButton;
    private JPanel mainPannel;
    private JTextField keyText;
    private boolean grupaFieldEnabled = false;

    public JPanel getMainPannel(){
        return this.mainPannel;
    }
    Register(JFrame owner)
    {
        keyText.setEnabled(false);
        owner.setSize(300,600);
        mainPannel.setPreferredSize(new Dimension(200,300));
        keyText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        creeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == creeazaButton)
                {

                    String username = textField1.getText();
                    String password = passwordField1.getText();
                    String nume = textField2.getText();
                    String prenume = textField3.getText();
                    String grupa = "";
                    if(grupaFieldEnabled)
                    {
                         grupa = keyText.getText();
                    }

                    int accTypeIndex = comboBox1.getSelectedIndex();
                    UserAccountType accType = accTypeIndex==0? UserAccountType.TEACHER : UserAccountType.STUDENT;
                    if(username.length() == 0 || password.length() ==0 || nume.length() == 0 || prenume.length() ==0)
                    {
                        JOptionPane.showMessageDialog(null, "Te rog introdu toti parametri!!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(grupaFieldEnabled && grupa.length() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Te rog completeaza toate campurile","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(accType == UserAccountType.STUDENT)
                    {
                        ///creem un student si verificam daca exista
                        Student studentToCreate = new Student(nume,prenume,Integer.parseInt(grupa));
                        System.out.print(studentToCreate);
                        if(ApplicationResources.studenti.indexOf(studentToCreate)!=-1)
                        {
                            JOptionPane.showMessageDialog(null,"User a fost creeat cu succes!");
                            User newUser = new User();
                            newUser.userName = username;
                            newUser.password = password;
                            newUser.studentAccType = studentToCreate;
                            newUser.menuStrategy = new StudentStrategy(studentToCreate);
                            List<User> users = Application.getInstance().getUserList();
                            users.add(newUser);
                            Application.getInstance().setUserList(users);

                            FileHandler.writeToXMLUsers(Application.getInstance().getUserList());
                            mainPannel.setVisible(false);
                            owner.setContentPane(new LoginForm(owner).getMainPanel());
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Studentul nu este inregistrat!","Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }


                    } else if(accType == UserAccountType.TEACHER)
                    {
                        Profesor profesorToCreate = new Profesor(nume,prenume);

                        if(ApplicationResources.profesori.indexOf(profesorToCreate)!=-1)
                        {
                            JOptionPane.showMessageDialog(null,"User a fost creeat cu success!");
                            User newUser = new User();
                            newUser.userName = username;
                            newUser.password = password;
                            newUser.profesorAccType = profesorToCreate;
                            newUser.menuStrategy = new TeacherStrategy(profesorToCreate);
                            List<User> users = Application.getInstance().getUserList();
                            users.add(newUser);
                            Application.getInstance().setUserList(users);
                            FileHandler.writeToXMLUsers(Application.getInstance().getUserList());
                            mainPannel.setVisible(false);
                            owner.setContentPane(new LoginForm(owner).getMainPanel());
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Profesorul nu este inregistrat in baza de date!","Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                }
            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getSource() == comboBox1)
                {
                    grupaFieldEnabled =  comboBox1.getSelectedIndex()==0? false:true;
                    if(grupaFieldEnabled)
                    {
                        keyText.setEnabled(true);
                    } else {
                        keyText.setEnabled(false);
                    }

                }
            }
        });
    }
}

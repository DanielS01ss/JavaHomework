import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginForm {
    public LoginForm(JFrame owner) {
        this.owner = owner;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));
                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        User currentUser =  Application.getInstance().currentUser;
                        HashMap<String,String> accHolderInfo = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation();
                        UserAccountType accType = Application.getInstance().currentUser.menuStrategy.getAccountType();
                        Iterator<Map.Entry<String, String>> iterator = accHolderInfo.entrySet().iterator();
                        System.out.println(accType);
                        if(accType == UserAccountType.STUDENT)
                        {
                            Map.Entry<String,String> value = iterator.next();
                            String nume = value.getKey();
                            String prenume = value.getValue();
                            value = iterator.next();
                            String line2 = value.getValue();
                            int grupa = Integer.parseInt(line2);
                            Student loggedStudent = new Student(nume,prenume,grupa);
                            loggedStudent = ApplicationResources.mg.populateEnrolledCourseStudent(loggedStudent);
                            ApplicationResources.loggedStudent = loggedStudent;
                            mainPanel.setVisible(false);
                            owner.setContentPane(new StudentForm(owner).getMainPanel());

                        } else if(accType == UserAccountType.TEACHER)
                        {
                            Map.Entry<String,String> value = iterator.next();
                            Profesor p = new Profesor(value.getKey(), value.getValue());
                            p.cursuri = ApplicationResources.mg.getProfessorCursuri(p);
                            ApplicationResources.loggedProfesor = p;
                            mainPanel.setVisible(false);
                            owner.setContentPane(new TeacherForm(owner).getPanel1());
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == registerButton)
                {
                    mainPanel.setVisible(false);
                    owner.setContentPane(new Register(owner).getMainPannel());
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton registerButton;
    private JFrame owner;
}

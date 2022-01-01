import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TeacherForm {
    private JFrame owner;
    public JPanel getPanel1() {
        return panel1;
    }
    TeacherForm(JFrame mainFrame)
    {
        owner = mainFrame;
        owner.setSize(300,350);
        afiseazaCursuriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == afiseazaCursuriButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new  CursuriProfesor(owner).getMainPannel());
                }
            }
        });
        afiseazaStudentiLaCursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == afiseazaStudentiLaCursButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new StudentsAtCourses(owner).getMainPanel());
                }
            }
        });
        noteazaStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == noteazaStudentButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new ProfesorNoteazaStudent(owner).getMainPannel());
                }
            }
        });
    }
    private JPanel panel1;
    private JButton afiseazaCursuriButton;
    private JButton afiseazaStudentiLaCursButton;
    private JButton noteazaStudentButton;
}

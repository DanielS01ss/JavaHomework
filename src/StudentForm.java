import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {
    private JButton veziCursuriButton;
    private JPanel panel1;
    private JButton calculeazaMediaButton;
    private JButton veziRestanteButton;
    private JButton veziNoteCursuriButton;
    private JFrame owner;

    StudentForm(JFrame owner)
    {
        this.owner = owner;

        veziCursuriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == veziCursuriButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new StudentCourses(owner).getMainPannel());
                }
            }
        });
        veziNoteCursuriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == veziNoteCursuriButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new StudentsMarks(owner).getMainPanel());
                }
            }
        });
        calculeazaMediaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == calculeazaMediaButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new CalculeazaMediaAn(owner).getPanel1());
                }
            }
        });
        veziRestanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                owner.setContentPane(new RestanteStudent(owner).getMainPanel());
            }
        });
    }

    public JPanel getMainPanel()
    {
        return panel1;
    }
}

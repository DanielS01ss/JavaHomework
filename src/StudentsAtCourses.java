import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentsAtCourses {
    private JTable table1;
    private JPanel mainPanel;
    private JButton inapoiButton;
    private JFrame owner;

    StudentsAtCourses(JFrame owner)
    {
        this.owner = owner;
        DefaultTableModel model;
  
        model = (DefaultTableModel) table1.getModel();
        table1.setEnabled(false);
        Object[] newIdentifiers = new Object[]{"Nume Curs","Nume","Prenume","Grupa"};
        model.setColumnIdentifiers(newIdentifiers);
        ///tragem cursurile
        /// si doar pe prima coloana la prima tura afisam numele

        ArrayList<Curs> data = ApplicationResources.loggedProfesor.cursuri;
        Object rowData[] = new Object[4];
        //populare coloane cu informatie
        ///iteram cursurile prima data

        for(int i=0;i<data.size();i++)
        {
            rowData[0] = data.get(i).getNumeCurs();
            rowData[1] = "";
            rowData[2] = "";
            rowData[3] = "";
            model.addRow(rowData);
            ArrayList<Student> studentAtCourse = Convert.convertToStudentArrayList(data.get(i).getStudenti());
            for(Student s:studentAtCourse)
            {
                rowData[0] = "";
                rowData[1] = s.nume;
                rowData[2] = s.prenume;
                rowData[3] = Integer.toString(s.grupa);
                model.addRow(rowData);
            }

        }

        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == inapoiButton)
                {
                    mainPanel.setVisible(false);
                    owner.setContentPane(new TeacherForm(owner).getPanel1());

                }
            }
        });
    }

    public JPanel getMainPanel() {return this.mainPanel;}
 }

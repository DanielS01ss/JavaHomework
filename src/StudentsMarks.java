import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentsMarks {

    private JPanel panel1;
    private JTable table1;
    private JButton inapoiButton;
    private JFrame owner;

    StudentsMarks(JFrame owner)
    {
        this.owner = owner;
        table1.setColumnSelectionAllowed(false);
        table1.setRowSelectionAllowed(true);

        DefaultTableModel model;
        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(model);
        model = (DefaultTableModel) table1.getModel();
        Object[] newIdentifiers = new Object[]{"Nume Curs","Nota"};
        model.setColumnIdentifiers(newIdentifiers);
        ArrayList<Curs> data = ApplicationResources.loggedStudent.cursuriInscrisi;
        Object rowData[] = new Object[2];
        //populare coloane cu informatie
        if(data.size()!=0)
        {
            for(int i=0;i<data.size();i++)
            {
                rowData[0] = data.get(i).nume;
                int nota = data.get(i).getNotaStudent(ApplicationResources.loggedStudent);
                rowData[1] = nota==-1?"":nota;
                model.addRow(rowData);
            }
        }
        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == inapoiButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new StudentForm(owner).getMainPanel());
                }
            }
        });
    }

    public JPanel getMainPanel()
    {
        return this.panel1;
    }
}


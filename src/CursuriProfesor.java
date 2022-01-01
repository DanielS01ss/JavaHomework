import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CursuriProfesor {
    private JPanel mainPannel;
    private JTable table1;
    private JButton inapoiButton;
    private JFrame owner;

    CursuriProfesor(JFrame owner) {
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
        Object[] newIdentifiers = new Object[]{"Nume Curs"};
        model.setColumnIdentifiers(newIdentifiers);
        ArrayList<String> data = ApplicationResources.loggedProfesor.getNumeCursuriTitular();
        Object rowData[] = new Object[1];
        //populare coloane cu informatie
        if(data.size()!=0)
        {
            for(int i=0;i<data.size();i++)
            {
                rowData[0] = data.get(i);
                model.addRow(rowData);
            }
        }

        //manipulare eveniment de selectie a coloanei
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(table1.getValueAt(table1.getSelectedRow(), 0).toString());
            }
        });

        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == inapoiButton)
                {
                    mainPannel.setVisible(false);
                    owner.setContentPane(new TeacherForm(owner).getPanel1());
                }
            }
        });
    }

    public JPanel getMainPannel()
    {
        return this.mainPannel;
    }
}

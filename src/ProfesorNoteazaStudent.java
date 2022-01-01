import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ProfesorNoteazaStudent {
    private JPanel panel1;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton noteazaButton;
    private JButton inapoiButton;
    private JLabel numeLabel;
    private JLabel prenumeLabel;
    private JLabel grupaLabel;
    private JLabel cursLabel;
    private JFrame owner;
    private String numeStudent = "";
    private String numeCurs = "";
    private String prenumeStudent = "";
    private int notaStudent = 1;
    private int grupaStudent = -1;
    ProfesorNoteazaStudent(JFrame owner){
        this.owner = owner;

        DefaultTableModel model;
        model = new DefaultTableModel(){public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(model);
        model = (DefaultTableModel) table1.getModel();


        Object[] newIdentifiers = new Object[]{"Nume Curs","Nume","Prenume","Grupa"};
        model.setColumnIdentifiers(newIdentifiers);
        ArrayList<Curs> data = ApplicationResources.loggedProfesor.cursuri;
        Object rowData[] = new Object[4];

        for(int i=0;i<data.size();i++)
        {
            ArrayList<Student> studentAtCourse = Convert.convertToStudentArrayList(data.get(i).getStudenti());
            for(Student s:studentAtCourse)
            {
                rowData[0] = data.get(i).getNumeCurs();
                rowData[1] = s.nume;
                rowData[2] = s.prenume;
                rowData[3] = Integer.toString(s.grupa);
                model.addRow(rowData);
            }

        }


        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                 numeCurs = table1.getValueAt(table1.getSelectedRow(), 0).toString();
                 numeStudent = table1.getValueAt(table1.getSelectedRow(), 1).toString();
                 prenumeStudent = table1.getValueAt(table1.getSelectedRow(), 2).toString();
                 grupaStudent = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 3).toString());
                 numeLabel.setText(numeStudent);
                 prenumeLabel.setText(prenumeStudent);
                 grupaLabel.setText(Integer.toString(grupaStudent));
                 cursLabel.setText(numeCurs);

            }
        });

        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == inapoiButton)
                {
                    panel1.setVisible(false);
                    owner.setContentPane(new TeacherForm(owner).getPanel1());
                }
            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getSource() == comboBox1)
                {
                    notaStudent = comboBox1.getSelectedIndex()+1;
                }
            }
        });
        noteazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(grupaStudent ==  -1 )
                {
                    JOptionPane.showMessageDialog(null,"Te rog completeaza toate campurile","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                } else{
                    ///logica backend notare student
                     //cursul are method noteaza student
                    ///unde un parametru este studentul
                    ///si celalalt este nota
                    Curs c = ApplicationResources.getSpecificCourse(numeCurs);
                    Student s = new Student(numeStudent,prenumeStudent,grupaStudent);
                    System.out.println(s);

                    if(c!=null)
                    {
                            try{
                                c.noteazaStudent(s,notaStudent);
                                JOptionPane.showMessageDialog(null,"Studentul a fost notat cu success!");
                                return;
                            } catch(Exception except)
                            {
                                except.printStackTrace();
                            }
                    }
                    JOptionPane.showMessageDialog(null,"A apartut o eroare la notare!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public JPanel getMainPannel()
    {
        return this.panel1;
    }
}

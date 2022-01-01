import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculeazaMediaAn {
    private JPanel panel1;
    private JButton inapoiButton;
    private JTextField keyText;
    private JButton calculeazaButton;
    private JFrame owner;
    private int an;

    CalculeazaMediaAn(JFrame owner)
    {
        this.owner = owner;
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
        calculeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == calculeazaButton)
                {
                    if(keyText.getText().length()==0)
                    {
                        JOptionPane.showMessageDialog(null,"Introdu un an te rog!","Error",JOptionPane.ERROR_MESSAGE);
                                return;
                    }
                    int an = Integer.parseInt(keyText.getText());
                    if(an<1990 || an>2022)
                    {
                        JOptionPane.showMessageDialog(null,"Anul este gresit introdus!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    double medie = ApplicationResources.loggedStudent.medieAnUniversitar(ApplicationResources.loggedStudent,an);
                    String toBeDisplayed = "";
                    if(medie == -1)
                    {
                        JOptionPane.showMessageDialog(null,"Studentul nu are nicio nota in anul dat","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    toBeDisplayed+="Media studentului din anul:"+Integer.toString(an)+" este:"+Double.toString(medie);
                    JOptionPane.showMessageDialog(null,toBeDisplayed);
                }
            }
        });
        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                owner.setContentPane(new StudentForm(owner).getMainPanel());
            }
        });
    }

    public JPanel getPanel1()
    {
        return panel1;
    }
}

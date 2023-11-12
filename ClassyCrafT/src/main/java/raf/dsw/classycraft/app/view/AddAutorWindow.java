package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.controller.ChangeNameAction;
import raf.dsw.classycraft.app.controller.PackageChildAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.message.MessageType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class AddAutorWindow extends JDialog {
    private Project project;

    private JTextField textField;

    private JButton btnPosalji;

    private boolean zaPrikazivanje;

    public AddAutorWindow(JFrame parent, ClassyNode node) {
        super(parent,true);

        if(!(node instanceof Project))
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("samo projektu moze da se menja autor", MessageType.ERROR);
            zaPrikazivanje=false;
        }
        else
        {
            this.project=(Project)node;
            this.setSize(300,300);
            this.setLocationRelativeTo(null);

            textField = new JTextField();
            textField.setSize(100,20);
            textField.setBounds(75,50,200,40);
            textField.setText((project).getImeAutora());
            btnPosalji = new JButton("zavrsi");
            btnPosalji.setSize(40,40);
            btnPosalji.setVisible(true);

            this.add(textField);
            this.add(btnPosalji);

            this.setLayout(null);
            this.setVisible(true);


            //zaPrikazivanje=true;

            ActionListener promenaImena=new ChangeNameAction(this,this.project,this.textField.getText());
            btnPosalji.addActionListener(promenaImena);






            /*JPanel pan=new JPanel();
            pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));



            pan.add(textField,btnPosalji);

            this.add(pan);*/
            //this.setVisible(true);

            //textField.setVisible(true);
            //btnPosalji.setVisible(true);

        }

    }



    public boolean isZaPrikazivanje() {
        return zaPrikazivanje;
    }

    public void setZaPrikazivanje(boolean zaPrikazivanje) {
        this.zaPrikazivanje = zaPrikazivanje;
    }
}

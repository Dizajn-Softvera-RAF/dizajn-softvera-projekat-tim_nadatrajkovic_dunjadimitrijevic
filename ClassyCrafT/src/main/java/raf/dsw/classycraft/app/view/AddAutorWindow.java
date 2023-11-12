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




            this.project = (Project) node;
            this.setSize(300, 300);
            this.setLocationRelativeTo(null);




            textField = new JTextField();
            textField.setSize(100, 20);
            textField.setBounds(75, 50, 200, 40);
            textField.setText((project).getImeAutora());
            //JPanel textPanel = new JPanel();
            //textPanel.add(textField);

            btnPosalji = new JButton("zavrsi");
            btnPosalji.setSize(40, 40);
            btnPosalji.setVisible(true);
            //JPanel btnPanel=new JPanel();
            //btnPanel.add(btnPosalji);

            ChangeNameAction changeNameAction = new ChangeNameAction(this, this.project);
            btnPosalji.addActionListener(changeNameAction);



            //BorderLayout bl = new BorderLayout(10,10);
            //bl.addLayoutComponent(textField,BorderLayout.LINE_START);
            //bl.addLayoutComponent(btnPosalji,BorderLayout.CENTER);


            this.add(textField);
            this.add(btnPosalji);

            //this.setLayout(bl);
            //this.setVisible(true);


            //zaPrikazivanje=true;

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);



            /*JPanel pan=new JPanel();
            pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));



            pan.add(textField,btnPosalji);

            this.add(pan);*/
            //this.setVisible(true);

            //textField.setVisible(true);
            //btnPosalji.setVisible(true);



    }


    public String textFromField()
    {
        return textField.getText();
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }




//    public boolean isZaPrikazivanje() {
//        return true;//zaPrikazivanje;
//    }
//
//    public void setZaPrikazivanje(boolean zaPrikazivanje) {
//        this.zaPrikazivanje = zaPrikazivanje;
//    }
}

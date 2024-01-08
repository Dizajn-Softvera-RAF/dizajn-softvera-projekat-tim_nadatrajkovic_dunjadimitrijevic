package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.controller.ChangeNameAction;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import javax.swing.*;
import java.awt.*;


public class AddAutorWindow extends JDialog {
    private Project project;

    private JTextField textField;

    private JButton btnPosalji;

    public AddAutorWindow(JFrame parent, ClassyNode node) {
        super(parent,true);
            this.project = (Project) node;
            this.setSize(300, 300);
            this.setLocationRelativeTo(null);

            textField = new JTextField();
            textField.setSize(100, 20);
            textField.setText((project).getImeAutora());

            btnPosalji = new JButton("zavrsi");
            btnPosalji.setSize(100,50);

            ChangeNameAction changeNameAction = new ChangeNameAction(this, this.project);
            btnPosalji.addActionListener(changeNameAction);

            Container pane = this.getContentPane();
            pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
            pane.setSize(200,200);

            textField.setSize(100,50);
            pane.add(textField);

            pane.add(btnPosalji);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

}

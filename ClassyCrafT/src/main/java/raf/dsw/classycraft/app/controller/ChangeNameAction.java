package raf.dsw.classycraft.app.controller;

import javafx.util.Pair;
import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.view.AddAutorWindow;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChangeNameAction implements ActionListener{ //IPublisher {

    //List<ISubscriber> subscriberList;
    Project project;
    String tekst;
    AddAutorWindow dialog;

    public ChangeNameAction(AddAutorWindow dialog, Project project) {
        //gde ide provera jel prazan
        //subscriberList=new ArrayList<>();
        this.project = project;

        System.out.println("napravio changenameacton + " + this.tekst );
        this.dialog=dialog;

        //this.addSubscriber(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.tekst = dialog.textFromField();

        System.out.println("uso u action prerformed1"+tekst);

        project.setImeAutora(tekst);  //dal ovde uopste treba observer mozda zapravo treba u project kad promeni tekst -> promenjeno
        //project.notifySubscribers(new Pair("promena imena",tekst));

//        for (ClassyNode p:project.getChildren()) {
//            if(p instanceof Package)
//            {
//                ((Package) p).notifySubscribers(new Pair("promena imena",tekst));
//            }
//        } // da li nam treba ovo?

        System.out.println("uso u action prerformed"+tekst);

        Component component = (Component) e.getSource();
        JDialog dialog1 = (JDialog) SwingUtilities.getRoot(component);
        //System.exit(0);
        dialog1.dispose();
        dialog.dispose();
    }

}

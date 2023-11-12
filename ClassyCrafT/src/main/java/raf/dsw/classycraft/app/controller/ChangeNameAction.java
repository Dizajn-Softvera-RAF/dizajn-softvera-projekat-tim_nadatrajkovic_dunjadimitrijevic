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

        project.setImeAutora(tekst);  //dal ovde uopste treba observer mozda zapravo treba u project kad promeni tekst
        project.notifySubscribers(new Pair("promena imena",tekst));

        for (ClassyNode p:project.getChildren()) {
            if(p instanceof Package)
            {
                ((Package) p).notifySubscribers(new Pair("promena imena",tekst));
            }
        }

        System.out.println("uso u action prerformed"+tekst);

        Component component = (Component) e.getSource();
        JDialog dialog1 = (JDialog) SwingUtilities.getRoot(component);
        //System.exit(0);
        dialog1.dispose();
        dialog.dispose();



        //dialog.setVisible(false);
        //dialog1.setVisible(false);

        //dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        //dialog1.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        //dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        /*dialog.setVisible(false);
        dialog.dispose();*/
        //notifySubscribers(tekst);




    }

    /*@Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscriberList ==null)
            this.subscriberList = new ArrayList<>();
        if(this.subscriberList.contains(sub))
            return;
        this.subscriberList.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscriberList == null || !this.subscriberList.contains(sub))
            return;
        this.subscriberList.remove(sub);

    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscriberList == null || this.subscriberList.isEmpty())
            return;

        for(ISubscriber listener : subscriberList){
            listener.Update(notification);
        }
    }*/
}

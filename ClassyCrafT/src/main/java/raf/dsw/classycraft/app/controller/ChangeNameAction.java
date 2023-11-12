package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.Observer.IPublisher;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChangeNameAction implements ActionListener{ //IPublisher {

    List<ISubscriber> subscriberList;
    Project project;
    String tekst;
    JDialog dialog;

    public ChangeNameAction(JDialog dialog, Project project, String tekst) {
        //gde ide provera jel prazan
        subscriberList=new ArrayList<>();
        this.project = project;
        this.tekst = tekst;
        this.dialog=dialog;

        //this.addSubscriber(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        project.setImeAutora(tekst);  //dal ovde uopste treba observer mozda zapravo treba u project kad promeni tekst
        dialog.setVisible(false);
        dialog.dispose();
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

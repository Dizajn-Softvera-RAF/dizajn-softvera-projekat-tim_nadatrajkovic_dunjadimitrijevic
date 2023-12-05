package raf.dsw.classycraft.app.view;

import com.sun.tools.javac.Main;
import javafx.util.Pair;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.util.ArrayList;

public class PackageView extends JPanel implements ISubscriber {


    private JPanel desktopinfo;
    private Label project;
    private Label author;
    private JPanel tabs_panel;

    private JTabbedPane tabs;

    public PackageView() {
        super();
        desktopinfo = new JPanel();
        desktopinfo.setLayout(new BoxLayout(desktopinfo, BoxLayout.Y_AXIS));
        project = new Label("Current project: ");
        author = new Label("Author: ");
        desktopinfo.add(project);
        desktopinfo.add(author);

        this.setLayout(new BorderLayout());
        this.add(desktopinfo, BorderLayout.NORTH);
        tabs_panel = new JPanel();
        tabs_panel.setLayout(new BorderLayout());
        this.add(tabs_panel,BorderLayout.CENTER);
        tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        //tabs.setSize(500,300);

        tabs_panel.add(tabs);

    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setAutor(String autor)
    {
        author.setText("Author: "+autor);
    }

    public void setProjectNaziv(String naziv)
    {
        project.setText("Current project: " + naziv);
    }

    private void showComponents(){
        project.setVisible(true);
        author.setVisible(true);
        tabs_panel.setVisible(true);
        tabs.setVisible(true);
    }
    private void createTabs(ArrayList<DiagramView> tabNames)
    {
        for(DiagramView dw : tabNames)
        {
            this.getTabs().addTab(dw.getDiagram().getName(), dw);
        }
    }

    @Override
    public void Update(Object notification) {
        System.out.println("usao u PackageView update()"+ notification.toString());

        /// Mora da se sredi sa Notification
        if(notification instanceof Notification)
        {
            if(((Notification) notification).getNotificationType() == NotificationType.SHOW)
            {
                MainFrame.getInstance().setPackageView(this);
                //Package currentPackage = (Package) ((Notification) notification).getObjectOfNotification();
                ClassyNode node = (ClassyNode) ((Notification) notification).getObjectOfNotification();
                while (!(node instanceof Project)) {
                    node = node.getParent();
                }
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                if(tabs.getTabCount() > 0)
                {
                    showComponents();
                    this.setProjectNaziv(((Project) node).getName());
                    this.setAutor(((Project) node).getImeAutora());
                    return;
                }
                ClassyNodeComposite cp = (ClassyNodeComposite) ((Notification) notification).getObjectOfNotification();
                ArrayList<DiagramView> tabNames = new ArrayList<>();
                for(ClassyNode c : cp.getChildren()){
                    if(c instanceof Diagram)
                    {
                        ((Diagram)c).addSubscriber(this);
                        DiagramView dw = new DiagramView((Diagram) c);
                        dw.Update(new Notification(c, NotificationType.SHOWDIAGRAM));
                        c.addSubscriber(dw);
                        //tabs.add(dw);
                        cp.addSubscriber(dw);
                        tabNames.add(dw);
                    }

                }
                createTabs(tabNames);
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                showComponents();

            }

            else if(((Notification) notification).getNotificationType() == NotificationType.RENAME)
            {
                if(((Notification) notification).getObjectOfNotification() instanceof Project) // za promenu imena Autora projekta i imena projekta
                {
                    this.setAutor(((Project) ((Notification) notification).getObjectOfNotification()).getImeAutora());
                    this.setProjectNaziv(((Project) ((Notification) notification).getObjectOfNotification()).getName());
                }
            }

        }
        else if(notification.toString().equals("brisi"))
        {
//            //removeAll();
//            //tabs.remove(DiagramView);
//            repaint();
//            revalidate();
        }
        else if(notification instanceof String) // sredi ove notifikacije, da se zna sta je sta
        {
            //setProjectNaziv(notification.toString());
        }
        else if(notification instanceof Diagram)
        {
            //DiagramView dw = new DiagramView();
            //this.getTabs().addTab(((Diagram) notification).getName(), dw);
            //MainFrame.getInstance().getPackageView().getTabs().addTab(((Diagram) notification).getName(), dw);
            //((Diagram)notification).addSubscriber(dw);
        }


    }
}

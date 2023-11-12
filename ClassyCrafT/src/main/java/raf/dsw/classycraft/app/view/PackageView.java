package raf.dsw.classycraft.app.view;

import javafx.util.Pair;
import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;

import javax.swing.*;
import java.awt.*;

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

    @Override
    public void Update(Object notification) {
        if(notification instanceof Pair)
        {
            setAutor(((Pair)notification).getValue().toString());
        }
        else if(notification.toString().equals("brisi"))
        {
            removeAll();
            repaint();
            revalidate();
        }
        else if(notification instanceof String)
        {
            setProjectNaziv(notification.toString());
        }
        else if(notification instanceof Diagram)
        {
            DiagramView dw = new DiagramView();
            MainFrame.getInstance().getPackageView().getTabs().addTab(((Diagram) notification).getName(), dw);
            ((Diagram)notification).addSubscriber(dw);
        }


    }
}

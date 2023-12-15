package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.controller.stateSablon.StateManager;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PackageView extends JPanel implements ISubscriber {


    private JPanel desktopinfo;
    private Label project;
    private Label author;
    private JPanel tabs_panel;

    private JTabbedPane tabbedPane;

    private Package paket; // paket koji se trenutno prikazuje

    private StateManager stateManager; //packageView je moderator za state sablon

    private DesniToolBar desniToolBar;

    private ArrayList<DiagramView> diagramViews;




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
        this.add(tabs_panel, BorderLayout.CENTER);
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        //tabs.setSize(500,300);
        diagramViews =new ArrayList<DiagramView>();

        tabs_panel.add(tabbedPane);

        stateManager = new StateManager();

        desniToolBar=new DesniToolBar();
        add(desniToolBar, BorderLayout.EAST);
    }

    public Package getPaket() {
        return paket;
    }

    public void setPaket(Package paket) {
        this.paket = paket;
        this.Update(new Notification(paket, NotificationType.SHOW));
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setAutor(String autor) {
        author.setText("Author: " + autor);
    }

    public void setProjectNaziv(String naziv) {
        project.setText("Current project: " + naziv);
    }

    private void showComponents() {
        project.setVisible(true);
        author.setVisible(true);
        tabs_panel.setVisible(true);
        tabbedPane.setVisible(true);
    }

    private void createTabs() {
        tabbedPane.removeAll();
        for(var child : paket.getChildren())
        {
            if(child instanceof Diagram)
            {
                var diagram = (Diagram) child;
                DiagramView dw = new DiagramView(diagram);
                diagram.addSubscriber(dw);
                tabbedPane.add(diagram.getName(), dw);
            }
        }

        // setListeners

//        for (DiagramView dv : diagramViews) {
//            this.getTabbedPane().addTab(dv.getDiagram().getName(), dv);
//        }
    }

    private void hideComponents() {

    }

    @Override
    public void Update(Object notification) {
        System.out.println("usao u PackageView update()" + notification.toString());


        if (notification instanceof Notification) {
            if (((Notification) notification).getNotificationType() == NotificationType.SHOW) {
                MainFrame.getInstance().setPackageView(this);
                System.out.println(paket.getName());
                //Package currentPackage = (Package) ((Notification) notification).getObjectOfNotification();
                //ClassyNode node = (ClassyNode) ((Notification) notification).getObjectOfNotification();

                // Ovde nalazi projekat u kom se paket nalazi
                ClassyNode node = (ClassyNode) paket;

                while (!(node instanceof Project)) {
                    node = node.getParent();
                }
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                node.addSubscriber(this);
                //---------------------------

                //ClassyNodeComposite cp = (ClassyNodeComposite) paket;
                //diagramViews = new ArrayList<>();
//                for (ClassyNode c : cp.getChildren()) {
//                    if (c instanceof Diagram) {
//                        //((Diagram)c).addSubscriber(this); // ?
//                        DiagramView dw = new DiagramView((Diagram) c);
//                        //dw.Update(new Notification(c, NotificationType.SHOWDIAGRAM));
//                        c.addSubscriber(dw);
//                        //tabs.add(dw);
//                        //cp.addSubscriber(dw); // jel mi treba ovo?
//                        //diagramViews.add(dw);
//                    }
//
//                }
  /*ovo je bilo pokusaj da se popravi ono sa tabovima ne znam da li radi sa tim ili bez toga, meni je radlo 
                ClassyNodeComposite cp = (ClassyNodeComposite) paket;
                //tabNames = new ArrayList<>();


                for (ClassyNode c : cp.getChildren()) {
                    if (c instanceof Diagram) {
                        //((Diagram)c).addSubscriber(this); // ?
                        DiagramView dw = new DiagramView((Diagram) c);
                        //dw.Update(new Notification(c, NotificationType.SHOWDIAGRAM));
                        c.addSubscriber(dw);
                        //tabs.add(dw);
                        //cp.addSubscriber(dw); // jel mi treba ovo?
                        tabNames.add(dw);
                    }

                }

*/
                createTabs();
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                showComponents();

            } else if (((Notification) notification).getNotificationType() == NotificationType.RENAME) {
                if (((Notification) notification).getObjectOfNotification() instanceof Project) // za promenu imena Autora projekta i imena projekta
                {
                    this.setAutor(((Project) ((Notification) notification).getObjectOfNotification()).getImeAutora());
                    this.setProjectNaziv(((Project) ((Notification) notification).getObjectOfNotification()).getName());
                } else if (((Notification) notification).getObjectOfNotification() instanceof Diagram) {

                }
            } else if (((Notification) notification).getNotificationType() == NotificationType.ADD) {
                if (((Notification) notification).getObjectOfNotification() instanceof Diagram) {
                    Diagram d = (Diagram) ((Notification) notification).getObjectOfNotification();
                    if (d.getParent() == paket) {
                        System.out.println("usao u 'jeste se dodalo na otvoreni paket' ");
                        DiagramView dv = new DiagramView(d);

//                        JScrollPane s=new JScrollPane();
//                        this.setPreferredSize(new Dimension(dv.getSize().width-50, dv.getSize().height-50));
//                        s.setSize(new Dimension(dv.getSize().width, dv.getSize().height));
//                        //s.setViewportView(this);
//
//                        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//                        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//
//                        //setPreferredSize(new Dimension(this.getSize().width,this.getSize().height));
//                        //
//                        // s.setBounds(0,0, 200,200);
//
//                        add(s);
//                        s.setVisible(true);
//
//                        setLayout(null);


                        //d.addSubscriber(this);
                        System.out.println(d.getSubscriberList());
                        d.addSubscriber(dv);
                        System.out.println(d.getSubscriberList());
                        //dv.Update(new Notification(d, NotificationType.SHOWDIAGRAM));
                        //paket.addSubscriber(dv);
                        this.getTabbedPane().addTab(dv.getDiagram().getName(), dv);
                    }


                    //tabs.add(dw);


                }
            } else if (((Notification) notification).getNotificationType() == NotificationType.DELETE) {
                if (((Notification) notification).getObjectOfNotification() instanceof Project) {
                    // vrv dopuniti kasnije
                    hideComponents();
                    return;
                }
                if (((Notification) notification).getObjectOfNotification() instanceof Package) {
                    // vrv dopuniti kasnije
                    hideComponents();
                    return;
                }
                if (((Notification) notification).getObjectOfNotification() instanceof Diagram) {
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        DiagramView dv = (DiagramView) tabbedPane.getComponentAt(i);
                        if (dv.getDiagram().getName().equals(((Diagram) ((Notification) notification).getObjectOfNotification()).getName())) {
                            tabbedPane.remove(i);
                            return;
                        }
                    }
                }

            }

        }
//        else if(notification.toString().equals("brisi"))
//        {
////            //removeAll();
////            //tabs.remove(DiagramView);
////            repaint();
////            revalidate();
//        }
//        else if(notification instanceof String) // sredi ove notifikacije, da se zna sta je sta
//        {
//            //setProjectNaziv(notification.toString());
//        }
//        else if(notification instanceof Diagram)
//        {
//            //DiagramView dw = new DiagramView();
//            //this.getTabs().addTab(((Diagram) notification).getName(), dw);
//            //MainFrame.getInstance().getPackageView().getTabs().addTab(((Diagram) notification).getName(), dw);
//            //((Diagram)notification).addSubscriber(dw);
//        }


    }

    //funkcije za stateManager
    public void StartDodajConnectionState()
    {
        stateManager.setDodajConnectionState();
    }

    public void StartDodajInterclassState()
    {
        stateManager.setDodajInterclassState();
    }

    public void StartObrisiState()
    {
        stateManager.setObrisiState();
    }
    public void StartPromeniKlasuState()
    {
        stateManager.setPromeniKlasuState();
    }

    public void StartSelektujState()
    {
        stateManager.setSelektujState();
    }
    public void StartDuplicirajState()
    {
        stateManager.setDuplicirajState();
    }
    public void StartZoomInState()
    {
        stateManager.setZoomInState();
    }
    public void StartZoomOutState() {
        stateManager.setZoomOutState();
    }

    public void misPritisnutmng(Point P, DiagramView dv)
    {
        stateManager.getCurrentState().misPritisnut(P,dv);
    }
    public void misPovucenmng(Point P, DiagramView dv)
    {
        stateManager.getCurrentState().misPovucen(P,dv);
    }
    public void misOtpustenmng(Point P, DiagramView dv)
    {
        stateManager.getCurrentState().misOtpusten(P,dv);
    }


}

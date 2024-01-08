package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.controller.stateSablon.StateManager;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PackageView extends JPanel implements ISubscriber {


    private JPanel desktopinfo;
    private Label projectlbl;
    private Label author;
    private JPanel tabs_panel;

    private JTabbedPane tabbedPane;

    private Package paket; // paket koji se trenutno prikazuje
    private Project project;

    private StateManager stateManager; //packageView je moderator za state sablon

    private DesniToolBar desniToolBar;

    private ArrayList<DiagramView> diagramViews;

    public PackageView() {
        super();
        desktopinfo = new JPanel();
        desktopinfo.setLayout(new BoxLayout(desktopinfo, BoxLayout.Y_AXIS));
        projectlbl = new Label("Current project: ");
        author = new Label("Author: ");
        desktopinfo.add(projectlbl);
        desktopinfo.add(author);

        this.setLayout(new BorderLayout());
        this.add(desktopinfo, BorderLayout.NORTH);
        tabs_panel = new JPanel();
        tabs_panel.setLayout(new BorderLayout());
        this.add(tabs_panel, BorderLayout.CENTER);
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        diagramViews =new ArrayList<DiagramView>();

        tabs_panel.add(tabbedPane);

        stateManager = new StateManager();

        desniToolBar=new DesniToolBar();
        add(desniToolBar, BorderLayout.EAST);
    }

//    public Package getPaket() {
//        return paket;
//    }

    public void setPaket(Package paket) {
        this.paket = paket;
        this.Update(new Notification(paket, NotificationType.SHOW));
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setAutor(String autor) {
        author.setText("Author: " + autor);
    }

    public void setProjectNaziv(String naziv) {
        projectlbl.setText("Current project: " + naziv);
    }

    private void showComponents() {
        projectlbl.setVisible(true);
        author.setVisible(true);
        tabs_panel.setVisible(true);
        tabbedPane.setVisible(true);
    }

    private void createTabs() {
        tabbedPane.removeAll();
        for(var child : paket.getChildren())
        {
            if(child instanceof Diagram) {
                var diagram = (Diagram) child;
                DiagramView dv = null;
                boolean created = false;
                for (DiagramView d : diagramViews)
                {
                    if(d.getDiagram().getName().equals(child.getName()))
                    {
                        dv = d;
                        created = true;
                        break;
                    }
                }
                if(!created)
                {
                    dv = new DiagramView(diagram);
                    diagram.addSubscriber(dv);
                    diagramViews.add(dv);
                }
                tabbedPane.add(diagram.getName(), dv);
            }
        }

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
                // Ovde nalazi projekat u kom se paket nalazi ----------
                ClassyNode node = (ClassyNode) paket;

                while (!(node instanceof Project)) {
                    node = node.getParent();
                }
                project = (Project)node;
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                node.addSubscriber(this);
                //---------------------------

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
                        diagramViews.add(dv);

                        System.out.println(d.getSubscriberList());
                        d.addSubscriber(dv);
                        System.out.println(d.getSubscriberList());

                        this.getTabbedPane().addTab(dv.getDiagram().getName(), dv);
                    }
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

    }

    public DiagramView trenutniDv()
    {
        DiagramView dv=(DiagramView) tabbedPane.getSelectedComponent();
        if(dv!=null)
            return dv;
        System.out.println("null je");
        return null;
    }

    public DiagramView diagramViewOfDiagram(Diagram diagram)
    {
        for (DiagramView dv : diagramViews)
            if(dv.getDiagram().getName().equals(diagram.getName()))
            {
                return dv;
        }
        return new DiagramView(diagram);
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

    //-----------------------------------------------------------------
    public StateManager getStateManager() {
        return stateManager;
    }
}

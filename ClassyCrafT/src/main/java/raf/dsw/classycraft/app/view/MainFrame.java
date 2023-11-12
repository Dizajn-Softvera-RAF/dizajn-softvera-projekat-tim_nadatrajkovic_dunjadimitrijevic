package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.controller.OpenPackageAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.message.Message;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.tree.ClassyTreeImplementation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    private ActionManager actionManager;
    private ClassyTree classyTree;

    private JTree projectExplorer;
    private JTabbedPane tabs;
    private JPanel desktopinfo;
    private Label project;
    private Label author;
    private JPanel desktop;
    private JPanel tabs_panel;
    private JScrollPane scroll;
    private JSplitPane split;

    private PackageView packageView;

    private MainFrame(){

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        actionManager = new ActionManager();

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);


        classyTree = new ClassyTreeImplementation();

        //System.out.println(ApplicationFramework.getInstance());
        //System.out.println(ApplicationFramework.getInstance().getClassyRepositoryImplementation());
        //System.out.println("msg: "+ ApplicationFramework.getInstance().getMessageGenerator());
        projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());

//        desktopinfo = new JPanel();
//        desktopinfo.setLayout(new BoxLayout(desktopinfo, BoxLayout.Y_AXIS));
//        project = new Label("Current project: ");
//        author = new Label("Author: ");
//        desktopinfo.add(project);
//        desktopinfo.add(author);
//
//        desktop = new JPanel();
//        desktop.setLayout(new BorderLayout());
//        desktop.add(desktopinfo, BorderLayout.NORTH);
//        tabs_panel = new JPanel();
//        tabs_panel.setLayout(new BorderLayout());
//        desktop.add(tabs_panel,BorderLayout.CENTER);

        packageView=new PackageView();


        scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll,packageView);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

//        tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
//        //tabs.setSize(500,300);
//
//        tabs_panel.add(tabs);

        projectExplorer.addMouseListener(new OpenPackageAction());

    }

    public JTree getProjectExplorer() {
        return projectExplorer;
    }

//    public JTabbedPane getTabs() {
//        return tabs;
//    }

//    public void setTabs(JTabbedPane tabs) {
//        this.tabs = tabs;
//    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public ClassyTree getClassyTree() {
        return classyTree;
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }


    @Override
    public void Update(Object notification) {
        if(notification instanceof Message)
        {
            JFrame f=new JFrame();
            Message msg= (Message)notification;
            JOptionPane.showMessageDialog(f,msg.getSadrzaj(),msg.getTip().toString(),msg.getTip().ordinal());
        }
    }

    public PackageView getPackageView() {
        return packageView;
    }

}

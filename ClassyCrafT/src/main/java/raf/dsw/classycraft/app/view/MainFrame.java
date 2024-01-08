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
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        actionManager = new ActionManager();

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);


        classyTree = new ClassyTreeImplementation();

        projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());

        packageView=new PackageView();


        scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll,packageView);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        projectExplorer.addMouseListener(new OpenPackageAction());

    }

    public void setPackageView(PackageView packageView) {
        this.packageView = packageView;
    }

//    public JTree getProjectExplorer() {
//        return projectExplorer;
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
            JFrame f =new JFrame();
            Message msg = (Message)notification;
            JOptionPane.showMessageDialog(f,msg.getSadrzaj(),msg.getTip().toString(),msg.getTip().ordinal());
        }
    }

    public PackageView getPackageView() {
        return packageView;
    }

}

package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.message.Message;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.tree.ClassyTreeImplementation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    private ActionManager actionManager;
    private ClassyTree classyTree;
    //buduca polja za sve komponente view-a na glavnom prozoru

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

         // todo preko AppFrameworka jtree, split pane i tree
        classyTree = new ClassyTreeImplementation();

        //System.out.println(ApplicationFramework.getInstance());
        //System.out.println(ApplicationFramework.getInstance().getClassyRepositoryImplementation());
        //System.out.println("msg: "+ ApplicationFramework.getInstance().getMessageGenerator());
        JTree projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepositoryImplementation().getRoot());

        JPanel desktop = new JPanel();

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll,desktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }

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
}

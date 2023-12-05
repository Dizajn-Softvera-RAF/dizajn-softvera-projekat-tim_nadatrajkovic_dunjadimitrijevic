package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.AboutUsWindow;
import raf.dsw.classycraft.app.view.AddPackageChildWindow;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddNodeAction extends AbstractClassyAction{
    private NodeType type;
    private static boolean closed_window = false;

    public static boolean isClosed_window() {
        return closed_window;
    }

    public static void setClosed_window(boolean closed_window) {
        AddNodeAction.closed_window = closed_window;
    }

    public AddNodeAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Add");
        putValue(SHORT_DESCRIPTION, "Add node");

    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        //NodeType type;//= NodeType.DIAGRAM; //todo bira tip noda
        if(selected == null)
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("izabrani cvor nije izabran", MessageType.ERROR);
            return;
        }

        if(selected.getClassyNode() instanceof ClassyNodeComposite && selected.getClassyNode() instanceof Package)
        {
            Object[] options = {"New Package", "New Diagram"};
            //String nodeType = "";
            int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi paket ili diagram", "Add to package", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
            if(choice == 0)
            {
                //nodeType = "Package";
                MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.PACKAGE);
                return;
            }
            if(choice == 1)
            {
                MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.DIAGRAM);
                return;
            }
        }
        else
        {
            MainFrame.getInstance().getClassyTree().addChild(selected, type);
        }

//        if(selected.getClassyNode() instanceof Package)
//        {
//            AddPackageChildWindow pcw=new AddPackageChildWindow(MainFrame.getInstance());
//            pcw.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//            closed_window = false;
//            pcw.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    super.windowClosed(e);
//
//                    System.out.println("zatvoren pcw prozor");
//                    closed_window = true;
//                    System.out.println(closed_window);
//                }
//            });
//            pcw.setVisible(true);
//            //System.out.println(closed_window);
////            if(closed_window)
////            {
////
////                return;
////            }
//
//
//
////            if(pcw.getR1().isSelected())
////                type = NodeType.PROJECT;
////            else
////                type = NodeType.DIAGRAM;
//
//        }
//        else
//        {
//            MainFrame.getInstance().getClassyTree().addChild(selected, type);
//        }
        //System.out.println("pre addChild: "+ type.toString());
        //type = MainFrame.getInstance().getSelectedPackageChild();

        //MainFrame.getInstance().getClassyTree().addChild(selected, type);
        //System.out.println("add node clicked");
    }
}

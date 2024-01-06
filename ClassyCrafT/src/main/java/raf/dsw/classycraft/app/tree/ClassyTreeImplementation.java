package raf.dsw.classycraft.app.tree;

//import raf.dsw.classycraft.app.controller.DeserializerCrtac;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.message.MessageGenerator;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ClassyTreeImplementation implements ClassyTree{

    ClassyTreeItem root;
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
//    private static int project_cnt; // ovi cnt sluze kao neko (privremeno?) resenje za imenovanje
//    private static  int package_cnt;
//    private static int diagram_cnt;
    //private MessageGenerator messageGenerator = new MessageGenerator();



    public ClassyTreeItem getRoot() {
        return root;
    }

    public ClassyTreeView getTreeView() {
        return treeView;
    }

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        System.out.println("generate tree" + root.toString());

        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(MainFrame.getInstance());

        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent, NodeType type) {
        // glavni deo
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite)) // ako je izabrani cvor Diagram, ne moze
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("izabrani cvor ne moze da bude roditelj", MessageType.ERROR);
            return; // todo ispisati gresku/info, mozda treba da bude publisher pa da obavesti main frame

        }



        ClassyNode new_node = createChild(parent.getClassyNode(), type);
        System.out.println(new_node);
        parent.add(new ClassyTreeItem(new_node));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(new_node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public void addDiagramElement(ClassyTreeItem parent, ClassyNode element)
    {
        System.out.println(element);
        System.out.println("parent u addDiagramelement "+parent.getClassyNode().getName());
        parent.add(new ClassyTreeItem(element));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(element);
        TreeNode[] nodes=parent.getPath();
        System.out.println(nodes.toString());
        TreePath tp=new TreePath(nodes);
        treeView.expandPath(tp);//treeView.getSelectionPath());

        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(ClassyTreeItem child) {
        if((child.getClassyNode() instanceof ProjectExplorer))
        {
            return;
        }
        //child.getClassyNode().notifySubscribers("brisi");
        ClassyNodeComposite parent = (ClassyNodeComposite) child.getClassyNode().getParent();
        parent.removeChild(child.getClassyNode());
        deleteChild(child.getClassyNode());
        child.removeFromParent();

        SwingUtilities.updateComponentTreeUI(treeView);

    }
    private void deleteChild(ClassyNode child)
    {
        ApplicationFramework.getInstance().getClassyRepository().removeNode(child);
    }

    protected ClassyNode createChild(ClassyNode parent, NodeType type)
    {
        return ApplicationFramework.getInstance().getClassyRepository().createNode(parent, type);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public ClassyTreeItem NadjiClassyTreePrekoClassyNode(ClassyNode node,ClassyTreeItem parent) {
        if (parent.getClassyNode().equals(node))//ov
        {
            System.out.println("naso odgovarajuci1 " + parent.toString()); // nikad ne ulazi ovde

            return parent;
        }
        if (parent.getChildCount() > 0) {
            System.out.println("parent: "+ parent.getClassyNode().getName() + " decacnt: " + parent.getChildCount() + " trazimo node: "+ node.getName());
            for (int i = 0; i < parent.getChildCount(); i++) {
                TreeNode child = parent.getChildAt(i);
                if (child instanceof ClassyTreeItem) {
                    ClassyTreeItem childItem = (ClassyTreeItem) child;
                    ClassyTreeItem trenutni=NadjiClassyTreePrekoClassyNode(node, childItem);
                    if(trenutni==null)
                        continue;
                    return trenutni;
                    /*
                    if(NadjiClassyTreePrekoClassyNode(node, childItem) == null)
                        continue;
                    if (NadjiClassyTreePrekoClassyNode(node, childItem).getClassyNode().equals(node)) {
                        return childItem;
                    }*/
//                    else
//                    {
//                        continue; // ovde zelim da predje na sledece dete iz petlje, nzm zasto ne bi registrovao ovaj continue?
//                    }
//                if (childItem.getClassyNode().equals(node)) {
//                    System.out.println("naso odgovarajuci " + childItem.toString());
//
//                    return childItem;
//                }
                    //NadjiClassyTreePrekoClassyNode(node, childItem);
                } else {
                    System.out.println("tree node nije instance of classytreeitem" + node.getName());
                }
            }
        } else {
            System.out.println("nije naso odgovarajuci" + parent.getClassyNode().getName());
            return null;
        }
        return null;
    }

    @Override
    public void loadProject(Project node) {

        ClassyTreeItem loadedProject = new ClassyTreeItem(node);
        //treeModel.getRoot()
        //MainFrame.getInstance().getClassyTree()
        //this.addChild(loadedProject, NodeType.PROJECT);


        ClassyTreeItem root1 = (ClassyTreeItem) (treeModel.getRoot());
        root1.add(loadedProject);

        ClassyNodeComposite classyNode = (ClassyNodeComposite) root1.getClassyNode();
        classyNode.addChild(node);
        node.setParent(root1.getClassyNode());

        prolazDeca(node,loadedProject);

        TreeNode[] nodes=root1.getPath();
        System.out.println(nodes.toString());
        TreePath tp=new TreePath(nodes);
        treeView.expandPath(tp);//treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

        //DeserializerCrtac deserializerCrtac=new DeserializerCrtac();


        //treeView.expandPath(treeView.getSelectionPath());
        //SwingUtilities.updateComponentTreeUI(treeView);
    }

    private void prolazDeca(ClassyNode node,ClassyTreeItem nodeItem)
    {
        if(!(node instanceof ClassyNodeComposite))
            return;

        for (ClassyNode c :((ClassyNodeComposite)node).getChildren())
        {
            c.setParent(node);
            ClassyTreeItem item = new ClassyTreeItem(c);
            nodeItem.add(item);

            prolazDeca(c,item);
        }
    }

}

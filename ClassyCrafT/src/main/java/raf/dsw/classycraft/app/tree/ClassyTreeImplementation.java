package raf.dsw.classycraft.app.tree;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.message.MessageGenerator;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private static int project_cnt; // ovi cnt sluze kao neko (privremeno?) resenje za imenovanje
    private static  int package_cnt;
    private static int diagram_cnt;
    private MessageGenerator messageGenerator = new MessageGenerator();

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        System.out.println("generate tree" + root.toString());
        project_cnt = 0;

        messageGenerator.addSubscriber(MainFrame.getInstance());

        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent, NodeType type) {
        //todo implementirati - glavni deo
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite)) // ako je izabrani cvor Diagram, ne moze
        {
            messageGenerator.GenerateMessage("izabrani cvor ne moze da bude roditelj", MessageType.ERROR);
            return; // todo ispisati gresku/info, mozda treba da bude publisher pa da obavesti main frame

        }

        ClassyNode new_node = createChild(parent.getClassyNode(), type);
        parent.add(new ClassyTreeItem(new_node));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(new_node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }
    protected ClassyNode createChild(ClassyNode parent, NodeType type)
    {
        return ApplicationFramework.getInstance().getClassyRepository().createNode(parent,type);

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }
}

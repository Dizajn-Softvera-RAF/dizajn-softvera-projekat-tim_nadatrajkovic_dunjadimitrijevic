package raf.dsw.classycraft.app.tree;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private static int project_cnt; // ovi cnt sluze kao neko (privremeno?) resenje za imenovanje
    private static  int package_cnt;
    private static int diagram_cnt;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        System.out.println("generate tree" + root.toString());
        project_cnt = 0;
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        //todo implementirati - glavni deo
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite)) // ako je izabrani cvor Diagram, ne moze
        {
            return; // todo ispisati gresku/info
        }

        ClassyNode new_node = createChild(parent.getClassyNode());
        parent.add(new ClassyTreeItem(new_node));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(new_node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }
    private ClassyNode createChild(ClassyNode parent)
    {
        // todo ovo se radi preko Factory sablona
        if (parent instanceof ProjectExplorer)
        {
            project_cnt++;
            return  new Project("Project" + project_cnt, parent);
        }
        if (parent instanceof Project)
        {
            package_cnt++;
            return  new Package("Package" + package_cnt, parent);
        }

        return null;
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }
}

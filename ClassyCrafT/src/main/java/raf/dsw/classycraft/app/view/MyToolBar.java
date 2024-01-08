package raf.dsw.classycraft.app.view;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getAddNodeAction());
        add(MainFrame.getInstance().getActionManager().getAddAutorAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getLoadAction());
        add(MainFrame.getInstance().getActionManager().getSavePatternAction());
        add(MainFrame.getInstance().getActionManager().getLoadPatternAction());

        add(MainFrame.getInstance().getActionManager().getSaveImageAction());


    }
}

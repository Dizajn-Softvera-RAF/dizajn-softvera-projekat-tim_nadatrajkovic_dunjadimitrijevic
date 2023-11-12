package raf.dsw.classycraft.app.controller;

import javax.swing.*;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    private AddAutorAction addAutorAction;
    private DeleteAction deleteAction;
    public ActionManager() {
        initialiseActions();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
    public AboutUsAction getAboutUsAction(){return aboutUsAction;}

//    public PackageChildAction getPackageChildAction() {
//        return packageChildAction;
//    }

    public AddNodeAction getAddNodeAction() {
        return addNodeAction;
    }

    public AddAutorAction getAddAutorAction() {
        return addAutorAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    private void initialiseActions()
    {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        addNodeAction = new AddNodeAction();
        addAutorAction = new AddAutorAction();
        deleteAction = new DeleteAction();

    }

}

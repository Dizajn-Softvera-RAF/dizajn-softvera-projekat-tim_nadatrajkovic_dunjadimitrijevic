package raf.dsw.classycraft.app.controller;

import javax.swing.*;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    //private PackageChildAction packageChildAction;
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

    private void initialiseActions()
    {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        addNodeAction = new AddNodeAction();
    }

}

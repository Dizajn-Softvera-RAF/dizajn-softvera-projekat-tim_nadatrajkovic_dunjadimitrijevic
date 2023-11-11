package raf.dsw.classycraft.app.controller;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    public ActionManager() {
        initialiseActions();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
    public AboutUsAction getAboutUsAction(){return aboutUsAction;}

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

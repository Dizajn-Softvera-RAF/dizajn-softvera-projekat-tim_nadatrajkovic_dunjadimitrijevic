package raf.dsw.classycraft.app.controller;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    public ActionManager() {
        initialiseActions();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
    public AboutUsAction getAboutUsAction(){return aboutUsAction;}

    private void initialiseActions()
    {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();

    }
}

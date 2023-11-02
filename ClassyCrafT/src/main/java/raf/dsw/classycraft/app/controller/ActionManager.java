package raf.dsw.classycraft.app.controller;

public class ActionManager {

    private ExitAction exitAction;
    public ActionManager() {
        initialiseActions();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    private void initialiseActions()
    {
        exitAction = new ExitAction();
    }
}

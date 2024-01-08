package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.akcijeDesniTulbar.*;

import javax.swing.*;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    private AddAutorAction addAutorAction;
    private DeleteAction deleteAction;

    private SaveAction saveAction;
    private LoadAction loadAction;
    private SavePatternAction savePatternAction;
    private LoadPatternAction loadPatternAction;

    private SaveImageAction saveImageAction;

    private UndoAction undoAction;
    private RedoAction redoAction;


    private DodajConnectionAction dodajConnectionAction;
    private DodajInterclassAction dodajInterclassAction;
    private ObrisiAction obrisiAction;
    private PromeniKlasuAction promeniKlasuAction;
    private SelektujAction selektujAction;
    private DuplicirajAction duplicirajAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;


    public DodajConnectionAction getDodajConnectionAction() {
        return dodajConnectionAction;
    }


    public DodajInterclassAction getDodajInterclassAction() {
        return dodajInterclassAction;
    }


    public ObrisiAction getObrisiAction() {
        return obrisiAction;
    }


    public PromeniKlasuAction getPromeniKlasuAction() {
        return promeniKlasuAction;
    }

    public SelektujAction getSelektujAction() {
        return selektujAction;
    }
    public DuplicirajAction getDuplicirajAction() {
        return duplicirajAction;
    }


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

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public LoadAction getLoadAction() {
        return loadAction;
    }

    public SavePatternAction getSavePatternAction() {
        return savePatternAction;
    }

    public LoadPatternAction getLoadPatternAction() {
        return loadPatternAction;
    }

    public SaveImageAction getSaveImageAction()
    {return saveImageAction;}

    private void initialiseActions()
    {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        addNodeAction = new AddNodeAction();
        addAutorAction = new AddAutorAction();
        deleteAction = new DeleteAction();
        saveAction = new SaveAction();
        loadAction = new LoadAction();
        savePatternAction = new SavePatternAction();
        loadPatternAction = new LoadPatternAction();
        saveImageAction = new SaveImageAction();

        undoAction = new UndoAction();
        redoAction = new RedoAction();

        dodajConnectionAction=new DodajConnectionAction();
        dodajInterclassAction=new DodajInterclassAction();
        obrisiAction=new ObrisiAction();
        promeniKlasuAction=new PromeniKlasuAction();
        selektujAction=new SelektujAction();
        duplicirajAction = new DuplicirajAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
    }

    public ZoomInAction getZoomInAction() {
        return zoomInAction;
    }

    public ZoomOutAction getZoomOutAction() {
        return zoomOutAction;
    }
}

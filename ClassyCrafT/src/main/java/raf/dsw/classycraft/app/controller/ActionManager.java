package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.akcijeDesniTulbar.*;

import javax.swing.*;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private AddNodeAction addNodeAction;
    private AddAutorAction addAutorAction;
    private DeleteAction deleteAction;


    private DodajConnectionAction dodajConnectionAction;
    private DodajInterclassAction dodajInterclassAction;
    private ObrisiAction obrisiAction;
    private PromeniKlasuAction promeniKlasuAction;
    private SelektujAction selektujAction;

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

        dodajConnectionAction=new DodajConnectionAction();
        dodajInterclassAction=new DodajInterclassAction();
        obrisiAction=new ObrisiAction();
        promeniKlasuAction=new PromeniKlasuAction();
        selektujAction=new SelektujAction();

    }

}

package raf.dsw.classycraft.app.controller.stateSablon;

public class StateManager {
    private DodajConnectionState dodajConnectionState;
    private DodajInterclassState dodajInterclassState;
    private ObrisiState obrisiState;
    private PromeniKlasuState promeniKlasuState;
    private  SelektujState selektujState;
    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    public StateManager(){
        initStates();
    }

    private void initStates() {
        dodajConnectionState=new DodajConnectionState();
        dodajInterclassState=new DodajInterclassState();
        obrisiState=new ObrisiState();
        promeniKlasuState=new PromeniKlasuState();
        selektujState=new SelektujState();
        currentState=dodajInterclassState;
    }

    public void setDodajConnectionState() {
        this.currentState = dodajConnectionState;
        System.out.println("postavio currState na dodaj connection");
    }

    public void setDodajInterclassState() {
        this.currentState = dodajInterclassState;

        System.out.println("postavio currState na dodaj interclass");
    }

    public void setObrisiState() {
        this.currentState = obrisiState;

        System.out.println("postavio currState na obirisi");
    }

    public void setPromeniKlasuState() {
        this.currentState = promeniKlasuState;

        System.out.println("postavio currState na promeni");
    }

    public void setSelektujState() {
        this.currentState = selektujState;

        System.out.println("postavio currState na selektuj");
    }
}

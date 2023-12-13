package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromeniKlasuState implements State{
    //private Point pocetnaTacka;
    private Atribut napraviAtributOdStringa(String line)
    {
        Pattern pattern = Pattern.compile("\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);

        //+ime: String
        if (!line.equals("")) {
            Matcher matcher = pattern.matcher(line);
            //System.out.println("linija2 " + line);
            if (matcher.find()) {
                line = line.trim();
                char s0 = line.charAt(0);
                InterclassVidljivost vidljivost;
                if (s0 == '~') {
                    vidljivost = InterclassVidljivost.PROTECTED;
                } else if (s0 == '-') {
                    vidljivost = InterclassVidljivost.PRIVATE;
                } else {
                    vidljivost = InterclassVidljivost.PUBLIC;
                }
                //System.out.println("trenutna linija" + line);

                String nazivAtributa = line.substring(1, line.indexOf(":")).trim();
                //System.out.println("naziv atributa trimovanje:"+nazivAtributa);
                String tip = line.substring(line.indexOf(":") + 1).trim();
                Atribut atribut = new Atribut(nazivAtributa, vidljivost, tip);

                //System.out.println("trenutna metoda" + atribut.toString());
                return atribut;
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Atribut " + line + " nije unet kako treba", MessageType.WARNING);
                //return; // pravice interfejs koji ima ime i metode koje odgovaraju sablonu, a nece dodati lose unete metode
            }
        }
        System.out.println("line je prazna - atribut");
        return null;
    }

    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        //pocetnaTacka = P;
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
        //nista
    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {
        ElementPainter kliknut = null;
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep.elementAt(P))
            {
                kliknut = ep;
                break;
            }
        }
        if(kliknut != null)
        {
            if(kliknut instanceof KlasaPainter)
            {
                Klasa klasa = (Klasa)kliknut.getDiagramElement();
                JTextField nazivtf=new JTextField();
                JTextArea poljatf=new JTextArea();
                JTextArea metodetf=new JTextArea();

                JComponent[] inputs = new JComponent[] {
                        new JLabel("ImeKlase"),
                        nazivtf,
                        new JLabel("Polja razdvojena tacka zarezom, primer: +ime: String"),
                        poljatf,
                        new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                        metodetf
                };
                nazivtf.setText(klasa.getName());
                for (ClassContent classContent:klasa.getClassContent()) {
                    if(classContent instanceof Atribut)
                    {
                        if(poljatf.getText().equals(""))
                            poljatf.setText(classContent.toString());
                        else
                            poljatf.setText(poljatf.getText() +";"+ classContent.toString());
                    }
                    else if(classContent instanceof Metoda)
                    {
                        if(metodetf.getText().equals(""))
                            metodetf.setText(classContent.toString());
                        else
                            metodetf.setText(metodetf.getText() +";"+ classContent.toString());
                    }
                }
                int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novaKlasa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                String novo_ime = nazivtf.getText();
                if(rez == 0) //kliknuto ok -> sacuvaj promene u klasi i prikazi ih
                {
                    if(novo_ime.equals(""))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime klase", MessageType.WARNING);
                    }
                    else
                    {
                        klasa.setName(novo_ime);
                        if(!poljatf.getText().trim().equals(""))
                        {
                            for(String line : poljatf.getText().split(";"))
                            {
                                Atribut atribut = napraviAtributOdStringa(line);
                                // vidi kako ovo - da li da obrisem sve atribute prvo, pa onda sve iz tf da dodajem (i stare i sad dodate)

                                if(atribut != null && !klasa.getClassContent().contains(atribut))
                                    klasa.addClassContent(atribut); //ovo dodaje i sta treba i sta ne treba (i stara i nova polja)
                            }
                        }
                    }
                }
                else if(rez == 1) //kliknuto cancel -> nemoj da sacuvas promene
                {

                }
                dv.repaint(); //mozda?
            }
        }
        else
        {
            //nista
        }
    }
}

package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.commandPattern.implementations.AddInterclassCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.EnumeracijaPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;
import raf.dsw.classycraft.app.view.painteri.InterfejsPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DodajInterclassState implements State{

    private Metoda napraviMetoduOdStringa(String line)
    {
        Pattern pattern = Pattern.compile(
                "\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*\\(((\\s*[a-zA-z0-9_]+\\s+[a-zA-z0-9_]+\\s*)(,\\s*[a-zA-z0-9_]+\\s+[a-zA-z0-9_]+)*)*\\s*\\)\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);
        if (!line.equals("")) {
            Matcher matcher = pattern.matcher(line);
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

                String nazivMetode = line.substring(1, line.indexOf("(")).trim();
                String povratniTip = line.substring(line.indexOf(":") + 1).trim();
                Metoda metoda = new Metoda(nazivMetode, vidljivost, povratniTip);


                String parametriStr = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
                if (!parametriStr.equals("")) {
                    String[] parametri = parametriStr.split(",");
                    for (String parametar : parametri) {
                        parametar = parametar.trim();
                        String tip = parametar.substring(0, parametar.indexOf(" "));
                        String nazivParametra = parametar.substring(parametar.indexOf(" ") + 1).trim();
                        metoda.addParametarFunkcije(new Atribut(nazivParametra, InterclassVidljivost.PRIVATE, tip));
                    }
                }
                return metoda;
            }
            else {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Metoda "+ line + " nije uneta kako treba" , MessageType.WARNING);
            }
        }
        return null;
    }
    private Atribut napraviAtributOdStringa(String line)
    {
        Pattern pattern = Pattern.compile("\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);

        if (!line.equals("")) {
            Matcher matcher = pattern.matcher(line);
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

                String nazivAtributa = line.substring(1, line.indexOf(":")).trim();
                String tip = line.substring(line.indexOf(":") + 1).trim();
                Atribut atribut = new Atribut(nazivAtributa, vidljivost, tip);

                return atribut;
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Atribut " + line + " nije unet kako treba", MessageType.WARNING);
                //return; // pravice interfejs koji ima ime i metode koje odgovaraju sablonu, a nece dodati lose unete metode
            }
        }
        return null;
    }
    private ClanEnumeracije napraviClanEnumaOdStringa(String line)
    {
        Pattern pattern = Pattern.compile("\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);
        if(!line.equals(""))
        {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                line = line.trim();
                ClanEnumeracije ce = new ClanEnumeracije(line);
                return ce;
            }
            else
            {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Clan enumeracije " + line + " nije unet kako treba", MessageType.WARNING);
            }
        }
        return null;
    }


    @Override
    public void misPritisnut(Point P, DiagramView dv) {

        Interclass napravljenaInterklasa=null;


        Object[] options = {"Klasa","Interfejs","Enum"};
        int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        if(choice == 0)//crta klasu
        {
            JTextField nazivtf=new JTextField();
            JTextArea poljata=new JTextArea();
            JTextArea metodeta=new JTextArea();

            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeKlase"),
                    nazivtf,
                    new JLabel("Polja razdvojena tacka zarezom, primer: +ime: String"),
                    poljata,
                    new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                    metodeta
            };
            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novaKlasa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(rez == 0) // ako je kliknut OK
            {
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se klasa kreirala", MessageType.WARNING);
                }
                else
                {
                    napravljenaInterklasa = new Klasa(s, dv.getDiagram(), P);
                    if(!poljata.getText().trim().equals(""))
                    {
                        for(String line : poljata.getText().split(";"))
                        {
                            Atribut atribut = napraviAtributOdStringa(line);
                            if(atribut != null)
                                napravljenaInterklasa.addClassContent(atribut);
                        }
                    }

                    if(!metodeta.getText().trim().equals(""))
                    {
                        for (String line : metodeta.getText().split(";")) {
                            Metoda metoda = napraviMetoduOdStringa(line);
                            if(metoda != null)
                                napravljenaInterklasa.addClassContent(metoda);
                        }
                    }
                }

            }
            else if (rez == 1)
            {
            }

        }
        if(choice == 1)//crta interfejs
        {
            JTextField nazivtf=new JTextField();

            JTextArea metodeta=new JTextArea();

            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeInterfejsa"),
                    nazivtf,
                    new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                    metodeta
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novInterfejs",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(rez == 0) // ako je kliknut OK
            {
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se interfejs kreirao", MessageType.WARNING);
                }
                else
                {
                    napravljenaInterklasa = new Interfejs(s,dv.getDiagram(), P);
                    if(!metodeta.getText().trim().equals(""))
                    {
                        for (String line : metodeta.getText().split(";")) {
                            Metoda metoda = napraviMetoduOdStringa(line);
                            if(metoda != null)
                                napravljenaInterklasa.addClassContent(metoda);
                        }
                    }
                }
            }
            else if(rez == 1)
            {
            }
        }

        if(choice==2)//crta enumeraciju
        {
            JTextField nazivtf=new JTextField();

            JTextField enumeracije=new JTextField();

            JComponent[] inputs = new JComponent[] {
                    new JLabel("Ime enumeracije"),
                    nazivtf,
                    new JLabel("Upisi elemente enumeracije razdvojene zarezom"),
                    enumeracije
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"nova enumeracija",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(rez == 0)
            {
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se enumeracija kreirala", MessageType.WARNING);
                }
                else {
                    napravljenaInterklasa = new Enumeracija(s, dv.getDiagram(), P);
                    if (!enumeracije.getText().trim().equals("")) {
                        for (String line : enumeracije.getText().split(",")) {
                            ClanEnumeracije clanEnumeracije = napraviClanEnumaOdStringa(line);
                            if (clanEnumeracije != null)
                                napravljenaInterklasa.addClassContent(clanEnumeracije);
                        }
                    }
                }
            }
            else if(rez == 1)
            {
            }
        }


        if(napravljenaInterklasa!=null) {
            InterclassPainter interclassPainter=null;
            if(napravljenaInterklasa instanceof Klasa)
            {
                interclassPainter = new KlasaPainter(napravljenaInterklasa);
            }
            if(napravljenaInterklasa instanceof Interfejs)
            {
                interclassPainter = new InterfejsPainter(napravljenaInterklasa);
            }
            if(napravljenaInterklasa instanceof Enumeracija)
            {
                interclassPainter = new EnumeracijaPainter(napravljenaInterklasa);
            }
            AddInterclassCommand addInterclassCommand = new AddInterclassCommand(dv,napravljenaInterklasa,interclassPainter);

            dv.getCommandManager().addCommand(addInterclassCommand);
            //ovde dodamo u dv-u kao addCommand
        }
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}

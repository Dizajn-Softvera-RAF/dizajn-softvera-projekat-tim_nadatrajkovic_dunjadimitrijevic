package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.commandPattern.implementations.EditInterclassCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.*;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromeniKlasuState implements State{

    private Interclass nepromenjenaInterclass;
    private Interclass promenjenaInterclass;
    private InterclassPainter zvanicnaInterclass;

    private Interclass duplicirajElement(Interclass original)
    {
        Interclass novi_element = null;
        Point P = original.getPocetnaTacka();
        if(original instanceof Klasa)
        {
            novi_element = new Klasa(original.getName(), original.getParent(), P);

        }
        else if(original instanceof Interfejs)
        {
            novi_element = new Interfejs(original.getName(), original.getParent(), P);

        }
        else if(original instanceof Enumeracija)
        {
            novi_element = new Enumeracija(original.getName(), original.getParent(), P);
        }
        if(novi_element != null)
        {
            for (ClassContent classContent : ((Interclass) original).getSadrzaj()) {
                novi_element.addClassContent(classContent);
            }
        }

        return novi_element;
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
            }
        }
        return null;
    }

    @Override
    public void misPritisnut(Point P, DiagramView dv) {
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
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
        if(kliknut != null && kliknut instanceof InterclassPainter)
        {
            nepromenjenaInterclass = duplicirajElement((Interclass) kliknut.getDiagramElement());
            promenjenaInterclass = duplicirajElement((Interclass) kliknut.getDiagramElement());
            zvanicnaInterclass = (InterclassPainter) kliknut;
            boolean sacuvaj_promene = false;
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
                for (ClassContent classContent:klasa.getSadrzaj()) {
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
                int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"Menjanje sadrzaja klase",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

                if(rez == 0) //kliknuto ok -> sacuvaj promene u klasi i prikazi ih
                {
                    String novo_ime = nazivtf.getText();
                    if(novo_ime.equals(""))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime klase", MessageType.WARNING);
                    }
                    else
                    {
                        promenjenaInterclass.setName(novo_ime);
                        promenjenaInterclass.getSadrzaj().clear();
                        if(!poljatf.getText().trim().equals(""))
                        {
                            for(String line : poljatf.getText().split(";"))
                            {
                                Atribut atribut = napraviAtributOdStringa(line);
                                if(atribut != null);
                                {
                                    promenjenaInterclass.addClassContent(atribut);
                                }
                            }
                        }
                        if(!metodetf.getText().trim().equals(""))
                        {
                            for (String line : metodetf.getText().split(";")) {
                                Metoda metoda = napraviMetoduOdStringa(line);
                                if(metoda != null)
                                    promenjenaInterclass.addClassContent(metoda);
                            }
                        }
                        sacuvaj_promene = true;
                    }
                }
                else if(rez == 1) //kliknuto cancel -> nemoj da sacuvas promene
                {
                }
            }
            else if (kliknut instanceof InterfejsPainter)
            {
                Interfejs interfejs = (Interfejs)kliknut.getDiagramElement();
                JTextField nazivtf=new JTextField();

                JTextArea metodetf=new JTextArea();

                JComponent[] inputs = new JComponent[] {
                        new JLabel("ImeInterfejsa"),
                        nazivtf,
                        new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                        metodetf
                };
                nazivtf.setText(interfejs.getName());
                for (ClassContent classContent:interfejs.getSadrzaj()) {
                    if(classContent instanceof Metoda)
                    {
                        if(metodetf.getText().equals(""))
                            metodetf.setText(classContent.toString());
                        else
                            metodetf.setText(metodetf.getText() +";"+ classContent.toString());
                    }
                }
                int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"Menjanje sadrzaja interfejsa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(rez == 0)
                {
                    String novo_ime = nazivtf.getText();
                    if(novo_ime.equals(""))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime interfejsa", MessageType.WARNING);
                    }
                    else {
                        promenjenaInterclass.setName(novo_ime);
                        promenjenaInterclass.getSadrzaj().clear();
                        if(!metodetf.getText().trim().equals(""))
                        {
                            for (String line : metodetf.getText().split(";")) {
                                Metoda metoda = napraviMetoduOdStringa(line);
                                if(metoda != null)
                                    promenjenaInterclass.addClassContent(metoda);
                            }
                        }
                        sacuvaj_promene = true;
                    }
                }
            }
            else if(kliknut instanceof EnumeracijaPainter)
            {
                Enumeracija enumeracija = (Enumeracija) kliknut.getDiagramElement();
                JTextField nazivtf=new JTextField();

                JTextField enumeracijetf=new JTextField();

                JComponent[] inputs = new JComponent[] {
                        new JLabel("Ime enumeracije"),
                        nazivtf,
                        new JLabel("Upisi elemente enumeracije razdvojene zarezom"),
                        enumeracijetf
                };
                nazivtf.setText(enumeracija.getName());
                for (ClassContent classContent:enumeracija.getSadrzaj()) {
                    if(classContent instanceof ClanEnumeracije)
                    {
                        if(enumeracijetf.getText().equals(""))
                            enumeracijetf.setText(classContent.toString());
                        else
                            enumeracijetf.setText(enumeracijetf.getText() +","+ classContent.toString());
                    }
                }
                int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"Menjanje sadrzaja enumeracije",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(rez == 0)
                {
                    String novo_ime = nazivtf.getText();
                    if(novo_ime.equals(""))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime enumeracije", MessageType.WARNING);
                    }
                    else {
                        promenjenaInterclass.setName(novo_ime);
                        promenjenaInterclass.getSadrzaj().clear();
                        if(!enumeracijetf.getText().trim().equals(""))
                        {
                            for (String line : enumeracijetf.getText().split(",")) {
                                ClanEnumeracije clanEnumeracije = napraviClanEnumaOdStringa(line);
                                if(clanEnumeracije != null)
                                    promenjenaInterclass.addClassContent(clanEnumeracije);
                            }
                        }
                        sacuvaj_promene = true;
                    }
                }

            }
            if(sacuvaj_promene)
            {
                EditInterclassCommand editInterclassCommand = new EditInterclassCommand(dv, nepromenjenaInterclass, promenjenaInterclass, zvanicnaInterclass);
                dv.getCommandManager().addCommand(editInterclassCommand);
            }
        }
    }
}

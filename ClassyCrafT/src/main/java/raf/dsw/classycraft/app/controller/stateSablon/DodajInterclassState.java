package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Enumeracija;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interfejs;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.EnumeracijaPainter;
import raf.dsw.classycraft.app.view.painteri.InterfejsPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DodajInterclassState implements State{
    @Override
    public void misPritisnut(Point P, DiagramView dv) {

        Object[] options = {"Klasa","Interfejs","Enum"};
        int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
                //MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa", JOptionPane., JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        if(choice == 0)//crta klasu
        {

            //System.out.println("item"+item.toString());
            System.out.println( dv.getDiagram().getChildren().toString()+" ovo su parentova deca");

            JTextField nazivtf=new JTextField();

            JTextArea poljata=new JTextArea();
            JTextArea metodeta=new JTextArea();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeKlase"),
                    nazivtf,
                    new JLabel("Polja, primer: private String ime"),
                    poljata,
                    new JLabel("Metode, primer: private int izracunaj(int a, int b)"),
                    metodeta
            };

            JOptionPane novaKlasa=new JOptionPane(inputs);
            novaKlasa.setSize(500,500);
            novaKlasa.createDialog(MainFrame.getInstance(),"nova klasa");
            novaKlasa.setVisible(true);

            //int rez = novaKlasa.showConfirmDialog(MainFrame.getInstance(),inputs,"novaKlasa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    /*,
                    "Complete the sentence:\n"
                            + "\"Green eggs and...\"",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    possibilities,
                    "ham");*/


            //ako je s null proveri
            String s=nazivtf.getText();


            for (String line : poljata.getText().split("\\n"))
            {


            }

            Klasa klasa=new Klasa(s,dv.getDiagram());
            KlasaPainter klasaPainter=new KlasaPainter(klasa,P);
            dv.addPainter(klasaPainter);

            //nodeType = "Package";
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, klasa);
            //return;
        }
        if(choice == 1)//crta interfejs
        {

            JTextField nazivtf=new JTextField();

            JTextArea metodeta=new JTextArea();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeInterfejsa"),
                    nazivtf,
                    new JLabel("Metode razdvojene zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                    metodeta
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novInterfejs",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            //ako je s null proveri
            String s=nazivtf.getText();


            Interfejs interfejs = new Interfejs(s,dv.getDiagram());
            System.out.println("metode " + metodeta.getText());

            // ne radi regex:(
            //tvoja linija:
            //Pattern patern = Pattern.compile("[^[+-~]{1}[(]{1}[)]{1}[:]{1}]", Pattern.CASE_INSENSITIVE);
            //Pattern patern = Pattern.compile("-[A-Za-z0-9]+\\([^)]*\\):[A-Za-z]+", Pattern.CASE_INSENSITIVE);

            Pattern patern = Pattern.compile(
                    "[+~-][a-zA-z0-9_]+\\((([a-zA-z0-9_]+\\s[a-zA-z0-9_]+)(,\\s*[a-zA-z0-9_]+\\s[a-zA-z0-9_]+)*)*\\):[a-zA-z0-9_]+", Pattern.CASE_INSENSITIVE);
            Matcher matcher = patern.matcher("-metoda1(int a,String s):int");
            System.out.println(matcher.find());

            //Pattern patern = Pattern.compile("^[+~-][(][)][:]", Pattern.CASE_INSENSITIVE);
            //Pattern patern = Pattern.compile("[^[+\\-~]{1}[(]{1}[)]{1}[:]{1}]", Pattern.CASE_INSENSITIVE);

            //Pattern patern = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);


            for (String line : metodeta.getText().split(";"))
            {
                System.out.println("linija1 " + line);
                if(!Objects.equals(line, "")) {
                    //Pattern patern = Pattern.compile("^[+~-]", Pattern.CASE_INSENSITIVE);
                    //Matcher matcher = patern.matcher(line);
                    matcher = patern.matcher(line);
                    System.out.println("linija2 " + line);
                    if(matcher.find())
                    {
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
                        // srediti da pazi na to da ako nije uneta vidljivost
                        System.out.println("trenutna linija" + line);

                        String nazivMetode = line.substring(1, line.indexOf("("));
                        String povratniTip = line.substring(line.indexOf(":") + 1);
                        Metoda metoda = new Metoda(nazivMetode, vidljivost, povratniTip);

                        if(line.charAt(line.indexOf("(") + 1) != ')')
                        {
                            String[] parametri = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split(",");
                            for (String parametar : parametri) {
                                System.out.println(parametar);
                                parametar = parametar.trim();
                                String tip = parametar.substring(0, parametar.indexOf(" "));
                                String nazivParametra = parametar.substring(parametar.indexOf(" ") + 1);
                                metoda.addParametarFunkcije(new Atribut(nazivParametra, InterclassVidljivost.PRIVATE, tip));
                            }
                        }


                        interfejs.addClassContent(metoda);
                        System.out.println("trenutna metoda" + metoda.toString());
                    }
                    else
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("lepo upisi metodu bre", MessageType.WARNING);
                        return;
                    }

                }
//                String[] nazivMetode = line.split("(");
            }
            //interfejs.addClassContent(new Metoda("max", InterclassVidljivost.PRIVATE,"int"));
            //interfejs.addClassContent(new Metoda("min", InterclassVidljivost.PUBLIC,"int"));
            InterfejsPainter interfejsPainter=new InterfejsPainter(interfejs,P);
            dv.addPainter(interfejsPainter);

            //nodeType = "Package";
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, interfejs);
            //return;
        }
        if(choice==2)
        {
            JTextField nazivtf=new JTextField();

            JTextField enumeracije=new JTextField();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("Ime enumeracije"),
                    nazivtf,
                    new JLabel("upisi elemente enumeracije razdvojene zarezom"),
                    enumeracije
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"nova enumeracija",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            //ako je s null proveri
            String s=nazivtf.getText();

            Enumeracija enumeracija =new Enumeracija(s,dv.getDiagram());

            String[] tipoviEnum=enumeracije.getText().split(",");
            for (String jedanEnum: tipoviEnum)
            {
                ClanEnumeracije clan=new ClanEnumeracije(jedanEnum);
                enumeracija.addClassContent(clan);
            }

            EnumeracijaPainter enumeracijaPainter=new EnumeracijaPainter(enumeracija,P);
            dv.addPainter(enumeracijaPainter);

            //nodeType = "Package";
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, enumeracija);
        }

    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}

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
                    new JLabel("Metode razdvojene tacka zarezom, primer: -metoda1(int a,String s):int;+metoda2():void"),
                    metodeta
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novInterfejs",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(rez == 0) // ako je kliknut OK
            {
                System.out.println("kliknut OK");
                String s = nazivtf.getText().trim();
                if(s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Potrebno je uneti ime da bi se interfejs kreirao", MessageType.WARNING);
                    // jos nesto?
                }
                else
                {
                    Interfejs interfejs = new Interfejs(s,dv.getDiagram());
                    System.out.println("metode " + metodeta.getText());

//            Pattern patern = Pattern.compile(
//                    "[+~-][a-zA-z0-9_]+\\((([a-zA-z0-9_]+\\s[a-zA-z0-9_]+)(,\\s*[a-zA-z0-9_]+\\s[a-zA-z0-9_]+)*)*\\):[a-zA-z0-9_]+", Pattern.CASE_INSENSITIVE);

                    // \\s* da moze koirsnik da unese i nepotrebne razmake
                    Pattern patern = Pattern.compile(
                            "\\s*[+~-]\\s*[a-zA-z0-9_]+\\s*\\(((\\s*[a-zA-z0-9_]+\\s+[a-zA-z0-9_]+\\s*)(,\\s*[a-zA-z0-9_]+\\s+[a-zA-z0-9_]+)*)*\\s*\\)\\s*:\\s*[a-zA-z0-9_]+\\s*", Pattern.CASE_INSENSITIVE);

//                    Matcher matcher = patern.matcher("-metoda1(int a,String s):int");
//                    System.out.println(matcher.find());
                    if(!metodeta.getText().trim().equals(""))
                    {
                        for (String line : metodeta.getText().split(";")) {
                            System.out.println("linija1 " + line);
                            if (!Objects.equals(line, "")) {
                                Matcher matcher = patern.matcher(line);
                                System.out.println("linija2 " + line);
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
                                    System.out.println("trenutna linija" + line);

                                    String nazivMetode = line.substring(1, line.indexOf("(")).trim();
                                    String povratniTip = line.substring(line.indexOf(":") + 1).trim();
                                    Metoda metoda = new Metoda(nazivMetode, vidljivost, povratniTip);


                                    String parametriStr = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
                                    if (!parametriStr.equals("")) {
                                        String[] parametri = parametriStr.split(",");
                                        for (String parametar : parametri) {
                                            System.out.println(parametar);
                                            parametar = parametar.trim();
                                            String tip = parametar.substring(0, parametar.indexOf(" "));
                                            String nazivParametra = parametar.substring(parametar.indexOf(" ") + 1).trim();
                                            metoda.addParametarFunkcije(new Atribut(nazivParametra, InterclassVidljivost.PRIVATE, tip));
                                        }
                                    }

                                    interfejs.addClassContent(metoda);
                                    System.out.println("trenutna metoda" + metoda.toString());
                                }
                                else {
                                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Metoda "+ line + " nije uneta kako treba" , MessageType.WARNING);
                                    //return; // pravice interfejs koji ima ime i metode koje odgovaraju sablonu, a nece dodati lose unete metode
                                }
                            }
                        }
                    }

                        InterfejsPainter interfejsPainter=new InterfejsPainter(interfejs,P);
                        dv.addPainter(interfejsPainter);

                        MainFrame.getInstance().getClassyTree().addDiagramElement(item, interfejs);

                }
            }
            else if(rez == 1)
            {
                System.out.println("Kliknut Cancel kod dodavanja interfejsa");
                // ako je kliknut cancel - onda nista
            }
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

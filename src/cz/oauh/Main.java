package cz.oauh;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends JFrame {
    private static List<Navstevnik> list = new ArrayList<>();
    private static List<Integer> listOfNumbers = new ArrayList<>();
    private JPanel panel;
    private JTextArea textData;
    private JButton dalsiButton;
    private JButton predchoziButton;
    private Navstevnik current;
    private static final String SOUBOR = "file.txt";
    private JFileChooser jFileChooser = new JFileChooser(".");
    private Navstevnik navstevnik;

    public static void main(String[] args) {
        Main main = new Main();
        main.scan();
        list.add(new Navstevnik("Ondřej", "Krajan", LocalDate.of(2020, 12, 11), 15));
        list.add(new Navstevnik("Jakub", "Dvořáček", LocalDate.of(2023, 10, 25), 45));
    }

    public Main() {
        setVisible(true);
        setContentPane(panel);
        setSize(500, 500);
        setTitle("Návštěvníci Ondřej Krajan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Soubor");
        JMenu menu = new JMenu("uložit");
        JMenuItem save = new JMenuItem("save");
        menu.add(save);
        save.addActionListener(e -> saved());
        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);
        JMenuItem pocetN = new JMenuItem("Počet návštěvníků ");
        JMenuItem soucet = new JMenuItem("Kredity celkem ");
        jMenu.add(soucet);
        soucet.addActionListener(e -> soucet());
        jMenu.addSeparator();
        jMenu.add(pocetN);
        pocetN.addActionListener(e -> pocetNavstevniku());
        dalsiButton.addActionListener(e -> next());
        predchoziButton.addActionListener(e -> back());



    }

    private List<Navstevnik> scan() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(SOUBOR)))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("#");
                LocalDate datum = LocalDate.parse(data[3].trim());
                int kredit = Integer.parseInt(data[0].trim());
                list.add(new Navstevnik(data[1].trim(), data[2].trim(), datum, kredit));
                listOfNumbers.add(kredit);
                textData.append("jméno a příjmení: " + data[1] + " " + data[2] + ", datum návštěvy: " + datum + ", počet kreditu je: " + kredit);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Soubor nelze přečíst!");
        }

        return list;
    }

    private List<Navstevnik> loadData() {
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Typ soubou nelze přečíst!");
        }
        Navstevnik navstevnik;
        list = scan();
        current = list.get(list.indexOf(0));
        return list;
    }

    private void showData(Navstevnik navstevnik) {
        this.current = navstevnik;
        refresh();
        textData.setText("jméno a příjmení: " + navstevnik.getJmeno() + " " + navstevnik.getPrijmeni() + ", datum návštěvy: " + navstevnik.getDatum() + ", počet kreditu je: " + navstevnik.getKredit());
    }

    private void next() {
        try {
            current = list.get(list.indexOf(current) + 1);
            showData(current);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Není zde žádný další návštěvník!");
        }
    }
    private void saved(){
       try( PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(jFileChooser.getSelectedFile())))) {
           list.forEach(navstevnik -> {
               writer.println(navstevnik.getKredit() + "#" + navstevnik.getJmeno() + "#" + navstevnik.getPrijmeni() + "#" + navstevnik.getDatum());
           });
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
    private void save(){
        this.current = navstevnik;
        try{
      String jmeno =  JOptionPane.showInputDialog(null ,"zde napiště jméno návštěvníka");
      current.setJmeno(jmeno);
        }   catch (Exception e){
            JOptionPane.showMessageDialog(null, "musíte zadat jméno!");
        }
        try {
            String prijmeni = JOptionPane.showInputDialog(null, "zde napište příjmení návštěvníka");
            current.setPrijmeni(prijmeni);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "musíte zadat příjmení!");
        }
        try {
            LocalDate datum = LocalDate.parse(JOptionPane.showInputDialog(null, "zde napište datum ve formátu rok-měsíc-den"));
            current.setDatum(datum);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "musíte zadat datum ve formátu rok-měsíc-den!");
        }
        try {
            int kredit = Integer.parseInt(JOptionPane.showInputDialog(null, "zde napiště počet kreditů"));
            current.setKredit(kredit);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "musíte napsat číslo!");
        }
    }
    private void back() {
        try {
            current = list.get(list.indexOf(current) - 1);
            showData(current);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Není zde žádný předešlý návštěvník!");
        }
    }

    private void refresh() {
        textData.setText("");
    }
    private void pocetNavstevniku(){
        JOptionPane.showMessageDialog(this , "Celkový počet kreditů všech návštěvníků je " + list.stream().count());

    }
    private void soucet() {
        //nefunguje součet
        int i = 0;
        for (Integer sum : listOfNumbers) {
            i += (sum);
        }
        JOptionPane.showMessageDialog(this,"V seznamu je "+  i + " návštěvníků");
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package puzzle;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class mainMenu extends JFrame implements ActionListener
{
    public JButton _btn1;
    public JButton _btn2;
    public HashMap<Integer, LinkedList<int[][]>> _csvLists;
    public HashMap<String, ImageIcon[][]> _filesMap;
    
    public mainMenu() {
        super("welcome");
        final Image image = new ImageIcon("back5.png").getImage().getScaledInstance(500, 500, 4);
        final ImageIcon im = new ImageIcon(image);
        this.setContentPane(new JLabel(im));
        this.setDefaultCloseOperation(3);
        (this._btn1 = new JButton("NEW GAME")).addActionListener(this);
        this._btn1.setPreferredSize(new Dimension(100, 100));
        this._btn1.setBackground(Color.RED);
        this._btn1.setForeground(Color.WHITE);
        (this._btn2 = new JButton("EXIT")).addActionListener(this);
        this._btn2.setPreferredSize(new Dimension(100, 100));
        this._btn2.setBackground(Color.RED);
        this._btn2.setForeground(Color.WHITE);
        final Container cp = this.getContentPane();
        cp.add(this._btn1, "North");
        cp.add(this._btn2, "South");
        cp.setLayout(new GridBagLayout());
        this.pack();
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.initCvs();
        this.initFiles();
    }
    
    public static void main(final String[] args) {
        new mainMenu();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this._btn1)) {
            new secondMenu(this._csvLists, this._filesMap);
            this.dispose();
        }
        else {
            System.exit(0);
        }
    }
    
    public void initFiles() {
        final File files = new File("sample_pictures");
        this._filesMap = new HashMap<String, ImageIcon[][]>();
        final String[] folderNames = files.list();
        for (int i = 0; i < folderNames.length; ++i) {
            final String a = folderNames[i];
            final File files2 = new File("sample_pictures\\" + folderNames[i]);
            final String[] folderNames2 = files2.list();
            for (int j = 0; j < folderNames2.length; ++j) {
                if (folderNames2[j].endsWith(".jpeg") || folderNames2[j].endsWith(".jpg")) {
                    final ImageIcon[][] images1 = new ImageIcon[26][26];
                    images1[0][0] = new ImageIcon("sample_pictures\\" + folderNames[i] + "\\" + folderNames2[j]);
                    this._filesMap.put(a, images1);
                }
                else {
                    final File files3 = new File("sample_pictures\\" + folderNames[i] + "\\" + folderNames2[j]);
                    final String[] folderNames3 = files3.list();
                    for (int k = 0; folderNames3 != null && k < folderNames3.length; ++k) {
                        this._filesMap.get(a)[j][k + 1] = new ImageIcon("sample_pictures\\" + folderNames[i] + "\\" + folderNames2[j] + "\\" + folderNames3[k]);
                    }
                }
            }
        }
    }
    
    public void initCvs() {
        this._csvLists = new HashMap<Integer, LinkedList<int[][]>>();
        try {
            final Scanner lines = new Scanner(new File("boards.csv"));
            while (lines.hasNext()) {
                final String next = lines.next();
                if (next.length() == 1) {
                    final int size = Integer.parseInt(next);
                    final int[][] insert = new int[size][size];
                    for (int i = 0; i < size; ++i) {
                        final String next2 = lines.next();
                        final String[] a = next2.split(",");
                        for (int j = 0; j < size; ++j) {
                            insert[i][j] = Integer.parseInt(a[j]);
                        }
                    }
                    if (this._csvLists.containsKey(size)) {
                        this._csvLists.get(size).add(insert);
                    }
                    else {
                        final LinkedList<int[][]> N = new LinkedList<int[][]>();
                        N.add(insert);
                        this._csvLists.put(size, N);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println();
        }
    }
}

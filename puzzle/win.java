// 
// Decompiled by Procyon v0.5.36
// 

package puzzle;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class win extends JFrame implements ActionListener
{
    private JLabel _winner;
    private String _timer;
    private String _steps;
    private JButton _mainMenu;
    private JButton _newGame;
    private HashMap<Integer, LinkedList<int[][]>> _csvLists;
    private HashMap<String, ImageIcon[][]> _filesMap;
    
    public win(final String timer, final String steps, final HashMap<Integer, LinkedList<int[][]>> csvLists, final HashMap<String, ImageIcon[][]> filesMap) {
        this._csvLists = csvLists;
        this._filesMap = filesMap;
        this._timer = timer;
        this._steps = steps;
        final Image image = new ImageIcon("back4.jpg").getImage().getScaledInstance(200, 200, 4);
        final ImageIcon im = new ImageIcon(image);
        this.setContentPane(new JLabel(im));
        final FlowLayout fl = new FlowLayout();
        fl.setAlignment(1);
        this.setLayout(fl);
        (this._winner = new JLabel("you win!!")).setForeground(Color.WHITE);
        final Container cp = this.getContentPane();
        cp.add(this._winner, "North");
        final JLabel T = new JLabel("time: " + timer);
        T.setForeground(Color.WHITE);
        final JLabel S = new JLabel(steps);
        S.setForeground(Color.WHITE);
        cp.add(T, "Center");
        cp.add(S, "South");
        (this._mainMenu = new JButton("main menu")).addActionListener(this);
        (this._newGame = new JButton("new game")).addActionListener(this);
        final JPanel downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout(1));
        downPanel.add(this._mainMenu);
        downPanel.add(this._newGame);
        this.add(downPanel, "South");
        this.pack();
        this.setSize(200, 200);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final Window[] wind = Window.getWindows();
        for (int i = 0; i < wind.length; ++i) {
            wind[i].dispose();
        }
        if (e.getSource().equals(this._mainMenu)) {
            new mainMenu();
        }
        else {
            new secondMenu(this._csvLists, this._filesMap);
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package puzzle;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class game extends JFrame implements ActionListener
{
    private board _board;
    private JLabel _timer;
    private JLabel _steps;
    private Timer _time;
    private int _seconds;
    private JButton _undo;
    private JButton _menu;
    
    public game(final int n, final int[][] csv, final ImageIcon[] images, final HashMap<Integer, LinkedList<int[][]>> csvLists, final HashMap<String, ImageIcon[][]> filesMap) {
        this.setLayout(new BorderLayout());
        final JPanel upPanel = new JPanel();
        upPanel.setLayout(new FlowLayout(1));
        this._undo = new JButton("UNDO");
        this._menu = new JButton("BACK TO MENU");
        this._steps = new JLabel("steps: 0");
        upPanel.add(this._timer = new JLabel());
        upPanel.add(this._steps);
        upPanel.add(this._undo);
        upPanel.add(this._menu);
        (this._time = new Timer(1000, this)).start();
        this._board = new board(n, csv, images, this._steps, this._time, this._undo, this._timer, csvLists, filesMap, this._menu);
        this._undo.addActionListener(this._board);
        this._menu.addActionListener(this._board);
        this._undo.setFocusable(false);
        this._menu.setFocusable(false);
        this.add(this._board, "South");
        this.add(upPanel, "North");
        this.setDefaultCloseOperation(3);
        this.setSize(400, 400);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        ++this._seconds;
        String hour = "";
        String minute = "";
        String second = "";
        hour = String.valueOf(hour) + this._seconds / 3600;
        hour = this.zeroCheck(hour);
        minute = String.valueOf(minute) + this._seconds % 3600 / 60;
        minute = this.zeroCheck(minute);
        second = String.valueOf(second) + this._seconds % 60;
        second = this.zeroCheck(second);
        this._timer.setText(String.valueOf(hour) + ":" + minute + ":" + second);
    }
    
    private String zeroCheck(String s) {
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
}

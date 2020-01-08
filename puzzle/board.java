// 
// Decompiled by Procyon v0.5.36
// 

package puzzle;

import java.awt.event.KeyEvent;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class board extends JPanel implements ActionListener, KeyListener
{
    private JButton[][] _matrix;
    private int[][] _csv;
    private ImageIcon[] _images;
    private JLabel _steps;
    private int _stepsI;
    private Timer _time;
    private Stack<int[]> _unStuck;
    private JButton _undo;
    private JLabel _timer;
    private HashMap<Integer, LinkedList<int[][]>> _csvLists;
    private HashMap<String, ImageIcon[][]> _filesMap;
    private JButton _menu;
    
    public board(final int n, final int[][] _csv, final ImageIcon[] _images, final JLabel _steps, final Timer time, final JButton _undo, final JLabel timer, final HashMap<Integer, LinkedList<int[][]>> _csvLists, final HashMap<String, ImageIcon[][]> _filesMap, final JButton _menu) {
        super(new GridLayout(_csv.length, _csv.length));
        this._menu = _menu;
        this._csvLists = _csvLists;
        this._filesMap = _filesMap;
        this._timer = timer;
        this._undo = _undo;
        this._unStuck = new Stack<int[]>();
        this._time = time;
        this._csv = _csv;
        this._images = _images;
        this._steps = _steps;
        this._matrix = new JButton[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                final int a = _csv[i][j];
                (this._matrix[i][j] = new JButton(_images[a])).addActionListener(this);
                this._matrix[i][j].addKeyListener(this);
                this._matrix[i][j].setBorder(BorderFactory.createEmptyBorder());
                this.add(this._matrix[i][j]);
                this._matrix[i][j].setSize(100, 100);
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this._menu)) {
            final Window[] wind = Window.getWindows();
            for (int i = 0; i < wind.length; ++i) {
                wind[i].dispose();
            }
            new mainMenu();
        }
        if (e.getSource().equals(this._undo)) {
            if (this._unStuck.empty()) {
                return;
            }
            final int[] b = new int[2];
            final int[] c = this._unStuck.pop();
            b[0] = c[0];
            b[1] = c[1];
            this.swap(c[2], c[3], b);
        }
        for (int j = 0; j < this._matrix.length; ++j) {
            int k = 0;
            while (k < this._matrix.length) {
                if (this._matrix[j][k].equals(e.getSource())) {
                    final int[] zero = this.checkZero(j, k);
                    if (zero == null) {
                        return;
                    }
                    ++this._stepsI;
                    this._steps.setText("steps: " + this._stepsI);
                    this.swap(j, k, zero);
                    final int[] a = { j, k, zero[0], zero[1] };
                    this._unStuck.push(a);
                    this.isComplete();
                    return;
                }
                else {
                    ++k;
                }
            }
        }
    }
    
    private void swap(final int i, final int j, final int[] a) {
        this._matrix[a[0]][a[1]].setIcon(this._matrix[i][j].getIcon());
        this._matrix[i][j].setIcon(null);
        final int temp = this._csv[i][j];
        this._csv[i][j] = this._csv[a[0]][a[1]];
        this._csv[a[0]][a[1]] = temp;
    }
    
    private int[] checkZero(final int i, final int j) {
        int[] ret = new int[2];
        final int[][] a = new int[this._csv.length + 2][this._csv.length + 2];
        for (int m = 0; m < a.length; ++m) {
            for (int p = 0; p < a.length; ++p) {
                a[m][p] = -1;
            }
        }
        for (int k = 1; k <= this._csv.length; ++k) {
            for (int l = 1; l <= this._csv.length; ++l) {
                a[k][l] = this._csv[k - 1][l - 1];
            }
        }
        if (a[i][j + 1] == 0) {
            ret[0] = i - 1;
            ret[1] = j;
        }
        else if (a[i + 1][j] == 0) {
            ret[0] = i;
            ret[1] = j - 1;
        }
        else if (a[i + 1][j + 2] == 0) {
            ret[0] = i;
            ret[1] = j + 1;
        }
        else if (a[i + 2][j + 1] == 0) {
            ret[0] = i + 1;
            ret[1] = j;
        }
        else {
            ret = null;
        }
        return ret;
    }
    
    private boolean isComplete() {
        int counter = 1;
        for (int i = 0; i < this._csv.length; ++i) {
            for (int j = 0; j < this._csv.length; ++j) {
                if (this._csv[i][j] != counter % (this._csv.length * this._csv.length)) {
                    return false;
                }
                ++counter;
            }
        }
        this._matrix[this._matrix.length - 1][this._matrix.length - 1].setIcon(this._images[this._matrix.length * this._matrix.length]);
        for (int k = 0; k < this._matrix.length; ++k) {
            for (int m = 0; m < this._matrix.length; ++m) {
                this._matrix[k][m].removeActionListener(this);
                this._matrix[k][m].removeKeyListener(this);
            }
        }
        this._undo.removeActionListener(this);
        this._menu.removeActionListener(this);
        this._time.stop();
        new win(this._timer.getText(), this._steps.getText(), this._csvLists, this._filesMap);
        return true;
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        final int[] a = new int[2];
        final int temp = 0;
        int col = 0;
        int row = 0;
        for (int i = 0; i < this._csv.length; ++i) {
            for (int j = 0; j < this._csv.length; ++j) {
                if (this._csv[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }
        if (e.getKeyCode() == 38) {
            if (row == this._csv.length - 1) {
                return;
            }
            a[0] = row;
            this.swap(row + 1, a[1] = col, a);
            final int[] b = { row + 1, col, a[0], a[1] };
            this._unStuck.push(b);
            ++this._stepsI;
            this._steps.setText("steps: " + this._stepsI);
        }
        if (e.getKeyCode() == 40) {
            if (row == 0) {
                return;
            }
            a[0] = row;
            this.swap(row - 1, a[1] = col, a);
            final int[] b = { row - 1, col, a[0], a[1] };
            this._unStuck.push(b);
            ++this._stepsI;
            this._steps.setText("steps: " + this._stepsI);
        }
        if (e.getKeyCode() == 39) {
            if (col == 0) {
                return;
            }
            this.swap(a[0] = row, (a[1] = col) - 1, a);
            final int[] b = { row, col - 1, a[0], a[1] };
            this._unStuck.push(b);
            ++this._stepsI;
            this._steps.setText("steps: " + this._stepsI);
        }
        if (e.getKeyCode() == 37) {
            if (col == this._csv.length - 1) {
                return;
            }
            this.swap(a[0] = row, (a[1] = col) + 1, a);
            final int[] b = { row, col + 1, a[0], a[1] };
            this._unStuck.push(b);
            ++this._stepsI;
            this._steps.setText("steps: " + this._stepsI);
        }
        if (e.getKeyCode() == 90 && (e.getModifiers() & 0x2) != 0x0) {
            if (this._unStuck.empty()) {
                return;
            }
            final int[] b = new int[2];
            final int[] c = this._unStuck.pop();
            b[0] = c[0];
            b[1] = c[1];
            this.swap(c[2], c[3], b);
        }
        this.isComplete();
    }
    
    @Override
    public void keyReleased(final KeyEvent arg0) {
    }
    
    @Override
    public void keyTyped(final KeyEvent arg0) {
    }
}

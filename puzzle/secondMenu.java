// 
// Decompiled by Procyon v0.5.36
// 

package puzzle;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.AbstractButton;
import java.awt.Component;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class secondMenu extends JPanel implements ActionListener
{
    private JRadioButton _R3;
    private JRadioButton _R4;
    private JRadioButton _R5;
    private JButton _pic1;
    private JButton _pic2;
    private JButton _pic3;
    private JButton _Random;
    private JButton _upload;
    private ButtonGroup _tGrp;
    private JSpinner _size;
    private JLabel _sizeText;
    private JFileChooser _PhotoUp;
    private HashMap<Integer, LinkedList<int[][]>> _csvLists;
    private HashMap<String, ImageIcon[][]> _filesMap;
    
    public secondMenu(final HashMap<Integer, LinkedList<int[][]>> _csvLists, final HashMap<String, ImageIcon[][]> _filesMap) {
        super(new GridBagLayout());
        this._PhotoUp = new JFileChooser();
        this._csvLists = _csvLists;
        this._filesMap = _filesMap;
        final GridBagConstraints tProto = new GridBagConstraints();
        tProto.insets = new Insets(5, 2, 5, 2);
        this.setLayout(new BoxLayout(this, 1));
        final GridBagConstraints tLabelConst = (GridBagConstraints)tProto.clone();
        tLabelConst.anchor = 13;
        tLabelConst.fill = 0;
        tLabelConst.weightx = 0.0;
        final GridBagConstraints tTextConst = (GridBagConstraints)tProto.clone();
        tTextConst.anchor = 17;
        tTextConst.fill = 2;
        tTextConst.weightx = 1.0;
        final GridBagConstraints tSelConst = (GridBagConstraints)tProto.clone();
        tSelConst.anchor = 17;
        tSelConst.fill = 0;
        tSelConst.weightx = 0.0;
        GridBagConstraints tConst = (GridBagConstraints)tLabelConst.clone();
        tConst.gridx = 0;
        tConst.gridy = 1;
        final JPanel tPane = new JPanel();
        this._size = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
        this._sizeText = new JLabel("this size of board is: ");
        (this._R3 = new JRadioButton("CSV")).setActionCommand("3");
        this._R3.addActionListener(this);
        (this._R4 = new JRadioButton("Random BOARD")).setActionCommand("4");
        this._R4.addActionListener(this);
        this._R3.setSelected(true);
        tPane.add(this._R3);
        tPane.add(this._R4);
        tPane.add(this._sizeText);
        tPane.add(this._size);
        (this._tGrp = new ButtonGroup()).add(this._R3);
        this._tGrp.add(this._R4);
        tConst = (GridBagConstraints)tSelConst.clone();
        tConst.gridx = 1;
        tConst.gridy = 1;
        this.add(tPane, tConst);
        tConst.fill = 1;
        tConst.anchor = 10;
        tConst.weighty = 1.0;
        tConst.gridx = 0;
        tConst.gridy = 3;
        tConst.gridwidth = 4;
        tConst.gridheight = 2;
        this.add(tPane, tConst);
        final ImageIcon imageIcon_cat = _filesMap.get("cat")[0][0];
        final ImageIcon imageIcon_cyber = _filesMap.get("cyber")[0][0];
        final ImageIcon imageIcon_sushi = _filesMap.get("sushi")[0][0];
        final Image imagecat = imageIcon_cat.getImage();
        final Image new_imgcat = imagecat.getScaledInstance(120, 120, 4);
        final ImageIcon Fimagecat = new ImageIcon(new_imgcat);
        final Image imagecyber = imageIcon_cyber.getImage();
        final Image new_imgcyber = imagecyber.getScaledInstance(120, 120, 4);
        final ImageIcon Fimagecyber = new ImageIcon(new_imgcyber);
        final Image imagesushi = imageIcon_sushi.getImage();
        final Image new_imgsushi = imagesushi.getScaledInstance(120, 120, 4);
        final ImageIcon Fimagesushi = new ImageIcon(new_imgsushi);
        (this._pic1 = new JButton(Fimagecat)).setBorder(BorderFactory.createEmptyBorder());
        (this._pic2 = new JButton(Fimagecyber)).setBorder(BorderFactory.createEmptyBorder());
        (this._pic3 = new JButton(Fimagesushi)).setBorder(BorderFactory.createEmptyBorder());
        (this._Random = new JButton("Random PHOTO")).setBorder(BorderFactory.createEmptyBorder());
        (this._upload = new JButton("upload")).setBorder(BorderFactory.createEmptyBorder());
        final JPanel tPane2 = new JPanel();
        tConst = (GridBagConstraints)tSelConst.clone();
        tConst.gridx = 3;
        tConst.gridy = 2;
        this._pic1.addActionListener(this);
        tPane2.add(this._pic1);
        this._pic2.addActionListener(this);
        tPane2.add(this._pic2);
        this._pic3.addActionListener(this);
        tPane2.add(this._pic3);
        this._Random.addActionListener(this);
        tPane2.add(this._Random);
        this._upload.addActionListener(this);
        tPane2.add(this._upload);
        this.add(tPane2, tConst);
        final Font tFont = new Font("Dialog", 1, 16);
        UIManager.put("Label.font", tFont);
        UIManager.put("RadioButton.font", tFont);
        UIManager.put("TitledBorder.font", tFont);
        final JFrame tFrame = new JFrame();
        tFrame.setContentPane(this);
        tFrame.pack();
        tFrame.setDefaultCloseOperation(3);
        tFrame.setResizable(false);
        tFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final double _Random1 = Math.random();
        int rand1;
        if (_Random1 > 0.5) {
            rand1 = 1;
        }
        else {
            rand1 = 0;
        }
        if (this._tGrp.getSelection().getActionCommand() == "3") {
            if ((int)this._size.getValue() > 5 || (int)this._size.getValue() < 3) {
                JOptionPane.showMessageDialog(this, "csv is bettween 3-5");
            }
            else {
                final int[][] csv = this._csvLists.get((int)this._size.getValue()).get(rand1);
                final int[][] ret = this.changeCsv(csv);
                this.startGame(e, ret);
            }
        }
        else if (this._tGrp.getSelection().getActionCommand() == "4") {
            final int[][] ret2 = this.BuildCsv((int)this._size.getValue());
            this.startGame(e, ret2);
        }
    }
    
    private ImageIcon[] cutImage(final int _size, final ImageIcon image) {
        final Image image2 = image.getImage();
        final BufferedImage buff = toBufferedImage(image2);
        final ImageIcon[] images = new ImageIcon[_size * _size + 1];
        int counter = 0;
        for (int i = 0; i < _size; ++i) {
            for (int j = 0; j < _size; ++j) {
                ++counter;
                final BufferedImage cutImage = buff.getSubimage(j * buff.getWidth() / _size, i * buff.getHeight() / _size, buff.getWidth() / _size, buff.getHeight() / _size);
                images[counter] = new ImageIcon(cutImage);
            }
        }
        return images;
    }
    
    public static BufferedImage toBufferedImage(final Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage)img;
        }
        final BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
        final Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
    
    private int[][] changeCsv(final int[][] csv) {
        final int[][] ret = new int[csv.length][csv.length];
        for (int i = 0; i < csv.length; ++i) {
            for (int j = 0; j < csv.length; ++j) {
                ret[i][j] = csv[i][j];
            }
        }
        return ret;
    }
    
    private int[][] BuildCsv(final int n) {
        final int[][] ret = new int[n][n];
        int counter = 1;
        int xZero = n - 1;
        int yZero = n - 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                ret[i][j] = counter;
                ++counter;
            }
        }
        ret[n - 1][n - 1] = 0;
        final double _Random1 = Math.random();
        for (int k = 0; k < _Random1 + 8 * (n * n * n * n); ++k) {
            int left;
            if (xZero - 1 < n && xZero - 1 >= 0) {
                left = xZero - 1;
            }
            else {
                left = -1;
            }
            int right;
            if (xZero + 1 < n && xZero + 1 >= 0) {
                right = xZero + 1;
            }
            else {
                right = -1;
            }
            int up;
            if (yZero - 1 < n && yZero - 1 >= 0) {
                up = yZero - 1;
            }
            else {
                up = -1;
            }
            int down;
            if (yZero + 1 < n && yZero + 1 >= 0) {
                down = yZero + 1;
            }
            else {
                down = -1;
            }
            final String swap = this.WhoToswap_Random(left, right, up, down);
            if (swap == "left") {
                final int temp = ret[xZero][yZero];
                ret[xZero][yZero] = ret[xZero - 1][yZero];
                ret[xZero - 1][yZero] = temp;
                --xZero;
            }
            if (swap == "right") {
                final int temp = ret[xZero][yZero];
                ret[xZero][yZero] = ret[xZero + 1][yZero];
                ret[xZero + 1][yZero] = temp;
                ++xZero;
            }
            if (swap == "up") {
                final int temp = ret[xZero][yZero];
                ret[xZero][yZero] = ret[xZero][yZero - 1];
                ret[xZero][yZero - 1] = temp;
                --yZero;
            }
            if (swap == "down") {
                final int temp = ret[xZero][yZero];
                ret[xZero][yZero] = ret[xZero][yZero + 1];
                ret[xZero][yZero + 1] = temp;
                ++yZero;
            }
        }
        return ret;
    }
    
    private String WhoToswap_Random(final int left, final int right, final int up, final int down) {
        final double _Random1 = Math.random();
        if (_Random1 < 0.25) {
            if (left != -1) {
                return "left";
            }
            if (right != -1) {
                return "right";
            }
            return "up";
        }
        else if (_Random1 < 0.5) {
            if (right != -1) {
                return "right";
            }
            if (up != -1) {
                return "up";
            }
            return "down";
        }
        else if (_Random1 < 0.75) {
            if (up != -1) {
                return "up";
            }
            if (right != -1) {
                return "right";
            }
            return "left";
        }
        else {
            if (down != -1) {
                return "down";
            }
            if (up != -1) {
                return "up";
            }
            return "left";
        }
    }
    
    private void startGame(final ActionEvent e, final int[][] ret) {
        final double _Random2 = Math.random();
        String rand2;
        if (_Random2 < 0.3) {
            rand2 = "cat";
        }
        else if (_Random2 < 0.6) {
            rand2 = "cyber";
        }
        else {
            rand2 = "sushi";
        }
        if (e.getSource().equals(this._upload)) {
            final ImageIcon img = this._upload();
            if (img == null) {
                JOptionPane.showMessageDialog(this, "please choose a photo!");
            }
            else {
                final ImageIcon[] images = this.cutImage((int)this._size.getValue(), img);
                for (int i = 1; i <= (int)this._size.getValue() * (int)this._size.getValue(); ++i) {
                    final Image image = images[i].getImage().getScaledInstance(600 / (int)this._size.getValue(), 600 / (int)this._size.getValue(), 4);
                    images[i].setImage(image);
                }
                new game((int)this._size.getValue(), ret, images, this._csvLists, this._filesMap);
            }
        }
        if (e.getSource().equals(this._pic1)) {
            final ImageIcon[] images2 = this.cutImage((int)this._size.getValue(), this._filesMap.get("cat")[0][0]);
            new game((int)this._size.getValue(), ret, images2, this._csvLists, this._filesMap);
        }
        if (e.getSource().equals(this._pic2)) {
            final ImageIcon[] images2 = this.cutImage((int)this._size.getValue(), this._filesMap.get("cyber")[0][0]);
            new game((int)this._size.getValue(), ret, images2, this._csvLists, this._filesMap);
        }
        if (e.getSource().equals(this._pic3)) {
            final ImageIcon[] images2 = this.cutImage((int)this._size.getValue(), this._filesMap.get("sushi")[0][0]);
            for (int j = 1; j <= (int)this._size.getValue() * (int)this._size.getValue(); ++j) {
                final Image image2 = images2[j].getImage().getScaledInstance(600 / (int)this._size.getValue(), 600 / (int)this._size.getValue(), 4);
                images2[j].setImage(image2);
            }
            new game((int)this._size.getValue(), ret, images2, this._csvLists, this._filesMap);
        }
        if (e.getSource().equals(this._Random)) {
            final ImageIcon[] images2 = this.cutImage((int)this._size.getValue(), this._filesMap.get(rand2)[0][0]);
            if (rand2 == "sushi") {
                for (int j = 1; j <= (int)this._size.getValue() * (int)this._size.getValue(); ++j) {
                    final Image image2 = images2[j].getImage().getScaledInstance(600 / (int)this._size.getValue(), 600 / (int)this._size.getValue(), 4);
                    images2[j].setImage(image2);
                }
            }
            new game((int)this._size.getValue(), ret, images2, this._csvLists, this._filesMap);
        }
    }
    
    private ImageIcon _upload() {
        this._PhotoUp.setCurrentDirectory(new File("."));
        final int ret = this._PhotoUp.showOpenDialog(this._PhotoUp);
        String filePath = null;
        if (ret == 0) {
            filePath = this._PhotoUp.getSelectedFile().getAbsolutePath();
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
            final ImageIcon img = new ImageIcon(image);
            return img;
        }
        catch (Exception e1) {
            return null;
        }
    }
}

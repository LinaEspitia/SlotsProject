package presentation;

import javax.swing.*;
import java.awt.*;

public class GameRulesWindow extends JFrame{

    private JLabel labelOne;
    private JLabel labelTwo;
    private JLabel labelThree;
    private JLabel labelOneThree;
    private JLabel labelTwoThree;
    private JLabel labelThreeThree;
    private JLabel labelOneTwo;
    private JLabel labelTwoTwo;
    private JLabel labelThreeTwo;
    private ImageIcon[] images;
    private JButton btnAccept;
    private  JTextField jTextFieldThree;
    private JTextField jTextFieldTwo;
    private JTextField jTextField;
    private JLabel jLabelTitle;
    private JLabel jLabelOne;
    private JLabel jLabelTwo;
    private JLabel jLabelThree;

    public GameRulesWindow() {
        setTitle("Reglas del juego");
        setSize(800, 380);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void begin() {
        beginComponents();
        addComponents();
    }

    private void beginComponents() {
        labelOneThree = new JLabel();
        labelTwoThree = new JLabel();
        labelThreeThree = new JLabel();

        labelOneTwo = new JLabel();
        labelTwoTwo = new JLabel();
        labelThreeTwo = new JLabel();

        labelOne = new JLabel();
        labelTwo = new JLabel();
        labelThree = new JLabel();
        images = new ImageIcon[3];

        btnAccept= new JButton("Aceptar");

        images[0] = new ImageIcon("src/test/resources/Diamond.png");
        images[1] = new ImageIcon("src/test/resources/Clover.png");
        images[2] = new ImageIcon("src/test/resources/Currency.png");

        images[0] = new ImageIcon(images[0].getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        images[1] = new ImageIcon(images[1].getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        images[2] = new ImageIcon(images[2].getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));

        labelOneThree.setIcon(images[0]);
        labelTwoThree.setIcon(images[0]);
        labelThreeThree.setIcon(images[0]);

        labelOneTwo.setIcon(images[1]);
        labelTwoTwo.setIcon(images[1]);
        labelThreeTwo.setIcon(images[2]);

        labelOne.setIcon(images[2]);
        labelTwo.setIcon(images[1]);
        labelThree.setIcon(images[0]);

        jLabelOne = new JLabel("Ninguna figura igual");
        jLabelOne.setFont(new Font("Arial", Font.BOLD, 20));
        jLabelOne.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jLabelTwo = new JLabel("Dos figuras iguales");
        jLabelTwo.setFont(new Font("Arial", Font.BOLD, 20));
        jLabelTwo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jLabelThree = new JLabel("Tres figuras iguales");
        jLabelThree.setFont(new Font("Arial", Font.BOLD, 20));
        jLabelThree.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        jLabelTitle = new JLabel("REGLAS DEL JUEGO");
        jLabelTitle.setFont(new Font("Abril Fatface", Font.BOLD, 25));
        jLabelTitle.setBorder(BorderFactory.createEmptyBorder(20, 260, 30, 0));
        jTextFieldThree = new JTextField(20);
        jTextFieldTwo = new JTextField(20);
        jTextField = new JTextField(20);
        jTextFieldThree.setEditable(false);
        jTextFieldTwo.setEditable(false);
        jTextField.setEditable(false);
        jTextFieldThree.setBackground(Color.white);
        jTextFieldTwo.setBackground(Color.white);
        jTextField.setBackground(Color.white);
    }

    private void addComponents() {
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());

        titlePanel.add(jLabelTitle, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(1, 3, 20, 20));

        JPanel imagePanelThree = new JPanel();
        imagePanelThree.setLayout(new FlowLayout(FlowLayout.CENTER, -20, 0));

        imagePanelThree.add(labelOneThree);
        imagePanelThree.add(labelTwoThree);
        imagePanelThree.add(labelThreeThree);
        imagePanelThree.add(jLabelThree);
        imagePanelThree.add(jTextFieldThree);

        JPanel imagePanelTwo = new JPanel();
        imagePanelTwo.setLayout(new FlowLayout(FlowLayout.CENTER, -15, 0));

        imagePanelTwo.add(labelOneTwo);
        imagePanelTwo.add(labelTwoTwo);
        imagePanelTwo.add(labelThreeTwo);
        imagePanelTwo.add(jLabelTwo);
        imagePanelTwo.add(jTextFieldTwo);

        JPanel imagePanelOne = new JPanel();
        imagePanelOne.setLayout(new FlowLayout(FlowLayout.CENTER, -15, 0));

        imagePanelOne.add(labelOne);
        imagePanelOne.add(labelTwo);
        imagePanelOne.add(labelThree);
        imagePanelOne.add(jLabelOne);
        imagePanelOne.add(jTextField);

        imagePanel.add(imagePanelThree);
        imagePanel.add(imagePanelTwo);
        imagePanel.add(imagePanelOne);

        JPanel btnAcceptPanel = new JPanel();
        btnAcceptPanel.add(btnAccept);

        rulesPanel.add(imagePanel);
        rulesPanel.add(btnAcceptPanel);

        btnAccept.addActionListener((e)->{
            dispose();
            BetAmount betAmount = new BetAmount();
            betAmount.begin();
            betAmount.setVisible(true);
        });

        this.add(rulesPanel, BorderLayout.CENTER);
    }

}
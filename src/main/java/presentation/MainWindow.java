package presentation;

import Logic.WinCombination;
import Model.ThreadCount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends JFrame {

    private double betAmount = 0.0;
    private final double baseBetAmount = 1.0;
    private ArrayList<WinCombination> winCombinations;
    private double currentWin = 0.0;
    private JLabel labelOne;
    private JLabel labelTwo;
    private JLabel labelThree;
    private ImageIcon[] images;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnSpin;
    private JButton btnExit;
    private boolean threadsStarted = false;
    private ThreadCount runOne;
    private ThreadCount runTwo;
    private ThreadCount runThree;
    private Thread thOne;
    private Thread thTwo;
    private Thread thThree;
    private JTextField jTextField;
    private JLabel jLabel;
    private JLabel jLabelTitle;
    private Random random = new Random();

    public MainWindow() {
        setTitle("Máquina Tragamonedas");
        setSize(700, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        beginComponents();
        addComponents();
    }

    public void begin() {
        beginComponents();
        addComponents();
    }

    private void beginComponents() {
        labelOne = new JLabel();
        labelTwo = new JLabel();
        labelThree = new JLabel();
        images = new ImageIcon[3];

        btnOne = new JButton("Stop");
        btnTwo = new JButton("Stop");
        btnThree = new JButton("Stop");
        btnSpin = new JButton("Spin");
        btnExit = new JButton("Salir");

        btnOne.setPreferredSize(new Dimension(80, 30));
        btnTwo.setPreferredSize(new Dimension(80, 30));
        btnThree.setPreferredSize(new Dimension(80, 30));
        btnSpin.setPreferredSize(new Dimension(100, 40));
        btnExit.setPreferredSize(new Dimension(90, 30));

        images[0] = new ImageIcon("src/test/resources/Diamond.png");
        images[1] = new ImageIcon("src/test/resources/Clover.png");
        images[2] = new ImageIcon("src/test/resources/Currency.png");

        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon(images[i].getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            images[i].setDescription(getImageDescription(i));
        }

        winCombinations = new ArrayList<>();
        winCombinations.add(new WinCombination(getImageDescription(0), 50));
        winCombinations.add(new WinCombination(getImageDescription(1), 20));

        jTextField = new JTextField(20);
        jTextField.setEditable(false);
        jTextField.setText(String.format("%.2f", betAmount));

        labelOne.setIcon(images[0]);
        labelTwo.setIcon(images[1]);
        labelThree.setIcon(images[2]);

        jLabel = new JLabel("Win");
        jLabelTitle = new JLabel("SLOTS");
        jLabelTitle.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        jLabelTitle.setBorder(BorderFactory.createEmptyBorder(20, 190, 15, 0));
    }

    private String getImageDescription(int index) {
        switch (index) {
            case 0:
                return "Diamond";
            case 1:
                return "Clover";
            case 2:
                return "Currency";
            default:
                return "";
        }
    }

    public void multiplyBet(int multiplier) {
        betAmount *= multiplier;
        jTextField.setText(String.format("%.2f", betAmount));
    }

    private void addComponents() {
        JPanel slotMachinePanel = new JPanel();
        slotMachinePanel.setLayout(new BoxLayout(slotMachinePanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());

        titlePanel.add(jLabelTitle, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        imagePanel.add(labelOne);
        imagePanel.add(labelTwo);
        imagePanel.add(labelThree);

        JPanel btnStopPanel = new JPanel();
        btnStopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));

        btnStopPanel.add(btnOne);
        btnStopPanel.add(btnTwo);
        btnStopPanel.add(btnThree);

        JPanel btnSpinPanel = new JPanel();
        btnSpinPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        jTextField = new JTextField(20);
        jTextField.setText(String.format("%.2f", betAmount));
        jTextField.setEditable(false);

        btnSpinPanel.add(jTextField);
        btnSpinPanel.add(jLabel);
        btnSpinPanel.add(Box.createVerticalStrut(10));
        btnSpinPanel.add(btnSpin);

        JPanel btnExitPanel = new JPanel();
        btnExitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnExitPanel.add(btnExit);

        slotMachinePanel.add(imagePanel);
        slotMachinePanel.add(btnStopPanel);
        slotMachinePanel.add(btnSpinPanel);
        slotMachinePanel.add(btnExitPanel);

        this.add(slotMachinePanel, BorderLayout.CENTER);

        btnOne.addActionListener((e) -> {
            runOne.stopThread();
        });

        btnTwo.addActionListener((e) -> {
            runTwo.stopThread();
        });

        btnThree.addActionListener((e) -> {
            runThree.stopThread();
        });

        btnSpin.addActionListener((e) -> {
            betAmount -= baseBetAmount;

            if (threadsStarted) {
                runOne.stopThread();
                runTwo.stopThread();
                runThree.stopThread();
                thOne.interrupt();
                thTwo.interrupt();
                thThree.interrupt();
            }

            runOne = new ThreadCount(labelOne, images, true);
            runTwo = new ThreadCount(labelTwo, images, true);
            runThree = new ThreadCount(labelThree, images, true);

            runOne.setMainWindow(this);
            runTwo.setMainWindow(this);
            runThree.setMainWindow(this);

            thOne = new Thread(runOne);
            thTwo = new Thread(runTwo);
            thThree = new Thread(runThree);

            thOne.start();
            thTwo.start();
            thThree.start();

            threadsStarted = true;

            int matchingImages = 0;

            for (WinCombination combination : winCombinations) {
                ImageIcon imageOne = (ImageIcon) labelOne.getIcon();
                ImageIcon imageTwo = (ImageIcon) labelTwo.getIcon();
                ImageIcon imageThree = (ImageIcon) labelThree.getIcon();

                String descriptionOne = (imageOne != null) ? imageOne.getDescription() : null;
                String descriptionTwo = (imageTwo != null) ? imageTwo.getDescription() : null;
                String descriptionThree = (imageThree != null) ? imageThree.getDescription() : null;

                if (descriptionOne != null && descriptionTwo != null && descriptionThree != null &&
                        descriptionOne.equals(combination.getImageDescription()) &&
                        descriptionTwo.equals(combination.getImageDescription()) &&
                        descriptionThree.equals(combination.getImageDescription())) {
                    matchingImages = 3;
                    currentWin += combination.getWin() * 3;
                    break;
                } else if ((descriptionOne != null && descriptionTwo != null &&
                        descriptionOne.equals(descriptionTwo)) ||
                        (descriptionTwo != null && descriptionThree != null &&
                                descriptionTwo.equals(descriptionThree)) ||
                        (descriptionOne != null && descriptionThree != null &&
                                descriptionOne.equals(descriptionThree))) {
                    matchingImages = 2;
                    currentWin += combination.getWin() * 2;
                }
            }

            if (matchingImages > 0) {
                betAmount += baseBetAmount * matchingImages;
            }

            jTextField.setText(String.format("%.2f", betAmount));
            jLabel.setText("Win" + matchingImages);
        });

        btnExit.addActionListener((e) -> {
            System.exit(0);
        });
    }
}
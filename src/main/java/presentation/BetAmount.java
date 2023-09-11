package presentation;

import javax.swing.*;
import java.awt.*;

public class BetAmount extends JFrame {

    private JButton accept;
    private JTextField jTextField;
    private JLabel jLabel;

    public BetAmount() {
        setTitle("Monto Apuesta");
        setSize(400, 200);
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

        jTextField = new JTextField(15);
        jLabel = new JLabel("Ingrese el monto que desea apostar: ");
        accept = new JButton("Aceptar");
        accept.setPreferredSize(new Dimension(80,30));

    }

    private void addComponents() {

        JPanel machinePanel = new JPanel();
        machinePanel.setLayout(new BoxLayout(machinePanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        titlePanel.add(jLabel);

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,0));
        numberPanel.add(jTextField);

        JPanel btnAccept = new JPanel();
        btnAccept.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        btnAccept.add(accept);

        machinePanel.add(titlePanel);
        machinePanel.add(numberPanel);
        machinePanel.add(btnAccept);

        accept.addActionListener((e)->{
            String amountText = jTextField.getText();
            try {
                double amount = Double.parseDouble(amountText);
                dispose();
                MainWindow mainWindow = new MainWindow();
                mainWindow.begin();
                mainWindow.setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(machinePanel, BorderLayout.CENTER);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mirza Uzair Baig
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Q2 extends JFrame {
    private JTextField nameField = new JTextField(25);
    private JTextField emailField = new JTextField(30);
    private JTextField ageField = new JTextField(10);

    public Q2() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4));
        panel.setBackground(new Color(240, 240, 240)); 

        Font labelFont = new Font("Calibri", Font.BOLD, 14);
        Font textFieldFont = new Font("Calibri", Font.PLAIN, 14);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        panel.add(nameLabel);

        nameField.setFont(textFieldFont);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        panel.add(emailLabel);

        emailField.setFont(textFieldFont);
        panel.add(emailField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        panel.add(ageLabel);

        ageField.setFont(textFieldFont);
        panel.add(ageField);

        JButton submitButton = new JButton("REGISTER!");
        submitButton.setFont(labelFont);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(0, 122, 204));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    int age = Integer.parseInt(ageField.getText());

                   
                    JOptionPane.showMessageDialog(Q2.this,
                            "Registration successful:\nName: " + name + "\nEmail: " + email + "\nAge: " + age,
                            "Success", JOptionPane.INFORMATION_MESSAGE);

               
                    nameField.setText("");
                    emailField.setText("");
                    ageField.setText("");
                } else {
                    JOptionPane.showMessageDialog(Q2.this,
                            "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(new JLabel());
        panel.add(submitButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private boolean validateFields() {
        return !nameField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !ageField.getText().isEmpty();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Q2 regForm = new Q2();
            regForm.setVisible(true);
        });
    }
}

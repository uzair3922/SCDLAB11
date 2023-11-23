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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q3{
    private JFrame frame;
    private JPanel panel;
    private JTextField display;
    private JButton[] digitButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private double num1 = 0;
    private double num2 = 0;
    private char operator = ' ';
    private boolean calculating = false;

    public Q3() {
        frame = new JFrame("Basic Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 5));

        display = new JTextField();
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        frame.add(display, BorderLayout.NORTH);

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = createButton(Integer.toString(i), Color.BLUE);
            digitButtons[i].addActionListener(new DigitButtonListener(i));
        }

        operationButtons = new JButton[4];
        operationButtons[0] = createButton("+", Color.RED);
        operationButtons[1] = createButton("-", Color.RED);
        operationButtons[2] = createButton("*", Color.RED);
        operationButtons[3] = createButton("/", Color.RED);

        for (JButton button : operationButtons) {
            button.addActionListener(new OperationButtonListener(button.getText().charAt(0)));
        }

        equalsButton = createButton("=", Color.GREEN);
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        clearButton = createButton("C", Color.ORANGE);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDisplay();
            }
        });

        panel.add(clearButton);
        for (JButton button : digitButtons) {
            panel.add(button);
        }
        for (JButton button : operationButtons) {
            panel.add(button);
        }
        panel.add(equalsButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton(String label, Color color) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(color);
        return button;
    }

    private void appendToDisplay(String text) {
        String currentText = display.getText();
        if (calculating) {
            currentText = "";
            calculating = false;
        }
        display.setText(currentText + text);
    }

    private void calculateResult() {
        if (operator != ' ' && !calculating) {
            num2 = Double.parseDouble(display.getText());
            double result = 0;
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(Double.toString(result));
            num1 = result;
            calculating = true;
        }
    }

    private void clearDisplay() {
        display.setText("");
        num1 = 0;
        num2 = 0;
        operator = ' ';
        calculating = false;
    }

    private class DigitButtonListener implements ActionListener {
        private int digit;

        public DigitButtonListener(int digit) {
            this.digit = digit;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            appendToDisplay(Integer.toString(digit));
        }
    }

    private class OperationButtonListener implements ActionListener {
        private char op;

        public OperationButtonListener(char op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!calculating) {
                num1 = Double.parseDouble(display.getText());
                operator = op;
                calculating = true;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Q3();
            }
        });
    }
}

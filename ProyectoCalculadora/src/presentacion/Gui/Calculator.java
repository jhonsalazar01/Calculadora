package presentacion.Gui;


import logica.DataStructure.Arbol.ExpressionTree;
import logica.DataStructure.InfixToPostfixConverter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.EmptyStackException;


public class Calculator extends JFrame {

    private JLabel display;
    private JLabel displayInfo;
    private JButton[] buttons;


    public void inicialize() {
        setTitle("Calculadora");
        setSize(450, 460);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        initializeComponents();
    }

    private void initializeComponents() {
        display = new JLabel("0");
        display.setBounds(15, 15, 410, 70);
        display.setOpaque(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setBorder(new LineBorder(Color.DARK_GRAY));
        display.setFont(new Font("MONOSPACED", Font.BOLD, 32));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display);

        buttons = new JButton[20];

        displayInfo = new JLabel("");
        displayInfo.setBounds(15, 15, 410, 20);
        displayInfo.setOpaque(false);
        displayInfo.setBackground(Color.BLACK);
        displayInfo.setForeground(Color.orange);
        displayInfo.setFont(new Font("MONOSPACED", Font.BOLD, 18));
        displayInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        add(displayInfo);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        panel.setBounds(15, 95, 410, 320);
        panel.setOpaque(false);
        add(panel);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Button " + (i + 1));
            buttons[i].setFont(new Font("MONOSPACED", Font.BOLD, 18));
            buttons[i].setBackground(Color.DARK_GRAY);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(new LineBorder(Color.DARK_GRAY));
            panel.add(buttons[i]);

        }


        buttons[0].setText("AC");
        buttons[0].setBackground(Color.orange);


        buttons[0].addActionListener(e -> {
            display.setText("0");
            displayInfo.setText("");
        });


        buttons[5].addActionListener(e -> {
            String currentText = display.getText();
            int length = currentText.length();
            if (length > 1) {
                if (length >= 2 && currentText.substring(length - 2).equals("^(")) {
                    display.setText(currentText.substring(0, length - 2));
                } else {
                    display.setText(currentText.substring(0, length - 1));
                }
            } else {
                display.setText("0");
            }
        });


        buttons[1].setText("7");
        buttons[1].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "7" : display.getText() + "7"));
        buttons[2].setText("8");
        buttons[2].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "8" : display.getText() + "8"));
        buttons[3].setText("9");
        buttons[3].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "9" : display.getText() + "9"));
        buttons[5].setText("DEL");
        buttons[5].setBackground(Color.orange);


        buttons[6].setText("4");
        buttons[6].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "4" : display.getText() + "4"));

        buttons[7].setText("5");
        buttons[7].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "5" : display.getText() + "5"));

        buttons[8].setText("6");
        buttons[8].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "6" : display.getText() + "6"));

        buttons[4].setText("/");
        buttons[4].setBackground(Color.gray);
        buttons[4].addActionListener(e -> {

            String currentText = display.getText();
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                if (lastChar != '(') {
                    display.setText(display.getText().equals("0") ? "0" : display.getText() + "/");
                }

            }

        });
        buttons[9].setText("*");
        buttons[9].setBackground(Color.gray);
        buttons[9].addActionListener(e -> {

            String currentText = display.getText();
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                if (lastChar != '(') {
                    display.setText(display.getText().equals("0") ? "0" : display.getText() + "*");
                }

            }

        });

        buttons[10].setText("^");
        buttons[10].setBackground(Color.gray);
        buttons[10].addActionListener(e -> {
            String currentText = display.getText();
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '(') {
                    display.setText(display.getText().equals("0") ? "^(" : display.getText() + "");
                } else {
                    display.setText(display.getText().equals("0") ? "0^(" : display.getText() + "^(");
                }
            }
        });


        buttons[11].setText("1");
        buttons[11].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "1" : display.getText() + "1"));
        buttons[12].setText("2");
        buttons[12].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "2" : display.getText() + "2"));
        buttons[13].setText("3");
        buttons[13].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "3" : display.getText() + "3"));

        buttons[14].setText("-");
        buttons[14].setBackground(Color.gray);
        buttons[14].addActionListener(e -> {

            String currentText = display.getText();
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                if (lastChar != '(') {
                    display.setText(display.getText().equals("0") ? "0" : display.getText() + "-");
                }

            }

        });
        buttons[15].setText("(");
        buttons[15].setBackground(Color.gray);
        buttons[15].addActionListener(e -> {
                    String currentText = display.getText();
                    char lastChar = currentText.charAt(currentText.length() - 1);

                    if (lastChar == ')') {
                        display.setText(display.getText().equals("0") ? "0" : display.getText() + "*(");

                    } else {
                        display.setText(display.getText().equals("0") ? "(" : display.getText() + "(");
                    }
                }
        );

        buttons[16].setText(")");
        buttons[16].setBackground(Color.gray);
        buttons[16].addActionListener(e -> {
            String currentText = display.getText();
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                if (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '/') {
                    display.setText(display.getText().equals("0") ? "0" : display.getText() + ")");
                }
            }
        });

        buttons[17].setText("0");
        buttons[17].addActionListener(e ->
                display.setText(display.getText().equals("0") ? "0" : display.getText() + "0"));

        buttons[18].setText("=");
        buttons[18].setBackground(new Color(12, 180, 224));
        buttons[18].addActionListener(e -> {
            try {
                String currentText = display.getText();
                if (currentText.length() > 0) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                        display.setText(currentText.substring(0, currentText.length() - 1));
                    } else {
                        InfixToPostfixConverter infixToPostfixConverter = new InfixToPostfixConverter();
                        if (infixToPostfixConverter.isValidExpression(display.getText())) {
                            displayInfo.setText(display.getText());
                            ExpressionTree expressionTree = new ExpressionTree();
                            expressionTree.buildTree(infixToPostfixConverter.infixToPostfix(display.getText()));
                            double result = expressionTree.evaluate();
                            display.setText(String.valueOf(result));
                        } else {
                            if (!infixToPostfixConverter.isValidExpression(display.getText())) {
                                displayInfo.setText("Error al validar paréntesis");
                            } else {
                                displayInfo.setText("Error");
                            }
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                displayInfo.setText("Error");
            }catch (EmptyStackException ex){
                displayInfo.setText("Error");
            }
        });

        buttons[19].setText("+");
        buttons[19].setBackground(Color.gray);
        buttons[19].addActionListener(e -> {

            String currentText = display.getText();
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                if (lastChar != '(') {
                    display.setText(display.getText().equals("0") ? "0" : display.getText() + "+");
                }

            }

        });

    }
}
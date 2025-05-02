import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorMain implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JTextField historyfield;
    JButton[] numberButton = new JButton[10];
    JButton[] actionButton = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decimalButton, eqButton, delButton, clrButton , negButton;
    JPanel panel ;
    Boolean historySwitch = false;

    Font myFont = new Font("Arial", Font.BOLD,30);
    Font myFont2 = new Font("Arial", Font.BOLD,10);

    double num1 = 0, num2 = 0, result = 0;
    int resultInt = 0;
    char operator;

    CalculatorMain() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 40, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        historyfield = new JTextField();
        historyfield.setBounds(50, 15, 300, 20);
        historyfield.setFont(myFont2);
        historyfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decimalButton = new JButton(".");
        eqButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        actionButton[0] = addButton;
        actionButton[1] = subButton;
        actionButton[2] = mulButton;
        actionButton[3] = divButton;
        actionButton[4] = decimalButton;
        actionButton[5] = eqButton;
        actionButton[6] = delButton;
        actionButton[7] = clrButton;

        for(int i = 0; i < 8; i++) {
            actionButton[i].addActionListener(this);
            actionButton[i].setFont(myFont);
            actionButton[i].setFocusable(false);
        }

        for(int i = 0; i < 10; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(myFont);
            numberButton[i].setFocusable(false);
        }


        delButton.setBounds(50,430,145,50);
        clrButton.setBounds(205,430,145,50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(mulButton);
        panel.add(decimalButton);
        panel.add(numberButton[0]);
        panel.add(eqButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.add(historyfield);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        CalculatorMain calc = new CalculatorMain();

    }

    private void switche() {
        if (historySwitch) {
            historyfield.setText(null);
            historySwitch = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 10 ; i++){
            if(e.getSource() == numberButton[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
            switche();
        }

        if (e.getSource() == decimalButton) {
            textfield.setText(textfield.getText().concat("."));
        }

        if(e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
            historyfield.setText(historyfield.getText().concat(String.valueOf(num1) + " " + operator + " "));
            switche();
        }

        if(e.getSource() == subButton) {
            if(textfield.getText().isEmpty()){
                textfield.setText(textfield.getText().concat("-"));
            } else {
                num1 = Double.parseDouble(textfield.getText());
                operator = '-';
                textfield.setText("");
                historyfield.setText(historyfield.getText().concat(String.valueOf(num1) + " " + operator + " "));
            }
            switche();

        }

        if(e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
            historyfield.setText(historyfield.getText().concat(String.valueOf(num1) + " " + operator + " "));
            switche();
        }

        if(e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
            historyfield.setText(historyfield.getText().concat(String.valueOf(num1) + " " + operator + " "));
            switche();
        }

        if(e.getSource() == eqButton) {
            num2 = Double.parseDouble(textfield.getText());

            switch (operator) {
                case'+':
                    result = num1 + num2 ;
                    break;
                case'-':
                    result = num1 - num2 ;
                    break;
                case'*':
                    result = num1 * num2 ;
                    break;
                case'/':
                    result = num1 / num2 ;
                    break;

            }

            if (result % 1 == 0) {
                String str = String.valueOf(result);
                String resultSTR = "";
                for (int i = 0; i < str.length() - 2; i++) {
                    resultSTR += str.charAt(i);
                }
                textfield.setText(resultSTR);
            } else {
                textfield.setText(String.valueOf(result));
            }

            historyfield.setText(historyfield.getText().concat(String.valueOf(num2) + " = " + String.valueOf(result)));
            historySwitch = true;
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
            switche();
        }

        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for(int i = 0; i < string.length() - 1 ; i++) {
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }

    }
}

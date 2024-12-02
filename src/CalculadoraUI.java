import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraUI extends JFrame {

    //Variaveis global
    private JTextField txtDisplay;
    private JPanel panel;
    private double num1, num2, result;
    private char operator;


    public CalculadoraUI() {
        setTitle("Calculadora");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtDisplay = new JTextField();
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 24));
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        add(txtDisplay, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        add(panel, BorderLayout.CENTER);

        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }
    }


    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    txtDisplay.setText("");
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    num1 = Double.parseDouble(txtDisplay.getText());
                    operator = command.charAt(0);
                    txtDisplay.setText("");
                    break;

                case "=":
                    num2 = Double.parseDouble(txtDisplay.getText());
                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/': result = num1 / num2; break;
                    }
                    txtDisplay.setText(String.valueOf(result));
                    break;

                default:
                    txtDisplay.setText(txtDisplay.getText() + command);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraUI calc = new CalculadoraUI();
            calc.setVisible(true);
        });
    }
}

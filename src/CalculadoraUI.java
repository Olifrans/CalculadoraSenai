import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculadoraUI extends JFrame {

    // Variáveis globais
    private JTextField txtDisplay;
    private JPanel panel;
    private double num1, num2, result;
    private char operator;


    public CalculadoraUI() {
        // Configurações do JFrame
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Tela de exibição
        txtDisplay = new JTextField();
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 24));
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setEditable(false);  // Evitar edição direta
        add(txtDisplay, BorderLayout.NORTH);



        // Painel de botões
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5)); // 5 linhas e 4 colunas
        add(panel, BorderLayout.CENTER);

        // Definir os botões da calculadora
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "%"
        };


        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setFocusPainted(false);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }


        // Adicionar um KeyListener para capturar entradas do teclado
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();

                // Verifica se a tecla pressionada é um número ou operador
                if ((key >= '0' && key <= '9') || key == '+' || key == '-' || key == '*' || key == '/' || key == '=' || key == '%') {
                    processInput(String.valueOf(key));
                }

                // Verifica se a tecla pressionada é a tecla "Enter"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processInput("=");  // Simula o pressionamento de "=" quando Enter for pressionado
                }
            }
        });

        // Torna o JFrame focado para receber os eventos do teclado
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    // Método para processar a entrada
    private void processInput(String input) {
        switch (input) {
            case "C":
                txtDisplay.setText("");  // Limpa o display
                break;
            case "+": case "-": case "*": case "/": case "%":
                if (!txtDisplay.getText().isEmpty()) {
                    num1 = Double.parseDouble(txtDisplay.getText());
                    operator = input.charAt(0);  // Salva o operador
                    txtDisplay.setText("");  // Limpa o display
                }
                break;
            case "=":
                if (!txtDisplay.getText().isEmpty()) {
                    num2 = Double.parseDouble(txtDisplay.getText());
                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/':
                            if (num2 == 0) {
                                txtDisplay.setText("Erro");  // Divisão por zero
                                return;
                            }
                            result = num1 / num2; break;
                        case '%':
                            result = num1 % num2; break;
                    }
                    txtDisplay.setText(String.valueOf(result));  // Exibe o resultado
                }
                break;
            default:
                // Quando um número é pressionado
                txtDisplay.setText(txtDisplay.getText() + input);
                break;
        }
    }

    // Classe para escutar os eventos dos botões
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            processInput(command);
        }
    }

    // Método principal para inicializar a calculadora
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraUI calc = new CalculadoraUI();
            calc.setVisible(true);
        });
    }
}
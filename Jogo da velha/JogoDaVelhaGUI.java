import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelhaGUI extends JFrame {
    JButton[] botoes = new JButton[9];
    char jogadorAtual = 'X';

    public JogoDaVelhaGUI() {
        setTitle("Jogo da Velha");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton("");
            botoes[i].setFont(new Font("Arial", Font.BOLD, 60));
            final int index = i;
            botoes[i].addActionListener(e -> jogar(index));
            add(botoes[i]);
        }

        setVisible(true);
    }

    void jogar(int index) {
        if (!botoes[index].getText().equals("")) return;

        botoes[index].setText(String.valueOf(jogadorAtual));
        if (verificaVitoria()) {
            JOptionPane.showMessageDialog(this, "Jogador " + jogadorAtual + " venceu!");
            reiniciar();
        } else if (empate()) {
            JOptionPane.showMessageDialog(this, "Empate!");
            reiniciar();
        } else {
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }
    }

    boolean verificaVitoria() {
        int[][] combinacoes = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] c : combinacoes) {
            if (botoes[c[0]].getText().equals(String.valueOf(jogadorAtual)) &&
                botoes[c[1]].getText().equals(String.valueOf(jogadorAtual)) &&
                botoes[c[2]].getText().equals(String.valueOf(jogadorAtual)))
                return true;
        }
        return false;
    }

    boolean empate() {
        for (JButton b : botoes)
            if (b.getText().equals("")) return false;
        return true;
    }

    void reiniciar() {
        for (JButton b : botoes)
            b.setText("");
        jogadorAtual = 'X';
    }

    public static void main(String[] args) {
        new JogoDaVelhaGUI();
    }
}

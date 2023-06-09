import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToeGame extends JFrame implements ActionListener {

  private JButton[] buttons;

  private JLabel player;
  private JLabel l1;
  private JLabel l2;

  private String currentPlayer;
  Color color = Color.GREEN;
  private static int totalClicks = 0;

  public TicTacToeGame() {
    currentPlayer = "X";
    setTitle("Tic Tac Toe Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add the JButtons to the JFrame
    buttons = new JButton[9];
    for (int i = 0; i < 9; i++) {
      buttons[i] = new JButton((i + 1) + "");
      add(buttons[i]);
    }

    l1 = new JLabel("");
    l1.setHorizontalAlignment(SwingConstants.CENTER);
    add(l1);

    player = new JLabel("Your Turn " + currentPlayer);
    player.setHorizontalAlignment(SwingConstants.CENTER);
    add(player);

    l2 = new JLabel("");
    l2.setHorizontalAlignment(SwingConstants.CENTER);
    add(l2);

    // JFrame properties
    setLayout(new GridLayout(4, 3));
    setResizable(false);
    setSize(300, 300);
    setVisible(true);
  }

  public void startGame() {
    for (int i = 0; i < 9; i++) {
      buttons[i].addActionListener(this);
    }

  }

  public void actionPerformed(ActionEvent ae) {
    totalClicks++;

    for (int i = 0; i < buttons.length; i++) {
      if (buttons[i] == ae.getSource()) {
        buttons[i].setText(currentPlayer);
        buttons[i].setBackground(color);
        buttons[i].setEnabled(false);
        break;
      }
    }

    if (checkWin()) {
      player.setText(" " + currentPlayer + " Won");
      for (int i = 0; i < buttons.length; i++) {
        buttons[i].setEnabled(false);
      }
    } else {
      if (currentPlayer.equals("X")) {
        currentPlayer = "0";
        color = Color.PINK;
      } else {
        currentPlayer = "X";
        color = Color.GREEN;
      }
      player.setText("Your Turn " + currentPlayer);
      if (totalClicks == 9) {
        player.setText("It's a draw!");
      }
    }
  }

  public boolean checkWin() {
    String[] board = new String[9];
    for (int i = 0; i < 9; i++) {
      board[i] = buttons[i].getText();
    }

    // Check rows
    for (int i = 0; i < 9; i += 3) {
      if (board[i].equals(currentPlayer) && board[i + 1].equals(currentPlayer) && board[i + 2].equals(currentPlayer)) {
        return true;
      }
    }

    // Check columns
    for (int i = 0; i < 3; i++) {
      if (board[i].equals(currentPlayer) && board[i + 3].equals(currentPlayer) && board[i + 6].equals(currentPlayer)) {
        return true;
      }
    }

    // Check diagonals
    if (board[0].equals(currentPlayer) && board[4].equals(currentPlayer) && board[8].equals(currentPlayer)) {
      return true;
    }
    if (board[2].equals(currentPlayer) && board[4].equals(currentPlayer) && board[6].equals(currentPlayer)) {
      return true;
    }

    return false;
  }

}

public class TicTacToe {
  public static void main(String[] args) {
    TicTacToeGame ttt = new TicTacToeGame();
    ttt.setLocationRelativeTo(null);
    ttt.startGame();
  }
}


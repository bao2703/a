package com.neptune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Neptune on 3/18/2017.
 */
public class GomokuFrame extends JFrame {
    private Gomoku gomoku;
    private MarkButton[][] markButton;
    GameState gameState = GameState.ON_GOING;

    public GomokuFrame() {
        gomoku = new Gomoku();
        markButton = new MarkButton[Rule.SIZE][Rule.SIZE];
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(Rule.SIZE, Rule.SIZE));
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                markButton[i][j] = new MarkButton(i, j);
                markButton[i][j].setFont(new Font("Arial", Font.BOLD, 45));
                panel.add(markButton[i][j]);
            }
        }
    }

    private class MarkButton extends JButton implements ActionListener {
        private boolean active;
        private int row;
        private int col;

        public MarkButton(int row, int col) {
            active = true;
            this.row = row;
            this.col = col;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (active) {
                gomoku.performMove(row, col);
                this.changeIcon();

                if (gameState == GameState.ON_GOING) {
                    Move move = gomoku.getBestMove();
                    gomoku.performMove(move.row, move.col);
                    markButton[move.row][move.col].changeIcon();
                }
            }
        }

        private void changeIcon() {
            active = false;
            if (gomoku.state.getCurrentPlayer() == Mark.X) {
                this.setText("O");
                this.setForeground(Color.red);
            } else {
                this.setText("X");
                this.setForeground(Color.blue);
            }

        }
    }
}

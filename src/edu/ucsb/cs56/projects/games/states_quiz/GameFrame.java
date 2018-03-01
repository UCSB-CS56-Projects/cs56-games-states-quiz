package edu.ucsb.cs56.projects.games.states_quiz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JFrame;

/**
 * GameFrame sets and initializes the frame and QuestionManager.
 *
 * @author Nina Kaufman
 * @author Jenny Vien
 * @author Zhansaya Abdikarimova
 * @author Nick Eidler
 * @author Ryan Allen
 * @author Ryan Kemper
 */

public class GameFrame extends JFrame implements ActionListener {

    private static Dimension frameDimension = new Dimension(980, 680);

    private GamePanel gamePanel;
    private FrontPanel frontPanel;
    private QuestionManager questionManager;

    public GameFrame() {
        init();
    }

    private void init() {
        gamePanel = new GamePanel(
                () -> {
                    GameFrame.this.getContentPane().removeAll();
                    GameFrame.this.init();
                }
        );
        questionManager = new QuestionManager(gamePanel, () -> {
            GameFrame.this.getContentPane().removeAll();
            GameFrame.this.init();
        });
        gamePanel.setQuestionManager(questionManager);

        frontPanel = new FrontPanel();

        frontPanel.getStateButton().addActionListener(this);
        frontPanel.getCapitalButton().addActionListener(this);
        frontPanel.getStateThenCapitalButton().addActionListener(this);

        this.setResizable(false);
        this.getContentPane().add(gamePanel);
        this.getContentPane().add(frontPanel);
        this.setMinimumSize(frameDimension);

        this.setTitle("You think you know all US states?");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frontPanel.setVisible(false);
        gamePanel.setVisible(true);


        if (e.getActionCommand().matches("Capitals")) {
            questionManager.setGameMode("Capitals");
            System.out.println("Capitals");
            this.setTitle("Capitals");
        } else if (e.getActionCommand().matches("States")) {
            questionManager.setGameMode("States");
            System.out.println("States");
            this.setTitle("States");
        } else if (e.getActionCommand().matches("States then Capitals")) {
            questionManager.setGameMode("States then Capitals");
            System.out.println("States then Capitals");
            this.setTitle("States then Capitals");
        }
        ButtonModel selectedDiff = frontPanel.getDifficultiesGroup().getSelection();
        questionManager.init();
        questionManager.setDifficulty(selectedDiff.getActionCommand());
        System.out.println(questionManager.getDifficulty());
    }
}

package edu.ucsb.cs56.projects.games.states_quiz;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class HighscoreFrame extends JFrame {

    JTextArea scoreArea;
    
    public HighscoreFrame() {

	this.setTitle("Highscores");

        scoreArea = new JTextArea();
	scoreArea.setBounds(0, 0, 500, 500);

	this.add(scoreArea);
	
	this.setSize(500, 500);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    public void writeHighScores(File file) {
	try {
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String line = in.readLine();
	    while(line != null){
		scoreArea.append(line + "\n");
		line = in.readLine();
	    }
	} catch(IOException e){
	    e.printStackTrace();
	}
    }
}

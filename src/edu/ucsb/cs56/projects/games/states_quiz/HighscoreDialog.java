package edu.ucsb.cs56.projects.games.states_quiz;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class HighscoreDialog extends JDialog {

    JTextArea scoreArea;
    
    public HighscoreDialog(JFrame frame, int x, int y, File file) {

	super(frame, "Highscores", true);

        scoreArea = new JTextArea();
	scoreArea.setBounds(0, 0, x, y);

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

	this.add(scoreArea);
	
	this.setSize(x, y);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
}

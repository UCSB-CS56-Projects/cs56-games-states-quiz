package edu.ucsb.cs56.projects.games.states_quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Provides method to start stopwatch.
 *
 * @author Vincent Wang, Veena Chandran
 * @author Diana Reyes
 */
public class StopWatch extends JLabel {

    private int time = 0;
    private Timer timer;

    public StopWatch(int x, int y, int width, int height) {
	super();
        setBounds(x, y, width, height);
        setVisible(true);

	ActionListener task = evt -> {
            time = time + 1;
	    setText("Time Elapsed: "+ getFormattedTime());
        };
        timer = new Timer(1000, task); // Execute task each 1000 milliseconds
        setOpaque(true);

    }
    
    /**
     * Starts the stopwatch
     */
    
    public void start() {
	timer.start();
    }

    public void addPenalty() {
        time += 3;
    }

    public String getFormattedTime() {
	int minutes = time/60;
	int seconds = time%60;
	return String.format("%02d:%02d", minutes, seconds);
    }

    public void stop() {
	timer.stop();
    }
}

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

    public StopWatch(int x, int y, int width, int height) {
        super();
        setBounds(x, y, width, height);
        setVisible(true);

           }

    /**
     * Starts the stopwatch
     */

    public void start() {

        ActionListener task = evt -> {
            int minutes = time/60;
            int seconds = time%60;

            time = time + 1;

	    String text = String.format("%02d:%02d", minutes, seconds);

	    setText("Time Elapsed: "+ text);
        };
        Timer timer = new Timer(1000, task); // Execute task each 1000 milliseconds           
        setOpaque(true);
        timer.start();


    }

    public void addPenalty() {
        time += 3;
    }

    public int getTime() {
	return time;
    }
}

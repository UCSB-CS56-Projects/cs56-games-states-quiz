package edu.ucsb.cs56.projects.games.states_quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**                                                                                    
 * Provides method to start ready set go texts                                                             
 * @author Diana Reyes                                                                 
 */
public class ReadyGo extends JLabel {
    private String ready = "Ready?";
    private String set = "Set.";
    private String go = "GO!";
    private int pretime = 0;



    public ReadyGo(int x, int y, int width, int height) {
        super();
        setBounds(x, y, width, height);
        setVisible(true);
    }
    public void readyGoStart(){
        ActionListener task = evt -> {

            if(pretime == 0){
                setText(ready);
            }
            if(pretime == 1){
                setText(set);
            }
            if(pretime == 2){
                setText(go);
            }
	    if (pretime >= 3){
	        setText("");
	    }
            pretime = pretime + 1;
        };
        Timer timer2 = new Timer(2000, task);
        setOpaque(true);
        timer2.start();
    }
}

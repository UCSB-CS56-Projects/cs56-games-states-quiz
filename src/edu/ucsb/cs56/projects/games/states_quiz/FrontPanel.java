package edu.ucsb.cs56.projects.games.states_quiz;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class FrontPanel extends JPanel{
    private BufferedImage map;
    private JLabel title;
    private JButton stateButton;
    private JButton capitalButton;


    public FrontPanel(){

        this.setLayout(null);
        try {
            map = ImageIO.read(getClass().getClassLoader().getResource(
                    "image/front.jpg"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        assert (map != null);

        title = new JLabel("Welcome to Country Quiz!",JLabel.CENTER);
        Font titleFont = new Font("TimesRoman",Font.PLAIN,50);
        title.setFont(titleFont);


        stateButton = new JButton("States");
        stateButton.setFont(new Font("TimesRoman",Font.PLAIN,30));

        capitalButton = new JButton("Capitals");
        capitalButton.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        title.setBounds(100,80,800,200);
        stateButton.setBounds(340,280,300,100);
        capitalButton.setBounds(340, 400, 300, 100);

        this.add(title);
        this.add(stateButton);
        this.add(capitalButton);

        this.setVisible(true);
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(map, 0, 0,980,680, this);

    }

    public JButton getStateButton(){
        return this.stateButton;
    }
    public JButton getCapitalButton(){
        return this.capitalButton;
    }
}

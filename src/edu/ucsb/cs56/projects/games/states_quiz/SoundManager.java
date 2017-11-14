package edu.ucsb.cs56.projects.games.states_quiz;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Provides methods to play different sounds. Add all new sound effects here.
 *
 * @author Vincent Wang, Veena Chandran
 */
public class SoundManager {

    private static final String correctSoundUrl
	= "src/edu/ucsb/cs56/projects/games/states_quiz/sound/correct.wav";
    private static final String incorrectSoundUrl
	= "src/edu/ucsb/cs56/projects/games/states_quiz/sound/incorrect.wav";
    private static final String completedGameSoundUrl
	= "src/edu/ucsb/cs56/projects/games/states_quiz/sound/applause.wav";

    public SoundManager() {}

    /**
     * Plays the sound for correct answers.
     */
    public void playCorrectSound() {
	playSound(correctSoundUrl);
    }

    /**
     * Plays the sound for incorrect answers.
     */
    public void playIncorrectSound() {
	playSound(incorrectSoundUrl);
    }

    /**
     * Plays the sound for completing the game.
     */
    public void playCompletedSound() {
	playSound(completedGameSoundUrl);
    }

    private void playSound(String url) {
	try {
	    // Create sound effect
	    AudioInputStream audioInputStream
		= AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	    clip.start();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

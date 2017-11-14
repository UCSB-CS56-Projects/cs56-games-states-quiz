package edu.ucsb.cs56.projects.games.states_quiz;

public enum AnswerOption {
    CORRECT("Congrats! "),
    INCORRECT("Capital is Incorrect! "),
    NO_ANSWER("Skipped! Moving on. ");

    private String message;

    AnswerOption(String message) {
	this.message = message;
    }

    public String getMessage() {
	return message;
    }
}

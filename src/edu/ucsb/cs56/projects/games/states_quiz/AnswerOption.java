package edu.ucsb.cs56.projects.games.states_quiz;

public enum AnswerOption {
    CORRECT("Congrats! "),
    INCORRECT("Capital is Incorrect! "),
    NO_ANSWER("Skipped! Moving on. ");

    private String message;
    private static String optionalCapitalName;

    AnswerOption(String message) {
	    this.message = message;
    }

    public static void setCapital(String capitalName) {
        AnswerOption.optionalCapitalName = capitalName;
    }

    public static String getCapital() {
        return optionalCapitalName;
    }

    public String getMessage() {
	return message;
    }
}

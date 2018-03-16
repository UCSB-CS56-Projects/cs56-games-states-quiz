package edu.ucsb.cs56.projects.games.states_quiz;
/** 
*   
*   
*   
*/  
class StateErrorChecker extends NameErrorChecker {
    private int previousError;

    StateErrorChecker(String other) {
        super(other);
        this.previousError = -1;
    }

    public String guessCritique(String other) {
        int errors = this.numberOfErrors(other);
        boolean leastErrorsYet = false;

        if (this.previousError == -1)
            this.previousError = errors;
        if (this.previousError > errors) {
            previousError = errors;
            leastErrorsYet = true;
        }
        if (errors > 20)
            return "Capital is incorrect!";
        
        String quality;
        String closeness;
        if (errors > 15){
            quality = "Decent ";
            closeness = " might be onto something.";
        } else if (errors > 10) {
            quality = "Nice";
            closeness = "'re getting close.";
        } else if (errors > 5) {
            quality = "Good";
            closeness = "'re close.";
        } else {
            quality = "Great";
            closeness = "'re very close!";
        }




        String returnThis = "";


        if (leastErrorsYet)
            return new String(quality + " guess. That's the closest you've gotten!"); 
        return new String(quality + " guess. You" + closeness);
    }
}
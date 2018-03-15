/** 
*   NameErrorChecker compares strings, expressed in integer value of "errors"
*   that aims to scale inversely with human intuition of "similarity"
*   between words   
*
*   In making meaning of the returned error quantity, we should
*   expect more error count from longer words and account for that
*
*   @author Glen Taggart
*/  


class NameErrorChecker {
    private String data;

    NameErrorChecker(String source) {
        this.data = source;
    }

    /**
    *   calculates total number of "errors," as a measure of difference between Strings
    *   @param other String with which to compare calling object
    *   @return number of "errors". currently these are weighted arbitrarily,
    */
    /*   but it would be interesting to base this off of human spelling data
    *   or spatial keyboard models
    */  
    public int numberOfErrors(String other){
        int errors = 0;
        errors += this.letterInclusionErrors(other) * 2;
        errors += java.lang.Math.abs(this.data.length() - other.length());
        errors += this.sequentialityErrors(other) + (new NameErrorChecker(other)).sequentialityErrors(this.data);

        return errors;
    }

    /**
    *   checks closeness of strings, including ordering significance, by measuring errors as
    *   the number of cycles (cycling forwards only) through other necessary to spell the
    *   calling object in order plus 
    *   @return number of loops through 
    *   @param other nonempty string to compare calling object with
    */
    private int sequentialityErrors(String other) {
        int errors = 0;
        int otherIndex = 0;
        int prevOtherIndex = 0;

        if ((this.data.length() == 0) || (other.length() == 0))
            return -1;

        for (int thisIndex = 0; thisIndex < this.data.length(); thisIndex++) {
            while ((this.data.toLowerCase().charAt(thisIndex) != other.toLowerCase().charAt(otherIndex)) 
                                                    && (prevOtherIndex != -1)) {
                otherIndex++;
                if (otherIndex >= other.length()) {
                    otherIndex = 0;
                    errors++;
                }
                if (otherIndex == prevOtherIndex) {
                    errors++;
                    prevOtherIndex = -1;
                }

            }
            prevOtherIndex = (otherIndex + other.length() - 1) % other.length();
        }
        return errors;
    }

    /** 
    *   @return number of letters with inequal occurance frequency between the strings
    *   @param other String compared
    */
    private int letterInclusionErrors(String other) {
        int errors = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 26; i++) {
            int thisNextIndex = -1;
            int otherNextIndex = -1;

            do {
                otherNextIndex = other.toLowerCase().indexOf(alphabet.charAt(i),otherNextIndex + 1);
                thisNextIndex = this.data.toLowerCase().indexOf(alphabet.charAt(i),thisNextIndex + 1);
            } while ((otherNextIndex != -1) && (thisNextIndex != -1));

            if (otherNextIndex != thisNextIndex) {
                errors++;
            }   
        }

        return errors;
    }
}
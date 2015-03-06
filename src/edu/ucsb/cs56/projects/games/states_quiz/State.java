package edu.ucsb.cs56.projects.games.states_quiz;

/**
 * State represents a state such as California and New York.
 * @author Zhansaya Abdikarimova
 * @author Jenny Vien
 * @author Nick Eidler
 */

public class State {

    private String name, capital;
    private int xCoord, yCoord;

    /**
     * The default no-args constructor for State.
     */
    public State(){
        name = null;
        capital = null;
        xCoord = 0;
        yCoord = 0;
    }

    /**
     * @param name name of the state.
     * @param capital capital of the state.
     * @param x x coordinate of the state
     * @param y y coordinate of the state
     */
    public State(String name, String capital, int x, int y){
        this.name = name;
        this.capital = capital;
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * Getter for the state's name.
     * @return the name of the state.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the state's capital.
     * @return the capital of the state.
     */
    public String getCapital(){
        return this.capital;
    }


    /**
     * Getter for the x-coordinate of the state.
     * @return the x-coordinate of the state.
     */
    public int getXCoord(){
        return this.xCoord;
    }

    /**
     * Getter fot the y-coordinate of the state
     * @return the y-coordinate of the state
     */
    public int getYCoord(){
        return this.yCoord;
    }

    /**
     * Setter for the name of the state.
     * @param name the name of the state
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter for the capital of the state.
     * @param capital the capital of the state
     */
    public void setCapital(String capital){
        this.capital = capital;
    }

    /**
     * Setter for the x-coordinate of the state.
     * @param x the x-coordinate of the state
     */
    public void setXCoord(int x){
        this.xCoord = x;
    }

    /**
     * Setter for the y-coordinate of the state.
     * @param y the y-coordinate of the state
     */
    public void setYCoord(int y){
        this.yCoord = y;
    }

    /**
     * Returns a string with the name and the capital of the state.
     */

    @Override
    public String toString(){
        return this.getName()+" "+this.getCapital();
    }

}

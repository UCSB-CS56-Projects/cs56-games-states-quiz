package edu.ucsb.cs56.projects.games.states_quiz;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Country represent a country such as the United States.
 * @author Zhansaya Abdikarimova
 * @author Jenny Vien
 * @author Nick Eidler
 */

public class Country {
    private String name;
    private String capital;
    private ArrayList<State> states;

    /**
     * Constructor for creating a Country object.
     */
    public Country(){
        states = new ArrayList<State>();
        this.name = null;
        this.capital = null;
    }

    /**
     * @param name name of the country
     * @param capital the capital of the country
     */
    public Country(String name, String capital){
        states = new ArrayList<State>();
        this.name = name;
        this.capital = capital;

    }

    /**
     * @return The name of the country.
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return The capital of the country.
     */
    public String getCapital(){
        return this.capital;
    }

    /**
     * @param name The name of the country.
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * @param capital The capital of the ocuntry.
     */
    public void setCapital(String capital){
        this.capital = capital;
    }


    /**
     * @param numberOfStates represents the number of states in country
     * @param file represent the file that hold all te states info.
     */
    public void addStates(int numberOfStates, File file) throws Exception {
        Scanner scanner = new Scanner(file);

        String line;
        int x;
        int y;

        for(int i=0;i<numberOfStates;i++){
            line = scanner.nextLine();
            String[] splitted = line.split(",");

            x=Integer.parseInt(splitted[2]);
            y=Integer.parseInt(splitted[3]);
            states.add(new State(splitted[0],splitted[1],x,y));
            //System.out.println(states.get(i));
        }
    }

    /**
     * A list of all the states in the country.
     */
    public ArrayList<State> getStatesArray(){
        return states;
    }

    /**
     * Returns a string with name and the capital of the country. 
     */
    @Override
    public String toString(){
        return this.getName()+" "+this.getCapital();
    }
}

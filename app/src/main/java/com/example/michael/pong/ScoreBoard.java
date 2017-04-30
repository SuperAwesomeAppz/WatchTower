package com.example.michael.pong;

/**
 * Created by Michael on 30/04/2017.
 */

public class ScoreBoard {
    private String name;
    private int score;

    public ScoreBoard(){}

    public ScoreBoard(String name, int score) {
        super();
        this.name = name;
        this.score= score;
    }

    //getters & setters

    @Override
    public String toString() {
        return "ScoreBoard [id=" + name;
    }
    public int getScore()
    {
        return score;
    }
    public String getName()
    {
        return name;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    public void setName(String title)
    {
        this.name = name;
    }


}

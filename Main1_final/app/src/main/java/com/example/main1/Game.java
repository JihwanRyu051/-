package com.example.main1;

import android.widget.Toast;

public class Game {
    private String name;
    private Double best;
    private Double sum;
    private Integer times;

    public Game(){}

    public Game(String name, Double best, Double sum, Integer times){
        this.name = name;
        this.best = best;
        this.sum = sum;
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBest() {
        return best;
    }

    public void setBest(Double best) {
        this.best = best;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public boolean update(Double score){
        this.sum += score;
        this.times++;

        if(score > this.best){
            this.best = score;
            return true;
        }

        return false;
    }
}

package com.thomasbuilds.incidentflow;

public class Incident {
    private int id;
    private String category;
    private String state;
    private int duration;

    public Incident(int id, String category, String state, int duration){
        this.id = id;
        this.category = category;
        this.state = state;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }
    public String getCategory(){
        return category;
    }

    public String getState() {
        return state;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isOpen(){
      return "open".equals(getState());
    }
    public boolean isSlaAtRisk(){
        return isOpen() && getDuration() >= 20;
    }
}

package entity;

import java.util.Date;

public class Spittle implements Comparable<Spittle>{
    private int id;
    private Spitter spitter;
    private String text;
    private Date when;

    public Spittle() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", spitter=" + spitter +
                ", text='" + text + '\'' +
                ", when=" + when +
                '}';
    }

    public int compareTo(Spittle o) {
        return when.compareTo(o.getWhen());
    }
}

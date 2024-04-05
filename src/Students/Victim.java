package src.Students;

import src.Students.StudentFunctions.*;

public class Victim {
    private int points;
    private int absences;
    private int numPicked;
    private int passed;
    private int answered;
    private int phone;
    private int jail;

    private double infPoints;

    private String image;
    private Names name;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone += phone;
    }

    public int getJail() {
        return jail;
    }

    public void setJail(int jail) {
        this.jail += jail;
    }

    public int getAnswered(){
        return answered;
    }

    public void setAnswered(int answered){
        this.answered += answered;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences += absences;
    }

    public int getNumPicked() {
        return numPicked;
    }

    public void setNumPicked(int numPicked) {
        this.numPicked += numPicked;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed += passed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Names getName() {
        return name;
    }

    public void setName(Names name) {
        this.name = name;
    }
    public Victim(Names names, int points, int absences, int numPicked, int passed, int answered, int phone, int jail){
        this.name = names;
        this.points = points;
        this.absences = absences;
        this.numPicked = numPicked;
        this.passed = passed;
        this.answered = answered;
        this.infPoints = Influence.getInfluence(points, absences, passed, answered, numPicked);
        this.phone = phone;
        this.jail = jail;
    }

    public Victim(Victim student){
        this.name = student.getName();
        this.points = student.getPoints();
        this.absences = student.getAbsences();
        this.numPicked = student.getNumPicked();
        this.passed = student.getPassed();
        this.answered = student.getAnswered();
        this.infPoints = student.getInfluenceScore();
        this.phone = student.getPhone();
        this.jail = student.getJail();
    }

    public double getInfluenceScore(){
        this.infPoints = Influence.getInfluence(points, absences, passed, answered, numPicked);
        return infPoints;
    }
}

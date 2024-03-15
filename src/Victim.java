public class Victim {
    private String name;
    private int timesPicked;
    private int score;
    public Victim(String name){
        this.name = name;
        timesPicked = 0;
        score = 0;
    }

    //adds a number to a score
    public void addScore(int n){
        score += n;
    }
    //adds one to the times picked
    public void picked(){
        timesPicked++;
    }
    public String getName() {
        return name;
    }

    public int getTimesPicked() {
        return timesPicked;
    }
    //sets he times picked so they either get picked more or less
    public void setTimesPicked(int t){
        timesPicked = t;
    }
    public int getScore() {
        return score;
    }
}

package game.core.drinkers;

import java.util.Random;

public class Player {
    static Random random = new Random();
    public boolean passed;
    private int alcohol;
    private String name;
    private boolean isAbleToSeePrediction = true;

    private int alcoholPower = 100;

    public Player(String name) {
        this.name = name;
        this.alcohol = 0;
    }

    public Player() {
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void becomeAbleToSeePrediction() {
        isAbleToSeePrediction = true;
    }

    public void becomeUnableToSeePrediction() {
        isAbleToSeePrediction = false;
    }

    public int getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(int alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isAbleToSeePrediction() {
        return isAbleToSeePrediction;
    }

    public void pass() {
        passed = true;
    }

    public void drink() {
        if (!passed) {
            this.alcohol += 40 + random.nextInt(alcoholPower);
            alcoholPower += 40;
        } else {
            System.out.println("You have passed");
        }
    }

    public void npcDrink() {
            drink();
    }
}

package org.app.simulation.agent.food;

import org.app.simulation.agent.Agent;

public class Food extends Agent {
    private int foodAmount;

    public Food() {
        super();
        this.foodAmount = 100;
    }

    public Food(int foodAmount, int x, int y) {
        super();
        this.foodAmount = foodAmount;
        this.setLocx(x);
        this.setLocy(y);
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void ReduceFood(int amount) {
        this.foodAmount -= amount;
    }

    public void FoodDecrease() {
        this.foodAmount -= 1;
    }
}

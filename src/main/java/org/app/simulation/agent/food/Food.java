package org.app.simulation.agent.food;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.meneger.config.Config;

public class Food extends Agent {

    private int foodAmount;

    public Food(Config settings) {
        super(TypAgenta.FOOD, settings);
        this.foodAmount = 100000;
    }

    public double requestFood(int amount) {
        if (this.foodAmount >= amount) {
            this.foodAmount -= amount;
            return amount;
        } else {
            double food = this.foodAmount;
            this.foodAmount = 0;
            return food;
        }
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

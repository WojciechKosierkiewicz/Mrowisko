package org.app.simulation.agent.food;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.meneger.config.Config;

/**
 * Odpowiada za zarządzanie ilością dostępnego pożywienia oraz udostępnianie go dla innych agentów.
 */
public class Food extends Agent {

    private int foodAmount;

    /**
     * Konstruktor klasy Food,
     * inicjalizuje obiekt jedzenia.
     */
    public Food(Config settings) {
        super(TypAgenta.FOOD, settings);
        this.foodAmount = 100000;
    }

    /**
     * Metoda służąca do pobierania żądanej ilości jedzenia.
     * Zwraca rzeczywistą ilość jedzenia,
     * którą udało się pobrać.
     */
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

    /**
     * Redukuje ilość jedzenia o określoną wartość.
     */
    public void ReduceFood(int amount) {
        this.foodAmount -= amount;
    }

    /**
     * Zmniejsza ilość jedzenia o 1.
     */
    public void FoodDecrease() {
        this.foodAmount -= 1;
    }
}

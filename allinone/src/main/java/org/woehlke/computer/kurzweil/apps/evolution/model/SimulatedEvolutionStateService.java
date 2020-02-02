package org.woehlke.computer.kurzweil.apps.evolution.model;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

@Log
public class SimulatedEvolutionStateService {

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private SimulatedEvolutionState simulatedEvolutionState;

    public SimulatedEvolutionStateService(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        createNewState();
    }

    private void createNewState(){
        int foodPerDay = this.ctx.getProperties().getEvolution().getFood().getFoodPerDay();
        int foodPerDayGardenOfEden = this.ctx.getProperties().getEvolution().getGardenOfEden().getFoodPerDay();
        boolean gardenOfEdenEnabled = this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.simulatedEvolutionState = new SimulatedEvolutionState();
        this.simulatedEvolutionState.setFoodPerDay(foodPerDay);
        this.simulatedEvolutionState.setFoodPerDayGardenOfEden(foodPerDayGardenOfEden);
        this.simulatedEvolutionState.setGardenOfEdenEnabled(gardenOfEdenEnabled);
    }

    public void increaseFoodPerDay() {
        simulatedEvolutionState.increaseFoodPerDay();
    }

    public void decreaseFoodPerDay(){
        simulatedEvolutionState.decreaseFoodPerDay();
    }

    public void toggleGardenOfEden() {
        simulatedEvolutionState.toggleGardenOfEden();
    }
}

package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycleStatus;

@Log4j2
@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SimulatedEvolutionPopulation implements SimulatedEvolution {

   private int youngCells;
   private int youngAndFatCells;
   private int fullAgeCells;
   private int hungryCells;
   private int oldCells;
   private int deadCells;
   private int population;
   private long generationYoungest;
   private long generationOldest;
   private long worldIteration;

    /**
   * TODO write doc.
   */
  public void countStatusOfOneCell(CellLifeCycleStatus status) {
    population++;
    switch (status) {
      case YOUNG:
        youngCells++;
        break;
      case YOUNG_AND_FAT:
        youngAndFatCells++;
        break;
      case FULL_AGE:
        fullAgeCells++;
        break;
      case HUNGRY:
        hungryCells++;
        break;
      case OLD:
        oldCells++;
        break;
      case DEAD:
        deadCells++;
        break;
    }
  }

}

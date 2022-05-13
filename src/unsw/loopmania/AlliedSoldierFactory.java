package unsw.loopmania;

import java.util.*;

public class AlliedSoldierFactory {

    private IAlliedSoldierHandler alliedSoldierHandler;
    private List<AlliedSoldier> alliedSoldiers;

    /**
     * Constructor for AlliedSoldierFactory
     * @param alliedSoldierHandler
     * @param alliedSoldiers
     */
    public AlliedSoldierFactory(IAlliedSoldierHandler alliedSoldierHandler, List<AlliedSoldier> alliedSoldiers) {
        this.alliedSoldierHandler = alliedSoldierHandler;
        this.alliedSoldiers = alliedSoldiers;
    }

    /**
     * Creates a new Allied Soldier
     * @return
     */
    public AlliedSoldier createAlliedSoldier() {
        AlliedSoldier soldier = new AlliedSoldier(alliedSoldierHandler);
        soldier.shouldExist().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                alliedSoldiers.remove(soldier);
            }
        });
        return soldier;
    }
}
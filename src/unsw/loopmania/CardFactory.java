package unsw.loopmania;

import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;


public class CardFactory {

    private Map<String, ICardHandler> cardHandlerMap;

    /**
     * Constructor for CardFactory
     * @param cardHandlerMap
     */
    public CardFactory(Map<String, ICardHandler> cardHandlerMap) {
        this.cardHandlerMap = cardHandlerMap;
    }

    // Creates Card
    public BaseCard createCard(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creates Vampire Castle Card
    public BaseCard createVampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.VampireCastle;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creates Zombie Pit Card
    public BaseCard createZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.ZombiePit;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creates Village Card
    public BaseCard createVillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.Village;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creates Barracks Card
    public BaseCard createBarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.Barracks;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creates Trap Card
    public BaseCard createTrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.Trap;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creates Tower Card
    public BaseCard createTowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.Tower;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }

    // Creats CampFire Card
    public BaseCard createCampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.CardName.Campfire;
        return new BaseCard(x, y, cardHandlerMap.get(type), type);
    }
}
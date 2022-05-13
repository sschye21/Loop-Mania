package unsw.loopmania;

public class ShopPotion extends Shop {

    public ShopPotion() {
        super(8, 0);
    }

    @Override
    public boolean buy() {
        if (GlobalConfig.character.getGold() >= costPrice) {
            GlobalConfig.character.acquireGold(-1 * costPrice);
            GlobalConfig.character.acquireHP(GlobalConfig.GameStatics.CharacterInitHP);
            return true;
        }
        return false;
    }

}

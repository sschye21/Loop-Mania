package unsw.loopmania;

public abstract class Shop {

    public int costPrice;
    public int sellPrice;
    private Character character;

    public Shop(int costPrice, int sellPrice) {
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        character = GlobalConfig.character;
    }

    public boolean buy() {
        if (character.getGold() >= costPrice) {
            character.acquireGold(-1 * costPrice);
            return true;
        }
        return false;
    }

    public void sell() {
        character.acquireGold(sellPrice);
    }
}

package unsw.loopmania;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ShopController {

    private MenuSwitcher gameResumeSwitcher;
    private LoopManiaWorldController worldController;
    private ModeMenuController modeController;
    private int countPotion = 0;
    private int countProtective = 0;

    public ShopController(LoopManiaWorldController worldController, ModeMenuController modeController) {
        this.modeController = modeController;
        this.worldController = worldController;
    }

    @FXML
    private ImageView sword;

    @FXML
    private ImageView stake;

    @FXML
    private ImageView staff;

    @FXML
    private ImageView armour;

    @FXML
    private ImageView shield;

    @FXML
    private ImageView helmet;

    @FXML
    private ImageView healthPotion;

    @FXML
    private Button exitShop;

    @FXML
    private Button buySword;

    @FXML
    private Button sellSword;

    @FXML
    private Button buyStake;

    @FXML
    private Button sellStake;

    @FXML
    private Button buyStaff;

    @FXML
    private Button sellStaff;

    @FXML
    private Button buyArmour;

    @FXML
    private Button sellArmour;

    @FXML
    private Button buyShield;

    @FXML
    private Button sellShield;

    @FXML
    private Button buyHelmet;

    @FXML
    private Button sellHelmet;

    @FXML
    private Button buyHealthPotion;

    @FXML
    private Button sellHealthPotion;

    @FXML
    void armourBuyHandler(ActionEvent event) {
        String mode = modeController.getGameMode();
        if (mode.equals("Berserker")) {
            if (countProtective < 1) {
                countProtective++;
                ShopArmour shop = new ShopArmour();
                BaseEquipment armour = worldController.getWorld().addUnequippedEquipment("Armour");
                worldController.onLoadUnequipped(armour);
                shop.buy();
            } else {
                System.out.println("Berserker mode means only one protective gear is bought!");
            }
        } else {
            countProtective++;
            ShopArmour shop = new ShopArmour();
            BaseEquipment armour = worldController.getWorld().addUnequippedEquipment("Armour");
            worldController.onLoadUnequipped(armour);
            shop.buy();
        }

    }

    @FXML
    void armourSellHandler(ActionEvent event) {
        ShopArmour shop = new ShopArmour();
        BaseEquipment r = null;
        for (BaseEquipment e : worldController.getWorld().getUnequippedInventoryItems()) {
            if (e.getType().equals("Armour")) {
                r = e;
            }
        }
        worldController.getWorld().removeUnequippedInventoryItemByCoordinates(r.getX(), r.getY());
        shop.sell();
    }

    public void setResumeSwitcher(MenuSwitcher gameResumeSwitcher) {
        this.gameResumeSwitcher = gameResumeSwitcher;
    }

    @FXML
    void exitShopHandler(ActionEvent event) {
        gameResumeSwitcher.switchMenu();
    }

    @FXML
    void healthPotionBuyHandler(ActionEvent event) {
        String mode = modeController.getGameMode();
        if (mode.equals("Survive")) {
            if (countPotion < 1) {
                ShopPotion shop = new ShopPotion();
                countPotion++;
                shop.buy();
            } else {
                System.out.println("Survive mode means only one potion can be bought!");
            }
        } else {
            ShopPotion shop = new ShopPotion();
            countPotion++;
            shop.buy();
        }

    }

    @FXML
    void helmetBuyHandler(ActionEvent event) {
        String mode = modeController.getGameMode();
        if (mode.equals("Berserker")) {
            if (countProtective < 1) {
                ShopHelmet shop = new ShopHelmet();
                BaseEquipment helmet = worldController.getWorld().addUnequippedEquipment("Helmet");
                worldController.onLoadUnequipped(helmet);
                shop.buy();
                countProtective++;
            } else {
                System.out.println("Berserker mode means only one protective gear is bought!");
            }
        } else {
            ShopHelmet shop = new ShopHelmet();
            BaseEquipment helmet = worldController.getWorld().addUnequippedEquipment("Helmet");
            worldController.onLoadUnequipped(helmet);
            shop.buy();
            countProtective++;
        }
    }

    @FXML
    void helmetSellHandler(ActionEvent event) {
        ShopHelmet shop = new ShopHelmet();
        BaseEquipment r = null;
        for (BaseEquipment e : worldController.getWorld().getUnequippedInventoryItems()) {
            if (e.getType().equals("Helmet")) {
                r = e;
            }
        }
        worldController.getWorld().removeUnequippedInventoryItemByCoordinates(r.getX(), r.getY());
        shop.sell();
    }

    @FXML
    void shieldBuyHandler(ActionEvent event) {
        String mode = modeController.getGameMode();
        if (mode.equals("Berserker")) {
            if (countProtective < 1) {
                ShopShield shop = new ShopShield();
                BaseEquipment shield = worldController.getWorld().addUnequippedEquipment("Shield");
                worldController.onLoadUnequipped(shield);
                shop.buy();
                countProtective++;
            } else {
                System.out.println("Berserker mode means only one protective gear is bought!");
            }
        } else {
            ShopShield shop = new ShopShield();
            BaseEquipment shield = worldController.getWorld().addUnequippedEquipment("Shield");
            worldController.onLoadUnequipped(shield);
            shop.buy();
            countProtective++;
        }

    }

    @FXML
    void shieldSellHandler(ActionEvent event) {
        ShopShield shop = new ShopShield();
        BaseEquipment r = null;
        for (BaseEquipment e : worldController.getWorld().getUnequippedInventoryItems()) {
            if (e.getType().equals("Shield")) {
                r = e;
            }
        }
        worldController.getWorld().removeUnequippedInventoryItemByCoordinates(r.getX(), r.getY());
        shop.sell();
    }

    @FXML
    void staffBuyHandler(ActionEvent event) {
        ShopStaff shop = new ShopStaff();
        BaseEquipment staff = worldController.getWorld().addUnequippedEquipment("Staff");
        worldController.onLoadUnequipped(staff);
        shop.buy();
    }

    @FXML
    void staffSellHandler(ActionEvent event) {
        ShopStaff shop = new ShopStaff();
        BaseEquipment r = null;
        for (BaseEquipment e : worldController.getWorld().getUnequippedInventoryItems()) {
            if (e.getType().equals("Staff")) {
                r = e;
            }
        }
        worldController.getWorld().removeUnequippedInventoryItemByCoordinates(r.getX(), r.getY());
        shop.sell();
    }

    @FXML
    void stakeBuyHandler(ActionEvent event) {
        ShopStake shop = new ShopStake();
        BaseEquipment stake = worldController.getWorld().addUnequippedEquipment("Stake");
        worldController.onLoadUnequipped(stake);
        shop.buy();
    }

    @FXML
    void stakeSellHandler(ActionEvent event) {
        ShopStake shop = new ShopStake();
        BaseEquipment r = null;
        for (BaseEquipment e : worldController.getWorld().getUnequippedInventoryItems()) {
            if (e.getType().equals("Stake")) {
                r = e;
            }
        }
        worldController.getWorld().removeUnequippedInventoryItemByCoordinates(r.getX(), r.getY());
        shop.sell();
    }

    @FXML
    void swordBuyHandler(ActionEvent event) {
        ShopSword shop = new ShopSword();
        BaseEquipment sword = worldController.getWorld().addUnequippedEquipment("Sword");
        worldController.onLoadUnequipped(sword);
        shop.buy();
    }

    @FXML
    void swordSellHandler(ActionEvent event) {
        ShopSword shop = new ShopSword();
        BaseEquipment r = null;
        for (BaseEquipment e : worldController.getWorld().getUnequippedInventoryItems()) {
            if (e.getType().equals("Sword")) {
                r = e;
            }
        }
        worldController.getWorld().removeUnequippedInventoryItemByCoordinates(r.getX(), r.getY());
        shop.sell();
    }
}

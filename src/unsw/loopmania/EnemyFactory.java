package unsw.loopmania;

import java.util.*;

public class EnemyFactory {

    private Map<String, IEnemyHandler> enemyHandlerMap;
    private List<BaseEnemy> enemies;

    public EnemyFactory(Map<String, IEnemyHandler> enemyHandlerMap, unsw.loopmania.Character character, List<BaseEnemy> enemies) {
        this.enemyHandlerMap = enemyHandlerMap;
        this.enemies = enemies;
    }

    public BaseEnemy createEnemy(PathPosition position, String type) {
        BaseEnemy enemy = new BaseEnemy(position, type, enemyHandlerMap.get(type));
        enemy.shouldExist().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                killEnemy(enemy);
            }
        });
        return enemy;
    }

    public BaseEnemy createSlug(PathPosition position) {
        String type = GlobalConfig.EnemyName.Slug;
        return createEnemy(position, type);
    }

    public BaseEnemy createZombie(PathPosition position) {
        String type = GlobalConfig.EnemyName.Zombie;
        return createEnemy(position, type);
    }

    public BaseEnemy createVampire(PathPosition position) {
        String type = GlobalConfig.EnemyName.Vampire;
        return createEnemy(position, type);
    }

    public BaseEnemy createDoggie(PathPosition position) {
        String type = GlobalConfig.EnemyName.Doggie;
        return createEnemy(position, type);
    }

    public BaseEnemy createElanMuske(PathPosition position) {
        String type = GlobalConfig.EnemyName.ElanMuske;
        return createEnemy(position, type);
    }

        /**
     * kill an enemy
     *
     * @param enemy enemy to be killed
     */
    public void killEnemy(BaseEnemy enemy) {
        System.out.printf("%s is defeated\n", enemy.getType());
        enemy.destroy();
        enemies.remove(enemy);
        GlobalConfig.character.acquireExp(enemy.getExp());
        GlobalConfig.character.acquireGold(enemy.getGold());
    }
}

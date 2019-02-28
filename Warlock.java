/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Dossman
 */
public class Warlock extends Decorator implements Magical {

    /**
     * @param e base enemy
     */
    public Warlock(Enemy e) {
        super(e, e.getName() + " Warlock", e.getLevel(), e.getMaxHP());
        this.increaseMaxHP(1);
    }

    /**
     * overrides attacks to add magical attacks
     *
     * @param e entity to be attacked
     */
    @Override
    public void attack(Entity e) {
        for (int y = 0; y < this.getLevel(); y++) {
            int min = 1;
            int max = 3;
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            if (randomNum == 1) {
                Random rand = new Random();
                int low;
                int high;
                low = 2;
                high = 5;
                int attackdamage;
                attackdamage = (rand.nextInt(high - low) + low) * magicMissile();
                enemyOut = "Enemy uses MagicMissile for " + attackdamage + " damage!";
                e.takeDamage(attackdamage);
            } else if (randomNum == 2) {
                Random rand = new Random();
                int low;
                int high;
                low = 2;
                high = 5;
                int attackdamage;
                attackdamage = (rand.nextInt(high - low) + low) * fireball();
                enemyOut = "Enemy uses FireBall for " + attackdamage + " damage!";
                e.takeDamage(attackdamage);
            } else {
                Random rand = new Random();
                int low;
                int high;
                low = 2;
                high = 5;
                int attackdamage;
                attackdamage = (rand.nextInt(high - low) + low) * thunderclap();
                enemyOut = "Enemy uses ThunderClap for  " + attackdamage + " damage!";
                e.takeDamage(attackdamage);
            }
        }
    }

    /**
     *
     * @return damage multiplier
     */
    @Override
    public int magicMissile() {
        return 1;
    }

    /**
     *
     * @return damage multiplier
     */
    @Override
    public int fireball() {
        return 1;
    }

    /**
     *
     * @return damage multiplier
     */
    @Override
    public int thunderclap() {
        return 1;
    }

}

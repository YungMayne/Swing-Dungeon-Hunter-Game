/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * generates enemies
 *
 * @author Dossman
 */
public class EnemyGenerator {

    /**
     * sets instance to null given we are using singleton design pattern
     */
    private static EnemyGenerator instance = null;
    /**
     * Itemgenerator to generate an item for enemy to hold
     */
    ItemGenerator ig;

    /**
     * generates enemy
     *
     *
     */
    private EnemyGenerator() {

    }

    /**
     * returns current instance of object
     *
     * @return
     */
    public static EnemyGenerator getInstance() {
        if (instance == null) {
            instance = new EnemyGenerator();
        }
        return instance;
    }

    /**
     *
     * @param level
     * @return enemy object randomly selected from array list, uses hero's level
     * as a mutator to change health and damage done by the enemy
     */
    Enemy generateEnemy(int level) {
        Enemy newEnemy;
        Enemy finalEnemy;
        Random rand = new Random();
        int high;
        high = 3;
        int randEnemy = rand.nextInt(high);
        if (randEnemy == 0) {
            newEnemy = new Goblin();
        } else if (randEnemy == 1) {
            newEnemy = new Froglok();
        } else if (randEnemy == 2) {
            newEnemy = new Orc();
        } else {
            newEnemy = new Troll();
        }
        if (level > newEnemy.getLevel()) {

            for (int i = 0; i < level - 1; i++) {
                newEnemy.increaseLevel();
            }
        }

        if (newEnemy.getLevel() > 1) {
            Random decoratorRand = new Random();
            int decHigh = 1;
            int randDec = rand.nextInt(decHigh);
            Decorator dec;
            if (randDec == 0) {
                for (int i = 1; i < newEnemy.getLevel(); i++) {
                    newEnemy = new Warlock(newEnemy);

                }
            } else {

                for (int i = 1; i < newEnemy.getLevel(); i++) {
                    newEnemy = new Warrior(newEnemy);
                }
            }
        }
        newEnemy.heal(newEnemy.getMaxHP() * level);
        return newEnemy;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

import java.util.Random;

/**
 *
 * @author Dossman
 */
public class Goblin extends Enemy {

    /**
     *
     * @param n
     * @param l
     * @param m
     *
     *
     *
     */
    public Goblin(String n, int l, int m) {
        super("Goblin", l, 2);
    }

    /**
     * default constructor
     */
    public Goblin() {
        super("Goblin", 1, 2);

    }

    /**
     *
     * @return the item enemy is holding
     */
    Item getItem() {
        return item;
    }

}

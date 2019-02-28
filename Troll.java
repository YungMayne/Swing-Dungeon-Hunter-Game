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
public class Troll extends Enemy {

    /**
     *
     * @param n name
     * @param l level
     * @param m max health
     *
     *
     *
     */
    public Troll(String n, int l, int m) {
        super("Troll", l, 5);
    }

    /**
     *
     */
    public Troll() {
        super("Troll", 1, 5);
    }

    /**
     *
     * @return the item that is getting operated on
     */
    Item getItem() {
        return item;
    }

}

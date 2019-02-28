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
public class Orc extends Enemy {

    /**
     *
     * @param n name
     * @param l level
     * @param m max health
     *
     *
     */
    public Orc(String n, int l, int m) {
        super("Orc", 1, 4);

    }

    /**
     * default constructor
     */
    public Orc() {
        super("Orc", 1, 4);

    }

    /**
     *
     * @return the item that is getting operated on
     */
    Item getItem() {
        return item;
    }

}

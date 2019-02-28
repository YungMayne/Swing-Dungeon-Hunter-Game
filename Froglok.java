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
public class Froglok extends Enemy {

    /**
     *
     * @param n name
     * @param l level
     * @param m max hp
     *
     *
     */
    public Froglok(String n, int l, int m) {
        super("Froglok", l, 2);
    }

    /**
     * default constructor
     */
    public Froglok() {
        super("Froglok", 1, 2);
    }

    /**
     * @return item enemy is holding
     */
    Item getItem() {
        return item;
    }

}

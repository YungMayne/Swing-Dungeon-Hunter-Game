/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

/**
 *
 * @author Dossman
 */
public abstract class Decorator extends Enemy {

    /**
     * enemy to be stored as original enemy
     */
    private Enemy enemy;

    /**
     *
     * @param e enemy to be decorated
     * @param n name
     * @param l level
     * @param m max health
     */
    public Decorator(Enemy e, String n, int l, int m) {
        super(n, l, m);
        enemy = e;
    }

    @Override
    public void attack(Entity e) {
        enemy.attack(e);
    }

}

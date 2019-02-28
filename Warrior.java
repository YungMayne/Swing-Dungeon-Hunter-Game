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
public class Warrior extends Decorator {

    /**
     *
     * @param e original enemy
     */
    public Warrior(Enemy e) {
        super(e, e.getName() + " Warrior", e.getLevel(), e.getMaxHP());
        this.increaseMaxHP(2);

    }

    /**
     *
     * @param e entity to be attacked
     */
    @Override
    public void attack(Entity e) {
        for (int y = 0; y <= this.getLevel(); y++) {
            this.attack(e);
        }
    }

}

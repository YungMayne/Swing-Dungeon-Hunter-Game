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
 *
 * @author Dossman
 */
public abstract class Entity {

    /**
     * entity name
     */
    private String name;
    /**
     * enemy level
     */

    private int level;
    /**
     * enemy maxHP
     */
    private int maxHP;
    /**
     * enemy HP
     */
    private int HP;

    /**
     * @param n name
     * @param l level
     * @param m max HP constructs entity
     *
     */
    public Entity(String n, int l, int m) {
        name = n;
        level = l;
        maxHP = m;
    }

    /**
     *
     * @param e -- an entity creates the abstract attack method that takes in an
     * entity to be attacked, will be overwritten in subclasses
     */
    public abstract void attack(Entity e);

    /**
     *
     * @return name of entity
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return level of entity
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return hp of entity
     */
    public int getHP() {
        return HP;
    }

    /**
     *
     * @return the max possible HP of entity
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * increases entity level by 1
     */
    public void increaseLevel() {
        level = level + 1;
    }

    /**
     * heals the entity amount specified by parameter, or to max health
     *
     * @param h amount of health
     */
    public void heal(int h) {
        if (HP + h >= maxHP) {
            HP = maxHP;
        } else {
            HP = HP + h;
        }
    }

    /**
     * damages the entity amount specified by parameter, or to 0
     *
     * @param h amount of health
     */
    public void takeDamage(int h) {
        if (HP - h > 0) {
            HP = HP - h;
        } else {
            HP = 0;
        }
    }

    /**
     * increases entity maxhp by specified value
     *
     * @param h health
     */
    public void increaseMaxHP(int h) {
        maxHP = maxHP + h;
    }

    /**
     * decreases entity maxhp by specified value
     *
     * @param h health
     */
    public void decreaseMaxHP(int h) {

        maxHP = maxHP - h;
        if (HP > maxHP) {
            HP = maxHP;
        }
    }

    /**
     * displays entitys attributes
     */
    public void display() {
        System.out.println("Name: " + name + "\nLevel: " + level + "\nMax HP: " + maxHP + "\nHP: " + HP);
    }

    /**
     *
     * @return string to be used in GUI
     */
    public String displayString() {
        return ("<html>" + "Name: " + name + "<br>" + "Level: " + level + "<br>" + "Max HP: " + maxHP + "<br>" + " HP: " + HP + "<br>" + "<html>");
    }
}

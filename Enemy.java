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
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Dossman
 */
public abstract class Enemy extends Entity {

    /**
     * item enemy holds
     */
    Item item;
    /**
     * string containing attack info
     *
     */
    String enemyOut;

    /**
     *
     * @param n name
     * @param l
     * @param m maxHp
     *
     */
    public Enemy(String n, int l, int m) {
        super(n, l, m);
        item = ItemGenerator.getInstance().generateItem();
        enemyOut = "";
    }

    /**
     *
     * @returns the item that enemy contains
     */
    Item getItem() {
        return item;
    }

    /**
     * @return string to be displayed in GUI
     */
    public String displayString() {
        return (super.displayString() + enemyOut);
    }

    /**
     * writes attack info to enemyout
     *
     * @param e entity to be attacked
     */
    @Override
    public void attack(Entity e) {
        Random rand = new Random();
        int low;
        int high;
        low = 2 * e.getLevel();
        high = 4 * e.getLevel();
        int attackdamage;
        attackdamage = rand.nextInt(high - low) + low;
        enemyOut = "Enemy attacks for " + attackdamage + "damage!";
        e.takeDamage(attackdamage);
    }

    /**
     *
     * @return enemies name and checks to make sure decorator doesnt add
     * repeated words
     */
    public String getName() {
        String name = "";
        if (super.getName().contains("Troll Warlock")) {
            return "Troll Warlock";
        } else if (super.getName().contains("Troll Warrior")) {
            return "Troll Warrior";
        } else if (super.getName().contains("Orc Warrior")) {
            return "Orc Warrior";
        } else if (super.getName().contains("Orc Warlock")) {
            return "Orc Warlock";
        } else if (super.getName().contains("Goblin Warrior")) {
            return "Goblin Warrior";
        } else if (super.getName().contains("Goblin Warlock")) {
            return "Goblin Warlock";
        } else if (super.getName().contains("Froglok Warrior")) {
            return "Froglok Warrior";
        } else if (super.getName().contains("Froglok Warlock")) {
            return "Froglok Warlock";
        } else {
            return super.getName();
        }

    }

    /**
     *
     * @return image for enemy types
     */
    public Image getImage() {
        Image icon = null;
        String iconName = "";

        if (getName().contains("Troll")) {
            iconName = ".//build/trollface.jpg";
        } else if (getName().contains("Orc")) {
            iconName = ".//build/orc.jpg";
        } else if (getName().contains("Froglok")) {
            iconName = ".//build/datboi.jpg";
        } else if (getName().contains("Goblin")) {
            iconName = ".//build/goblin.jpg";
        }

        try {
            icon = ImageIO.read(new File(iconName));
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }

        return icon;
    }
}

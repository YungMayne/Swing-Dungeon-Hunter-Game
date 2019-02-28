/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Dossman
 */
public class Hero extends Entity implements Magical {

    private ArrayList<Item> items = new ArrayList<Item>();
    private Map map;
    private Point location;
    private int gold = 25;
    private String heroOut;
    private String fightOut;

    /**
     * starts at level 1 with 15 hp
     *
     * @param n name
     * @param m maxhp
     */
    public Hero(String n, Map m) {
        super(n, 1, 15);
        map = m;
        location = map.findStart();
        heroOut = "";
        fightOut = "";
    }

    /**
     * uses entity display method
     */
    public void display() {
        super.display();
    }

    /**
     *
     * @return
     */
    public String displayString() {
        return (super.displayString() + "$: " + gold + "<html>" + "<br>" + fightOut + "<br>" + heroOut);
    }

    /**
     * draws item boxes and items
     *
     * @param g graphics
     */
    public void draw(Graphics g) {

        int sideLength = 52;
        int distance = sideLength * 3;
        int startingX = 595;
        int startingY = 200;
        int i = 0;
        int j = 0;
        for (int x = startingX; x < distance + startingX; x += sideLength) {

            for (int y = startingY; y <= distance + startingY - sideLength - sideLength; y += sideLength) {
                if (x >= startingX + sideLength + sideLength && y >= startingY + sideLength) {
                } else {
                    g.setColor(Color.WHITE);
                    g.drawRect(x, y, sideLength, sideLength);

                    if (j < items.size()) {

                        Image useThis = items.get(j).getImage();
                        useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                        g.drawImage(useThis, x, y, sideLength, sideLength, null);

                        j++;
                    }
                }
            }
        }
    }

    /**
     * displays the items in the heros inventory
     */
    public void displayItems() {

        for (int i = 0; i < items.size(); i++) {
            System.out.println("|" + items.get(i).getName() + "; Value = " + items.get(i).getValue() + "|");
        }
        System.out.println("\n");
    }

    /**
     * returns amount of items in heros inventory
     *
     * @return
     */
    int getNumItems() {
        return items.size();
    }

    /**
     * returns true if there is room in the heros inventory for whatever ITEM
     * that is getting passed in, adds to MaxHP or gold depending on what item
     * it is
     *
     * @param i item
     * @return
     */
    boolean pickUpItem(Item i) {
        if (i.getName().equals("Bag o' Gold")) {
            collectGold(25);
            heroOut = "Struck Gold!";
            return true;
        }

        if (items.size() < 5) {
            Item testitem = i;
            if (testitem.getName().equals("Health Potion")) {
                items.add(i);
                heroOut = "Magic Potion";
            } else {
                items.add(i);
                increaseMaxHP(i.getValue());
                heroOut = "Grabbed " + i.getName();
            }
            return true;
        } else {
            heroOut = "<html>you do not have room for <br> " + i.getName() + " in your inventory!<html>";
            return false;
        }
    }

    /**
     *
     * @param n item name
     * @returns item that is removed
     */
    Item removeItem(String n) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(n)) {
                return items.remove(i);
            }
        }
        return null;
    }

    /**
     * removes item from index
     *
     * @param indx index of item to remove
     */
    Item removeItem(int index) {
        Item testitem = items.get(index);
        if (testitem.getName().equals("Health Potion")) {
            return items.remove(index);
        } else {
            decreaseMaxHP(items.get(index).getValue());
            return items.remove(index);
        }
    }

    /**
     * checks if the hero is holding a potion
     *
     * @returns true if the character has a health potion
     */
    boolean hasPotion() {
        for (int i = 0; i < items.size(); i++) {
            String test = items.get(i).getName();
            if (test.equals("Health Potion")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return heros location as a point
     */
    Point getLocation() {
        return location.getLocation();
    }

    /**
     * moves the character and checks for out of bounds, if player tries to move
     * out of bounds they stay in the same place
     *
     * @return returns characters location once he has moved
     */
    char goWest() {
        int xTemp = (int) location.getX();
        int yTemp = (int) location.getY();
        int newPos = yTemp - 1;
        if (newPos > 4 || newPos < 0) {
            heroOut = "Edge of map!";
            return 'p';
        } else {
            location.setLocation(xTemp, newPos);
            Map.getInstance().reveal(location);
            heroOut = "";
            fightOut = "";
            return map.getCharAtLoc(location);
        }
    }

    /**
     * moves the character and checks for out of bounds, if player tries to move
     * out of bounds they stay in the same place
     *
     * @return returns characters location once he has moved
     */
    char goEast() {
        int xTemp = (int) location.getX();
        int yTemp = (int) location.getY();
        int newPos = yTemp + 1;
        if (newPos > 4 || newPos < 0) {
            System.out.print("Edge of map!");
            return 'p';
        } else {
            location.setLocation(xTemp, newPos);
            Map.getInstance().reveal(location);
            heroOut = "";
            fightOut = "";
            return map.getCharAtLoc(location);
        }
    }

    /**
     * moves the character and checks for out of bounds, if player tries to move
     * out of bounds they stay in the same place
     *
     * @return returns characters location once he has moved
     */
    char goSouth() {
        int xTemp = (int) location.getX();
        int yTemp = (int) location.getY();
        int newPos = xTemp + 1;
        if (newPos > 4 || newPos < 0) {
            System.out.print("Edge of map!");
            return 'p';
        } else {
            location.setLocation(newPos, yTemp);
            Map.getInstance().reveal(location);
            heroOut = "";
            fightOut = "";
            return map.getCharAtLoc(location);
        }

    }

    /**
     *
     * @return image of hero
     */
    public Image getHeroImage() {
        Image icon = null;
        try {
            icon = ImageIO.read(new File("/Users/Dossman/NetBeansProjects/CECS_277_Project 4/build/link.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return icon;
    }

    /**
     * moves the character and checks for out of bounds, if player tries to move
     * out of bounds they stay in the same place
     *
     * @return returns characters location once he has moved
     */
    char goNorth() {
        int xTemp = (int) location.getX();
        int yTemp = (int) location.getY();
        int newPos = xTemp - 1;
        if (newPos > 4 || newPos < 0) {
            System.out.print("Edge of map!");
            return 'p';
        } else {
            location.setLocation(newPos, yTemp);
            Map.getInstance().reveal(location);
            heroOut = "";
            fightOut = "";
            return map.getCharAtLoc(location);
        }
    }

    /**
     * returns the amount of gold the hero has
     *
     * @returns amount of gold
     */
    int getGold() {
        return gold;
    }

    /**
     * adds gold to heros wallet
     *
     * @param g amount of gold
     */
    void collectGold(int g) {
        gold = gold + g;
    }

    /**
     * removes gold from heros wallet
     *
     * @param g amount of gold
     */
    void spendGold(int g) {
        gold = gold - g;
    }

    /**
     * overrides attack function from entity class, uses magic interface to
     * allow character to pick magic attacks (magic attacks apply a multiplier
     * to randomly generated damage), all attacks generate a random number of
     * damage
     *
     * @param e takes in an entity
     */
    @Override
    public void attack(Entity e) {
        Random rand = new Random();
        int low;
        int high;
        low = e.getLevel() * 2;
        high = e.getLevel() * 5;
        int attackdamage;
        attackdamage = rand.nextInt(high - low) + low;
        fightOut = "<html>  hit " + e.getName() + "<br>for" + attackdamage + " damage!<html>";
        e.takeDamage(attackdamage);

    }

    /**
     * all of these act as multipliers
     *
     * @return
     * @returns damage
     */
    @Override
    public int magicMissile() {
        Random rand = new Random();
        int low;
        int high;
        low = getLevel() * 2;
        high = 1000;
        int attackDamage;
        attackDamage = rand.nextInt(high - low) + low;
        attackDamage = attackDamage * 2;
        fightOut = "<html>Used MagicMissile<br> for" + attackDamage + " damage!<html>";
        return attackDamage;

    }

    /**
     * fireball attack
     *
     * @return damage
     */
    @Override
    public int fireball() {
        Random rand = new Random();
        int low;
        int high;
        low = getLevel() * 2;
        high = getLevel() * 5;
        int attackdamage;
        attackdamage = rand.nextInt(high - low) + low;
        attackdamage = attackdamage;
        fightOut = "<html>Used fireball<br> for" + attackdamage + " damage!<html>";
        return attackdamage;

    }

    /**
     * thunderclap attack
     *
     * @return damage
     */
    @Override
    public int thunderclap() {
        Random rand = new Random();
        int low;
        int high;
        low = getLevel() * 2;
        high = getLevel() * 5;
        int attackDamage;
        attackDamage = rand.nextInt(high - low) + low;
        attackDamage = attackDamage * (3 / 2);
        fightOut = "<html>Used thunderclap<br> for" + attackDamage + " damage!<html>";

        return attackDamage;
    }

}

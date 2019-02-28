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
import javax.swing.ImageIcon;

/**
 *
 * @author Dossman
 */
public class Item implements Prototype {

    /**
     * item name
     */
    private String name;
    /**
     * item value
     */
    private int value;

    /**
     * constructs the item object
     *
     * @param n name
     * @param v value
     */
    public Item(String n, int v) {
        name = n;
        value = v;
    }

    /**
     *
     * @param a item
     */
    public Item(Item a) {
        if (a != null) {
            name = a.name;
            value = a.value;
        }
    }

    /**
     * returns item string name
     *
     * @return name
     */
    String getName() {
        return name;
    }

    /**
     * returns gold value of the item
     *
     * @return value
     */
    int getValue() {
        return value;
    }

    /**
     * uses clone
     *
     * @return clone of item
     */
    @Override
    public Item clone() {
        return new Item(this);
    }

    /**
     *
     * @return image of item
     */
    public Image getImage() {
        Image icon = null;
        String iconName = "";

        if (getName().equals("Health Potion")) {
            iconName = ".//build/potion.jpg";
        } else if (getName().equals("Shield")) {
            iconName = ".//build/shield.jpg";
        } else if (getName().equals("Helm")) {
            iconName = ".//build/helm.jpg";
        } else if (getName().equals("Belt")) {
            iconName = ".//build/belt.jpg";
        } else if (getName().equals("Ring")) {
            iconName = ".//build/ring.jpg";
        } else if (getName().equals("Gloves")) {
            iconName = ".//build/gloves.jpg";
        } else if (getName().equals("Boots")) {
            iconName = ".//build/boots.jpg";
        } else if (getName().equals("Ringmail")) {
            iconName = ".//build/chainmail.jpg";
        }

        try {
            icon = ImageIO.read(new File(iconName));
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }

        return icon;
    }

}

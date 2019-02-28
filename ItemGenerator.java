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
public class ItemGenerator {

    /**
     * arraylist of items
     */
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private static ItemGenerator instance = null;

    /**
     * Reads the items in from the text file
     */
    private ItemGenerator() {

        try {
            Scanner Scan = new Scanner(new File("ItemList.txt"));
            String enemyListLine;
            do {
                enemyListLine = Scan.nextLine();
                String[] contents = enemyListLine.split(",");
                itemList.add(new Item(contents[0], Integer.parseInt(contents[1])));
            } while (Scan.hasNext());
            Scan.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("ItemList file not found!");
        }

    }

    /**
     *
     * @return instance of itemGenerator
     */
    public static ItemGenerator getInstance() {
        if (instance == null) {
            instance = new ItemGenerator();
        }
        return instance;
    }

    /**
     * constructor for the item object, grabs a random item from item array list
     *
     * @returns item that was generated
     */
    public Item generateItem() {
        Item newitem;
        Random rand = new Random();
        int high;
        high = itemList.size() - 1;
        int randIndex = rand.nextInt(high);
        Item yeet = itemList.get(randIndex).clone();
        return yeet;
    }

    /**
     *
     *
     * @returns a potion
     */
    public Item getPotion() {
        return itemList.get(0);
    }
}

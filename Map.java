/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * Map for CECS 277 Lab 1
 *
 * @author Dossman
 */
public class Map extends Point {

    /**
     * constructs map array of rooms
     *
     */
    private char[][] map;
    /**
     * instance of map
     *
     */
    private static Map instance;
    /**
     * provides boolean values for which locations are revealed
     */
    private boolean[][] revealed;

    /**
     * constructs map
     *
     */
    private Map() {

        instance = null;
        revealed = new boolean[5][5];
    }

    /**
     *
     * @return instance of map
     */
    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    /**
     * draws map
     *
     * @param g graphics
     * @param h hero
     */
    public void draw(Graphics g, Hero h) {
        reveal(findStart());

        int sideLength = 105;
        int distance = sideLength * 5;
        int i = 0;
        int j = 0;

        for (int x = 20; x <= distance; x += sideLength) {

            for (int y = 20; y <= distance; y += sideLength) {
                g.setColor(Color.WHITE);
                g.drawRect(x, y, sideLength, sideLength);

                if ((j == h.getLocation().getX()) && (i == h.getLocation().getY())) {
                    Image useThis = h.getHeroImage();
                    useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                    g.drawImage(useThis, x, y, sideLength, sideLength, null);

                } else if (revealed[j][i] == true) {
                    g.drawString(Character.toString(map[j][i]), x + sideLength / 2, y + sideLength / 2);
                }
                j++;
                if (j > 4) {
                    j = 0;
                }
            }
            i++;
        }

    }

    /**
     *
     * @param mapNumber which map will be generated
     */
    public void loadMap(int mapNumber) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                revealed[i][j] = false;
            }
        }
        try {
            Scanner scan;
            scan = new Scanner(new File("Map" + mapNumber + ".txt"));
            int x = 0;
            String line;
            ArrayList<String> maplines = new ArrayList<String>();
            do {
                line = scan.nextLine();
                String contents[] = line.split(" ");
                for (int a = 0; a < contents.length; a++) {
                    x++;
                }
                maplines.add(line);
            } while (scan.hasNextLine());
            scan.close();
            /**
             * Since map is a square, we can use the information we already
             * gathered about the length of x
             */
            int y = x;
            map = new char[x][y];
            String contents[] = new String[x];

            for (int i = 0; i < 5; i++) {
                contents = maplines.get(i).split(" ");
                for (int j = 0; j < 5; j++) {
                    map[i][j] = contents[j].charAt(0);

                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Map not found!");

        }

    }

    /**
     *
     * @param p point
     * @returns character at location
     */
    char getCharAtLoc(Point p) {
        return map[(int) p.getX()][(int) p.getY()];
    }

    /**
     * displays the map
     *
     * @param p point (heros location)
     */
    public void displayMap(Point p) {

        reveal(findStart());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (p.x == i && p.y == j) {
                    System.out.print("*");
                } else if (revealed[i][j] == false) {
                    System.out.print("x");
                } else {
                    Point temp = new Point(i, j);
                    System.out.print(getCharAtLoc(temp));

                }
            }
            System.out.println();
        }

    }

    /**
     *
     * @return starting point on map
     */
    public Point findStart() {
        Point StartingPoint = new Point();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 's') {
                    StartingPoint.setLocation(i, j);
                    break;
                }
            }
        }
        return StartingPoint;
    }

    /**
     *
     * @param p point to be revealed
     */
    public void reveal(Point p) {
        revealed[(int) p.getX()][(int) p.getY()] = true;

    }

    /**
     * returns an empty room
     *
     * @param p point
     *
     */
    public void removeCharAtLoc(Point p) {
        map[(int) p.getX()][(int) p.getY()] = 'n';
    }
}

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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Window class to create window
 *
 * @author Dossman
 */
public class Window extends JFrame {

    /**
     * Main Calls window to show GUI
     *
     * @param args
     */
    public static void main(String[] args) {
        Window w = new Window();

    }

    /**
     *
     */
    @SuppressWarnings("empty-statement")
    /**
     * default constructor for Window sets bounds,Title,CloseOperation, and uses
     * the Panel class to operate
     */
    public Window() {

        setBounds(200, 200, 795, 590);
        setTitle("DO THESE THINGS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel b = new Panel();
        setContentPane(b);

        Thread a = new Thread(b);
        setVisible(true);
        a.start();

    }

    /**
     * creates the panel to interact with in the window
     */
    public class Panel extends JPanel implements ActionListener, KeyListener, Runnable, MouseListener {

        /**
         * item box with items in index 1
         */
        private Rectangle itemBox1;
        /**
         * item box with items in index 3
         */
        private Rectangle itemBox2;
        /**
         * item box with items in index 5
         */
        private Rectangle itemBox3;
        /**
         * item box with items in index 2
         */
        private Rectangle itemBox4;
        /**
         * item box with items in index 4
         */
        private Rectangle itemBox5;
        /**
         * tells player they died at end
         */
        JLabel youDiedFoo;
        /**
         * item in room
         */
        private Item itemRoomItem;
        /**
         * enemy in room
         */
        private Enemy enemy;
        /**
         * number of map to be generated
         */
        int mapNum;
        /**
         * level of hero
         */
        int heroLevel;
        /**
         * state gui is in
         */
        private int state;
        /**
         * inner state to dictate what item is sold at shop
         */
        private int storeInnerState;
        /**
         * option pane that asks player for name
         */
        private JOptionPane getHeroName;
        /**
         * item enemy is holding
         */
        private Item enemyItem;
        /**
         * top button
         */
        private JButton Button1;
        /**
         * middle button
         */
        private JButton Button2;
        /**
         * bottom button
         */

        private JButton Button3;
        /**
         * name of room
         */
        private JLabel roomName;
        /**
         * status box for room info
         */
        private JLabel roomStatus;
        /**
         * info box for hero
         */
        private JLabel heroStats;
        /**
         * hero
         */
        private Hero hero;

        /**
         * paints gui
         *
         * @param g graphics
         */
        @Override
        public void paintComponent(Graphics g) {
            heroStats.setText(hero.displayString());
            setBackground(Color.BLACK);
            Map.getInstance().draw(g, hero);
            g.drawRect(565, 20, 210, 300);
            g.drawRect(565, 20, 210, 40);
            g.drawRect(565, 330, 210, 215);
            g.drawRect(565, 330, 210, 40);
            g.drawString("Hero Stats", 630, 32);
            hero.draw(g);

            //default
            if (state > 30 && state < 40) {
                roomStatus.setText(enemy.displayString());
            }
            if (state == 0) {
                roomName.setText("Wilderness");
                roomStatus.setText("");
                Button1.setVisible(false);
                Button2.setVisible(false);
                Button3.setVisible(false);
                //monster room
            } else if (state == 1) {
                Image useThis = enemy.getImage();
                useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                g.drawImage(useThis, 20, 20, 525, 525, null);
                roomName.setText("Monster!");
                Button1.setText("fight!");
                Button2.setText("RUN!!");
                Button1.setVisible(true);
                Button2.setVisible(true);
                Button3.setVisible(false);
                //state for fight which checks if player has potion

                //fight 
            } else if (state == 3) {
                Image useThis = enemy.getImage();
                useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                g.drawImage(useThis, 20, 20, 525, 525, null);
                if (hero.hasPotion()) {
                    state = 33;
                } else {
                    state = 31;
                }

            }//fight with potion 
            else if (state == 33) {
                Image useThis = enemy.getImage();
                useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                g.drawImage(useThis, 20, 20, 525, 525, null);
                Button1.setText("Attack");
                Button2.setText("Magic attack");
                Button3.setText("Use Health potion");
                Button1.setVisible(true);
                Button2.setVisible(true);
                Button3.setVisible(true);

                //fight no potion
            } else if (state == 31) {
                Image useThis = enemy.getImage();
                useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                g.drawImage(useThis, 20, 20, 525, 525, null);
                Button1.setText("Attack");
                Button2.setText("Magic attack");
                Button1.setVisible(true);
                Button2.setVisible(true);
                Button3.setVisible(false);
                //sets state when running
            } else if (state == 32) {
                Button1.setVisible(false);
                Button2.setVisible(false);
                Button3.setVisible(false);

            }//magicattack buttons
            else if (state == 34) {
                Image useThis = enemy.getImage();
                useThis.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
                g.drawImage(useThis, 20, 20, 525, 525, null);
                Button1.setText("MagicMissile");
                Button2.setText("Firebolt");
                Button3.setText("Thunderclap");
                Button1.setVisible(true);
                Button2.setVisible(true);
                Button3.setVisible(true);
            } //store
            else if (state == 6) {
                roomName.setText("Store");
                Button1.setText("Buy Potion for 25 Gold");
                Button2.setText("Leave shop");
                Button3.setText("Sell items");
                Button1.setVisible(true);
                Button2.setVisible(true);
                Button3.setVisible(true);
            }//selling items
            else if (state == 62) {

                Button1.setText("Menu");
                Button2.setText("leave shop");
                Button2.setVisible(true);
                Button3.setVisible(false);
            }//item room
            else if (state == 7) {

                Button1.setText("Yes");
                Button2.setText("No");
                Button1.setVisible(true);
                Button2.setVisible(true);
                Button3.setVisible(false);
            } //hero dies
            else if (state == 100) {
                heroStats.setText(hero.displayString());
                youDiedFoo.setVisible(true);

            }

        }

        /**
         *
         */
        public Panel() {
            addKeyListener(this);
            addMouseListener(this);
            setFocusable(true);
            state = 0;
            itemBox1 = new Rectangle(595, 200, 52, 52);
            itemBox2 = new Rectangle(647, 200, 52, 52);
            itemBox3 = new Rectangle(699, 200, 52, 52);
            itemBox4 = new Rectangle(595, 252, 52, 52);
            itemBox5 = new Rectangle(647, 252, 52, 52);

            Button1 = new JButton();
            Button2 = new JButton();
            Button3 = new JButton();
            roomName = new JLabel();
            heroStats = new JLabel();
            roomStatus = new JLabel();

            getHeroName = new JOptionPane();

            Button1.addActionListener(this);
            Button2.addActionListener(this);
            Button3.addActionListener(this);
            setLayout(null);
            youDiedFoo = new JLabel();
            youDiedFoo.setText("YOU DEAAAD!!!!!");
            youDiedFoo.setBounds(100, 100, 795, 590);
            youDiedFoo.setFont(new Font("Monospaced Bold Italic", Font.PLAIN, 100));
            youDiedFoo.setForeground(Color.RED);
            add(youDiedFoo);
            youDiedFoo.setVisible(false);
            Button2.setBounds(590, 495, 160, 25);
            Button1.setBounds(590, 470, 160, 25);
            Button3.setBounds(590, 520, 160, 25);

            Button2.setVisible(true);

            roomName.setBounds(620, 330, 200, 40);
            roomName.setText("testing");
            roomStatus.setBounds(580, 335, 200, 180);
            heroStats.setBounds(580, 46, 200, 180);

            String heroName = getHeroName.showInputDialog("Tell me your name, traveler:");

            add(roomStatus);
            roomStatus.setForeground(Color.WHITE);
            add(roomName);
            roomName.setForeground(Color.WHITE);
            add(heroStats);
            heroStats.setForeground(Color.WHITE);
            add(getHeroName);
            add(Button2);
            add(Button1);
            add(Button3);
            heroLevel = 1;
            mapNum = 1;

            Map.getInstance().loadMap(mapNum);
            hero = new Hero(heroName, Map.getInstance());

            for (int j = 1; j < heroLevel; j++) {
                hero.increaseLevel();
                hero.increaseMaxHP(10);
            }
            hero.heal(hero.getMaxHP());

        }

        /**
         * creates monster and sees if hero wnats to fight or not
         *
         * @param h hero
         *
         */
        public void monsterRoom(Hero h) {
            if (state == 1) {
                enemy = EnemyGenerator.getInstance().generateEnemy(h.getLevel());
                enemyItem = enemy.getItem();
                roomStatus.setText("A wild " + enemy.getName() + " appeared!");
                enemy.attack(h);
                if (h.getHP() < 1) {
                    state = 100;
                } else {

                    roomStatus.setText(enemy.displayString());
                    h.display();

                    roomStatus.setText("Whats your move adventurer?");
                }
            } else if (state == 31) {
                fight(h);

            } else if (state == 32) {
                state = 0;
                System.out.println("You dash in a random direction!");
                int min = 1;
                int max = 4;
                int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                if (randomNum == 1) {
                    h.goNorth();
                } else if (randomNum == 2) {
                    h.goSouth();
                } else if (randomNum == 3) {
                    h.goEast();
                } else {
                    h.goWest();
                }
                Map.getInstance().displayMap(h.getLocation());

            }

        }

        /**
         * checks for what is happening in fight and updates GUI accordingly
         *
         * @param h hero
         */
        public void fight(Hero h) {
            if (state == 35 || state == 391) {
                if (state == 35) {
                    String itemtoremove = "Health Potion";
                    h.heal(25);
                    h.removeItem(itemtoremove);
                    h.displayItems();
                    h.display();
                    state = 3;

                } else if (state == 391) {

                    roomStatus.setText("<html>" + h.getName() + "<br>" + "monster dropped: " + enemyItem.getName());
                    Map.getInstance().removeCharAtLoc(h.getLocation());
                    h.pickUpItem(enemyItem);
                    state = 0;
                }
            } else if (state != 34) {
                if (state == 36) {
                    h.attack(enemy);

                } else if (state == 37) {
                    enemy.takeDamage(h.magicMissile());
                } else if (state == 38) {
                    enemy.takeDamage(h.fireball());

                } else if (state == 39) {
                    enemy.takeDamage(h.thunderclap());

                }
                if (enemy.getHP() < 1) {

                    roomStatus.setText("<html>" + h.getName() + "wins!<br>monster dropped: " + enemyItem.getName());
                    Map.getInstance().removeCharAtLoc(h.getLocation());
                    h.pickUpItem(enemyItem);
                    state = 0;
                } else {
                    enemy.attack(h);
                    if (h.getHP() < 1) {
                        state = 100;
                    } else {
                        state = 3;
                    }
                }
            }

        }

        /**
         * creates the store room
         *
         * @param h hero
         */
        public void store(Hero h) {
            Item potion = ItemGenerator.getInstance().getPotion();
            roomStatus.setText("<html>" + "Welcome to the store!");

            if (state == 61) {
                if (h.getGold() >= 25) {
                    boolean didget = h.pickUpItem(potion);
                    if (didget == true) {
                        h.spendGold(25);
                        state = 0;
                    }
                } else {
                    roomStatus.setText("Not enough funds!");
                    state = 0;
                }

            } else if (state == 62) {
                if (h.getNumItems() == 0) {
                    roomStatus.setText("inventory empty!");
                    state = 0;

                } else {
                    roomStatus.setText("<html>Enter number of item<br> + or click on item in box<html>");
                    int index;

                    if (storeInnerState == 1) {
                        index = 0;
                        if (index <= h.getNumItems() - 1) {
                            h.collectGold(h.removeItem(index).getValue());
                        }
                        storeInnerState = 0;
                    } else if (storeInnerState == 2) {
                        index = 1;
                        if (index <= h.getNumItems() - 1) {
                            h.collectGold(h.removeItem(index).getValue());
                        }
                        storeInnerState = 0;

                    } else if (storeInnerState == 3) {
                        index = 2;
                        if (index <= h.getNumItems() - 1) {

                            h.collectGold(h.removeItem(index).getValue());
                        }
                        storeInnerState = 0;
                    } else if (storeInnerState == 4) {
                        index = 3;
                        if (index <= h.getNumItems() - 1) {

                            h.collectGold(h.removeItem(index).getValue());
                        }
                        storeInnerState = 0;
                    } else if (storeInnerState == 5) {
                        index = 4;
                        if (index <= h.getNumItems() - 1) {

                            h.collectGold(h.removeItem(index).getValue());
                        }
                        storeInnerState = 0;

                    }
                }
            } else if (state == 63) {
                state = 0;
            }

        }

        /**
         * creates the item room for the character to enter and decide if he
         * would like to pick up the item or not, eidts GUI accordingly
         *
         * @param h hero
         */
        public void itemRoom(Hero h) {

            if (state == 7) {
                itemRoomItem = ItemGenerator.getInstance().generateItem();
                roomStatus.setText("<html>Found a " + itemRoomItem.getName());
            } else if (state == 71) {
                boolean didpickup = h.pickUpItem(itemRoomItem);
                if (didpickup == true) {
                    Map.getInstance().removeCharAtLoc(h.getLocation());
                    state = 0;
                } else {
                    roomStatus.setText("Inventory full!");
                    state = 0;
                }
            } else if (state == 72) {
                state = 0;
            }

        }

        /**
         * checks what action was performed
         *
         * @param e action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (state == 1) {
                if (e.getSource() == Button1) {
                    state = 3;
                    monsterRoom(hero);
                } else if (e.getSource() == Button2) {
                    state = 32;
                    monsterRoom(hero);
                }

            } else if (state == 31) {
                if (e.getSource() == Button1) {
                    state = 36;
                    fight(hero);
                } else if (e.getSource() == Button2) {
                    state = 34;
                    fight(hero);
                }
            } else if (state == 33) {
                if (e.getSource() == Button1) {
                    state = 36;
                    fight(hero);
                } else if (e.getSource() == Button2) {
                    state = 34;
                    fight(hero);
                } else if (e.getSource() == Button3) {
                    state = 35;
                    fight(hero);
                }
            } else if (state == 34) {
                if (e.getSource() == Button1) {
                    state = 37;
                    fight(hero);
                } else if (e.getSource() == Button2) {
                    state = 38;
                    fight(hero);
                } else if (e.getSource() == Button3) {
                    state = 39;
                    fight(hero);
                }
            } else if (state == 6) {
                if (e.getSource() == Button1) {
                    state = 61;
                    store(hero);
                } else if (e.getSource() == Button2) {
                    state = 63;
                    store(hero);
                } else if (e.getSource() == Button3) {
                    state = 62;
                    store(hero);

                }
            } else if (state == 7) {
                if (e.getSource() == Button1) {
                    state = 71;
                    itemRoom(hero);
                } else if (e.getSource() == Button2) {
                    state = 72;
                    itemRoom(hero);
                }

            } else if (state == 62) {
                if (e.getSource() == Button2) {
                    state = 0;
                    itemRoom(hero);
                } else if (e.getSource() == Button1) {
                    state = 6;
                    itemRoom(hero);
                }

            }

        }

        /**
         * runs GUI thread
         */
        @Override
        public void run() {

            while (true) {
                repaint();
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                }

            }

        }

        /**
         * mandatory override
         *
         * @param e action event
         */
        @Override
        public void keyTyped(KeyEvent e
        ) {

        }

        /**
         * checks for what key was pressed
         *
         * @param e key event
         */
        @Override
        public void keyPressed(KeyEvent e
        ) {
            if (state == 0 || state == 4000) {
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                    hero.goNorth();

                } else if (state == 0 && (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)) {
                    hero.goWest();

                } else if (state == 0 && (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)) {
                    hero.goSouth();

                } else if (state == 0 && (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                    hero.goEast();

                }
                if (Map.getInstance().getCharAtLoc(hero.getLocation()) == 'm') {
                    state = 1;
                    Map.getInstance().displayMap(hero.getLocation());
                    roomName.setText("M0N$T3R!");
                    monsterRoom(hero);

                } /**
                 * checks if hero enters the store, runs the store method
                 */
                else if (Map.getInstance().getCharAtLoc(hero.getLocation()) == 's') {
                    state = 6;
                    store(hero);
                } /**
                 * checks if an item room, runs item
                 */
                else if (Map.getInstance().getCharAtLoc(hero.getLocation()) == 'i') {
                    state = 7;
                    itemRoom(hero);
                } /**
                 * checks if youve reached, the finish/makes sure map wont go
                 * out of bounds, and breks loop to restart with new level and
                 * such
                 */
                else if (Map.getInstance().getCharAtLoc(hero.getLocation()) == 'f') {

                    mapNum++;
                    heroLevel++;
                    if (mapNum == 4) {
                        mapNum = mapNum - 3;
                    }
                    Map.getInstance().loadMap(mapNum);

                    for (int j = 1; j < heroLevel; j++) {
                        hero.increaseLevel();
                        hero.increaseMaxHP(10);
                    }
                    hero.heal(hero.getMaxHP());
                    roomStatus.setText("<html>" + "You've reached the finish!" + "<br>" + "leveling up " + "<br>" + "loading next map...");
                    state = 4000;
                }

            } else if (state == 62) {
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    storeInnerState = 1;
                    store(hero);
                } else if (e.getKeyCode() == KeyEvent.VK_2) {
                    storeInnerState = 2;
                    store(hero);
                } else if (e.getKeyCode() == KeyEvent.VK_3) {
                    storeInnerState = 3;
                    store(hero);
                } else if (e.getKeyCode() == KeyEvent.VK_4) {
                    storeInnerState = 4;
                    store(hero);
                } else if (e.getKeyCode() == KeyEvent.VK_5) {
                    storeInnerState = 5;
                    store(hero);
                }
            }
        }

        /**
         * mandatory overide
         *
         * @param e key event
         */
        @Override
        public void keyReleased(KeyEvent e
        ) {

        }

        /**
         * checks for mouse events
         *
         * @param e mouse event
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            Point click = new Point(e.getX(), e.getY());
            if (state == 62) {
                if (itemBox1.contains(click)) {
                    storeInnerState = 1;
                    store(hero);
                } else if (itemBox2.contains(click)) {
                    storeInnerState = 3;
                    store(hero);
                } else if (itemBox3.contains(click)) {
                    storeInnerState = 5;
                    store(hero);
                } else if (itemBox4.contains(click)) {
                    storeInnerState = 2;
                    store(hero);
                } else if (itemBox5.contains(click)) {
                    storeInnerState = 4;
                    store(hero);
                }
            }
        }

        /**
         * mandatory override
         *
         * @param e mouse event
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * mandatory override
         *
         * @param e mouse event
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * mandatory override
         *
         * @param e mouse event
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * mandatory override
         *
         * @param e mouse event
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }

    }
}

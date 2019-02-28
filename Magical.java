/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg277.project.pkg1;

/**
 * Constructs the magical interface to be called by hero and enemy
 *
 * @author Dossman
 */
public interface Magical {

    /**
     * constructs magic info
     */
    public static final String Magic_Menu = (" 1. Magic Missle\n2. Fireball \n3. Thunderclap");

    /**
     * constructs magic attack
     *
     * @return damage
     */
    public int magicMissile();

    /**
     * constructs magic attack
     *
     * @return damage
     */
    public int fireball();

    /**
     * constructs magic attack
     *
     * @return damage
     */
    public int thunderclap();
}

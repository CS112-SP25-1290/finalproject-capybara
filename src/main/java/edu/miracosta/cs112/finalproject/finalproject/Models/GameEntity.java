/**
 * The GameEntity class serves as an abstract base class for all entities within the game.
 * It provides common attributes such as position coordinates and enforces essential behaviors
 * like updating the entity state and rendering it on the screen.
 * Furthermore:
 * All game objects, such as the PlayerShip and Alien, inherit from this class
 * and must implement its abstract methods.
 *
 *
 * @author Sarah Caudill
 * @version 1.0
 */
package edu.miracosta.cs112.finalproject.finalproject.Models;

public abstract class GameEntity { //core class and abstract class
    /**
     * the x-coordinate and y-coordinate of the entity in the game world
      */
    protected double x, y;

    /**
     * constructs a GameEntity object at the specified coordinates
     * @param x the initial x-coordinate of the entity
     * @param y the initial y-coordinate of the entity
     */
    public GameEntity(double x, double y) { //constructor
        this.x = x;
        this.y = y;
    }

    /**
     * updates the entity state. This method must be implemented by subclasses
     * to define how the entity should behave over time
     */

    public abstract void update();

    /**
     * gets the current x-coordinate of the entity
     *
     * @return the x-coordinate of the entity
     */

    public double getX() {
        return x;
    }

    /**
     * gets the current y-coordinate of the entity
     *
     * @return the y-coordinate of the entity
     */

    public double getY(){
        return y;
    }
}

package de.jjj.dnasic.ships;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.jjj.dnasic.weapons.Weapon;

public class EnemyShip extends Ship {
    private int health;

    private float turnSpeed;

    public EnemyShip(TextureRegion texture, float x, float y, float speed, float turnSpeed, int health, Weapon[] weapons){
        super(texture, x, y, speed, weapons);

        this.turnSpeed = turnSpeed;

        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

    public void inflictDamage(int amount){
        this.health -= amount;

        if(this.health <= 0){
            this.death();
        }
    }

    private void death(){
        System.out.println("Enemy was killed!");
    }
}

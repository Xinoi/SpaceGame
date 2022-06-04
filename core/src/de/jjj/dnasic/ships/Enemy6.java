package de.jjj.dnasic.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.jjj.dnasic.weapons.Weapon;

public class Enemy6 extends EnemyShip {

    public Enemy6(float x, float y) {
        super(new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/NPC_Ship/NPC_Ship.atlas")).findRegion("Ship_6"), x, y, 0.5f, 0.5f, 200, new Weapon[1]);

        this.setWeapon(0, new Weapon(25, 0.5f));
    }
}
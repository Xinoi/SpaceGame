package de.jjj.dnasic.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level1 extends Level{

    private SpriteBatch batch;

    public Level1() {
        super(new Sprite(new Texture(Gdx.files.internal("Images/Background/Background_1.jpg"))));
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(Color.BLACK));
        super.render(batch);
        super.update(delta);
        System.out.println(ticker);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    	batch.dispose();
    	this.dispose();
    }

}

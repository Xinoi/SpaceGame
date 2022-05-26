package de.jjj.dnasic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen extends ScreenAdapter{
	
	private Stage stage;
	private Table table;

	private Label musicL;
	private Label title;
	private TextButton musicB;
	private TextButton.TextButtonStyle bStyle;

	private TextureAtlas atlas;
	private Skin skin;

	private Music bgMusic;

	private BitmapFont font = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));

	private Sound clickSound;
	
	public SettingsScreen() {
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		title = new Label("Settings", new Label.LabelStyle(font, Color.WHITE));

		musicL = new Label("Music:", new Label.LabelStyle(font, Color.WHITE));

		createButtonStyles();
		musicB = new TextButton(getMbuttonText(), bStyle);
		musicB.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				musicB.setText(setMbuttonText());
			}
		});

		clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.wav"));
		musicB.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play();
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		table = new Table();
		table.setFillParent(true);
		table.add(title).spaceBottom(100);
		table.row();
		table.add(musicL).spaceRight(20);
		table.add(musicB).pad(10);

		// background Music
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Asteroid.mp3"));
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.3f);
		
		stage.addActor(table);
		stage.addActor(DNASIC.INSTANCE.BackB);
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(new Color(Color.BLACK));

		if(DNASIC.INSTANCE.getSettings().getMusic()){
			bgMusic.play();
		}else {
			bgMusic.stop();
		}

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void hide() {
		bgMusic.stop();
	}
	@Override
	public void dispose() {
		stage.dispose();
		bgMusic.dispose();
	}

	private void createButtonStyles() {
		atlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Button/buttons.atlas"));
		skin = new Skin();
		skin.addRegions(atlas);
		bStyle = new TextButton.TextButtonStyle();
		bStyle.font = font;
		bStyle.fontColor = Color.GRAY;
		bStyle.up = skin.getDrawable("button_up");
		bStyle.down = skin.getDrawable("button_down");
		bStyle.pressedOffsetX = 1;
		bStyle.pressedOffsetY = -1;
	}

	private String getMbuttonText() {
		if(DNASIC.INSTANCE.getSettings().getMusic()) {
			return "  On  ";
		}else {
			return " Off  ";
		}
	}

	private String setMbuttonText() {
		if(DNASIC.INSTANCE.getSettings().getMusic()) {
			DNASIC.INSTANCE.getSettings().setMusic(false);
			return " Off  ";
		}else {
			DNASIC.INSTANCE.getSettings().setMusic(true);
			return "  On  ";
		}
	}

}

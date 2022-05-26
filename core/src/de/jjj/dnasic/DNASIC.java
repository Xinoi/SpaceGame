package de.jjj.dnasic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;

public class DNASIC extends Game {
	private Json json;
    private Settings settings;
    private GameData data;

    public TextButton BackB;
    private TextureAtlas atlas;
    private Skin skin;
    private TextButton.TextButtonStyle bStyle;

    private Sound clickSound;

	public static DNASIC INSTANCE = new DNASIC();

	public DNASIC() {
		INSTANCE = this;

        this.json = new Json();
	}

	@Override
	public void create() {
        this.settings = this.loadSettings();
        this.data = this.loadGameData();

        createButtonStyles();
        BackB = new TextButton("  Back  ", bStyle);
        BackB.setPosition(50, 50);
        BackB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScreen(new MenuScreen());
            }
        });

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.wav"));
        BackB.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

		this.setScreen(new MenuScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        this.saveSettings(this.settings);
        this.saveGameData(this.data);

        super.pause();
    }

    private void createButtonStyles() {
        atlas = new TextureAtlas(Gdx.files.internal("TextureAtlas/packed/Button/buttons.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);
        bStyle = new TextButton.TextButtonStyle();
        bStyle.font = new BitmapFont(Gdx.files.internal("BitmapFonts/MainFont.fnt"));;
        bStyle.fontColor = Color.GRAY;
        bStyle.up = skin.getDrawable("button_up");
        bStyle.down = skin.getDrawable("button_down");
        bStyle.pressedOffsetX = 1;
        bStyle.pressedOffsetY = -1;
    }

    // Save game data to disk
	public void saveGameData(GameData data){
		String dataString = this.json.toJson(data);
		FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
		file.writeString(dataString, false);
	}

	// Load game data from disk
	public GameData loadGameData(){
        try{
            FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/GameData/save.json");
            String dataString = file.readString();
            return this.json.fromJson(GameData.class, dataString);
        }catch(Exception e){
            return new GameData();
        }
	}

    // Save settings to disk
    public void saveSettings(Settings data){
        String dataString = this.json.toJson(data);
        FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/settings.json");
        file.writeString(dataString, false);
    }

    // Load settings from disk
    private Settings loadSettings(){
        try{
            FileHandle file = com.badlogic.gdx.Gdx.files.local("assets/settings.json");
            String dataString = file.readString();
            return this.json.fromJson(Settings.class, dataString);
        }catch(Exception e){
            return new Settings();
        }
    }

    // Get current settings
    public Settings getSettings(){
        return this.settings;
    }
}
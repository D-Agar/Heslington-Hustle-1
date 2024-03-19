package com.heslington_hustle.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.Player;
import com.badlogic.gdx.Gdx;
import com.heslington_hustle.game.HeslingtonHustle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.heslington_hustle.game.HeslingtonHustle.*;

/**
 * This class represents the screen where the player selects their character.
 * It displays options for different characters and allows the player to choose one.
 */
public class CharacterSelectionScreen extends ScreenAdapter {

    /** The stage for managing UI elements. */
    private final Stage stage;


    /** The skin for UI elements. */
    Skin skin;

    /** The game instance. */
    private final HeslingtonHustle game;

    /**
     * Constructs a new CharacterSelectionScreen with the specified game instance.
     *
     * @param game The game instance
     */
    public CharacterSelectionScreen(final HeslingtonHustle game) {
        this.game = game;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     * Sets up the UI elements for character selection, including buttons and character images.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        Label title = new Label("Choose your character:", skin, "title", Color.WHITE);

        // Create buttons
        TextButton Char1 = new TextButton("Character 1", skin);
        TextButton Char2 = new TextButton("Character 2", skin);
        TextButton back = new TextButton("Back", skin);

        // Show the characters
        Texture char1Text = new Texture(Gdx.files.internal("characters/char1/char1sd.png"));
        Texture char2Text = new Texture(Gdx.files.internal("characters/char2/char2sd.png"));
        Image char1Image = new Image(char1Text);
        Image char2Image = new Image(char2Text);

        // Arrange table
        table.add(title).fillX().center().colspan(2);
        table.row().pad(30, 0, 0, 0).height(80);
        table.add(char1Image).fill().center();
        table.add(char2Image).fill().center();
        table.row().pad(10, 0, 10, 0);
        table.add(Char1).uniformX();
        table.add(Char2).uniformX();
        table.row().pad(10, 0, 0, 0);
        table.row();
        table.add(back).uniformX().colspan(2);

        // create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(MENU);
            }
        });

        Char1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Player.character = "char1";
                game.changeScreen(NEWDAY);
            }
        });

        Char2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Player.character = "char2";
                game.changeScreen(NEWDAY);
            }
        });
    }

    /**
     * Renders the character selection screen by clearing the screen and drawing the stage.
     *
     * @param delta the time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Called when the screen is resized. Updates the stage's viewport accordingly.
     *
     * @param width  the new width of the screen
     * @param height the new height of the screen
     */
    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes of resources used by the character selection screen, such as the stage and skin.
     * This method is called when the screen is no longer needed.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    /**
     * Called when the screen is hidden. Sets the input processor to null.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}

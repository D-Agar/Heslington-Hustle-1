package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.heslington_hustle.game.HeslingtonHustle;

public class EndGameScreen extends ScreenAdapter {
    HeslingtonHustle game;
    private final Stage stage;
    Skin skin;
    BitmapFont font;
    private final SpriteBatch batch;

    public EndGameScreen (final HeslingtonHustle game) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        game.setState(HeslingtonHustle.GameState.END);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // assign skin to the exit
        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        //create button
        TextButton exit = new TextButton("Menu", skin);

        //add buttons to table
        table.add(exit).fillX().uniformX();

        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.reset();
                game.changeScreen(HeslingtonHustle.MENU);
                dispose();
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.begin();
        font.draw(batch, "End Game:", 200, 600);
        int[] stats = game.stats.getTally();
        font.draw(batch, "Studied " + stats[0] + " times", 200, 550);
        font.draw(batch, "Performed " + stats[1] + " recreation activities", 200, 500);
        font.draw(batch, "Eaten " + stats[2] + " times", 200, 450);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}

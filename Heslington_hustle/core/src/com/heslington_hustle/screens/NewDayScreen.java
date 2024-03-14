package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.HeslingtonHustle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class NewDayScreen extends ScreenAdapter {

    private final Stage stage;
    private final HeslingtonHustle game;
    BitmapFont font;
    private final SpriteBatch batch;

    public NewDayScreen (final HeslingtonHustle game) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        HeslingtonHustle.Energy = 100;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // assign skin to the menu
        Skin skin = new Skin(Gdx.files.internal("skin.json"));

        //create button
        TextButton ok = new TextButton("Start Day!", skin);

        //add buttons to table
        table.add(ok).fillX().uniformX();

        // create button listeners
        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.stats.addDay(game.stats.getStats());
                System.out.println(game.stats.getDay(0));
                game.stats.newDay();
                HeslingtonHustle.Day++;
                game.setScreen(new GameScreen(game));
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
        font.draw(batch, "Day " + HeslingtonHustle.Day, 200, 600);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        System.out.println("Main menu hiding");
        Gdx.input.setInputProcessor(null);
    }
}



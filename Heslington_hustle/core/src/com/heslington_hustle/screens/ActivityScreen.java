package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.Activity;
import com.heslington_hustle.game.Heslington_hustle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.heslington_hustle.game.Activity.ActivityType;

import static com.heslington_hustle.game.Activity.EnergyUse;
import static com.heslington_hustle.game.Activity.TimeUse;
import static com.heslington_hustle.game.Heslington_hustle.*;

public class ActivityScreen implements Screen {

    private final int w = Gdx.graphics.getWidth();
    private final int h = Gdx.graphics.getHeight();
    private final Stage stage;
    private final Heslington_hustle game;
    BitmapFont font = new BitmapFont();
    private final SpriteBatch batch;


    public ActivityScreen (final Heslington_hustle game) {
        this.game = game;
        batch = new SpriteBatch();
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
        TextButton yes = new TextButton("Yes", skin);
        TextButton back = new TextButton("Back", skin);

        //add buttons to table
        table.add(back).fillX().uniformX();

        // create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        yes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Energy - EnergyUse < 0) {
                    game.setScreen(new ErrorScreen(game));
                }
                else if (Time - TimeUse < 0) {
                    Day += 1;
                    game.setScreen(new NewDayScreen(game));
                }
                else {
                    Energy -= EnergyUse;
                    Time -= TimeUse;
                    game.setScreen(new GameScreen(game));
                }
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        if (Activity.type == ActivityType.EAT) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to eat?", 200, 600);
            batch.end();
        }
        if (Activity.type == ActivityType.RECREATION) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to feed the ducks?", 200, 600);
            batch.end();
        }
        if (Activity.type == ActivityType.STUDY) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to Study?", 200, 600);
            batch.end();
        }
        if (Activity.type == ActivityType.SLEEP) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to Sleep?", 200, 600);
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        System.out.println("Main menu hiding");
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
    }
}

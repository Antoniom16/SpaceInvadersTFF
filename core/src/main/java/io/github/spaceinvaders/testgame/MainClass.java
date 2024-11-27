package io.github.spaceinvaders.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainClass extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("xwing.png");
        player = new Player(image);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();
        player.Draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

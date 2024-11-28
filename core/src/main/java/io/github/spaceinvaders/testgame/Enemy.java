package io.github.spaceinvaders.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    public Vector2 positione;
    public Vector2 position_initial;
    public Sprite sprite_enemy;
    Boolean Alive = true;
    public Enemy(Vector2 _position,Texture image_enemy)
    {

        positione = _position;
        position_initial = positione;
        sprite_enemy = new Sprite (image_enemy);
        float scaleFactor = 0.16f;
        sprite_enemy.setScale(scaleFactor);
        float enemyWidth = image_enemy.getWidth() * scaleFactor;
        float enemyHeight = image_enemy.getHeight() * scaleFactor;
    }
    public void Draw(SpriteBatch batch)
    {
        sprite_enemy.setPosition(positione.x, positione.y);
        sprite_enemy.draw(batch);
    }
}

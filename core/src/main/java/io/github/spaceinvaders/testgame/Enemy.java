package io.github.spaceinvaders.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    public Vector2 position;
    public Sprite sprite;
    public Boolean Alive = true;
    public Enemy(Texture image_enemy)
    {

        sprite = new Sprite (image_enemy);

        float scaleFactor = 1f;
        sprite.setScale(scaleFactor);
        float scaledWidth = image_enemy.getWidth() * scaleFactor;
        float scaledHeight = image_enemy.getHeight() * scaleFactor;
        float enemyX = ((Gdx.graphics.getWidth() - scaledWidth) / 2);
        float enemyY = Gdx.graphics.getHeight() - scaledHeight;
        position = new Vector2(enemyX, enemyY);

        System.out.println("Screen Width: " + Gdx.graphics.getWidth());
        System.out.println("Screen Height: " + Gdx.graphics.getHeight());
        System.out.println("Original Sprite Width: " + image_enemy.getWidth());
        System.out.println("Original Sprite Height: " + image_enemy.getHeight());
        System.out.println("Scale Factor: " + scaleFactor);
        System.out.println("Scaled Width: " + scaledWidth);
        System.out.println("Scaled Height: " + scaledHeight);
        System.out.println("Calculated X Position: " + enemyX);
        System.out.println("Calculated Y Position: " + enemyY);
    }
    public void Draw(SpriteBatch batch)
    {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}

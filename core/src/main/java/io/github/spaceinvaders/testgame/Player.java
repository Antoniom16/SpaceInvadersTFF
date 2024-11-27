package io.github.spaceinvaders.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player {
        public Vector2 position;
        public Sprite sprite;
        public Player(Texture image)
        {
            // Create sprite
            sprite = new Sprite(image);

            // Set a fixed scale factor
            float scaleFactor = 0.07f;
            sprite.setScale(scaleFactor);

            // Calculate the dimensions after scaling
            float scaledWidth = image.getWidth() * scaleFactor;
            float scaledHeight = image.getHeight() * scaleFactor;

            // Positioning based on your previous successful method
            float xPos = ((Gdx.graphics.getWidth() - scaledWidth) / 2) - 570;
            float yPos = Gdx.graphics.getHeight() - scaledHeight - 950;

            System.out.println("Screen Width: " + Gdx.graphics.getWidth());
            System.out.println("Screen Height: " + Gdx.graphics.getHeight());
            System.out.println("Original Sprite Width: " + image.getWidth());
            System.out.println("Original Sprite Height: " + image.getHeight());
            System.out.println("Scale Factor: " + scaleFactor);
            System.out.println("Scaled Width: " + scaledWidth);
            System.out.println("Scaled Height: " + scaledHeight);
            System.out.println("Calculated X Position: " + xPos);
            System.out.println("Calculated Y Position: " + yPos);

            position = new Vector2(xPos, yPos);
        }

    public void Draw(SpriteBatch batch)
    {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}

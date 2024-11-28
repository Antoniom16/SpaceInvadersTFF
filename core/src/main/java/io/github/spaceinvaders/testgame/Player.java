package io.github.spaceinvaders.testgame;

import com.badlogic.gdx.Input.Keys;
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
        public Vector2 position_bullet;
        public Sprite sprite;
        public Sprite sprite_bullet;
        public float speed = 350;
        public float speed_bullet = 950;
        public Player(Texture image, Texture image_bullet)
        {
            sprite = new Sprite(image);
            sprite_bullet = new Sprite(image_bullet);

            float scaleFactor = 0.07f;
            sprite.setScale(scaleFactor);
            sprite_bullet.setScale(0.5F);
            float scaledWidth = image.getWidth() * scaleFactor;
            float scaledHeight = image.getHeight() * scaleFactor;
            float bulletHeight = image_bullet.getHeight();
            float xPos = ((Gdx.graphics.getWidth() - scaledWidth) / 2) - 563;
            float yPos = Gdx.graphics.getHeight() - scaledHeight - 985;


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
            position_bullet = new Vector2(position.x + (sprite.getWidth()/2.1053F),10);
        }
    public void Update(float deltaTime)
    {
            if (Gdx.input.isKeyPressed(Keys.SPACE) && position_bullet.y>=Gdx.graphics.getHeight()){
                position_bullet.x = position.x + (sprite.getWidth()/2.1053F);
                position_bullet.y = 10;

            }
            if (Gdx.input.isKeyPressed(Keys.A)) position.x-=deltaTime*speed;
            if (Gdx.input.isKeyPressed(Keys.D)) position.x+=deltaTime*speed;

            if(position.x<=-560) position.x = -560;
            if(position.x>=-15) position.x = -15;


        position_bullet.y += deltaTime * speed_bullet;

    }

    public void Draw(SpriteBatch batch)
    {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        sprite_bullet.setPosition(position_bullet.x, position_bullet.y);
        sprite_bullet.draw(batch);
    }
}

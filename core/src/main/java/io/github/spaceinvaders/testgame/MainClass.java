package io.github.spaceinvaders.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector2;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainClass extends ApplicationAdapter {
    SpriteBatch batch;
    Texture image;
    Texture image_bullet;
    Texture image_enemy;
    Player player;
    Enemy[] enemies;
    int NumWidth_enemies = 7;
    int NumHeight_enemies = 5;
    int spacing_enemies = 80;
    int minX_enemies;
    int minY_enemies;
    int maxX_enemies;
    int maxY_enemies;
    int direction_enemies = 1;
    float speed_enemies = 70;

    private int score = 0;
    private BitmapFont font;
    Vector2 offset_enemies;

    @Override
    public void create() {
        offset_enemies = Vector2.Zero;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        image = new Texture("xwing.png");
        image_bullet = new Texture ("red laser.png");
        image_enemy = new Texture("tiefighter.png");
        player = new Player(image,image_bullet);
        enemies = new Enemy[NumHeight_enemies*NumWidth_enemies];
        int i = 0;
        for(int y = 0; y<NumHeight_enemies;y++)
        {
            for (int x = 0; x<NumWidth_enemies;x++)
            {
                Vector2 position = new Vector2(x*spacing_enemies,y*spacing_enemies+40);
                position.x+=Gdx.graphics.getWidth()/2;
                position.y+=Gdx.graphics.getHeight();
                position.x-=(NumWidth_enemies/2)*spacing_enemies;
                position.y-=(NumHeight_enemies)*spacing_enemies;
                enemies [i] = new Enemy(position,image_enemy);
                i++;
            }
        }
    }
    int amount_alive_enemies = 0;
    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();
        player.Draw(batch);
        for (int i = 0; i < enemies.length; i++)
        {
            if(enemies[i].Alive)
            {
                if (player.sprite_bullet.getBoundingRectangle().overlaps(enemies[i].sprite_enemy.getBoundingRectangle()))
                {
                    score += 10;
                    // Debug prints for bounding rectangles

                    System.out.println("Bullet Rect: " + player.sprite_bullet.getBoundingRectangle());
                    System.out.println("Enemy " + i + " Rect: " + enemies[i].sprite_enemy.getBoundingRectangle());
                    System.out.println("COLLISION DETECTED with Enemy " + i);
                    player.position_bullet.y = Gdx.graphics.getHeight()+100000;
                    enemies[i].Alive = false;
                    break;
                }

            }

        }
        minX_enemies = 10000000;
        minY_enemies = 1000000;
        maxX_enemies = 0;
        maxY_enemies = 0;
        amount_alive_enemies = 0;
        for (int i = 0;i<enemies.length;i++)
        {
            if(enemies[i].Alive) {
                int IndexX = i % NumWidth_enemies;
                int IndexY = i / NumWidth_enemies;
                if (IndexX > maxX_enemies) maxX_enemies = IndexX;
                if (IndexX < minX_enemies) minX_enemies = IndexX;
                if (IndexY > maxY_enemies) maxY_enemies = IndexY;
                if (IndexY < minY_enemies) minY_enemies = IndexY;
                amount_alive_enemies++;
            }
        }
        if(amount_alive_enemies == 0)
        {
            for (int i = 0;i<enemies.length;i++)
            {
                enemies[i].Alive = true;
            }
            offset_enemies = new Vector2(-180,40);
            batch.end();
            speed_enemies = 70;
            return;

        }
        offset_enemies.x+=direction_enemies*deltaTime*speed_enemies;
        if(enemies[maxX_enemies].positione.x>=Gdx.graphics.getWidth()-250)
        {
            direction_enemies = -1;
            offset_enemies.y-= .45;
            speed_enemies+=.04;
        }
        if(enemies[minX_enemies].positione.x<=-180)
        {
            direction_enemies = 1;
            offset_enemies.y-= .45;
            speed_enemies+=.04;
        }
        if(enemies[minY_enemies].positione.y<=player.position.y)
        {
            Gdx.app.exit();
        }



        for (int i = 0; i < enemies.length; i++)
        {
            enemies[i].positione = new Vector2(enemies[i].position_initial.x+offset_enemies.x,enemies[i].position_initial.y+offset_enemies.y);

            if(enemies[i].Alive)
            {
                enemies[i].Draw(batch);
                if(enemies[i].sprite_enemy.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle()))
                {
                    Gdx.app.exit();
                }
            }
        }
        font.draw(batch, "Score: " + score, 10, Gdx.graphics.getHeight() - 10);
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

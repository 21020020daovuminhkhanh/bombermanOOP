package bomberman.entity.movingEntity;

import bomberman.entity.Bomb;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends MovingEntity {
    Random random = new Random();
    @Override
    public abstract void draw(Graphics2D g2);

    @Override
    public abstract void update();

    @Override
    public boolean collideWithExplosion(Bomb b) {
        int leftEnemyMapX = mapX + hitbox.x;
        int rightEnemyMapX = mapX + hitbox.x + hitbox.width;
        int topEnemyMapY = mapY + hitbox.y;
        int bottomEnemyMapY = mapY + hitbox.y + hitbox.height;

        int leftVerticalMapX = b.mapX;
        int rightVerticalMapX = b.mapX + tileSize;
        int topVerticalMapY = b.mapY - b.explosionFlame.topFlameLength * tileSize;
        int bottomVerticalMapY = b.mapY + (b.explosionFlame.downFlameLength + 1) * tileSize;

        int leftHorizontalMapX = b.mapX - b.explosionFlame.leftFlameLength * tileSize;
        int rightHorizontalMapX = b.mapX + (b.explosionFlame.rightFlameLength + 1) * tileSize;
        int topHorizontalMapY = b.mapY;
        int bottomHorizontalMapY = b.mapY + tileSize;

        if (leftEnemyMapX <= rightVerticalMapX && rightEnemyMapX >= leftVerticalMapX &&
                topEnemyMapY <= bottomVerticalMapY && bottomEnemyMapY >= topVerticalMapY) return true;
        if (leftEnemyMapX <= rightHorizontalMapX && rightEnemyMapX >= leftHorizontalMapX &&
                topEnemyMapY <= bottomHorizontalMapY && bottomEnemyMapY >= topHorizontalMapY) return true;
        return false;
    }
}

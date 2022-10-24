package bomberman.entity;

import bomberman.entity.movingEntity.MovingEntity;
import bomberman.GamePanel;

public class CheckCollision {
    GamePanel gamePanel;

    public CheckCollision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(MovingEntity e) {
        int leftMapX = e.mapX + e.hitbox.x;
        int rightMapX = e.mapX + e.hitbox.x + e.hitbox.width;
        int topMapY = e.mapY + e.hitbox.y;
        int bottomMapY = e.mapY + e.hitbox.y + e.hitbox.height;

        int eLeftCol = leftMapX / gamePanel.tileSize;
        int eRightCol = rightMapX / gamePanel.tileSize;
        int eTopRow = topMapY / gamePanel.tileSize;
        int eBottomRow = bottomMapY / gamePanel.tileSize;

        char infrontTileNum1, infrontTileNum2;

        switch (e.direction) {
            case "up":
                eTopRow = (topMapY - e.speed) / gamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eLeftCol][eTopRow];
                infrontTileNum2 = gamePanel.level.mapTile[eRightCol][eTopRow];
                if ((infrontTileNum1 != ' ' || infrontTileNum2 != ' ') &&
                    (infrontTileNum1 != '3' || infrontTileNum2 != '3')) {
                    e.isCollide = true;
                }
                break;
            case "down":
                eBottomRow = (bottomMapY + e.speed) / gamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eLeftCol][eBottomRow];
                infrontTileNum2 = gamePanel.level.mapTile[eRightCol][eBottomRow];
                if ((infrontTileNum1 != ' ' || infrontTileNum2 != ' ') &&
                    (infrontTileNum1 != '3' || infrontTileNum2 != '3')) {
                    e.isCollide = true;
                }
                break;
            case "left":
                eLeftCol = (leftMapX - e.speed) / gamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eLeftCol][eTopRow];
                infrontTileNum2 = gamePanel.level.mapTile[eLeftCol][eBottomRow];
                if ((infrontTileNum1 != ' ' || infrontTileNum2 != ' ') &&
                    (infrontTileNum1 != '3' || infrontTileNum2 != '3')) {
                    e.isCollide = true;
                }
                break;
            case "right":
                eRightCol = (rightMapX + e.speed) / gamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eRightCol][eBottomRow];
                infrontTileNum2 = gamePanel.level.mapTile[eRightCol][eTopRow];
                if ((infrontTileNum1 != ' ' || infrontTileNum2 != ' ') &&
                    (infrontTileNum1 != '3' || infrontTileNum2 != '3')) {
                    e.isCollide = true;
                }
                break;
        }

    }

}

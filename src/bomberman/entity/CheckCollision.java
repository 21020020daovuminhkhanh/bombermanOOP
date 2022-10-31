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

        int eLeftCol = leftMapX / GamePanel.tileSize;
        int eRightCol = rightMapX / GamePanel.tileSize;
        int eTopRow = topMapY / GamePanel.tileSize;
        int eBottomRow = bottomMapY / GamePanel.tileSize;

        char infrontTileNum1, infrontTileNum2;

        switch (e.direction) {
            case "up":
                eTopRow = (topMapY - e.speed) / GamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eLeftCol][eTopRow];
                infrontTileNum2 = gamePanel.level.mapTile[eRightCol][eTopRow];
                if (infrontTileNum1 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum2 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum1 == 'b') {
                    gamePanel.level.mapTile[eLeftCol][eTopRow] = ' ';
                    gamePanel.board.player.bombAmount++;
                }
                else if (infrontTileNum2 == 'b') {
                    gamePanel.level.mapTile[eRightCol][eTopRow] = ' ';
                    gamePanel.board.player.bombAmount++;
                }
                else if (infrontTileNum1 == 's') {
                    gamePanel.level.mapTile[eLeftCol][eTopRow] = ' ';
                    gamePanel.board.player.speed++;
                }
                else if (infrontTileNum2 == 's') {
                    gamePanel.level.mapTile[eRightCol][eTopRow] = ' ';
                    gamePanel.board.player.speed++;
                }
                else if (infrontTileNum1 == 'f') {
                    gamePanel.level.mapTile[eLeftCol][eTopRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum2 == 'f') {
                    gamePanel.level.mapTile[eRightCol][eTopRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum1 != ' ' || infrontTileNum2 != ' ') {
                    e.isCollide = true;
                }
                break;
            case "down":
                eBottomRow = (bottomMapY + e.speed) / GamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eLeftCol][eBottomRow];
                infrontTileNum2 = gamePanel.level.mapTile[eRightCol][eBottomRow];
                if (infrontTileNum1 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum2 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum1 == 'b') {
                    gamePanel.level.mapTile[eLeftCol][eBottomRow] = ' ';
                    gamePanel.board.player.bombAmount += 1;
                }
                else if (infrontTileNum2 == 'b') {
                    gamePanel.level.mapTile[eRightCol][eBottomRow] = ' ';
                    gamePanel.board.player.bombAmount += 1;
                }
                else if (infrontTileNum1 == 's') {
                    gamePanel.level.mapTile[eLeftCol][eBottomRow] = ' ';
                    gamePanel.board.player.speed += 1;
                }
                else if (infrontTileNum2 == 's') {
                    gamePanel.level.mapTile[eRightCol][eBottomRow] = ' ';
                    gamePanel.board.player.speed += 1;
                }
                else if (infrontTileNum1 == 'f') {
                    gamePanel.level.mapTile[eLeftCol][eBottomRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum2 == 'f') {
                    gamePanel.level.mapTile[eRightCol][eBottomRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum1 != ' ' || infrontTileNum2 != ' ') {
                    e.isCollide = true;
                }
                break;
            case "left":
                eLeftCol = (leftMapX - e.speed) / GamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eLeftCol][eTopRow];
                infrontTileNum2 = gamePanel.level.mapTile[eLeftCol][eBottomRow];
                if (infrontTileNum1 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum2 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum1 == 'b') {
                    gamePanel.level.mapTile[eLeftCol][eTopRow] = ' ';
                    gamePanel.board.player.bombAmount += 1;
                }
                else if (infrontTileNum2 == 'b') {
                    gamePanel.level.mapTile[eLeftCol][eBottomRow] = ' ';
                    gamePanel.board.player.bombAmount += 1;
                }
                else if (infrontTileNum1 == 's') {
                    gamePanel.level.mapTile[eLeftCol][eTopRow] = ' ';
                    gamePanel.board.player.speed += 1;
                }
                else if (infrontTileNum2 == 's') {
                    gamePanel.level.mapTile[eLeftCol][eBottomRow] = ' ';
                    gamePanel.board.player.speed += 1;
                }
                else if (infrontTileNum1 == 'f') {
                    gamePanel.level.mapTile[eLeftCol][eTopRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum2 == 'f') {
                    gamePanel.level.mapTile[eLeftCol][eBottomRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum1 != ' ' || infrontTileNum2 != ' ') {
                    e.isCollide = true;
                }
                break;
            case "right":
                eRightCol = (rightMapX + e.speed) / GamePanel.tileSize;
                infrontTileNum1 = gamePanel.level.mapTile[eRightCol][eBottomRow];
                infrontTileNum2 = gamePanel.level.mapTile[eRightCol][eTopRow];
                if (infrontTileNum1 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum2 == 'x') {
                    e.isCollide = true;
                    if (gamePanel.board.countEnemies() == 0) gamePanel.board.player.reset = true;
                }
                else if (infrontTileNum1 == 'b') {
                    gamePanel.level.mapTile[eRightCol][eBottomRow] = ' ';
                    gamePanel.board.player.bombAmount++;
                }
                else if (infrontTileNum2 == 'b') {
                    gamePanel.level.mapTile[eRightCol][eTopRow] = ' ';
                    gamePanel.board.player.bombAmount++;
                }
                else if (infrontTileNum1 == 's') {
                    gamePanel.level.mapTile[eRightCol][eBottomRow] = ' ';
                    gamePanel.board.player.speed++;
                }
                else if (infrontTileNum2 == 's') {
                    gamePanel.level.mapTile[eRightCol][eTopRow] = ' ';
                    gamePanel.board.player.speed++;
                }
                else if (infrontTileNum1 == 'f') {
                    gamePanel.level.mapTile[eRightCol][eBottomRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum2 == 'f') {
                    gamePanel.level.mapTile[eRightCol][eTopRow] = ' ';
                    gamePanel.board.player.flameLength++;
                }
                else if (infrontTileNum1 != ' ' || infrontTileNum2 != ' ') {
                    e.isCollide = true;
                }
                break;
        }

    }

}

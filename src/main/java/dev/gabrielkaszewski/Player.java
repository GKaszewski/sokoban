package dev.gabrielkaszewski;

import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.RAYWHITE;

public class Player extends Entity {

    private String playerTexturePath = "assets/player.png";
    private Texture playerTexture;

    Player(int x, int y) {
        super(x, y, Type.PLAYER);

        initialize();
    }

    private void initialize() {
        playerTexture = LoadTexture(playerTexturePath);
    }

    public void draw() {
        DrawTexture(playerTexture, x * World.TILE_SIZE, y * World.TILE_SIZE, RAYWHITE);
    }

    private void handleCrateCollisions() {
        Entity upEntity = World.getEntityAt(x, y - 1);
        Entity downEntity = World.getEntityAt(x, y + 1);
        Entity leftEntity = World.getEntityAt(x - 1, y);
        Entity rightEntity = World.getEntityAt(x + 1, y);

        if (IsKeyPressed(KEY_UP) && upEntity instanceof Crate) {
            Crate crate = (Crate) upEntity;
            if (World.getStaticEntityAt(x, y - 2) == null && !(World.getEntityAt(x, y - 2) instanceof Crate)) {
                crate.y -= 1;
            }
        }

        if (IsKeyPressed(KEY_DOWN) && downEntity instanceof Crate) {
            Crate crate = (Crate) downEntity;
            if (World.getStaticEntityAt(x, y + 2) == null && !(World.getEntityAt(x, y + 2) instanceof Crate)) {
                crate.y += 1;
            }
        }

        if (IsKeyPressed(KEY_LEFT) && leftEntity instanceof Crate) {
            Crate crate = (Crate) leftEntity;
            if (World.getStaticEntityAt(x - 2, y) == null && !(World.getEntityAt(x - 2, y) instanceof Crate)) {
                crate.x -= 1;
            }
        }

        if (IsKeyPressed(KEY_RIGHT) && rightEntity instanceof Crate) {
            Crate crate = (Crate) rightEntity;
            if (World.getStaticEntityAt(x + 2, y) == null && !(World.getEntityAt(x + 2, y) instanceof Crate)) {
                crate.x += 1;
            }
        }

    }

    public void update() {
        handleCrateCollisions();

        if (IsKeyPressed(KEY_UP) && (World.getStaticEntityAt(x, y - 1) == null) && !(World.getEntityAt(x, y - 1) instanceof Crate)) {
            y -= 1;
        }
        if (IsKeyPressed(KEY_DOWN) && (World.getStaticEntityAt(x, y + 1) == null) && !(World.getEntityAt(x, y + 1) instanceof Crate)) {
            y += 1;
        }
        if (IsKeyPressed(KEY_LEFT) && (World.getStaticEntityAt(x - 1, y) == null) && !(World.getEntityAt(x - 1, y) instanceof Crate)) {
            x -= 1;
        }
        if (IsKeyPressed(KEY_RIGHT) && (World.getStaticEntityAt(x + 1, y) == null) && !(World.getEntityAt(x + 1, y) instanceof Crate)) {
            x += 1;
        }

    }

}

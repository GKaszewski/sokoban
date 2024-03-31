package dev.gabrielkaszewski;
import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.RAYWHITE;

public class Crate extends Entity {
    private String crateTexturePath = "assets/crate.png";
    private Texture crateTexture;

    Crate(int x, int y) {
        super(x, y, Type.CRATE);

        initialize();
    }

    private void initialize() {
        crateTexture = LoadTexture(crateTexturePath);
    }

    public void draw() {
        DrawTexture(crateTexture, x * World.TILE_SIZE, y * World.TILE_SIZE, RAYWHITE);
    }
}

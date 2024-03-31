package dev.gabrielkaszewski;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Mark extends Entity {
    private String markTexturePath = "assets/ground_mark.png";
    private Texture markTexture;

    Mark(int x, int y) {
        super(x, y, Type.MARK);

        initialize();
    }

    private void initialize() {
        markTexture = LoadTexture(markTexturePath);
    }

    public void draw() {
        DrawTexture(markTexture, x * World.TILE_SIZE, y * World.TILE_SIZE, RAYWHITE);
    }
}

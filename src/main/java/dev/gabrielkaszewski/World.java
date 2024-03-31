package dev.gabrielkaszewski;

import java.util.ArrayList;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class World {
    private int width;
    private int height;
    private int crateCount;
    public static final int TILE_SIZE = 64;

    private String groundTexturePath = "assets/ground.png";
    private String wallTexturePath = "assets/wall.png";

    private Texture groundTexture;
    private Texture wallTexture;

    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static ArrayList<StaticEntity> staticEntities = new ArrayList<StaticEntity>();

    private ArrayList<Mark> marks = new ArrayList<Mark>();
    private ArrayList<Crate> crates = new ArrayList<Crate>();

    private Player player;
    private boolean isGameOver = false;

    public World() {
        this.width = 5;
        this.height = 10;
        this.crateCount = 2;

        initialize();
    }

    public World(int width, int height, int crateCount) {
        this.width = width;
        this.height = height;
        this.crateCount = crateCount;

        initialize();
    }

    public void draw() {
        drawGrid();
        drawWalls();
        for (Mark mark : marks) {
            mark.draw();
        }

        for (Crate crate : crates) {
            crate.draw();
        }

        player.draw();

        if (isGameOver) {
            DrawText("You win!", 10, 10, 20, RAYWHITE);
        }
    }

    public void update() {
        player.update();

        if (IsKeyPressed(KEY_R)) {
            reset();
        }

        if (IsKeyPressed(KEY_KP_1)) {
            crateCount = 1;
            reset();
        }

        if (IsKeyPressed(KEY_KP_2)) {
            crateCount = 2;
            reset();
        }

        if (IsKeyPressed(KEY_KP_3)) {
            crateCount = 3;
            reset();
        }

        if (IsKeyPressed(KEY_KP_4)) {
            crateCount = 4;
            reset();
        }

        if (IsKeyPressed(KEY_KP_5)) {
            crateCount = 5;
            reset();
        }

        isGameOver = checkWinCondition();
    }

    private void drawGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                DrawTexture(groundTexture, x * TILE_SIZE, y * TILE_SIZE, RAYWHITE);
            }
        }
    }

    private void drawWalls() {
        for (int x = 0; x < width; x++) {
            DrawTexture(wallTexture, x * TILE_SIZE, 0, RAYWHITE);
            DrawTexture(wallTexture, x * TILE_SIZE, (height - 1) * TILE_SIZE, RAYWHITE);
        }

        for (int y = 0; y < height; y++) {
            DrawTexture(wallTexture, 0, y * TILE_SIZE, RAYWHITE);
            DrawTexture(wallTexture, (width - 1) * TILE_SIZE, y * TILE_SIZE, RAYWHITE);
        }
    }

    private void initialize() {
        groundTexture = LoadTexture(groundTexturePath);
        wallTexture = LoadTexture(wallTexturePath);

        player = new Player(1, 1);

        addWallsToStaticEntities();
        entities.addAll(staticEntities);
        entities.add(player);

        for (int i = 0; i < crateCount; i++) {
            int[] position = getRandomEmptyPosition();
            crates.add(new Crate(position[0], position[1]));
            entities.add(crates.get(i));

            int[] markPosition = getRandomEmptyPosition();
            marks.add(new Mark(markPosition[0], markPosition[1]));
            entities.add(marks.get(i));
        }

    }

    private int[] getRandomEmptyPosition() {
        int x = GetRandomValue(1, width - 2);
        int y = GetRandomValue(1, height - 2);

        if (getEntityAt(x, y) != null) {
            return getRandomEmptyPosition();
        }

        return new int[]{x, y};
    }

    private void addWallsToStaticEntities() {
        for (int x = 0; x < width; x++) {
            staticEntities.add(new StaticEntity(x, 0));
            staticEntities.add(new StaticEntity(x, height - 1));
        }

        for (int y = 0; y < height; y++) {
            staticEntities.add(new StaticEntity(0, y));
            staticEntities.add(new StaticEntity(width - 1, y));
        }
    }

    public static StaticEntity getStaticEntityAt(int x, int y) {
        for (StaticEntity staticEntity : staticEntities) {
            if (staticEntity.x == x && staticEntity.y == y) {
                return staticEntity;
            }
        }
        return null;
    }

    public static Entity getEntityAt(int x, int y) {
        for (Entity entity : entities) {
            if (entity.x == x && entity.y == y) {
                return entity;
            }
        }
        return null;
    }

    private Mark getMarkAt(int x, int y) {
        for (Mark mark : marks) {
            if (mark.x == x && mark.y == y) {
                return mark;
            }
        }
        return null;
    }

    private void reset() {
        entities.clear();
        staticEntities.clear();
        marks.clear();
        crates.clear();
        initialize();
    }

    private boolean checkWinCondition() {
        for (Crate crate : crates) {
            if (getMarkAt(crate.x, crate.y) != null) {
                continue;
            }
            return false;
        }
        return true;
    }
}

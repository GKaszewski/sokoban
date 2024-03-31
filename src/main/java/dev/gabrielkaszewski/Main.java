package dev.gabrielkaszewski;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;


public class Main {
    public static void main(String[] args) {
        int width = 10;
        int height = 10;
        int crateCount = 1;

        if (args.length == 2) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }

        if (args.length == 3) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            crateCount = Integer.parseInt(args[2]);
        }

        int maxScreenWidth = 1920;
        int maxScreenHeight = 1080;

        if (width * World.TILE_SIZE > maxScreenWidth || height * World.TILE_SIZE > maxScreenHeight) {
            System.out.println("The screen size is too big for the current resolution.");
            width = maxScreenWidth / World.TILE_SIZE;
            height = maxScreenHeight / World.TILE_SIZE;
        }

        InitWindow(width * World.TILE_SIZE, height * World.TILE_SIZE, "Sokoban");
        SetTargetFPS(60);

        World world = new World(width, height, crateCount);

        while (!WindowShouldClose()) {
            world.update();

            BeginDrawing();
            ClearBackground(RAYWHITE);

            world.draw();

            EndDrawing();
        }
    }
}
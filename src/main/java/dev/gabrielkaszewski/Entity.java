package dev.gabrielkaszewski;

public class Entity {
    public int x;
    public int y;

    public enum Type {
        PLAYER,
        CRATE,
        MARK,
        STATIC
    }

    public Type type;

    Entity(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean isCollding(Entity entity) {
        return this.x == entity.x && this.y == entity.y;
    }
}

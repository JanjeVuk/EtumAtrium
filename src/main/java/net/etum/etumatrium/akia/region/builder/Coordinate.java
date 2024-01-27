package net.etum.etumatrium.akia.region.builder;

public class Coordinate {

    private final double x;
    private final double y;
    private final double z;
    private final String world;

    public Coordinate(double x, double y, double z, String world) {
        validateCoordinates(x, y, z);
        validateWorld(world);

        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public String getWorld() {
        return this.world;
    }

    private void validateCoordinates(double x, double y, double z) {
        if (Double.isNaN(x) || Double.isInfinite(x) ||
                Double.isNaN(y) || Double.isInfinite(y) ||
                Double.isNaN(z) || Double.isInfinite(z)) {
            throw new IllegalArgumentException("Coordinates must be valid values.");
        }
    }

    private void validateWorld(String world) {
        if (world == null || world.trim().isEmpty()) {
            throw new IllegalArgumentException("World cannot be null or empty.");
        }
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", world='" + world + '\'' +
                '}';
    }
}

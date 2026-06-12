package de.lavaclient.feature.waypoints;

public class Waypoint {
    public String name;
    public int x, y, z, color;

    public Waypoint(String name, int x, int y, int z, int color) {
        this.name = name; this.x = x; this.y = y; this.z = z; this.color = color;
    }

    public double distanceTo(double px, double py, double pz) {
        double dx = x - px, dy = y - py, dz = z - pz;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
}

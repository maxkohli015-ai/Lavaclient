package de.lavaclient.event;

public class Events {

    public static class RenderTickEvent {
        public final float tickDelta;
        public RenderTickEvent(float tickDelta) { this.tickDelta = tickDelta; }
    }

    public static class GameTickEvent {}

    public static class KeyPressEvent {
        public final int key;
        public final int action;
        public KeyPressEvent(int key, int action) { this.key = key; this.action = action; }
    }

    public static class AttackEvent {
        public boolean cancelled = false;
    }

    public static class MotionEvent {
        public double x, y, z;
        public float yaw, pitch;
        public boolean onGround;
        public MotionEvent(double x, double y, double z, float yaw, float pitch, boolean onGround) {
            this.x = x; this.y = y; this.z = z;
            this.yaw = yaw; this.pitch = pitch;
            this.onGround = onGround;
        }
    }

    public static class WorldRenderEvent {
        public final float tickDelta;
        public WorldRenderEvent(float tickDelta) { this.tickDelta = tickDelta; }
    }

    public static class ChatReceiveEvent {
        public String message;
        public boolean cancelled = false;
        public ChatReceiveEvent(String message) { this.message = message; }
    }

    public static class ChatSendEvent {
        public String message;
        public boolean cancelled = false;
        public ChatSendEvent(String message) { this.message = message; }
    }

    public static class ServerConnectEvent {
        public final String address;
        public ServerConnectEvent(String address) { this.address = address; }
    }

    public static class PlayerDeathEvent {}
}

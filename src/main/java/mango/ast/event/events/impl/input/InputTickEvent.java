package mango.ast.event.events.impl.input;

import mango.ast.event.events.Event;

public class InputTickEvent implements Event {
    public boolean up, down, left, right, jump, shift, sprint;

    public InputTickEvent(boolean up, boolean down, boolean left, boolean right, boolean jump, boolean shift, boolean sprint) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.jump = jump;
        this.shift = shift;
        this.sprint = sprint;
    }
}

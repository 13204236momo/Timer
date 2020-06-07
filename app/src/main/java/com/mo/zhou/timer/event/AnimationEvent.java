package com.mo.zhou.timer.event;

public class AnimationEvent {

    public static final int STATE_SHOW = 0;
    public static final int STATE_HIDE = 1;

    public final int state;
    public AnimationEvent(int state) {
        this.state = state;
    }
}

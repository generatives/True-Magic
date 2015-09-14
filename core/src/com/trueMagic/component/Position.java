package com.trueMagic.component;

import com.artemis.Component;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class Position extends Component {
    public float x, y;

    public void set(Position position) {
        this.x = position.x;
        this.y = position.y;
    }
}

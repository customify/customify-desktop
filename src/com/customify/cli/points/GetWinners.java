package com.customify.cli.points;

import com.customify.cli.Keys;

public class GetWinners {
    private Keys key;



    public GetWinners(Keys key) {
      this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}

package com.customify.cli.data_format.billing;

import com.customify.cli.Keys;

public class GetFeaturesFormat {
    private Keys key;
    public GetFeaturesFormat(){}
    public GetFeaturesFormat(Keys key){
        this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}

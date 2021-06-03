package com.customify.desktop.utils.interfaces;

import java.util.ArrayList;
import java.util.List;

public class SelectBusinessFormat {
    List<Integer> ids = new ArrayList<>();
    List<String> names = new ArrayList<>();

    public SelectBusinessFormat() {
    }

    public SelectBusinessFormat(List<Integer> ids, List<String> names) {
        this.ids = ids;
        this.names = names;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}

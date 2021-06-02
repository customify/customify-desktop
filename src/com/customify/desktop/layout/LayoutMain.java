package com.customify.desktop.layout;

import java.awt.*;
import java.io.IOException;

public class LayoutMain {
    public static void main(String[] args) throws IOException {
        Container container = new Container();
        new Layout(container,"Welcome");
    }
}

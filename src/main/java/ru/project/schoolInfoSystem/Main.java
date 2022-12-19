package ru.project.schoolInfoSystem;

import ru.project.schoolInfoSystem.panel.MainFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("Панель Завуча", new Dimension(900, 600));
        mainFrame.init();
    }

}

package graphicalModified;

import javax.swing.*;
import java.util.Arrays;

public final class Screen extends JFrame {

    public Screen(String title, int width, int height, boolean resize,
                  String imageAddress, String exitButtonDoes) {
        setExitButton(exitButtonDoes);
        this.setResizable(resize);
        this.setSize(width, height);
        this.setTitle(title);
        ImageIcon image = new ImageIcon(imageAddress);
        this.setIconImage(image.getImage());
        this.setLayout(null);
    }

    public  void setExitButton(String exitButton) {
        String[] actionList = {"close", "hide", "nothing"};
        if (Arrays.asList(actionList).contains(exitButton.toLowerCase())) {
            switch (exitButton) {
                case "close" -> this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                case "hide" -> this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                case "nothing" -> this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        } else
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

package PacketSniffer.graphicalModified;


import javax.swing.*;

public final class Button extends JButton {
    public Button(String text,boolean enabled) {
        setText(text);
        this.setFocusPainted(false);
        setEnabled(enabled);
    }
}

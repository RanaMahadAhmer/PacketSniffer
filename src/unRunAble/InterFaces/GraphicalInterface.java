package unRunAble.InterFaces;

import unRunAble.BackEnd;

import javax.swing.*;

public interface GraphicalInterface {
    void BuildGUIComponents();
    JComponent[] getGuiScreenComponents();

    JComponent[] getGuiBarComponents();

    void addComponentsToToolBar(JComponent... comp);

    void addComponentsToScreen(JComponent... comp);

    void setBackEnd(BackEnd backEnd);
    void setVisible();
}

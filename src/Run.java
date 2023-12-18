

import unRunAble.BackEnd;
import unRunAble.Graphical;
import unRunAble.InterFaces.BackEndInterface;
import unRunAble.InterFaces.GraphicalInterface;

import javax.swing.*;

public class Run {
    public  static void main(String[] args) {

        GraphicalInterface gui = new Graphical();

        gui.BuildGUIComponents();
        

        JComponent[] toolBarComponents = gui.getGuiBarComponents();
        gui.addComponentsToToolBar(toolBarComponents);

        JComponent[] screenComponents =  gui.getGuiScreenComponents();
        gui.addComponentsToScreen(screenComponents);

        BackEndInterface procedural = new BackEnd((Graphical) gui);

        gui.setBackEnd((BackEnd) procedural);

        gui.setVisible();
    }
}

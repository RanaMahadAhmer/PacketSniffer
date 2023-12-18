package PacketSniffer;

import PacketSniffer.unRunAble.BackEnd;
import PacketSniffer.unRunAble.Graphical;
import PacketSniffer.unRunAble.InterFaces.BackEndInterface;
import PacketSniffer.unRunAble.InterFaces.GraphicalInterface;

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

package PacketSniffer.unRunAble;


//import CompletedProject.sniffer;

import PacketSniffer.unRunAble.InterFaces.GraphicalInterface;
import PacketSniffer.graphicalModified.Button;
import PacketSniffer.graphicalModified.Screen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.io.UnsupportedEncodingException;

public class Graphical implements GraphicalInterface {
    /**Main Screen for adding everything to the screen(Ultimate Use to SHow OOP to Sir)/*/
    private Screen screen;
    /**Tool Bar to add buttons and ComboBoxes*/
    private   JToolBar bar = new JToolBar();

    private JLabel labelForInterFace;

    /**Text Area to Show Packet Info*/
    public  JTextArea textAreaPacketInfo;

    /**Text Area to Show Packet Hexa*/
    public  JTextArea textAreaHexaView;

    /**Save Button for saving captured packets to a file*/
    private PacketSniffer.graphicalModified.Button saveButton;

    /**Stop Button to stop packet capturing*/
    private PacketSniffer.graphicalModified.Button stopButton;
    /**Start Button to start packet capturing*/
    private PacketSniffer.graphicalModified.Button captureButton;

    private JLabel labelForFilter;

    /**Combo Box to show available filtering Options*/

    public  JComboBox filterOptions;

    /**Combo Box to show available network interfaces Options*/
    public  JComboBox interfaceOptions;

    /**Table to show packets in traffic in the particular interface*/

    public  JTable packetTable;

    private JScrollPane packetTableScrollPane;
    private JScrollPane textAreaHexaScrollPane;
    private JLabel label;
    private JLabel label1;

    private JLabel labelInterfaceInfo;
    public JTextArea textAreaInterfaceInfo;


    /**Boolean to control if we care capturing packets or not*/
    public  boolean captureState =false;

    private BackEnd backEnd;
    public Graphical(){
        this.backEnd = null;
    }
    public void setBackEnd(BackEnd backEnd){
        this.backEnd = backEnd;
    }

    /**Main Function to build the GUI and add all the components*/

    public  void BuildGUIComponents() {

//-------------------------------------------------------------------------------------------------------
        screen = new Screen("Caught It",1900,822,false,"src/PacketSniffer/graphicalAssets/IMG-20220712-WA0005.jpg","close");
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        labelForInterFace = new JLabel("    Interface   ");
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        interfaceOptions = new JComboBox();
        interfaceOptions.setPreferredSize(new Dimension(370,25));
        interfaceOptions.setMaximumSize(interfaceOptions.getPreferredSize() );
        interfaceOptions.addItem("Select Active Network Interface");
        interfaceOptions.addActionListener(e -> interfaceOptionsActionPerformed());
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        labelForFilter = new JLabel("  Protocol Filter   ");
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        filterOptions = new JComboBox();
        filterOptions.setPreferredSize(new Dimension(150,25));
        filterOptions.setMaximumSize( filterOptions.getPreferredSize() );
        filterOptions.addItem("No Filter");
        filterOptions.addItem("UDP");
        filterOptions.addItem("TCP");
        filterOptions.addItem("ICMP");
        filterOptions.setEnabled(false);
//-------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------
        bar.setBounds(0,0,1900,40);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        captureButton = new PacketSniffer.graphicalModified.Button("Capture",false);
        captureButton.setBackground(new Color(0,0,200));
        captureButton.addActionListener(e->captureButtonActionPerformed());
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        stopButton = new PacketSniffer.graphicalModified.Button("Stop",false);
        stopButton.setBackground(new Color(240,0,0));
        stopButton.addActionListener(e->stopButtonActionPerformed());
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        saveButton = new Button("Save",false);
        saveButton.setBackground(new Color(0,240,0));
        saveButton.addActionListener(e->saveButtonActionPerformed());
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        packetTable = new JTable();
        packetTable =   new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        packetTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                        "No.", "Length", "Source", "Destination", "Protocol"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        packetTable.setRowHeight(20);
        packetTable.isEditing();
        packetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        packetTableScrollPane = new JScrollPane();
        packetTableScrollPane.setViewportView(packetTable);
        packetTableScrollPane.setBounds(10,45,1505,450);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        labelInterfaceInfo = new JLabel("Interface Info:");
        labelInterfaceInfo.setFont(new Font("Calibri",Font.BOLD,18));
        labelInterfaceInfo.setBounds(15,500,180,25);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        textAreaInterfaceInfo = new JTextArea();
        textAreaInterfaceInfo.setEditable(false);
        textAreaInterfaceInfo.setBorder(new LineBorder(Color.BLACK));
        textAreaInterfaceInfo.setBounds(10,525,260,250);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        label = new JLabel("Packet Info:");
        label.setFont(new Font("Calibri",Font.BOLD,18));
        label.setBounds(305,500,180,25);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        textAreaPacketInfo = new JTextArea();
        textAreaPacketInfo.setEditable(false);
        textAreaPacketInfo.setBorder(new LineBorder(Color.BLACK));
        textAreaPacketInfo.setBounds(300,525,480,250);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        label1 = new JLabel("Hexa Decimal:");
        label1.setFont(new Font("Calibri",Font.BOLD,18));
        label1.setBounds(800,500,180,25);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
        textAreaHexaView = new JTextArea();
        textAreaHexaView.setEditable(false);
//-------------------------------------------------------------------------------------------------------
        textAreaHexaScrollPane = new JScrollPane();
        textAreaHexaScrollPane.setViewportView(textAreaHexaView);
        textAreaHexaScrollPane.setBounds(790,525,725,250);
    }



    private  void interfaceOptionsActionPerformed(){
        if(interfaceOptions.getSelectedItem()!=interfaceOptions.getItemAt(0)){
            textAreaInterfaceInfo.setText(null);
            backEnd.addInterfaceInfo();
            interfaceOptions.setEnabled(false);
            captureButton.setEnabled(true);
            stopButton.setEnabled(false);
            filterOptions.setEnabled(true);

        }
    }

    private  void captureButtonActionPerformed(){
        captureButton.setEnabled(false);
        backEnd.startReadingPackets();
        captureState =true;
        interfaceOptions.setEnabled(false);
        filterOptions.setEnabled(false);
        stopButton.setEnabled(true);
        saveButton.setEnabled(false);
    }
    private  void stopButtonActionPerformed(){
        captureState =false;
        interfaceOptions.setEnabled(true);
        filterOptions.setEnabled(true);
        captureButton.setEnabled(true);
        saveButton.setEnabled(true);
        stopButton.setEnabled(false);
        backEnd.thread.interrupt();
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        Object obj = packetTable.getModel().getValueAt(packetTable.getSelectedRow(), 0);
        if (PacketContents.rowList.get((int) obj)[4] == "TCP") {

            textAreaPacketInfo.setText(" Packet No: " + PacketContents.rowList.get((int) obj)[0]
                    + "\n Seq No: " + PacketContents.rowList.get((int) obj)[10]
                    + "\n Protocol: " + PacketContents.rowList.get((int) obj)[4]
                    + "\n Source IP: " + PacketContents.rowList.get((int) obj)[2]
                    + "\n Dist IP: " + PacketContents.rowList.get((int) obj)[3]
                    + "\n Length: " + PacketContents.rowList.get((int) obj)[1]
                    + "\n Source Port: " + PacketContents.rowList.get((int) obj)[5]
                    + "\n Dist Port: " + PacketContents.rowList.get((int) obj)[6]
                    + "\n Ack: " + PacketContents.rowList.get((int) obj)[7]
                    + "\n Ack No: " + PacketContents.rowList.get((int) obj)[8]
                    + "\n Sequence No: " + PacketContents.rowList.get((int) obj)[10]
                    + "\n Offset: " + PacketContents.rowList.get((int) obj)[11]
                    + "\n Header: " + PacketContents.rowList.get((int) obj)[12]
                    + "\n Data: " + PacketContents.rowList.get((int) obj)[9]
            );

            try {
                textAreaHexaView.setText(customizeHexa(toHexadecimal(textAreaPacketInfo.getText())));
            } catch (UnsupportedEncodingException ex) {
                //
            }
        } else if (PacketContents.rowList.get((int) obj)[4] == "UDP") {
            textAreaPacketInfo.setText(" Packet No: " + PacketContents.rowList.get((int) obj)[0]
                    + "\n Protocol:" + PacketContents.rowList.get((int) obj)[4]
                    + "\n Source IP: " + PacketContents.rowList.get((int) obj)[2]
                    + "\n Dist IP: " + PacketContents.rowList.get((int) obj)[3]
                    + "\n Length: " + PacketContents.rowList.get((int) obj)[1]
                    + "\n Source Port: " + PacketContents.rowList.get((int) obj)[5]
                    + "\n Dist Port: " + PacketContents.rowList.get((int) obj)[6]
                    + "\n Offset: " + PacketContents.rowList.get((int) obj)[8]
                    + "\n Header: " + PacketContents.rowList.get((int) obj)[9]
                    + "\n Data: " + PacketContents.rowList.get((int) obj)[7]
            );

            try {
                textAreaHexaView.setText(customizeHexa(toHexadecimal(textAreaPacketInfo.getText())));
            } catch (UnsupportedEncodingException ex) {
                //
            }

        } else if (PacketContents.rowList.get((int) obj)[4] == "ICMP") {
            textAreaPacketInfo.setText(" Packet No: " + PacketContents.rowList.get((int) obj)[0]
                    + "\n Protocol:" + PacketContents.rowList.get((int) obj)[4]
                    + "\n Source IP: " + PacketContents.rowList.get((int) obj)[2]
                    + "\n Dist IP: " + PacketContents.rowList.get((int) obj)[3]
                    + "\n Length: " + PacketContents.rowList.get((int) obj)[1]
                    + "\n Checksum: " + PacketContents.rowList.get((int) obj)[5]
                    + "\n Header: " + PacketContents.rowList.get((int) obj)[6]
                    + "\n Offset: " + PacketContents.rowList.get((int) obj)[7]
                    + "\n Originate TimeStamp: " + PacketContents.rowList.get((int) obj)[8] + "bits"
                    + "\n Receive TimeStamp: " + PacketContents.rowList.get((int) obj)[9] + "bits"
                    + "\n Transmit TimeStamp: " + PacketContents.rowList.get((int) obj)[10] + "bits"
                    + "\n Data: " + PacketContents.rowList.get((int) obj)[11]
            );

            try {
                textAreaHexaView.setText(
                        customizeHexa(toHexadecimal(textAreaPacketInfo.getText())));
            } catch (UnsupportedEncodingException ex) {
                //
            }
        }
    }

    private void saveButtonActionPerformed(){
        backEnd.savePackets();
    }
    private String toHexadecimal(String text) throws UnsupportedEncodingException {
        byte[] myBytes = text.getBytes("UTF-8");
        return DatatypeConverter.printHexBinary(myBytes);
    }

    private   String customizeHexa(String text) {
        String out;
        out = text.replaceAll("(.{32})", "$1\n");
        return out.replaceAll("..(?!$)", "$0 ");
    }


    public JComponent[] getGuiScreenComponents(){
        return new JComponent[]{bar, packetTableScrollPane,label,labelInterfaceInfo, textAreaInterfaceInfo, textAreaPacketInfo,label1, textAreaHexaScrollPane};
    }
    public JComponent[] getGuiBarComponents(){
        return new JComponent[]{labelForInterFace,interfaceOptions, labelForFilter,filterOptions,captureButton,stopButton,saveButton};
    }
    public void addComponentsToToolBar(JComponent... comp){

        for (int x = 0; x<comp.length; x++){
            bar.add(comp[x]);
        }
    }
    public void addComponentsToScreen(JComponent... comp){

        for (int x = 0; x<comp.length; x++){
            screen.add(comp[x]);
        }
    }
    public void setVisible() {
        screen.setVisible(true);
    }
}


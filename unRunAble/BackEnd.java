package PacketSniffer.unRunAble;

/**-----------------------------------------------------------------------------------------------------------*/
/**The above line defines the block of code which will present a method  (a class if possible) In the final GUI Project*/



/**Importing all import classes in the statics methods(static methods are in the JpcapCaptor class)*/

import PacketSniffer.unRunAble.InterFaces.BackEndInterface;
import PacketSniffer.abstractClasses.SniffThread;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.Packet;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Objects;

import static jpcap.JpcapCaptor.getDeviceList;
public class BackEnd implements BackEndInterface {

//--------------------------------------------------------------------------------------------------------

    /**List Of NetworkInterface for storing interfaces available for networking*/
    private NetworkInterface[] networkInterfacesList;
    /**Integer for storing the number of interface selected dbu user in the gui*/
    private int selectedInterFace;
    /**Thread For Multi Processing*/
    public SniffThread thread;

    /**Class for opening an interface and capturing packets (Methods are static are provided by this class*/
    private  JpcapCaptor Cap;
    /**List for storing the received packets*/
    private  ArrayList<Packet> packetList = new ArrayList<Packet>(10);

    /**Class for storing each non-null packet in the traffic*/
    private  Packet packet;
    /**Class for writing the captured packets*/
    private  Formatter writer;
    /**For the sake of class association and adding data to gui interface*/
    private Graphical gui;

    public BackEnd(Graphical gui){
        this.gui = gui;
        startAddingInfoToGui();
    }


    public void startAddingInfoToGui() {


//-----------------------------------------------------------------------------------------------------------
        /**Getting List Of InterFaces Start*/
        networkInterfacesList = getDeviceList();
        /**Getting List Of InterFaces End*/
//-----------------------------------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------------------------------
        /**Looping InterFaces For Info Start*/
        for (int y = 0; y < networkInterfacesList.length; y++) {
            gui.interfaceOptions.addItem("Number " + (y + 1) + " \t " + networkInterfacesList[y].description);
        }
        /**Looping InterFaces For Info End*/
//-----------------------------------------------------------------------------------------------------------

    }



    public  void startReadingPackets(){


//-----------------------------------------------------------------------------------------------------------
        /**Choosing  Selected InterFace Start*/
        selectedInterFace = Integer.parseInt(gui.interfaceOptions.getSelectedItem().toString().split(" ")[1])-1;
        // Lists from 0 to 7 in real but for GUI start from 1 to 8! 5 Is Wi-Fi in GUI and My case
        //Add selectNumberInRange method here for data validation (must be between 1 and 8 and minus 1 for actual use)
        /**Choosing  Selected InterFace End*/
//-----------------------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------------------------
        /**Capturing Packets from  Selected InterFace Start*/
        // New Instance of A thread class to initiate a multithreaded function in swing without error
        thread = new SniffThread() {
            @Override
            public Object construct() {
                try{
                    Cap = JpcapCaptor.openDevice(networkInterfacesList[selectedInterFace],65535,false,20);

                    if ("UDP".equals(gui.filterOptions.getSelectedItem().toString())) {
                        Cap.setFilter("udp", true);
                    } else if ("TCP".equals(gui.filterOptions.getSelectedItem().toString())) {
                        Cap.setFilter("tcp", true);
                    } else if ("ICMP".equals(gui.filterOptions.getSelectedItem().toString())) {
                        Cap.setFilter("icmp", true);
                    }
                    PacketContents packerReceive = new PacketContents();
                    packerReceive.setGui(gui);
                    while (gui.captureState){
                        Cap.processPacket(1,packerReceive);
                        packet = Cap.getPacket();
                        if (!Objects.isNull(packet)) {
                            packetList.add(packet);
                        }
                    }
                    Cap.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println("Error In Capturing");
                }
                return 0;
            }
            public void finished(){
                this.interrupt();
            }
        };
        thread.start();
        /**Capturing Packets from  Selected InterFace End*/

    }
//-----------------------------------------------------------------------------------------------------------






//-----------------------------------------------------------------------------------------------------------
    public  void addInterfaceInfo(){

        byte[] r = networkInterfacesList[Integer.parseInt(gui.interfaceOptions.getSelectedItem().toString().split(" ")[1])].mac_address;
        String[] macAddress = new String[r.length];
        for (int z = 0; z < r.length;z++) {
            macAddress[z] = Integer.toHexString(r[z] & 0xff);
        }
        gui.textAreaInterfaceInfo.append("\n\n");
        gui.textAreaInterfaceInfo.append(" Interface MacAddress-->");
        for (String macBit: macAddress) {
            if(macBit!=null) {
                if (macBit == macAddress[macAddress.length - 1]) {
                    gui.textAreaInterfaceInfo.append(macBit);
                } else
                    gui.textAreaInterfaceInfo.append(macBit + "-");
            }
        }
        gui.textAreaInterfaceInfo.append("\n");


        /**Listing the addresses of the interfaces*/
        NetworkInterfaceAddress[] net = networkInterfacesList[Integer.parseInt(gui.interfaceOptions.getSelectedItem().toString().split(" ")[1])].addresses;

        if (net.length !=0) { // Checking if the interfaces ae real (definition of real is defined in the else block
            gui.textAreaInterfaceInfo.append(" Interface Address --> " + net[1].address+"\n");
            gui.textAreaInterfaceInfo.append(" Interface Subnet --> " + net[1].subnet+"\n");
            gui.textAreaInterfaceInfo.append(" Interface Broadcast --> " + net[1].broadcast+"\n");
        }
        else {// For connections there are not real (real means which I (Rana Mahad Ahmer) am not familiar with
            gui.textAreaInterfaceInfo.append(" Interface Address --> " + "No Connection"+"\n");
            gui.textAreaInterfaceInfo.append(" Interface Subnet --> " +  "No Connection"+"\n");
            gui.textAreaInterfaceInfo.append(" Interface Broadcast --> " +  "No Connection"+"\n");
        }
    }

//-----------------------------------------------------------------------------------------------------------
    public  void savePackets(){
        try {
            writer = new Formatter("src/PacketSniffer/Captured.txt");
        } catch (FileNotFoundException e) {
            System.exit(0);
        }finally {
            for (Packet pac:packetList) {
                writer.format(pac+"\n");
            }if(writer !=null)
                writer.close();
        }
    }
//-----------------------------------------------------------------------------------------------------------

}

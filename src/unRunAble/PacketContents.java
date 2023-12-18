package PacketSniffer.src.unRunAble;


import jpcap.PacketReceiver;
import jpcap.packet.ICMPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
public class PacketContents implements PacketReceiver {

    public static TCPPacket tcp;
    public static UDPPacket udp;
    public static ICMPPacket icmp;
    public static List<Object[]> rowList = new ArrayList<Object[]>();
    private int packetNo=0;
    private Graphical gui;

    public void setGui(Graphical gui){
        this.gui = gui;
    }

    @Override
    public void receivePacket(Packet packet) {

        if (packet instanceof TCPPacket) {
            tcp = (TCPPacket) packet;
            Object[] row = {packetNo, tcp.length, tcp.src_ip, tcp.dst_ip, "TCP"};
            rowList.add(new Object[]{packetNo, tcp.length, tcp.src_ip, tcp.dst_ip, "TCP", tcp.src_port, tcp.dst_port,
                tcp.ack, tcp.ack_num, tcp.data, tcp.sequence, tcp.offset, tcp.header});
            DefaultTableModel model = (DefaultTableModel) gui.packetTable.getModel();
            model.addRow(row);
            packetNo++;
        } else if (packet instanceof UDPPacket) {
            udp = (UDPPacket) packet;
            Object[] row = {packetNo, udp.length, udp.src_ip, udp.dst_ip, "UDP"};
            rowList.add(new Object[]{packetNo, udp.length, udp.src_ip, udp.dst_ip, "UDP", udp.src_port, udp.dst_port,
                udp.data, udp.offset, udp.header});
            DefaultTableModel model = (DefaultTableModel) gui.packetTable.getModel();
            model.addRow(row);
            packetNo++;
        } else if (packet instanceof ICMPPacket) {
            icmp = (ICMPPacket) packet;
            Object[] row = {packetNo, icmp.length, icmp.src_ip, icmp.dst_ip, "ICMP"};
            rowList.add(new Object[]{packetNo, icmp.length, icmp.src_ip, icmp.dst_ip, "ICMP", icmp.checksum, icmp.header,
                icmp.offset, icmp.orig_timestamp, icmp.recv_timestamp, icmp.trans_timestamp, icmp.data});
            DefaultTableModel model = (DefaultTableModel) gui.packetTable.getModel();
            model.addRow(row);
            packetNo++;
        }
    }
}

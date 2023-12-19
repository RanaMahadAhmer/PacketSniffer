package unRunAble;

import jpcap.packet.ICMPPacket;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class PacketContentsTest {
    PacketContents packetContents;

    @BeforeEach
    void setUp() {
        packetContents = new PacketContents();
    }

    @AfterEach
    void tearDown() {
        PacketContents.rowList = new ArrayList<>();
        packetContents = null;
    }


    @Test
    void testReceiveMultiplePackets() {
        // TCP Packet
        TCPPacket tcpPacket = new TCPPacket(1234, 80, 1000L, 500L, false, true, false, false, true, false, false, false, 0, 0);

        packetContents.receivePacket(tcpPacket);

        // UDP Packet
        UDPPacket udpPacket = new UDPPacket(1234, 5678);

        packetContents.receivePacket(udpPacket);

        // ICMP Packet
        ICMPPacket icmpPacket = new ICMPPacket();
        // Set properties of the ICMP packet as needed for the test
        packetContents.receivePacket(icmpPacket);

        List<Object[]> expectedList = new ArrayList<>(Arrays.asList(
                new Object[]{0, 0, null, null, "TCP", 1234, 80, true, 500, null, 1000, 0, null},
                new Object[]{1, 0, null, null, "UDP", 1234, 5678, null, 0, null},
                new Object[]{2, 0, null, null, "ICMP", 0, null, 0, 0, 0, 0, null}
        ));
        List<Object[]> result = PacketContents.rowList;


        assertEquals(expectedList.toArray().length, result.toArray().length);
        for (int i = 0; i < expectedList.toArray().length; i++) {
            assertEquals(expectedList.get(i).length, result.get(i).length);
            for (int j = 0; j < expectedList.get(i).length; j++) {
                if (expectedList.get(i)[j] != null) {
                    assertEquals(expectedList.get(i)[j].toString(), result.get(i)[j].toString());
                } else {
                    assertEquals(expectedList.get(i)[j], result.get(i)[j]);
                }
            }
        }


    }

    @Test
    void testReceiveUDPPacketWithSpecificValues() {
        // Create a UDPPacket with specific source and destination ports
        UDPPacket udpPacket = new UDPPacket(1234, 5678);
        // Set other properties of the UDP packet as needed for the test
        packetContents.receivePacket(udpPacket);
        Object[] result = PacketContents.rowList.getFirst();


        Object[] expectedList = new Object[]{0, 0, null, null, "UDP", 1234, 5678, null, 0, null};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }

    }

    @Test
    void testReceiveUDPPacketWithNullValues() {
        // Create a UDPPacket with source and destination ports set to 0
        UDPPacket udpPacket = new UDPPacket(0, 0);

        // Set other properties of the UDP packet as needed for the test
        Object[] result = packetContents.receivePacket(udpPacket);

        Object[] expectedList =
                new Object[]{0, 0, null, null, "UDP"};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }

    }

    @Test
    void testReceiveValuedTCPPacketTable() {
        // Create a TCPPacket with specific values
        TCPPacket tcpPacket = new TCPPacket(1234, 80, 1000L, 500L, false, true, false, false, true, false, false, false, 0, 0);

        // Set other properties of the TCP packet as needed for the test
        Object[] result = packetContents.receivePacket(tcpPacket);


        Object[] expectedList =
                new Object[]{0, 0, null, null, "TCP"};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }
    }

    @Test
    void testReceiveValuedTCPPacketTextArea() {
        // Create a TCPPacket with specific values
        TCPPacket tcpPacket = new TCPPacket(1234, 80, 1000L, 500L, false, true, false, false, true, false, false, false, 0, 0);

        // Set other properties of the TCP packet as needed for the test
        packetContents.receivePacket(tcpPacket);
        Object[] result = PacketContents.rowList.getFirst();
        Object[] expectedList =
                new Object[]{0, 0, null, null, "TCP", 1234, 80, true, 500, null, 1000, 0, null};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }
    }

    @Test
    void testReceiveNullTCPPacketTable() {

        TCPPacket tcpPacket = new TCPPacket(0, 0, 0L, 0L, false, true, false, false, true, false, false, false, 0, 0);

        // Set other properties of the TCP packet as needed for the test
        Object[] result = packetContents.receivePacket(tcpPacket);
        Object[] expectedList =
                new Object[]{0, 0, null, null, "TCP"};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }
    }

    @Test
    void testReceiveNullTCPPacketTextArea() {

        TCPPacket tcpPacket = new TCPPacket(0, 0, 0L, 0L, false, true, false, false, true, false, false, false, 0, 0);

        // Set other properties of the TCP packet as needed for the test
        packetContents.receivePacket(tcpPacket);
        Object[] result = PacketContents.rowList.getFirst();

        Object[] expectedList =
                new Object[]{0, 0, null, null, "TCP", 0, 0, true, 0, null, 0, 0, null};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }
    }

    @Test
    void testReceiveICMPPacketTable() {
        ICMPPacket icmpPacket = new ICMPPacket();
        // Set properties of the ICMP packet as needed for the test

        Object[] result = packetContents.receivePacket(icmpPacket);

        Object[] expectedList = new Object[]{0, 0, null, null, "ICMP"};
        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }
    }

    @Test
    void testReceiveICMPPacketTextArea() {
        ICMPPacket icmpPacket = new ICMPPacket();
        // Set properties of the ICMP packet as needed for the test

        packetContents.receivePacket(icmpPacket);

        Object[] result = PacketContents.rowList.getFirst();
        Object[] expectedList = new Object[]{0, 0, null, null, "ICMP", 0, null, 0, 0, 0, 0, null};

        assertEquals(expectedList.length, result.length);
        for (int i = 0; i < expectedList.length; i++) {
            if (expectedList[i] != null) {
                assertEquals(expectedList[i].toString(), result[i].toString());
            } else {
                assertEquals(expectedList[i], result[i]);
            }
        }
    }
}
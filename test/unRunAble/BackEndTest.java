package unRunAble;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unRunAble.InterFaces.FrontEndTestInterface;

import static org.junit.jupiter.api.Assertions.*;

class BackEndTest {

    FrontEndTestInterface mockGui;
    BackEnd backend;

    @BeforeEach
    void setUp() {
        mockGui = new FrontEnd();
        backend = new BackEnd((FrontEnd) mockGui);

    }

    @AfterEach
    void tearDown() {
        mockGui = null;
        backend = null;
    }


    @Test
    void testStartAddingInfoToGuiInterfaceList() {

        mockGui.setBackEnd(backend);
        backend.start();
        // Test if the networkInterfacesList is initialized and the GUI is updated
        assertNotNull(backend.getNetworkInterfacesList());


    }

    @Test
    void testStartAddingInfoToGuiInterfaceListCount() {

        mockGui.setBackEnd(backend);
        backend.start();
        // Test if the networkInterfacesList is initialized and the GUI is updated
        assertTrue(mockGui.getInterfaceListCount() > 0);

    }

    @Test
    void testStartReadingPacketsThread() {
        mockGui.setBackEnd(backend);
        backend.start();

        mockGui.addInterface("Number 1\tMock Interface");
        mockGui.setInterface(2);
        // Simulate starting packet reading

        mockGui.startCapture();

//        // Check if the thread is started
        assertNotNull(backend.thread);
        assertTrue(mockGui.getCaptureState());
    }

    void testStartReadingPacketsCaptureState() {
        mockGui.setBackEnd(backend);
        backend.start();

        mockGui.addInterface("Number 1\tMock Interface");
        mockGui.setInterface(2);
        // Simulate starting packet reading

        mockGui.startCapture();

//        // Check if the capture state is true
        assertTrue(mockGui.getCaptureState());
    }

    @Test
    void testAddInterfaceInfo() {

        mockGui.setBackEnd(backend);
        backend.start();

        // Simulate adding interface info
        mockGui.addInterface("Number 1\tMock Interface");
        mockGui.setInterface(2);
        backend.addInterfaceInfo();

        // Check if the interface info is appended to the text area
        assertNotNull(mockGui.getInterfaceInfo());
    }

    @Test
    void testStopReadingPackets() {

        mockGui.setBackEnd(backend);
        backend.start();
        // Simulate starting packet reading
        mockGui.setInterface(2);
        mockGui.startCapture();

        // Simulate stopping packet reading

        mockGui.stopCapture();


        // Check if the capture state is false
        assertFalse(mockGui.getCaptureState());

    }
}
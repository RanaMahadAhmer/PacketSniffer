package unRunAble;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unRunAble.InterFaces.FrontEndTestInterface;

import static org.junit.jupiter.api.Assertions.*;


class FrontEndTest {
    FrontEndTestInterface frontEnd;

    @BeforeEach
    void setUp() {
        frontEnd = new FrontEnd();
    }

    @AfterEach
    void tearDown() {
        frontEnd = null;
    }

    @Test
    void testInterfaceOptionsInterfaceInfo() {
        // Create a mock BackEnd object for testing
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();

        // Simulate selecting an interface
        frontEnd.addInterface("Mock Interface");
        frontEnd.setInterface(2);


        // Check if interface info is added and components are updated
        assertNotNull(frontEnd.getInterfaceInfo());

    }

    @Test
    void testInterfaceOptionsInterfaceOptionsState() {
        // Create a mock BackEnd object for testing
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();

        // Simulate selecting an interface
        frontEnd.addInterface("Mock Interface");
        frontEnd.setInterface(2);


        // Check if interface info is added and components are updated
        assertFalse(frontEnd.interfaceOptionsState());
    }

    @Test
    void testInterfaceOptionsFilterOptionState() {
        // Create a mock BackEnd object for testing
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();

        // Simulate selecting an interface
        frontEnd.addInterface("Mock Interface");
        frontEnd.setInterface(2);


        // Check if interface info is added and components are updated
        assertTrue(frontEnd.filterOptionsState());
    }


    @Test
    void testCaptureButtonInterfaceOptionState() {
        // Create a mock BackEnd object for testing
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);
        frontEnd.startCapture();

        // Check if packet reading is started and components are updated
        assertFalse(frontEnd.interfaceOptionsState());

    }

    @Test
    void testCaptureButtonFilterOptionState() {
        // Create a mock BackEnd object for testing
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);
        frontEnd.startCapture();

        // Check if packet reading is started and components are updated
        assertFalse(frontEnd.filterOptionsState());
    }


    @Test
    void testStopButtonCaptureSate() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);
        frontEnd.startCapture();

        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Check if packet reading is stopped and components are updated
        assertFalse(frontEnd.getCaptureState());

    }

    @Test
    void testStopButtonInterfaceOptionState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);
        frontEnd.startCapture();

        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Check if packet reading is stopped and components are updated
        assertTrue(frontEnd.interfaceOptionsState());
    }

    @Test
    void testStopButtonFilterOptionState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);
        frontEnd.startCapture();

        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Check if packet reading is stopped and components are updated
        assertTrue(frontEnd.filterOptionsState());
    }


    @Test
    void testSaveButtonCaptureState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);

        frontEnd.startCapture();
        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Simulate clicking the save button
        frontEnd.saveCapture();

        // Check if packet reading is stopped and components are updated
        assertFalse(frontEnd.getCaptureState());
    }

    @Test
    void testSaveButtonInterfaceOptionState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);

        frontEnd.startCapture();
        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Simulate clicking the save button
        frontEnd.saveCapture();

        // Check if packet reading is stopped and components are updated

        assertTrue(frontEnd.interfaceOptionsState());


    }

    @Test
    void testSaveButtonFilterOptionState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);

        frontEnd.startCapture();
        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Simulate clicking the save button
        frontEnd.saveCapture();

        // Check if packet reading is stopped and components are updated
        assertTrue(frontEnd.filterOptionsState());


    }

    @Test
    void testSaveButtonCaptureButtonState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);

        frontEnd.startCapture();
        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Simulate clicking the save button
        frontEnd.saveCapture();

        // Check if packet reading is stopped and components are updated
        assertTrue(frontEnd.captureButtonState());


    }

    @Test
    void testSaveButtonSaveButtonState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);

        frontEnd.startCapture();
        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Simulate clicking the save button
        frontEnd.saveCapture();

        // Check if packet reading is stopped and components are updated
        assertTrue(frontEnd.saveButtonState());
        assertFalse(frontEnd.stopButtonState());
    }

    @Test
    void testSaveButtonStopButtonState() {
        BackEnd mockBackEnd = new BackEnd((FrontEnd) frontEnd);
        frontEnd.setBackEnd(mockBackEnd);
        mockBackEnd.start();
        // Simulate clicking the capture button
        frontEnd.setInterface(2);

        frontEnd.startCapture();
        // Simulate clicking the stop button
        frontEnd.stopCapture();

        // Simulate clicking the save button
        frontEnd.saveCapture();

        // Check if packet reading is stopped and components are updated
        assertFalse(frontEnd.stopButtonState());

    }
}
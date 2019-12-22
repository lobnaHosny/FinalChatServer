package Server;

//import ChatServer.src.Server.Server;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class ServerInitTest {

    Server server; //declaring server


    void runServer(int port){
        server = new Server(port); //initializing new Server on given port
    }


    @Test
    void validPortTest(){

    int[] validPorts = new int[]{ 1, 32768, 65535}; //array of valid ports that server should be able to connect to

    for (int i=0; i<validPorts.length; i++) {
        runServer(validPorts[i]);
        assertEquals(validPorts[i], server.server.getLocalPort());

    }
}


    @Test
    void invalidPortTest(){
        int[] invalidPorts = new int[] {-1, 65536};

        for (int j=0; j<invalidPorts.length; j++){
            runServer(invalidPorts[j]);
            assertNull(server.server);
        }
    }


    @Test
        void busyPortTest() throws IOException, BindException {
        ServerSocket ss = new ServerSocket(8080);
        runServer(8080);
        assertNull(server.server);


    }


}
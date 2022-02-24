import java.net.*;
import java.io.*;

//This is server side
public class Server {

    //Initialize socket and input output streams
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    //Constructor with Port
    public Server(int port) {

        //Starts server and waits for connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            //Takes input from the client socket
            in = new DataInputStream (new BufferedInputStream(socket.getInputStream()));

            String line = "";

            //Reads message from client until "Over" is sent
            while (!line.equalsIgnoreCase("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Closing the connection");

            //Close the connection
            socket.close();
            in.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}

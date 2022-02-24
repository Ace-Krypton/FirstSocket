import java.net.*;
import java.io.*;

//This is client side
public class Client {

    //Initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    //Constructor to put IP address and Port
    public Client(String address, int port) {
        //Establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            //Takes input from terminal
            input = new DataInputStream(System.in);

            //Sends output to the socket
            output = new DataOutputStream(socket.getOutputStream());
        }

        catch (IOException u) {
            u.printStackTrace();
        }

        //String to read message from input
        String line = "";

        //Keep reading until "Over" is input
        while(!line.equalsIgnoreCase("Over")) {
            try {
                line = input.readLine();
                output.writeUTF(line);

            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Close the connection
        try {
            input.close();
            output.close();
            socket.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }
}

import java.io.*;
import java.net.*;
import java.util.*;
import java.time.*;
public class WaldronUDPClient {
    public static void main(String[] args) throws IOException {

        Scanner stdIn = new Scanner(System.in);
        Clock clock = Clock.systemDefaultZone();
        DatagramSocket udpSocket = new DatagramSocket();
        String userInput = null;
        String serverOutput = null;
        boolean keepEntering = true;
        boolean keepGoing = true;
        long startTime;
        long totalTime;
        
        System.out.println("Enter the DNS or IP address where the server program is located");
        InetAddress serverAddress = InetAddress.getByName(stdIn.next());
        
        while(keepGoing){
            System.out.println("Item ID       Item Description");
            System.out.println("00001         New Inspiron 15");
            System.out.println("00002         New Inspiron 17");
            System.out.println("00003         New Inspiron 15R");
            System.out.println("00004         New Inspiron 15z Ultrabook");
            System.out.println("00005         XPS 14 Ultrabook");
            System.out.println("00006         New XPS 12 UltrabookXPS");
        
            System.out.println("Enter the item id you would like to know about. (Example: enter 00001)");
        
            while(keepEntering){
                   userInput = stdIn.next();
                   if (userInput.equals("00001") || userInput.equals("00002") || userInput.equals("00003") || userInput.equals("00004")
                    || userInput.equals("00005") || userInput.equals("00006")) {
                  
                        keepEntering = false;
                   }
                  else {
                     System.out.println("The input you entered is not a valid item id. Please enter a valid item id.");
                  }
            }
            keepEntering = true;
            byte[] buf = userInput.getBytes();
            DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, serverAddress, 5320);
            startTime = clock.millis();
            udpSocket.send(udpPacket);
        
            byte[] buf2 = new byte[256];
            DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
            udpSocket.receive(udpPacket2);
            totalTime = clock.millis() - startTime;
        
            serverOutput = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
            
            System.out.println("");
            System.out.println("Item ID   Item Description         Unit Price  Inventory    RTT of Query");
            System.out.println(serverOutput + "   " + totalTime);
        
            System.out.println("If you would like to see the info of another item enter 'yes' otherwise enter 'no'");
            userInput = stdIn.next();
            
            if (userInput.equalsIgnoreCase("no")){
                keepGoing = false;
            }
        }
        udpSocket.close();
    }
}
import java.io.*;
import java.net.*;
import java.util.*;

public class WaldronUDPServer {
    public static void main(String[] args) throws IOException {
	 
	     CompObject compObject1 = new CompObject("00001", "New Inspiron 15", "$379.99", "157");
        CompObject compObject2 = new CompObject("00002", "New Inspiron 17", "$449.99", "128");
        CompObject compObject3 = new CompObject("00003", "New Inspiron 15R", "$549.99", "202");
        CompObject compObject4 = new CompObject("00004", "New Inspiron 15z Ultrabook", "$749.99", "315");
        CompObject compObject5 = new CompObject("00005", "XPS 14 Ultrabook", "$999.99", "261");
        CompObject compObject6 = new CompObject("00006", "New XPS 12 UltrabookXPS", "$1199.99", "178");
        
        CompObject[] objectList = new CompObject[6];
        objectList[0] = compObject1;
        objectList[1] = compObject2;
        objectList[2] = compObject3;
        objectList[3] = compObject4;
        objectList[4] = compObject5;
        objectList[5] = compObject6;
        
        DatagramSocket udpServerSocket = null;
        BufferedReader in = null;
		  DatagramPacket udpPacket = null, udpPacket2 = null;
		  String fromClient = null, toClient = null;
        boolean morePackets = true;

		  byte[] buf = new byte[256];
	
		  udpServerSocket = new DatagramSocket(5320);
		  	  
        while (morePackets) {
            try {

                // receive UDP packet from client
                udpPacket = new DatagramPacket(buf, buf.length);
                udpServerSocket.receive(udpPacket);

					 fromClient = new String(
					 		udpPacket.getData(), 0, udpPacket.getLength());
                     
					 if (fromClient.equals("00001")){
                     toClient = (objectList[0].getNum() + "    " + objectList[0].getDesc() + "    " + objectList[0].getPrice() + "  " + objectList[0].getInv());
                }
                else if(fromClient.equals("00002")){
                     toClient = (objectList[1].getNum() + "    " + objectList[1].getDesc() + "    " + objectList[1].getPrice() + "  " + objectList[1].getInv());
                }
                else if(fromClient.equals("00003")){
                     toClient = (objectList[2].getNum() + "    " + objectList[2].getDesc() + "    " + objectList[2].getPrice() + "  " + objectList[2].getInv());
					 }
                else if(fromClient.equals("00004")){
                     toClient = (objectList[3].getNum() + "    " + objectList[3].getDesc() + "    " + objectList[3].getPrice() + "  " + objectList[3].getInv());
                }
                else if(fromClient.equals("00005")){
                     toClient = (objectList[4].getNum() + "    " + objectList[4].getDesc() + "    " + objectList[4].getPrice() + "  " + objectList[4].getInv());
                }
                else if(fromClient.equals("00006")){
                     toClient = (objectList[5].getNum() + "    " + objectList[5].getDesc() + "    " + objectList[5].getPrice() + "  " + objectList[5].getInv());
                }
                // get the response
					 //toClient = fromClient.toUpperCase();
					 
					 // send the response to the client at "address" and "port"
                InetAddress address = udpPacket.getAddress();
                int port = udpPacket.getPort();
					 byte[] buf2 = toClient.getBytes();
                udpPacket2 = new DatagramPacket(buf2, buf2.length, address, port);
                udpServerSocket.send(udpPacket2);
					 
            } catch (IOException e) {
                e.printStackTrace();
					 morePackets = false;
            }
        }
		  
        udpServerSocket.close();

    }
}

class CompObject {
    private String idNum, itemDesc, itemPrice, itemInv;
    
    public CompObject(String num, String desc, String price, String inv) {
         this.idNum = num;
         this.itemDesc = desc;
         this.itemPrice = price;
         this.itemInv = inv;
    }
    
    public String getNum() {
         return idNum;
    }
    
    public String getDesc() {
         return itemDesc;
    }
    
    public String getPrice() {
         return itemPrice;
    }
    
    public String getInv() {
         return itemInv;
    }
}

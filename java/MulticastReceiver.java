package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
    final static String INET_ADDR = "224.0.0.3"; // Multicast guruh manzili
    final static int PORT = 8888;                // Port raqami

    public static void main(String[] args) {
        // Guruh manzilini olish
        try {
            InetAddress addr = InetAddress.getByName(INET_ADDR);

            // Multicast socket yaratish
            try (MulticastSocket clientSocket = new MulticastSocket(PORT)) {
                // Guruhga qo‘shilish
                clientSocket.joinGroup(addr);

                System.out.println("Receiver started. Waiting for messages...");

                // Doimiy ravishda xabarlarni qabul qilish
                while (true) {
                    byte[] buf = new byte[256]; // Paket uchun buffer
                    DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);

                    // Xabarni kutish
                    clientSocket.receive(msgPacket);

                    String msg = new String(msgPacket.getData(), 0, msgPacket.getLength());
                    System.out.println("Received msg: " + msg);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

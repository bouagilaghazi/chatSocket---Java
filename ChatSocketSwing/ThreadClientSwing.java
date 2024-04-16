import java.io.DataInputStream;
import java.net.Socket;

public class ThreadClientSwing extends Thread {
    private Socket socketCommunication;
    private ClientSwing clientSwing;

    /**
     * Représente un thread pour gérer les messages du serveur.
     */
    public ThreadClientSwing(Socket socketCommunication, ClientSwing clientSwing) {
        this.socketCommunication = socketCommunication;
        this.clientSwing = clientSwing;
    }

    /**
     * Lit les messages du serveur et les affiche dans la fenêtre de chat.
     */
    public void run() {
        try {
            DataInputStream ins = new DataInputStream(socketCommunication.getInputStream());

            while (true) {
                String message = ins.readUTF();
                clientSwing.appendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

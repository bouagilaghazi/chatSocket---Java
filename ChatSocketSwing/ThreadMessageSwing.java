import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadMessageSwing extends Thread {

    private Socket socketCommunication;
    private ServerSwing serverSwing;

    public ThreadMessageSwing(Socket socketCommunication, ServerSwing serverSwing) {
        this.socketCommunication = socketCommunication;
        this.serverSwing = serverSwing;
    }

    /**
     * Lit les messages du client et les affiche dans le log du serveur.
     */
    public void run() {
        try {
            DataInputStream ins = new DataInputStream(socketCommunication.getInputStream());
            String pseudo = ins.readUTF();

            for (Socket socket : ServerSwing.clients) {
                DataOutputStream outs = new DataOutputStream(socket.getOutputStream());
                String hello = pseudo + " vient de rejoindre le chat";
                outs.writeUTF(hello);
            }

            while (true) {
                String message = ins.readUTF();
                String messagess = pseudo + ": " + message;
                serverSwing.appendLog(messagess);

                // Suppression du client de la liste si message = exit
                if (message.equals("exit")) {
                    ServerSwing.clients.remove(socketCommunication);
                    for (Socket socket : ServerSwing.clients) {
                        DataOutputStream outs = new DataOutputStream(socket.getOutputStream());
                        String out = pseudo + " vient de quitter le chat";
                        outs.writeUTF(out);
                    }
                    break;
                }

                // Envoi du message à tous les clients
                for (Socket socket : ServerSwing.clients) {
                    DataOutputStream outs = new DataOutputStream(socket.getOutputStream());
                    String messages = pseudo + ": " + message;
                    outs.writeUTF(messages);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

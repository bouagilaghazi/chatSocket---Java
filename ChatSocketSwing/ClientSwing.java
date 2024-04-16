import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Représente un client de chat basé sur Swing.
 */
public class ClientSwing extends JFrame {
    private String pseudo ; 
    private JTextField messageField;
    private JTextArea chatArea;
    private DataOutputStream dataOutputStream;

    public ClientSwing() {
        super("Chat Client");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        messageField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);

        try {
            
            Socket socket = new Socket("197.26.128.118",9999);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Demander le pseudo du client
            pseudo = JOptionPane.showInputDialog("Pseudo:");
            dataOutputStream.writeUTF(pseudo);

            // Lancer le thread pour lire les messages du serveur
            ThreadClientSwing threadClient = new ThreadClientSwing(socket, this);
            threadClient.start();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to server", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void sendMessage() {
        try {
            String message = messageField.getText();
            dataOutputStream.writeUTF(message);

            // Suppression du client de la liste si le message est "exit"
            if (message.trim().equalsIgnoreCase("exit")) {
                dataOutputStream.writeUTF(pseudo+" a quitté le chat");
                System.exit(0);
            }

            messageField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    /**
     * Méthode principale.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientSwing().setVisible(true);
            }
        });
    }
}

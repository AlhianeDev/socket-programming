// File Name GreetingServer.java => Server

import java.net.*;

import java.io.*;

public class GreetingServer extends Thread {

    private ServerSocket serverSocket;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public GreetingServer(int port) throws IOException {

        serverSocket = new ServerSocket(port);

        serverSocket.setSoTimeout(100000);

    }

    public void run() {

        System.out.println("Waiting for client on port " +

                serverSocket.getLocalPort() + "...");

        while (true) {

            try {

                Socket server = serverSocket.accept();

                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println("Client says: " + in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                System.out.print("To Client: ");

                String msg = br.readLine();

                out.writeUTF(msg);

                server.close();

            } catch (SocketTimeoutException s) {

                System.out.println("Socket timed out!");

                break;

            } catch (IOException e) {

                e.printStackTrace();

                break;

            }

        }

    }

    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);

        try {

            Thread t = new GreetingServer(port);

            t.start();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
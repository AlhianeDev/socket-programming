// File Name GreetingClient.java => Client

import java.net.*;

import java.io.*;

public class GreetingClient {

    public static void main(String[] args) {

        String serverName = args[0];

        int port = Integer.parseInt(args[1]);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connecting to " + serverName + " on port " + port);

        while (true) {

            try {

                Socket client = new Socket(serverName, port);

                OutputStream outToServer = client.getOutputStream();

                DataOutputStream out = new DataOutputStream(outToServer);

                System.out.print("To Server: ");

                String msg = br.readLine();

                out.writeUTF(msg);

                InputStream inFromServer = client.getInputStream();

                DataInputStream in = new DataInputStream(inFromServer);

                System.out.println("Server says: " + in.readUTF());

                client.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
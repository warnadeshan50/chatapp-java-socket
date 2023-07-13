package lk.ijse.chatapp.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class Handler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private ArrayList<Handler> clients;

    public Handler(Socket socket,ArrayList<Handler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            String msg;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            while ((msg = in.readLine())!= null){
                if(msg.equalsIgnoreCase("exit")){
                    break;
                }
                for (Handler handler :clients){
                    handler.out.println(msg);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
        finally {
            try {
                in.close();
                out.close();
                socket.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import RoboRace.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ab11fl
 */
public class NetworkPort implements Port{

    private Socket sock = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
  public NetworkPort(Socket sock){
      try {
        this.sock = sock;
        InputStream iStream = sock.getInputStream();
        OutputStream oStream = sock.getOutputStream();
        in = new BufferedReader(new InputStreamReader(iStream));
        out = new PrintWriter(new OutputStreamWriter(oStream));
      } catch (IOException ex) {
        Logger.getLogger(NetworkPort.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  //client
  @Override
  public void send(String message) {
    System.out.println("Client Send: ");
    System.err.println(message);
    out.println(message.replaceAll("\n", "") + "\0");
    out.flush();
  }
  
  //client
  private String remain = "";
 @Override
  public String recieve() {
    String tmp = remain;
      try {
        while (true){
          String tmp2 = in.readLine();
          if (!tmp.contains("\0")){
            String[] tmp3 = tmp2.split("\0");
            tmp += tmp3[0];
            remain = tmp3.length != 2 ? "" : tmp3[1];
            break;
          }
          tmp += tmp2;
        }
      } catch (IOException ex) {
        System.err.println("Receive error: Socket does not exist");
        Logger.getLogger(NetworkPort.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println("Client Receive: ");
      System.err.println(tmp);
    return tmp.length() == 0 ? "" : tmp;
  }

  @Override
  public void close() throws IOException {
    in.close();
    out.flush();
    out.close();
    sock.close();
  }
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import RoboRace.*;
import java.io.*;
import java.net.*;
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
        this.sock = new Socket("localhost", 33333);
        InputStream iStream = sock.getInputStream();
        OutputStream oStream = sock.getOutputStream();
        in = new BufferedReader(new InputStreamReader(iStream));
        out = new PrintWriter(new OutputStreamWriter(oStream));
      } catch (IOException ex) {
        Logger.getLogger(NetworkPort.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  //server
  @Override
  public void send(String message) {
    out.println(message);
    out.println("\0");
    out.flush();
  }
  //server
 @Override
  public String recieve() {
    String tmp = "";
      try {
        while (!tmp.contains("\0")){
          String tmp2 = in.readLine();
          if (tmp2.equals("\0"))
            break;
          tmp += tmp2;
        }
      } catch (IOException ex) {
        System.err.println("Receive error: Socket does not exist");
        Logger.getLogger(NetworkPort.class.getName()).log(Level.SEVERE, null, ex);
      }
    return tmp.length() == 0 ? null : tmp;
  }

  @Override
  public void close() throws IOException {
    in.close();
    out.flush();
    out.close();
    sock.close();
  }
  
}

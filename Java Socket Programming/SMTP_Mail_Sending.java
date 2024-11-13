import java.io.*;
import java.time.LocalDateTime;
import javax.net.ssl.*;
import java.util.*;

class AzMail2 {

  private static DataOutputStream dos;
  public static BufferedReader br;

  public static void main(String argv[]) throws Exception {
    String user = "s2010176137@ru.ac.bd";
    String pass = "asdf_asdf";
    
    String username =new String(Base64.getEncoder().encode(user.getBytes()));
    String password =pass; // new String(Base64.getEncoder().encode(pass.getBytes()));
    SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
    dos = new DataOutputStream(s.getOutputStream()); // dos sends data to the server through the SSL socket
    br = new BufferedReader(new InputStreamReader(s.getInputStream()));

    // send function sends this command to the server, and \r\n signifies the end of the line.
    send("EHLO smtp.gmail.com\r\n"); // EHLO is the command to greet the server and indicate that the client is ready to communicate.
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
              System.out.println("SERVER: "+ br.readLine());
    
    send("AUTH LOGIN\r\n"); // Initiates the login process to authenticate the client
              System.out.println("SERVER: "+ br.readLine());

    send(username + "\r\n");
              System.out.println("SERVER: "+ br.readLine());

    send(password + "\r\n");
              System.out.println("SERVER: "+ br.readLine());

    send("MAIL FROM:<asif@ru.ac.bd>\r\n"); // Specifies the sender's email address for the outgoing message
              System.out.println("SERVER: "+ br.readLine());

    send("RCPT TO:<tamim101rahman@gmail.com>\r\n"); // Specifies the recipient’s email address
              System.out.println("SERVER: "+ br.readLine());

    send("DATA\r\n"); // Signals the server that the email content (body) is about to be sent
            System.out.println("SERVER: "+ br.readLine());

    send("FROM: s2010176137@ru.ac.bd\r\n");
    send("TO: tamim101rahman@gmail.com\r\n");
    send("Subject: Email test" + LocalDateTime.now() + "\r\n");
    send("THIS IS A TEST EMAIL. THANK YOU\r\n");
    send(".\r\n"); // indicates the end of the email data
          System.out.println("SERVER: "+ br.readLine());
          
    send("QUIT\r\n"); // Signals the end of the session, closing the connection with the server
    System.out.println("SERVER: "+ br.readLine());
  }

  private static void send(String s) throws Exception {
    dos.writeBytes(s);
    Thread.sleep(1000);
    System.out.println("CLIENT: " + s);
    }
}

package client;
import java.net.*;
import java.io.*;
/*Class name : TCPClient
 * description : This class is used to connect the user to the server and perform queries 
 */
public class TCPClient {
  public static String serverMessageDecoded="";
  public static String serverMessageEncoded="";
  /*Method name : callingClient
   * Description : This method is used to send the encrypted clientMessage to the Server
   */  
  public static String client(String encmsg) throws Exception {
  try{
	  Socket socket=new Socket("localhost",8888);
	  DataInputStream inStream=new DataInputStream(socket.getInputStream());
	  DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
	  outStream.writeUTF(encmsg);
      outStream.flush();
      serverMessageEncoded=inStream.readUTF();
      decodeServerMsg();     
      outStream.close();
      outStream.close();
      socket.close();
  	}catch(Exception e){
  		System.out.println(e);
  	}
  	return serverMessageDecoded;
  }
  /*Method name : decodeServerMsg
   * Description : This method is used to decode encrypted serverMessage.
   */	
  private static void decodeServerMsg() {
		int hashIndex = serverMessageEncoded.lastIndexOf('#');
		int starIndex = serverMessageEncoded.lastIndexOf('*');
		int i=hashIndex+1;
		serverMessageDecoded = serverMessageEncoded.substring(i,starIndex);
	}
}
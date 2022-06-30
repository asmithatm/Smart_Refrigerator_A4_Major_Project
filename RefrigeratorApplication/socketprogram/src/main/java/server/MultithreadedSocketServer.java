package server;
import java.net.*;
import java.sql.Connection;
import java.io.*;
/*Class name : ServerClientThread
 * Description : This class is used to create a client thread 
 */
class ServerClientThread extends Thread {
	  /*Datatype Declaration*/
	  int clientNo;
	  Socket serverClient;
	  OperationsOnTree OpOnN;
	  n_aryTree NTree;
	  Connection con;
	  ServerSocket server;
	  String category="", subcategory="", brand="", itemnameDecoded="", rfid="", status="";
	  
	  /*Parameterized constructor*/
	  ServerClientThread(Socket inSocket, int counter, OperationsOnTree OpOnTree, n_aryTree nTree, Connection conn, ServerSocket serverSock){
	    serverClient = inSocket;
	    clientNo=counter;
	    OpOnN=OpOnTree;
	    NTree=nTree;
	    con=conn;
	    server=serverSock;
	  }
	  
	  /*Method name : closeServer
	   * Description : This method updates the database with latest values and stops the Socket Server.
	   */
	  public String closeServer() throws IOException {
		  DBHandler.insertIntoDB(con, NTree.root);
		  server.close();
		  return "Server Closed";
	  }
	  
	  /*Method name : decodeCliMsg
	   * Description : This method is used to decode the client message and get the category, subcategory, brand and itemname
	   * 				based on the v2.0 protocol.
	   */
	  public void decodeCliMsg(String climsg)
	  {
		  int j=0;
			int hashIndex = climsg.lastIndexOf('#');
			int i=hashIndex+1;
			j= hashIndex+1;
			while(climsg.charAt(j)!='&') {
				j++;
			}
			category=climsg.substring(i,j);
			i=j+1;j++;
			while(climsg.charAt(j)!='&') {
				j++;
			}
			subcategory=climsg.substring(i,j);
			i=j+1;
			j++;
			while(climsg.charAt(j)!='&') {
				j++;
			}
			brand=climsg.substring(i,j);
			i=j+1;
			j++;
			int starIndex3 = climsg.indexOf('*');
			itemnameDecoded = climsg.substring(i,starIndex3);
			i=0;j=0;
	  }
	  
	  /*Method name : decodeRFIDCliMsg
	   * Description : This method is used to decode the encrypted client message to get the RFID tag
	   * 				based on the v2.0 protocol.
	   */
	  public void decodeRFIDCliMsg(String climsg)
	  {
			int hashIndex = climsg.lastIndexOf('#');
			int i=hashIndex+1;
			int AmPindex = climsg.lastIndexOf('&');
			rfid=climsg.substring(i,AmPindex); 	
			int starIndex = climsg.lastIndexOf('*');
			status=climsg.substring(AmPindex+1,starIndex);
	  }
	  
	  /*Method name : run
	   * Description : This method is used to run the server.
	   */
	  public void run(){
		  try{
			  	DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			  	DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			  	String clientMessage="", serverMessage="";
			  	boolean check = true;
			  	/*Until the server is up, it waits for client's requests*/
			  	while(check){
			  		clientMessage=inStream.readUTF();
			  		System.out.println("From Client-" +clientNo +clientMessage);
			  		String climsg= clientMessage;
			  		/*Type of Operation is decoded here*/
			  		String opDecoded = climsg.substring(14,16);
			  		String result="";
			  		/*Based on the type of operation, the request is processed and response is sent back as serverMessage*/
			  		switch(Integer.parseInt(opDecoded)) {
			  			case 1 :  	/*Display all the items in the Refrigerator*/
			  						String resultString = "";
			  						result = "v2.0#R#30#001#01#R#"+OpOnN.searchAll(NTree.root, resultString)+"*";
			  						break;
			  			case 2 :   	/*Get expiry date of a particular item in the Refrigerator*/
			  						decodeCliMsg(climsg);
 		   							result = "v2.0#R#30#001#02#R#"+OpOnN.getExpiryDate(category, subcategory, brand, itemnameDecoded, NTree)+"*";
 		   							break;	        
			  			case 3 :   	/*Search for a particular item in the Refrigerator*/
			  						decodeCliMsg(climsg);	  						
			  						result = "v2.0#R#30#001#03#R#"+OpOnN.searchItem(category, subcategory, brand, itemnameDecoded, NTree)+"*";
			  						break;
			  			case 4 :	/*Get the shelf life report*/
			  						String resultShelfReport = "";
			  						result = "v2.0#R#30#001#04#R#"+OpOnN.shelfLife(NTree.root, resultShelfReport)+"*";
			  						break;
			  			case 5 : 	/*Insert or Delete an item from the refrigerator*/
			  						decodeRFIDCliMsg(climsg);
			  						result = "v2.0#R#30#001#05#R#"+OpOnN.rfidIO(rfid, status, NTree)+"*";
			  						break;
			  			case 6 :	/*Stop the Socket Server i.e Refrigerator Server*/
			  						result = "v2.0#D#30#001#06#R#"+closeServer()+"*";
			  						check = false;
			  						break;
			  		}
			  		serverMessage= result;
			  		outStream.writeUTF(serverMessage);
			  		outStream.flush();   
	      }
		  inStream.close();
	      outStream.close();
	      serverClient.close();
	    }catch(Exception ex){
	      System.out.println(ex);
	    }finally{
	      System.out.println("Client -" + clientNo + " exit!! ");
	    }
	  }
	}

/*Class name : MultithreadedSocketServer
 * Description : This class opens Socket Connection and waits for clients to get binded with the server
 * 				It also maintains n-ary Tree instance.
 */
public class MultithreadedSocketServer {
	/*Method name : main
	 * Description : This method is used to invoke the Socket Server
	 */
	public static void main(String[] args) throws Exception {
	  try{
		  	/*Establish connection with the local repository of the refrigerator*/
		  	ConnectToDatabase connectToDatabase = new ConnectToDatabase();
		  	Connection con = connectToDatabase.connectToDB();
		  	/*n-ary tree is instantiated*/
		  	n_aryTree nAryTree = new n_aryTree();
		  	/*When the server loads for the first time, it fetches the data from the database and stores in the n-ary tree.*/
		  	DBHandler.fetchFromDB(con, nAryTree);
		  	nAryTree.displayTree(nAryTree.root,2);
		  	/*Socket Server is opened at port 8888*/
		  	ServerSocket server=new ServerSocket(8888);
		  	int counter=0;
		  	OperationsOnTree n	= new OperationsOnTree();
		  	System.out.println("Server Started ....");
		  	/*This while loop runs until the server is up*/
		  	while(!server.isClosed()){
		  				counter++;
		  				/*server accepts the client connection request*/
				  		Socket serverClient=server.accept();  
				  		System.out.println(" >> " + "Client No:" + counter + " started!");
				  		/*send  the request to a separate thread*/
				  		ServerClientThread sct = new ServerClientThread(serverClient,counter,n, nAryTree, con, server); 
				  		sct.start();
		  	}
	  }catch(Exception e){
      System.out.println(e);
	  }	  
  }
}
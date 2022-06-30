package server;
import java.sql.*;
import java.util.*;
/*Class name : ConnectToDatabase
 * description : This class is used to setup the connection with the database.
 */
class ConnectToDatabase {
	/*Method name : connectToDB
	 * Description : This method is used to setup the connection with database 
	 */
	public Connection connectToDB() {
	  Connection con = null;
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/refrigeratordb", "root","root@257");        
      } catch (Exception e) {
          e.printStackTrace();
      }
      return con;
	}
}

/*Class name : DBHandler
 * description : This class is used to perform CRUD operations on database.
 */
public class DBHandler {
	/*Method name : fetchFromDB
	 * Description : This method is used to fetch the RFID tags from the database and inserted into n-ary tree
	 */
	public static void fetchFromDB(Connection con, n_aryTree nAryTree) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("select * from contents");
			while(rs.next()) {
				String rfid = rs.getString(2);
				rfid.trim();
				Node newNode = nAryTree.createNode(rfid);
				nAryTree.insertNode(newNode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		  
	}
	/*Method name : deleteFromDB
	 * Description : This method is used to delete contents of the database.
	 */
	public static void deleteFromDB(Connection con) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute("truncate table contents");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*Method name : insertIntoDB
	 * Description : This method is used to insert values into the database.
	 */
	public static void insertIntoDB(Connection con, Node root) {
		Statement stmt;
		try {
			/*It deletes all the existing records in the database and inserts the latest values present in the n-ary tree*/
			deleteFromDB(con);
			/*It traverses the n-ary tree and creates the RFID tags from the tree and inserts them into database*/
			Set<String> ListOfCategories = root.getchildNodeMap().keySet();
			for (String key1 : ListOfCategories) { 
				Node catNode = root.getchildNodeMap().get(key1);
    		
				Set<String> ListOfSubCategories = catNode.getchildNodeMap().keySet();
				for (String key2 : ListOfSubCategories) { 
					Node subcatNode = catNode.getchildNodeMap().get(key2);
        		
					Set<String> ListOfBrandNames = subcatNode.getchildNodeMap().keySet();
					for (String key3 : ListOfBrandNames) { 
						Node brandNameNode = subcatNode.getchildNodeMap().get(key3);
            	
						Set<String> ListOfItemNames = brandNameNode.getchildNodeMap().keySet();
						for (String key4 : ListOfItemNames) { 
							Node itemNameNode = brandNameNode.getchildNodeMap().get(key4);
                		
							String prodName = itemNameNode.getprodName();
                		while(prodName.length()<25) {
                			prodName += '-';
                		}
                		String brandName = itemNameNode.getbrand();
                		while(brandName.length()<25) {
                			brandName += '-';
                		}
                		String rfid = itemNameNode.getMID() + itemNameNode.getTID() + "R" + prodName + itemNameNode.getDOM() + brandName + itemNameNode.getDOE() + itemNameNode.getslNo();
            			stmt = con.createStatement();
            			stmt.execute("insert into contents (itemrfid) values ('"+rfid+"')");
            		}
        		}
    		}
        }                
	  }
	  catch (SQLException e) {
		e.printStackTrace();
	  }
	}
}

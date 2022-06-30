package server;
/*Class name : refrigeratorINOUT
 * description : This class is used to insert or delete a item from the refrigerator
 */
public class refrigeratorINOUT {
	/*Method name : IN_RFID
	 * Description : This method is used to insert an item into the refrigerator
	 */
	public static void IN_RFID(String rfid, n_aryTree tree) {
		Node newNode = tree.createNode(rfid);
		tree.insertNode(newNode);
	}
	/*Method name : OUT_RFID
	 * Description : This method is used to remove a item from refrigerator
	 */
	public static void OUT_RFID(String rfid, n_aryTree tree) {
		tree.deleteNode(rfid);
	}
}
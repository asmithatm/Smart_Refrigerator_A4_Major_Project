package server;
import java.util.Hashtable;
/*Class name : Node
 * description : This class is used to define the Node structure
 */
public class Node {	
		/*Attributes of Node*/
		private String nodeID;
		private String MID;
		private String TID;
		private String slNo;
		private String category;
		private String subCategory;
		private String prodName;
		private String DOM;
		private String brand;
		private String DOE;
		private int leafNodeCnt;
		private int childNodeCnt;
		private Hashtable<String,Node> childNodeMap;		
		/*Setter methods*/
		public void setnodeID(String nodeID) {
			this.nodeID=nodeID;
		}
		public void setMID(String MID) {
			this.MID=MID;
		}
		public void setTID(String TID) {
			this.TID=TID;
		}
		public void setSlNo(String slNo) {
			this.slNo=slNo;
		}
		public void setcategory(String category) {
			this.category=category;
		}
		public void setsubCategory(String subCategory) {
			this.subCategory=subCategory;
		}
		public void setprodName(String prodName) {
			this.prodName=prodName;
		}
		public void setDOM(String DOM) {
			this.DOM=DOM;
		}
		public void setbrand(String brand) {
			this.brand=brand;
		}
		public void setDOE(String DOE) {
			this.DOE=DOE;
		}
		public void setleafNodeCnt(int leafNodeCnt) {
			this.leafNodeCnt = leafNodeCnt;
		}
		public void setchildNodeCnt(int childNodeCnt) {
			this.childNodeCnt = childNodeCnt;
		}
		public void setchildNodeMap(Hashtable<String,Node> childNodeMap) {
			this.childNodeMap=childNodeMap;
		}
		/*Getter methods*/
		public String getnodeID() {
			return nodeID;
		}
		public String getMID() {
			return MID;
		}
		public String getTID() {
			return TID;
		}
		public String getslNo() {
			return slNo;
		}
		public String getcategory() {
			return category;
		}
		public String getsubCategory() {
			return subCategory;
		}
		public String getprodName() {
			return prodName;
		}
		public String getDOM() {
			return DOM;
		}
		public String getbrand() {
			return brand;
		}
		public String getDOE() {
			return DOE;
		}
		public int getleafNodeCnt() {
			return leafNodeCnt;
		}
		public int getchildNodeCnt() {
			return childNodeCnt;
		}
		public Hashtable<String,Node> getchildNodeMap() {
			return childNodeMap;
		}
}
package server;
import java.util.Hashtable;
import java.util.Set;
/* Class name : n_aryTree
 * Description : The implementation of n-ary tree */
public class n_aryTree {
	Node root;
	static int count = 0;
	/*No parameter constructor: this is used to create root node*/
	public n_aryTree() {
		Hashtable<String, Node> htRoot = new Hashtable<>();
		root=new Node();
		root.setnodeID(String.valueOf(count++));
		root.setMID("M555");
		root.setTID("S01");
		root.setSlNo("12345");
		root.setcategory("S");
		root.setsubCategory("01");
		root.setprodName("Double Door Refrigerator");
		root.setDOM("2015-01-14");
		root.setbrand("Samsung");
		root.setDOE("2035-01-14");
		root.setleafNodeCnt(0);
		root.setchildNodeCnt(0);
		root.setchildNodeMap(htRoot);
	}
	/*Method name : createCatNode
	 * Description : This method is used to create a node for category level
	 */
	public Node createCatNode(String cat) {
		Hashtable<String, Node> htCat = new Hashtable<>();
		Node newCatNode = new Node();
		newCatNode.setnodeID(String.valueOf(count++));
		newCatNode.setMID("-");
		newCatNode.setTID("-");
		newCatNode.setSlNo("-");
		newCatNode.setcategory(cat);
		newCatNode.setsubCategory("-");
		newCatNode.setprodName("-");
		newCatNode.setDOM("-");
		newCatNode.setbrand("-");
		newCatNode.setDOE("-");
		newCatNode.setleafNodeCnt(0);
		newCatNode.setchildNodeCnt(0);
		newCatNode.setchildNodeMap(htCat);
		return newCatNode;
	}
	/*Method name : createSubCatNode
	 * Description : This method is used to create a node for subcategory level
	 */
	public Node createSubCatNode(String subCat) {
		Hashtable<String, Node> htSubCat = new Hashtable<>();
		Node newSubCatNode = new Node();
		newSubCatNode.setnodeID(String.valueOf(count++));
		newSubCatNode.setMID("-");
		newSubCatNode.setTID("-");
		newSubCatNode.setSlNo("-");
		newSubCatNode.setcategory("-");
		newSubCatNode.setsubCategory(subCat);
		newSubCatNode.setprodName("-");
		newSubCatNode.setDOM("-");
		newSubCatNode.setbrand("-");
		newSubCatNode.setDOE("-");
		newSubCatNode.setleafNodeCnt(0);
		newSubCatNode.setchildNodeCnt(0);
		newSubCatNode.setchildNodeMap(htSubCat);
		return newSubCatNode;
	}
	/*Method name : createBrandNode
	 * Description : This method is used to create a node for brand level
	 */
	public Node createBrandNode(String brand) {
		Hashtable<String, Node> htBrand = new Hashtable<>();
		Node newBrandNode = new Node();
		newBrandNode.setnodeID(String.valueOf(count++));
		newBrandNode.setMID("-");
		newBrandNode.setTID("-");
		newBrandNode.setSlNo("-");
		newBrandNode.setcategory("-");
		newBrandNode.setsubCategory("-");
		newBrandNode.setprodName("-");
		newBrandNode.setDOM("-");
		newBrandNode.setbrand(brand);
		newBrandNode.setDOE("-");
		newBrandNode.setleafNodeCnt(0);
		newBrandNode.setchildNodeCnt(0);
		newBrandNode.setchildNodeMap(htBrand);
		return newBrandNode;
	}
	/*Method name : createNode
	 * Description : This method is used to create a node for last level based on the category, subcategory, brand and itemname
	 */
	public Node createNode(String rfid) {
		int i=8, j=43;
		Node newNode = new Node();
		String mid = rfid.substring(0,4);
		newNode.setMID(mid);
		String tid = rfid.substring(4,7);
		newNode.setTID(tid);
		String cat = tid.substring(0,1);
		newNode.setcategory(cat);
		String subcat = tid.substring(1,3);
		newNode.setsubCategory(subcat);
		while(i<32 && rfid.charAt(i)!='-') {
			i++;
		}
		String prodName = rfid.substring(8,i);
		newNode.setprodName(prodName);
		String DOM = rfid.substring(33,43);
		newNode.setDOM(DOM);
		while(j<76 && rfid.charAt(j)!='-') {
			j++;
		}
		String brand = rfid.substring(43,j);
		newNode.setbrand(brand);
		String DOE = rfid.substring(68,78);
		newNode.setDOE(DOE);
		String Slno = rfid.substring(78,83);
		newNode.setSlNo(Slno);
		newNode.setleafNodeCnt(0);
		newNode.setchildNodeCnt(0);
		return newNode;
	}
	/*Method name : createNode
	 * Description : This method is used insert an item into the n-ary tree
	 */	
	public void insertNode(Node newNode) {
		String catOfNewNode = newNode.getcategory();
		String subCatOfNewNode = newNode.getsubCategory();
		String brandOfNewNode = newNode.getbrand();
		Node catNode, subCatNode, brandNode;
			/*If the desired Category is already present in the tree, then we get it. 
			 * Else we create a new Category Node and append it to the root node
			 */
			if(root.getchildNodeMap().containsKey(catOfNewNode)) {
				catNode = root.getchildNodeMap().get(catOfNewNode);
			}
			else {
				catNode = createCatNode(catOfNewNode);
				root.setchildNodeCnt(root.getchildNodeCnt()+1);
				root.getchildNodeMap().put(catOfNewNode,catNode);				
				Set<String> setOfKeys = root.getchildNodeMap().keySet();
				for (String key : setOfKeys) {
		            root.getchildNodeMap().get(key).setleafNodeCnt(root.getchildNodeCnt());   
		        }
			}
			/*If the desired Sub Category is already present in the tree, then we get it. 
			 * Else we create a new Sub Category Node and append it to the Category Node
			 */
			if(catNode.getchildNodeMap().containsKey(subCatOfNewNode)) {
				subCatNode = catNode.getchildNodeMap().get(subCatOfNewNode);
			}
			else {
				subCatNode = createSubCatNode(subCatOfNewNode);
				catNode.setchildNodeCnt(catNode.getchildNodeCnt()+1);
				catNode.getchildNodeMap().put(subCatOfNewNode,subCatNode);				
				Set<String> setOfKeys = catNode.getchildNodeMap().keySet();
				for (String key : setOfKeys) {
		            catNode.getchildNodeMap().get(key).setleafNodeCnt(catNode.getchildNodeCnt());   
		        }
			}	
			/*If the desired Brand is already present in the tree, then we get it. 
			 * Else we create a new Brand Node and append it to the Sub Category Node
			 */
			if(subCatNode.getchildNodeMap().containsKey(brandOfNewNode)) {
				brandNode = subCatNode.getchildNodeMap().get(brandOfNewNode);
			}
			else {
				brandNode = createSubCatNode(brandOfNewNode);
				subCatNode.setchildNodeCnt(subCatNode.getchildNodeCnt()+1);
				subCatNode.getchildNodeMap().put(brandOfNewNode,brandNode);				
				Set<String> setOfKeys = subCatNode.getchildNodeMap().keySet();
				for (String key : setOfKeys) {
					subCatNode.getchildNodeMap().get(key).setleafNodeCnt(subCatNode.getchildNodeCnt());   
		        }
			}
			brandNode.getchildNodeMap().put(newNode.getprodName(), newNode);
			brandNode.setchildNodeCnt(brandNode.getchildNodeCnt()+1);
			Set<String> setOfKeys = brandNode.getchildNodeMap().keySet();
			for (String key : setOfKeys) {
				brandNode.getchildNodeMap().get(key).setleafNodeCnt(brandNode.getchildNodeCnt());   
	        }
	}


	/*Method name : displayTree
	 * Description : This method is used to display the n_ary tree
	 */		
	public void displayTree(Node node,int level) {
		if(node.getchildNodeMap() == null) {
			return;
		}
		if(level==2) {
			System.out.println(node.getprodName());
		}
		Set<String> setOfKeys = node.getchildNodeMap().keySet();
		for (String key : setOfKeys) {
				System.out.println(" ");
				double i = level-1;
				for(double j=0; j<i;j++) {
					System.out.print("\t");
				}
				System.out.println("|");
				for(double j=0; j<i-1;j++) {
					System.out.print("\t"+"|");
				}
				System.out.print("\t");
			    System.out.print("+-------");
	            System.out.println("Level-"+level+" : " + key); 
	            Node nextNode = node.getchildNodeMap().get(key);
	            displayTree(nextNode,level+1);
        }
	}

	/*Method name : deleteNode
	 * Description : This method removes a node from the n_ary tree
	 */	
	public void deleteNode(String rfidToBeDeleted) {
		Node catNode, subcatNode, brandNode, itemNode;
		int i=8, j=43;
		String mid = rfidToBeDeleted.substring(0,4);		
		String tid = rfidToBeDeleted.substring(4,7);
		String cat = tid.substring(0,1);
		String subcat = tid.substring(1,3);
		while(i<32 && rfidToBeDeleted.charAt(i)!='-') {
			i++;
		}
		String prodName = rfidToBeDeleted.substring(8,i);		
		String DOM = rfidToBeDeleted.substring(33,43);		
		while(j<76 && rfidToBeDeleted.charAt(j)!='-') {
			j++;
		}
		String brand = rfidToBeDeleted.substring(43,j);
		String DOE = rfidToBeDeleted.substring(68,78);
		String Slno = rfidToBeDeleted.substring(78,83);	
		
		if(root.getchildNodeMap().containsKey(cat)) {
			catNode = root.getchildNodeMap().get(cat);
			
			if(catNode.getchildNodeMap().containsKey(subcat)) {
				subcatNode = catNode.getchildNodeMap().get(subcat);
				
				if(subcatNode.getchildNodeMap().containsKey(brand)) {
					brandNode = subcatNode.getchildNodeMap().get(brand);
					
					if(brandNode.getchildNodeMap().containsKey(prodName) && brandNode.getchildNodeMap().get(prodName).getslNo().equals(Slno)) {
						itemNode = brandNode.getchildNodeMap().get(prodName);
						brandNode.getchildNodeMap().remove(prodName);
						brandNode.setchildNodeCnt(brandNode.getchildNodeCnt()-1);
						Set<String> setOfKeys = brandNode.getchildNodeMap().keySet();
						for (String key : setOfKeys) {
							brandNode.getchildNodeMap().get(key).setleafNodeCnt(brandNode.getchildNodeCnt());   
				        }
						
						 if(brandNode.getchildNodeCnt() == 0) { 
							 if(brandNode.getleafNodeCnt()==1) {
						  		if(subcatNode.getleafNodeCnt() ==1) {
						  			root.getchildNodeMap().remove(cat);
						  			root.setchildNodeCnt(root.getchildNodeCnt()-1);
						  			Set<String> setOfKeys2 = root.getchildNodeMap().keySet();
									for (String key : setOfKeys2) {
										root.getchildNodeMap().get(key).setleafNodeCnt(root.getchildNodeCnt());   
				        			}
								}
						   		else {
						   		catNode.getchildNodeMap().remove(subcat);
						   		catNode.setchildNodeCnt(catNode.getchildNodeCnt()-1);
						  			Set<String> setOfKeys3 = catNode.getchildNodeMap().keySet();
									for (String key : setOfKeys3) {
										catNode.getchildNodeMap().get(key).setleafNodeCnt(catNode.getchildNodeCnt());   
				        			}
						       }	
						  } else {
						  		subcatNode.getchildNodeMap().remove(brand);
						  		subcatNode.setchildNodeCnt(subcatNode.getchildNodeCnt()-1);
						  			Set<String> setOfKeys4 = subcatNode.getchildNodeMap().keySet();
									for (String key : setOfKeys4) {
										subcatNode.getchildNodeMap().get(key).setleafNodeCnt(subcatNode.getchildNodeCnt());   
				        			}
						  	} 
						}
					  }
					}
				}
			}
		}	
}
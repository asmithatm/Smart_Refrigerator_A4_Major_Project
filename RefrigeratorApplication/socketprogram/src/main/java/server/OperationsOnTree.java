package server;
import java.text.ParseException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
/*Class name : OperationOnTree
 * description : This class is used to perform operations on n_ary tree based on clients request
 */
public class OperationsOnTree {
	/*Method name : searchAll
	 * Description : This method is used to fetch all the items present in the refrigerator
	 */
	public String searchAll(Node node, String result) {
		if(node.getchildNodeMap() != null) {
			Set<String> setOfKeys = node.getchildNodeMap().keySet();			
	        for (String key : setOfKeys) {
	        			Node nextNode = node.getchildNodeMap().get(key);
	        			if(nextNode.getchildNodeCnt() == 0) {
		        			result += " <br> "+nextNode.getprodName();
	        			}
		        		result = searchAll(nextNode, result);
	        }		
        }			
		return result;
	}
	/*Method name : getExpiryDate
	 * Description : This method is used to fetch the expiry date of a particular item in the refrigerator
	 */
	public String getExpiryDate(String category,String subcategory,String brand,String itemname, n_aryTree tree) {
		String returnStatement="Item is not Found in the Refrigerator";
		if(tree.root.getchildNodeMap().containsKey(category) && tree.root.getchildNodeCnt()!=0) 
		{
			Node cat =tree.root.getchildNodeMap().get(category);
			if(cat.getchildNodeMap().containsKey(subcategory) && cat.getchildNodeCnt()!=0) 
			{
				Node subcat =cat.getchildNodeMap().get(subcategory);
					if(subcat.getchildNodeMap().containsKey(brand)) 
					{
						Node brandNode = subcat.getchildNodeMap().get(brand);
						if(brandNode.getchildNodeMap().containsKey(itemname)) 
						{
							Node childNode = brandNode.getchildNodeMap().get(itemname);
							returnStatement = "Expiry Date of "+itemname+" is "+childNode.getDOE();
		        			return returnStatement;
		        		}
					}
					else {
						Set<String> setOfKeys = subcat.getchildNodeMap().keySet();
						for (String key : setOfKeys) 
						{
				        		Node brandNode = subcat.getchildNodeMap().get(key);
				        		if(brandNode.getchildNodeMap().containsKey(itemname)) 
				        		{
				        			Node childNode = brandNode.getchildNodeMap().get(itemname);
									returnStatement = "Expiry Date of "+itemname+" is "+childNode.getDOE();
				        			return returnStatement;
				        		}
				        }
					}
			}
		}
		return returnStatement;	
	}
	/*Method name : searchItem
	 * Description : This method is used to check if a particular item is present in the refrigerator
	 */
	public String searchItem(String category,String subcategory,String brand,String itemname, n_aryTree tree) {
		String returnStatement="Item Not Found";		
		if(tree.root.getchildNodeMap().containsKey(category) && tree.root.getchildNodeCnt()!=0) 
		{
			Node cat =tree.root.getchildNodeMap().get(category);
			if(cat.getchildNodeMap().containsKey(subcategory) && cat.getchildNodeCnt()!=0) 
			{
				Node subcat =cat.getchildNodeMap().get(subcategory);
					if(subcat.getchildNodeMap().containsKey(brand)) 
					{
						Node brandNode = subcat.getchildNodeMap().get(brand);
						if(brandNode.getchildNodeMap().containsKey(itemname)) 
						{
							returnStatement = "Item : "+itemname+" Found";
		        			return returnStatement;
		        		} else {
		        			returnStatement = "Item : "+itemname+" Not Found. <br> "
		        								+"Alternative Product Suggestion with same Brand Name : <br> ";
		        			Set<String> setOfKeys = brandNode.getchildNodeMap().keySet();
							for (String key : setOfKeys) 
							{
								returnStatement += key + "<br>";
							}
							return returnStatement;
		        		}
					}
					else {
						Set<String> setOfKeys = subcat.getchildNodeMap().keySet();
						for (String key : setOfKeys) 
						{
								Node brandNode = subcat.getchildNodeMap().get(key);
				        		if(brandNode.getchildNodeMap().containsKey(itemname)) 
				        		{
				        			returnStatement = "Item : "+itemname+" Found of the Brand : "+key+"<br>";
				        			return returnStatement;
				        		}
				        }
						returnStatement = "Item : "+itemname+" Not Found. <br> "
											+ "Alternative Product Suggestion Of Different Brand Name : <br> ";
						Set<String> setOfKeys2 = subcat.getchildNodeMap().keySet();
						for (String key2 : setOfKeys2) 
						{
							Node brandNode = subcat.getchildNodeMap().get(key2);
							Set<String> setOfKeys3 = brandNode.getchildNodeMap().keySet();
							for (String key3 : setOfKeys3) 
							{
								returnStatement += key3 + " Of "+ key2 +" Brand <br>";
							}
						}
						return returnStatement;
					}
			}
		}
		return returnStatement;	
	}
	/*Method name : shelfLife
	 * Description : This method is used to fetch all the products in present in the refrigerator which is likely to expire
	 */
	public String shelfLife(Node node, String resultx) throws ParseException {
		if(node.getchildNodeMap() != null) 
		{
			Set<String> setOfKeys = node.getchildNodeMap().keySet();			
	        for (String key : setOfKeys) 
	        {
	        			Node nextNode = node.getchildNodeMap().get(key);
		        		if(nextNode.getchildNodeCnt() == 0) 
		        		{
		        			String expiryDate = nextNode.getDOE();
		        			LocalDate dateOfexpiry = LocalDate.parse(expiryDate);	        			
		        			LocalDate todayDate = LocalDate.now();
		        			LocalDate dateAfter, dateBefore;		        			
		        			if(dateOfexpiry.compareTo(todayDate)<=0)
		        			{
		        				resultx += "<br> EXPIRED PRODUCTS : "+nextNode.getprodName();
		        			} else {
		        				dateAfter = dateOfexpiry;
		        				dateBefore = todayDate;
		        				long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
			        			if(noOfDaysBetween <= 30) {
			        				resultx += "<br> Likely To Expire : "+nextNode.getprodName();
			        			}
		        			}
		        		}
		        		resultx = shelfLife(nextNode, resultx);
	        }		
        }					
        return resultx;
	}
	/*Method name : rfidIO
	 * Description : This method is used to insert or delete an item from the refrigerator
	 */	
	public String rfidIO(String rfid, String input,n_aryTree naryTree) {
		String result="";
	  	if(input.equals("in")) {
	  		refrigeratorINOUT.IN_RFID(rfid,naryTree);
	  		naryTree.displayTree(naryTree.root,2);
	  		result="Successfuly Inserted";
	  	}
	  	else if(input.equals("out")) {
	  		refrigeratorINOUT.OUT_RFID(rfid,naryTree);
	  		naryTree.displayTree(naryTree.root,2);
	  		result="Successfuly Deleted";
	  	}
	  return result;	
	}
}
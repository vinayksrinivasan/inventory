package inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class InventoryManagement {
	HashMap <String, RecordDefinition> records = new HashMap<String, RecordDefinition>();
	double profit=0;
	double fromPreviousProfit=0;
	
	public void create(String itemName, double costPrice, double sellingPrice) {
		if (!records.containsKey(itemName)) {
			RecordDefinition rc = new RecordDefinition(costPrice, sellingPrice);
			records.put(itemName, rc);
		}
		System.out.println("create itemName:"+itemName+" costPrice:"+costPrice+" sellingPrice:"+sellingPrice);
	}
	
	public boolean delete(String itemName) {
		System.out.println("delete itemName:"+itemName);
		
		if (records.containsKey(itemName)) {
			RecordDefinition rdLocal = records.get(itemName);
			profit -= (rdLocal.getBoughtAt()*rdLocal.getAvailableQty());  //- costPrice of the deleted items
			records.remove(itemName);
		}
		return false;
	}
	
	public void updateBuy(String itemName, int quantity) {
		System.out.println("updateBuy itemName:"+itemName+" quantity:"+quantity);
		if (records.containsKey(itemName)) {
			RecordDefinition rdLocal = records.get(itemName);
			rdLocal.setAvailableQty(rdLocal.getAvailableQty()+quantity);
			records.replace(itemName, rdLocal);
		}
	}
	
	public void updateSell(String itemName, int quantity) {
		System.out.println("updateSell itemName:"+itemName+" quantity:"+quantity);
		if (records.containsKey(itemName)) {
			RecordDefinition rdLocal = records.get(itemName);
			rdLocal.setAvailableQty(rdLocal.getAvailableQty()-quantity);
			records.replace(itemName, rdLocal);
			profit += ((rdLocal.getSoldAt() - rdLocal.getBoughtAt()) * quantity); // summation (sellingPrice-costPrice) of the sold items multiplied by no. of items sold

		}
	}
	
	public void report() {
		System.out.println("Item Name 	Bought At    	Sold At       	AvailableQty    	Value");
		System.out.println("--------- 	---------    	-------       	-----------     	-------");
		double totalValue=0;
		Map<String, RecordDefinition> map = new TreeMap<String, RecordDefinition>(records); //to sort the hashmap
		for (String key : map.keySet()) {
			RecordDefinition rdLocal = records.get(key);
			System.out.println(key+"      		"+rdLocal.getBoughtAt()+"   		"+rdLocal.getSoldAt()+"  		"+rdLocal.getAvailableQty()+"   		"+(rdLocal.getBoughtAt()*rdLocal.getAvailableQty())+"\n");
			totalValue+=(rdLocal.getBoughtAt()*rdLocal.getAvailableQty());
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Total value                                                     	"+String.format( "%.2f", totalValue ));
		System.out.println("Profit since previous report                                      	"+String.format( "%.2f", (profit-fromPreviousProfit)));
		fromPreviousProfit = profit;
	}
	
	
	//Part b implementation
	public void updateSellPrice(String itemName, double sellingPrice) {
		System.out.println("updateSellPrice itemName:"+itemName+" sellingPrice:"+sellingPrice);
		if (records.containsKey(itemName)) {
			RecordDefinition rdLocal = records.get(itemName);
			rdLocal.setSoldAt(sellingPrice);
			records.replace(itemName, rdLocal);
		}
	}
	
	//To make the testing task simple we have stored the input file in a txt file, create a data structure based on txt file
	public void readInputFile(String filename)
	{
		BufferedReader br;
		try {
		    br = new BufferedReader(new FileReader(filename));
		    try {
		        String x;
		        while ( (x = br.readLine()) != null ) {

		            String[] record = x.split(" ");
		            switch (record[0])
		    		{
		    		     case "Create":
		    		     case "create":
		    		    	 this.create(record[1], Double.parseDouble(record[2]), Double.parseDouble(record[3]));
		    		    	 break;
		    		     case "Delete":
		    		     case "delete":
		    		    	 this.delete(record[1]);
		    		    	 break;
		    		     case "UpdateBuy":
		    		     case "updateBuy":
		    		    	 this.updateBuy(record[1], Integer.parseInt(record[2]));
		    		    	 break;
		    		     case "UpdateSell":
		    		     case "updateSell":
		    		    	 this.updateSell(record[1], Integer.parseInt(record[2]));
		    		    	 break;
		    		     case "Report":
		    		     case "report":
		    		    	 this.report();
		    		    	 break;
		    		     default:   ;
		    		}
		        }
		    }
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		InventoryManagement im = new InventoryManagement();
		if (true) {
			Scanner input = new Scanner(System.in);
	        while (input.hasNextLine()){
	        	String[] record = input.nextLine().split(" ");
	        	if (record[0].equals("#")) {
	        		input.close(); //to reduce memory leak
	        		System.exit(0);
	        	}
	        	switch (record[0])
	    		{
	    		     case "Create":
	    		     case "create":
	    		    	 im.create(record[1], Double.parseDouble(record[2]), Double.parseDouble(record[3]));
	    		    	 break;
	    		     case "Delete":
	    		     case "delete":
	    		    	 im.delete(record[1]);
	    		    	 break;
	    		     case "UpdateBuy":
	    		     case "updateBuy":
	    		    	 im.updateBuy(record[1], Integer.parseInt(record[2]));
	    		    	 break;
	    		     case "UpdateSell":
	    		     case "updateSell":
	    		    	 im.updateSell(record[1], Integer.parseInt(record[2]));
	    		    	 break;
	    		     case "Report":
	    		     case "report":
	    		    	 im.report();
	    		    	 break;
	    		     default:   ;
	    		}
	        }
		}else {//remove the if statement to enable input - right now unreachable code easy way to test
			im.readInputFile("C:\\Project\\src\\inventory\\inputfile.txt");
		}
		
	}
}

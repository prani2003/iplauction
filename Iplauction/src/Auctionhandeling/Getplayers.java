package Auctionhandeling;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import screens.AuctionDisplay;
import screens.playerlist;
import screens.popup;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import org.bson.Document;


public class Getplayers {

  static LinkedList<ArrayList<String>> linklist = new LinkedList<ArrayList<String>>();
  static int i = 0 ;
  static int j = 0 ;
    static final String url ="mongodb+srv://Singh:0T4px6E8Of7YIisU@singh.rdxxovq.mongodb.net/?retryWrites=true&w=majority"; 
    static  MongoClient mongoClient =  MongoClients.create(url);
     //Func.print("Connected Successfully!!!!!!!!!");
    static  MongoDatabase db = mongoClient.getDatabase("IPLAuction");
   
    public static void getplayer()
    {
        db.getCollection("playerList");
        MongoCollection<Document> collection = db.getCollection("playerList");

        //Retrieving the documents
        FindIterable<Document> iterDoc = collection.find();
        Iterator<Document> it = iterDoc.iterator();
        while (it.hasNext()) {
          ArrayList<String> list = new ArrayList<String>();
        Document data =  it.next();
       String name=(String)data.get("Name");
       String age=(String)data.get("Age");
       String country=(String)data.get("Country");
       String specialism=(String)data.get("Specialism");
       String batting=(String)data.get("Batting");
       String bowling=(String)data.get("Bowling");
       String matches=(String)data.get("No of Ipl matches");
       String cua=(String)data.get("C/U/A");
       String  bp=(String)data.get("Base Price"); 
       list.add(name);
       list.add(age);
       list.add(country);
       list.add(specialism);
       list.add(batting);
       list.add(bowling);
       list.add(matches);
       list.add(cua);
       list.add(bp); 
       linklist.add(list);
       System.out.println(); 
        
      }

    }
    public static void get_next()
    {
     ArrayList<String> abc= linklist.get(i);
      i = i+1; 
      if(i<0)
      AuctionDisplay.enter_auction(abc);
      else 
      popup.popup_sreen("Auction finished");
    }


    public static void get_one()
    {
      ArrayList<String> abc= linklist.get(j);
       j = j+1;
       if(j>=linklist.size())
       {
        j = linklist.size()-1;
        playerlist.player_list(linklist.get(j));
        popup.popup_sreen("No more Players avaliable");
        return;
       }
       ;
       playerlist.player_list(abc);
    }
    public static void get_previous()
    {
       j = j-1; 
       if(j<0)
       {
          j = 0 ;
          ArrayList<String> abc= linklist.get(0);
          playerlist.player_list(abc);
          popup.popup_sreen("End of List");
          return;
       } 
       else 
       {
          
          ArrayList<String> abc= linklist.get(j);
          playerlist.player_list(abc);

       }
       
    }

    public static void delete_this()
    { j = j -1; 
      linklist.remove(j);
      try {
        Thread.sleep(1500);   
      }
      catch (InterruptedException ex)
      {
          ex.printStackTrace();
      }

      get_one();
      popup.popup_sreen("Player Deleted Successfully");

    }
    
}

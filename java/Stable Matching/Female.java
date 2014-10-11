package stablematching;

import java.util.ArrayList;

public class Female extends Person
{
    private int myDateRank;
    private String myRejectedDate;
    
    Female(String n, ArrayList<String> p)
    {
        super(n, p);
    }
    
    public boolean isBetter(String newMan)
    {
        if(myDate == null) // no one will get rejected
        {
            myDate = newMan;
            myDateRank = preferences.indexOf(newMan);
            
            if(myDateRank > k)
                k = myDateRank;
            
            return false;
        }
        
        int newManRank = preferences.indexOf(newMan);
        
        if(newManRank < myDateRank) // old date is rejected
        {
            myRejectedDate = myDate;
            myDate = newMan;
            myDateRank = newManRank;
            
            if(myDateRank > k)
                k = myDateRank;
        }
        else
            myRejectedDate = newMan; // new date is rejected
  
        return true;
    }
    
    public int getMyDateRank()
    {
        return myDateRank;
    }
    
    public String getRejectedDate()
    {
        return myRejectedDate;
    }
}

package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.BookingModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PriceCalculator {

    /* This method calculates the total price of a specific booking, b. It also takes two integers,
    * dailyPriceOfMotorhomeModel and extraPrice, representing the basic price of a motorhome and the total daily
    * price of all extra items for a booking. It then calculates the total   */
    public double getPriceOfContract(int dailyPriceOfMotorhomeModel, BookingModel b, int extraPrice){
        double contractPrice = (dailyPriceOfMotorhomeModel * seasonMultiplier(b) + extraPrice) * b.getDurationInDays();
        contractPrice = contractPrice*100;   //These three lines
        contractPrice = (int) contractPrice; //rounds contractPrice
        contractPrice = contractPrice /100;  //to at most two decimals
        return contractPrice;
    }

    /* This method takes an ArrayList, items, with ArrayLists with three elements in them. Those elements are:
    * name of item, amount of items, and daily price of items. The 'items'-ArrayList represent all the extra
    * items attached to a booking, while each ArrayList inside of it represents a single type of item. The
    * method then iterates over 'items' with a for-each loop, calculating the total price of all amounts of
    * that specific item adding them together as it goes. Finally the method returns the total daily price of
    * all items. */
    public int getDailyPriceOfExtraItems(ArrayList<ArrayList<String>> items){
        int extrasDailyPrice = 0;
        for(ArrayList<String> arr : items){
            extrasDailyPrice += Integer.parseInt(arr.get(1)) * Integer.parseInt(arr.get(2));
        }
        return extrasDailyPrice;
    }

    /* This method calculates the multiplier for the price according to season. In high seasons,
    * June, July, and August, the method returns 1.6, which corresponds to a 60% price increase.
    * In low season, November, December, January, and February, the method returns 1.0, which
    * corresponds to the normal price without multipliers. The rest of the year, March, April,
    * May, September, October, the method returns 1.3, corresponding to a 30% price increase. */
    public double seasonMultiplier(BookingModel b){
        int startMonth = Integer.parseInt(b.getStartDate().substring(5, 7));
        int endMonth = Integer.parseInt(b.getEndDate().substring(5, 7));
        if(startMonth >= 6 && startMonth <= 8 || endMonth >= 6 && endMonth <= 8){
            return 1.6;
        }else if((startMonth < 3 || startMonth >= 11) && (endMonth < 3 || endMonth >= 11)){
            return 1.0;
        }else{
            return 1.3;
        }
    }
}
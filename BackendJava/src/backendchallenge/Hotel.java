//A simple Hotel Class which Gets the data from various Sources in JSON (like makemytrip etc) and stores in a db
//Here for simplicity we take Two sources from where we are getting the data and store in two Hash Maps.
/*
The Design Used here is Like this.
Hotel Class is the main class which can have the UI and calls the backend class.
This calls the Provider class to get the data from different Providers.

We get the JSON Data from the Different Providers in Provider Class.
The JSON Data which we get is formatted according to our need (Our DB fields)

The MyHotel class is what we use,So all the JSON Data which we get needs to get formated and set to this object type.
So whatever data we get its get transformed to MyHotel type object.

Now we override equals method in MyHotel class and check accordingly whether the two objects are pointing to similar hotel or not.
Even though some Data is different in the two hotels.

 */
/**
 * Created by ayseth on 04/08/16.
 */
package backendchallenge;

import java.util.ArrayList;
import java.util.List;


public class Hotel {


    public static void main(String args[]){

        Provider provider=new Provider();
        Provider provider1=new Provider();
        Provider provider2= new Provider();
        provider.setProviderId("1");
        provider.setProviderName("Hotel.com");
        provider.setRestURL("http://hotel.com/gethotels");

        provider1.setProviderId("2");
        provider1.setProviderName("Makemytrip");
        provider1.setRestURL("http://makemytrip.com/gethotels");

        provider2.setProviderId("3");
        provider2.setProviderName("Airbnb");
        provider2.setRestURL("http://airbnb.com/gethotels");

        List<MyHotel> hotellist1 = new ArrayList<MyHotel>();
        List<MyHotel> hotellist2 = new ArrayList<MyHotel>();
        List<MyHotel> hotellist3 = new ArrayList<MyHotel>();


        String jsonData;
        //Getting the Data from the provider by using the REST API's.
        jsonData=provider.getData(provider.getRestURL());
        //Formatting the data of hotels which we got from different Providers and setting it accordingly to our MyHotel class.
        hotellist1=provider.formatData(jsonData);

        jsonData=provider1.getData(provider1.getRestURL());
        hotellist2=provider1.formatData(jsonData);

        jsonData=provider2.getData(provider2.getRestURL());
        hotellist3=provider2.formatData(jsonData);


        //Now Checking if the two Providers are pointing to same Hotel or not. Even if there some data is changed.
        //     System.out.println("Case of different hotel with name but different locations");
        System.out.println("Checking if Hotel.com's Hotel 1 and MakeMyTrip's Hotel 1 object are same Hotel? "+ hotellist1.get(0).equals(hotellist2.get(0)));
        //     System.out.println("Case of same hotel with little difference in name and address");
        System.out.println("Checking if Hotel.com's Hotel 1 and MakeMyTrip's Hotel 3 object are same Hotel? "+ hotellist1.get(0).equals(hotellist2.get(2)));
        //     System.out.println("Case of  different hotel with same name but different rooms");
        System.out.println("Checking if Hotel.com's Hotel 2 and MakeMyTrip's Hotel 4 object are same Hotel? "+ hotellist1.get(1).equals(hotellist2.get(3)));
        //     System.out.println("Case of same hotel with additional name and address");
        System.out.println("Checking if Hotel.com's Hotel 4 and MakeMyTrip's Hotel 6 object are same Hotel? "+ hotellist1.get(3).equals(hotellist2.get(5)));
        //     System.out.println("Case of same hotel with different names");
        System.out.println("Checking if MakemyTrip's Hotel 2 and Airbnb's Hotel 2 object are same Hotel? "+ hotellist2.get(1).equals(hotellist3.get(1)));
        //     System.out.println("Case of different hotel with different name and address");
        System.out.println("Checking if MakemyTrip's Hotel 3 and Airbnb's Hotel 3 object are same Hotel? "+ hotellist2.get(2).equals(hotellist3.get(2)));
        //     System.out.println("Case of same hotel with same name and address");
        System.out.println("Checking if Hotel.com's Hotel 6 and Airbnb's Hotel 1 object are same Hotel? "+ hotellist1.get(5).equals(hotellist3.get(0)));
        //     System.out.println("Case of different hotel with similar name and address");
        System.out.println("Checking if Hotel.com's Hotel 6 and Airbnb's Hotel 3 object are same Hotel? "+ hotellist1.get(5).equals(hotellist1.get(2)));

    }


}
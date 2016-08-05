/**
 * Created by ayseth on 04/08/16.
 */
package backendchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This is the class where we get the Provider data.
//We create the different Providers in the Hotel Class with different REST API's and call the getData function to
// get the JSON data.
// Now we format the JSON Data using GSON Libraries in the formatData() method and set the data in our newly created MyHotel Objects.
// Here for example purpose we dont call the getData function in Hotel class and just set the newly created Objects in the formatData function.
//
public class Provider{
    private String providerName;
    private String providerId;
    private String restURL;


    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getRestURL() {
        return restURL;
    }

    public void setRestURL(String restURL) {
        this.restURL = restURL;
    }


    public String getData(String RestURL){

        String jsonInString=null;
        //There would be actual REST API CALLS MADE to different Providers but here setting the Dummy JSON Data which we will get from Different Providers
        if(RestURL.equals("http://makemytrip.com/gethotels")) {
            jsonInString = "{\"name\":\"Marriot fairfield\",\"address\":\"Ecospace\",\"phone\":\"1010\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"50\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"IBIS Hotel\",\"address\":\"Bellandur\",\"phone\":\"2020\",\"location\":\"X=1200,Y=2000\",\"totalRooms\":\"250\",\"pincode\":\"560103\"}"+
                    ",{\"name\":\"Marriot Hotel\",\"address\":\"Kormangala\",\"phone\":\"3030\",\"location\":\"X=1300,Y=2200\",\"totalRooms\":\"150\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Aloft Hotel\",\"address\":\"Sarjapur\",\"phone\":\"4040\",\"location\":\"X=1400,Y=2000\",\"totalRooms\":\"100\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Hotel Surya\",\"address\":\"Ulsoor\",\"phone\":\"5050\",\"location\":\"X=1400,Y=2300\",\"totalRooms\":\"40\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Hotel Sun\",\"address\":\"Mg Road\",\"phone\":\"6060\",\"location\":\"X=1600,Y=2000\",\"totalRooms\":\"80\",\"pincode\":\"560103\"}";
        }
        if(RestURL.equals("http://airbnb.com/gethotels")) {
            jsonInString = "{\"name\":\"Raj Guest House\",\"address\":\"Kormangala\",\"phone\":\"7070\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"50\",\"pincode\":\"560133\"}" +
                    ",{\"name\":\"IBISS Hotel\",\"address\":\"Bellandur Street 2\",\"phone\":\"2121\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"250\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Guest house2\",\"address\":\"Kormanagala\",\"phone\":\"8080\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"70\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"New Horizon hotel\",\"address\":\"Kadubeeshanali\",\"phone\":\"9090\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"25\",\"pincode\":\"560103\"}";
        }
        if(RestURL.equals("http://hotel.com/gethotels")) {
            jsonInString = "{\"name\":\"A Marriot Hotel\",\"address\":\"KORMANGALA NEW STREET\",\"phone\":\"1111\",\"location\":\"X=1301,Y=2200\",\"totalRooms\":\"150\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Aloft Hotel\",\"address\":\"MG ROAD\",\"phone\":\"3333\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"150\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Hotel Surya\",\"address\":\"Ulsoor\",\"phone\":\"5050\",\"location\":\"X=1400,Y=2300\",\"totalRooms\":\"40\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Hotal SUN\",\"address\":\"Mg Road 1 street 2 lane\",\"phone\":\"6061\",\"location\":\"X=1600,Y=2000\",\"totalRooms\":\"80\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Raj Hotel\",\"address\":\"Kormangala\",\"phone\":\"8888\",\"location\":\"X=1200,Y=2000\",\"totalRooms\":\"70\",\"pincode\":\"560112\"}" +
                    ",{\"name\":\"Raj Guest House\",\"address\":\"Kormangala\",\"phone\":\"7070\",\"location\":\"X=1000,Y=2000\",\"totalRooms\":\"50\",\"pincode\":\"560133\"}" +
                    ",{\"name\":\"New Hotel\",\"address\":\"Nre road\",\"phone\":\"7071\",\"location\":\"X=2020,Y=2100\",\"totalRooms\":\"55\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Old Hotel\",\"address\":\"Old Mysore road\",\"phone\":\"1129\",\"location\":\"X=3000,Y=2000\",\"totalRooms\":\"150\",\"pincode\":\"560103\"}" +
                    ",{\"name\":\"Hotel Gurgaon\",\"address\":\"Gurgaon\",\"phone\":\"2202\",\"location\":\"X=7000,Y=2000\",\"totalRooms\":\"350\",\"pincode\":\"160103\"}";
        }
        //This will make a rest call using this.getRestURL and return you a JSON Object.
        //Depending on api ,We parse this JSON object to String and return.
        return jsonInString;
    }

    public List<MyHotel> formatData(String jsonData){

        ObjectMapper mapper = new ObjectMapper();
        List<MyHotel> list =new ArrayList<>();

        // /Used earlier when JSON was not used.
//        MyHotel myhotel2 = new MyHotel();
//        myhotel2.setAddress("B 204 DSR Windsor");
//        myhotel2.setLocation("X=1000,Y=2000");
//        myhotel2.setName("Aloft Bangalore");
//        myhotel2.setPhone("8080");
//        myhotel2.setPincode("560103");
//        myhotel2.setTotalRooms("200");
//
//        MyHotel myhotel1 = new MyHotel();
//        myhotel1.setAddress(" Cessena Business Park");
//        myhotel1.setLocation("X=2000,Y=3000");
//        myhotel1.setName("Ibes");
//        myhotel1.setPhone("9090");
//        myhotel1.setPincode("560103");
//        myhotel1.setTotalRooms("150");
//
//        list.add(myhotel2);
//        list.add(myhotel1);

        String jsondatasplitted[];
        jsondatasplitted=  jsonData.split("(?<=},)");
        if(jsondatasplitted!=null) {
            System.out.println("====The provider is=== "+this.getProviderName());
            for (int i = 0; i < jsondatasplitted.length; i++) {
                MyHotel myhotel = new MyHotel();
                try {
                    myhotel = mapper.readValue(jsondatasplitted[i], MyHotel.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("MyHotel Hotel Number== "+(i+1)+" Name: " + myhotel.getName() + " Address: "+myhotel.getAddress() + " Location: "+myhotel.getLocation() + " Phone Number: "+myhotel.getPhone() + " Total Rooms: "+myhotel.getTotalRooms());
                list.add(myhotel);
            }

        }
        return list;
    }



}

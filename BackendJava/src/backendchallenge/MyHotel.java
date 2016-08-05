//My Hotel Class is the one which we create and we will USE THIS MY HOTEL Object to store in our DB.
//All the other Hotel objects which we get from different sources will be converted first to this Hotel Object and then processed Further.


/*
Here this is the class where we define the object according to our need.
We set the Fields here which we require in the db and set it accordingly.

Here the equals method and the hashcode method has been overriden

The equals method uses the below logic:

1. It checks if the 2 objects have the phone numbers as same or not. (If similar surely,two objects points to two same hotels).
2. It checks if the 2 objects have the location ,address,name exactly same or not.If any one of these is same surely the two objects belongs to same hotel.
3. It also checks if anything is NULL in the object it skips that check.
4. At last if nothing matches,
   Suppose the phone numbers are different , Location is also different , Address is also different , Name is also not exactly same then
   we check how much is the edit distance between the names.
  "Ideally we should check the Edit Distance between Location first and then names and address to come to conclusion.But here for dummy purpose lets assume names."
    The edit distance is the number of operations required to change one string from the other string.
   e.g. the Two hotel names are "Ibis" and "Ibes" which we get from two providers
   The edit distance would be one. changing e to i or vice versa.
   Now these are pointing to same Hotel just that provider data is different and we put a check if the distance is less than 2.
   and then if we have the data of total number of rooms in that hotel and put a check there.
   Surely they are pointing to same hotel.

   We can also have a additional check that if the getEditDistance is <2 for the names then check if getEditDistance is <4 for Address(As Address
   String is more in size).It depends on how we get the Data.
   We need to analyze the data and make further checks.
 */
/**
 * Created by ayseth on 04/08/16.
 */
package backendchallenge;
public class MyHotel {

    private String name;
    private String address;
    private String phone;
    private String location;
    private String totalRooms;
    private String pincode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(String totalRooms) {
        this.totalRooms = totalRooms;
    }

    @Override
    public boolean equals(Object object) {
        int one;
        int two;
        MyHotel obj=(MyHotel)object;
     //   System.out.println("The Distance is between these objects"+ getEditDistance(this.name, obj.getName()));
        if(this.getPhone()!=null && obj.getPhone()!=null && this.getPhone().equals(obj.getPhone())) //Checking Phone number
            return true;
        else{
            if(this.getLocation()!=null && obj.getLocation()!=null && this.getLocation().equals(obj.getLocation()))//Checking Geo-Locations
                return true;
            else {
                if (this.getAddress()!=null && obj.getAddress()!=null && this.getAddress().equals(obj.getAddress()))//Checking Address
                    return true;
                else {
                        if (this.name != null && obj.getName() != null && (getEditDistance(this.name, obj.getName()) <= 2))//Checking Name Distance
                        {
                            if(this.getTotalRooms()!=null && obj.getTotalRooms()!=null && (this.getTotalRooms().equals(obj.getTotalRooms())))//Checking Total number of rooms
                                return true;
                            else
                                return false;
                        }
                        else
                            return false;
                    }
                }
            }

//My first implementation of Equals function was like this..Very Vague though.

//        one=Integer.parseInt(this.phone)*Integer.parseInt(this.pincode)* Integer.parseInt(this.totalRooms);
//        two=Integer.parseInt(obj.getPhone())*Integer.parseInt(obj.getPincode())* Integer.parseInt(obj.getTotalRooms());
//
//        if(one==two)
//            return true;
//        else
//            return false;
//       super.equals(obj);
    }

    public static int getEditDistance(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int n = a.length(); // length of a
        int m = b.length(); // length of b

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less mem.
            String tmp = a;
            a = b;
            b = tmp;
            n = m;
            m = b.length();
        }

        int p[] = new int[n+1]; //"previous" cost array
        int d[] = new int[n+1]; // cost array
        int _d[];

        // indexes into strings a and b
        int i; // iterates through a
        int j; // iterates through b

        char b_j; // jth character of b

        int cost;

        for (i = 0; i<=n; i++) {
            p[i] = i;
        }

        for (j = 1; j<=m; j++) {
            b_j = b.charAt(j-1);
            d[0] = j;

            for (i=1; i<=n; i++) {
                cost = a.charAt(i-1)==b_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
            }
            _d = p;
            p = d;
            d = _d;
        }

        // last action in above loop was to switch d and p,
        // so p now has the most recent cost counts.
        return p[n];
    }

    @Override
    public int hashCode() {

        return Integer.parseInt(this.phone)*Integer.parseInt(this.pincode)* Integer.parseInt(this.totalRooms);
    }

}



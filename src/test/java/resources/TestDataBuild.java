package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public  AddPlace addPlacePayload(String name,String language,String address)
    {
        AddPlace p = new AddPlace();
        p.setAccuracy("50");
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("+91-7898592700");
        p.setWebsite("www.xyzabc.com");
        p.setName(name);
        List<String> mylist = new ArrayList<String>();
        mylist.add("asd");
        mylist.add("lkj");
        p.setTypes(mylist);
        Location l = new Location();
        l.setLat(3.545354);
        l.setLng(5.340343);
        p.setLocation(l);
        return p;
    }

    public String getDeletePayload(String placeid)
    {
        return "{\r\n   \"place_id\":\""+placeid+"\"\r\n}";
    }

}

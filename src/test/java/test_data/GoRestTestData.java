package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    public Map<String,String> dataMapMethod (String name,String email,String gender,String status ){
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("name",name);
        dataMap.put("email",email);
        dataMap.put("gender",gender);
        dataMap.put("status",status);

        return dataMap;
    }
    public Map<String,Object> expectedDataMethod(Object meta,Map<String,String> data ){
        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("meta",meta);
        expectedData.put("data",data);

        return expectedData;
    }

}
/*
    {
        "meta": null,
            "data": {
        "id": 4300,
                "name": "Suman Khanna PhD",
                "email": "khanna_suman_phd@goyette.io",
                "gender": "male",
                "status": "inactive"
    }
     */
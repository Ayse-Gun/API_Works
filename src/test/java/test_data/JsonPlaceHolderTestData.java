package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> expectedDataMethod(Integer userId, String title ,Boolean completed){

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",userId);
        expectedData.put("title",title);
        if (userId!=null){
            expectedData.put("userId",userId);

        }
        if (title!=null) {
            expectedData.put("title", title);
        }
        if (completed!=null){
            expectedData.put("completed",completed);
        }

        return expectedData;
    }
}



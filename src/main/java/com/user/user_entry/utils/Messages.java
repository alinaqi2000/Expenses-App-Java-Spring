package com.user.user_entry.utils;

import java.util.*;
import com.user.user_entry.models.*;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Messages {

    public static HashMap<String, String> setMessage(String status, String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        return map;
    }

    public static HashMap<String, Object> setItemData(String status, List<Item> data) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("success", data);
        return map;
    }

    public static HashMap<String, Object> setChartData(String status, Chart data) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("success", data);
        return map;
    }

    public static HashMap<String, String> setData(String status, String data) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", status);
        map.put("success", data);
        return map;
    }

    public static String convertObjIntoJSON(Object obj) {
        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}

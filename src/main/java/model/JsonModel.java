package model;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder; 

public abstract class JsonModel<T> {

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
    
    public static Object newInstance(Class klass, String json) {
        Gson gson = new Gson();
        return  gson.fromJson(json, klass);
    }
    

}

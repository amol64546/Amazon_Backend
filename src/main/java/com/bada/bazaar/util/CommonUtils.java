package com.bada.bazaar.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {

  public String prettyPrint(Object obj){
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(obj);
  }



}


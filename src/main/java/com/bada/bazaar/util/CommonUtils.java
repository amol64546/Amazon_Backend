package com.bada.bazaar.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {

  public void prettyPrint(Object obj){
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(obj);
    System.out.println(json);
  }



}


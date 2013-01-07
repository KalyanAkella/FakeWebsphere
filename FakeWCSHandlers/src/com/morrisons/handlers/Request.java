package com.morrisons.handlers;

import java.util.HashMap;
import java.util.Map;

public class Request {
  private Map<String, String> queryParameters;

  public Request() {
    queryParameters = new HashMap<String, String>();
  }

  public String get(String key) {
    return queryParameters.get(key);
  }

  public void put(String key, String value) {
    queryParameters.put(key, value);
  }

  public static Request fromQueryString(String queryString) {
    String[] params = queryString.split("&");
    Request request = new Request();
    for (String param : params) {
      String[] comps = param.split("=");
      request.put(comps[0], comps[1]);
    }
    return request;
  }
}

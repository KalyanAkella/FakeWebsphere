package com.morrisons.wcs;

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

  public static Request fromRequestParams(String queryString, Map<String, String[]> parameterMap) {
    Request request = new Request();
    request.setFromQueryParams(queryString);
    request.setFromParameterMap(parameterMap);
    return request;
  }

  private void setFromParameterMap(Map<String, String[]> parameterMap) {
    if (parameterMap == null) return;
    for (String paramKey : parameterMap.keySet()) {
      put(paramKey, parameterMap.get(paramKey)[0]);
    }
  }

  private void setFromQueryParams(String queryString) {
    if (queryString == null) return;
    String[] params = queryString.split("&");
    for (String param : params) {
      String[] comps = param.split("=");
      put(comps[0], comps[1]);
    }
  }

  public boolean contains(String key) {
    return queryParameters.containsKey(key);
  }
}

package com.morrisons.wcs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Request {
  private Map<String, String> queryParameters;
  private String content;
  private String contentType;

  public Request() {
    queryParameters = new HashMap<String, String>();
  }

  public String get(String key) {
    return queryParameters.get(key);
  }

  public void put(String key, String value) {
    queryParameters.put(key, value);
  }

  public void setFromParameterMap(Map<String, String[]> parameterMap) {
    if (parameterMap == null) return;
    for (String paramKey : parameterMap.keySet()) {
      put(paramKey, parameterMap.get(paramKey)[0]);
    }
  }

  public void setFromQueryParams(String queryString) {
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

  public void setFromInputStream(InputStream inputStream) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    try {
      String line;
      StringBuilder buffer = new StringBuilder();
      while ((line = bufferedReader.readLine()) != null) buffer.append(line);
      this.content = buffer.toString();
    } finally {
      bufferedReader.close();
    }
  }

  public String getContent() {
    return content;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getContentType() {
    return contentType;
  }
}

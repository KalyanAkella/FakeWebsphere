package com.morrisons.wcs;

public class Response {
  public int statusCode;
  public Object body;

  public Response(int statusCode, Object body) {
    this.statusCode = statusCode;
    this.body = body;
  }
}

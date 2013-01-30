package com.morrisons.wcs.carts;

import com.morrisons.wcs.Request;
import com.morrisons.wcs.Response;
import com.morrisons.wcs.WCSHandler;

public class CartHandler implements WCSHandler {
  @Override
  public Response handle(Request request) {
    String method = request.get("method");
    if ("post".equalsIgnoreCase(method)) {
      return addToBasket(request);
    }
    return null;
  }

  private Response addToBasket(Request request) {
    String storeId = request.get("storeId");
    String catentryId = request.get("catentryId");
    String partNumber = request.get("partNumber");
    String quantity = request.get("quantity");

    return null;
  }
}

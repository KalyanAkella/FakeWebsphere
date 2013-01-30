package com.morrisons.wcs.inventory;

import com.morrisons.wcs.Request;
import com.morrisons.wcs.Response;
import com.morrisons.wcs.WCSHandler;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class InventoryHandler implements WCSHandler {
  private static Map<String, GetInventoryResponse> inventoryMap = new HashMap<String, GetInventoryResponse>();

  @Override
  public Response handle(Request request) {
    String method = request.get("method");
    if ("get".equalsIgnoreCase(method)) {
      return get(request);
    }
    return null;
  }

  private Response get(Request request) {
    String productId = request.get("productId");
    GetInventoryResponse inventoryResponse = inventoryMap.get(productId);
    if (inventoryResponse == null) {
      inventoryResponse = new GetInventoryResponse();
      inventoryResponse.InventoryAvailability = asList(new InventoryAvailability("1", 100, "1", "1", 200));
      inventoryMap.put(productId, inventoryResponse);
    }
    return new Response(200, inventoryResponse);
  }
}

package com.morrisons.wcs.deliveryTypes;

import com.morrisons.wcs.Request;
import com.morrisons.wcs.Response;
import com.morrisons.wcs.WCSHandler;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShippingOptionsHandler implements WCSHandler {

  @Override
  public Response handle(Request request) {
    ArrayList<DeliveryType> deliveryTypes = new ArrayList<DeliveryType>();
    deliveryTypes.add(buildDeliveryType("Express", "1", "1-2 Days", new BigDecimal("0")));
    deliveryTypes.add(buildDeliveryType("Snail Mail", "2", "1-2 Months", new BigDecimal("0")));
    return new Response(200, deliveryTypes);
  }

  private DeliveryType buildDeliveryType(String shippingType, String id, String period, BigDecimal price) {
    DeliveryType deliveryType = new DeliveryType();
    deliveryType.name = shippingType;
    deliveryType.id = id;
    deliveryType.duration = period;
    deliveryType.price = price;
    return deliveryType;
  }

}

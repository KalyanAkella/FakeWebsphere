package com.morrisons.wcs.addresses;

import com.morrisons.wcs.Request;
import com.morrisons.wcs.Response;
import com.morrisons.wcs.WCSHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserAddressesHandler implements WCSHandler {

  private static Map<String, Address> addressMap = new HashMap<String, Address>();
  private static final AtomicInteger addressID;

  static {
    addressID = new AtomicInteger(0);
  }

  public Response addAddress(Request request) {
    String addressId = newAddressId();
    Address address = createAddressFromRequest(request, addressId);
    addressMap.put(addressId, address);
    return new Response(201, address);
  }

  private Response updateAddress(Request request) {
    String addressId = getAddressId(request);
    Address address = addressMap.get(addressId);
    if (address == null) return new Response(200, null);
    updateAddressFromRequest(address, request);
    return new Response(200, address);
  }

  private void updateAddressFromRequest(Address address, Request request) {
    address.address1 = request.contains("address1") ? request.get("address1") : address.address1;
    address.lastName = request.contains("lastName") ? request.get("lastName") : address.lastName;
    address.firstName = request.contains("firstName") ? request.get("firstName") : address.firstName;
    address.state = request.contains("state") ? request.get("state") : address.state;
    address.country = request.contains("country") ? request.get("country") : address.country;
    address.city = request.contains("city") ? request.get("city") : address.city;
    address.zipCode = request.contains("zipCode") ? request.get("zipCode") : address.zipCode;
    address.address1 = request.contains("address1") ? request.get("address1") : address.address1;
    address.address2 = request.contains("address2") ? request.get("address2") : address.address2;
    address.address3 = request.contains("address3") ? request.get("address3") : address.address3;
    address.nickName = request.contains("nickName") ? request.get("nickName") : address.nickName;
    address.addressType = request.contains("addressType") ? request.get("addressType") : address.addressType;
    address.title = request.contains("title") ? request.get("title") : address.title;
    address.phone = request.contains("phone") ? request.get("phone") : address.phone;
    address.mobilePhone = request.contains("mobilePhone") ? request.get("mobilePhone") : address.mobilePhone;
    address.email = request.contains("email") ? request.get("email") : address.email;
    address.company = request.contains("company") ? request.get("company") : address.company;
  }

  public Response deleteAddress(Request request) {
    String addressId = getAddressId(request);
    return new Response(200, addressMap.remove(addressId));
  }

  public Response getAddress(Request request) {
    String addressId = getAddressId(request);
    Address address = addressMap.get(addressId);
    if (address == null) return new Response(404, null);
    return new Response(200, new GetAddressResponse(address));
  }

  @Override
  public Response handle(Request request) {
    String method = request.get("method");
    if ("post".equalsIgnoreCase(method)) {
      return addAddress(request);
    } else if ("delete".equalsIgnoreCase(method)) {
      return deleteAddress(request);
    } else if ("get".equalsIgnoreCase(method)) {
      return getAddress(request);
    } else if ("put".equalsIgnoreCase(method)) {
      return updateAddress(request);
    }
    return null;
  }

  private static String newAddressId() {
    return String.valueOf(addressID.incrementAndGet());
  }

  private static String getAddressId(Request request) {
    String addressId = request.get("addressId");
    if (addressId == null) addressId = "";
    return addressId;
  }


  private static Address createAddressFromRequest(Request request, String addressId) {
    return new Address(addressId, request.get("lastName"), request.get("firstName"), request.get("state"), request.get("country"),
      request.get("city"), request.get("zipCode"), request.get("address1"), request.get("address2"), request.get("address3"), request.get("nickName"),
      request.get("addressType"), request.get("title"), request.get("phone"), request.get("mobilePhone"), request.get("email"), request.get("company"));
  }
}

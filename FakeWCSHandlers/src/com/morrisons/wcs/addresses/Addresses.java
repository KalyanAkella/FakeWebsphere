package com.morrisons.wcs.addresses;

import java.util.List;

public class Addresses {
  private List<Address> addresses;

  public Addresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public void addAddress(Address address) {
    addresses.add(address);
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public Address getAddress(String addressId) {
    for (Address address : addresses) {
      if (address.addressId.equals(addressId))
        return address;
    }

    return null;
  }
}

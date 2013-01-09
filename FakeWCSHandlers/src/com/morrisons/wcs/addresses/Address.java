package com.morrisons.wcs.addresses;

public class Address {
  public String addressId;
  public String lastName;
  public String firstName;
  public String state;
  public String country;
  public String city;
  public String zipCode;
  public String address1;
  public String address2;
  public String address3;
  public String nickName;
  public String addressType;
  public String title;
  public String phone;
  public String mobilePhone;
  public String email;
  public String company;

  public Address(String addressId, String lastName, String firstName, String state, String country, String city,
                 String zipCode, String address1, String address2, String address3, String nickName,
                 String addressType, String title, String phone, String mobilePhone, String email, String company) {
    this.addressId = addressId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.state = state;
    this.country = country;
    this.city = city;
    this.zipCode = zipCode;
    this.address1 = address1;
    this.address2 = address2;
    this.address3 = address3;
    this.nickName = nickName;
    this.addressType = addressType;
    this.title = title;
    this.phone = phone;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.company = company;
  }
}

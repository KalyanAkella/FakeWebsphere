package com.morrisons.wcs.inventory;

public class InventoryAvailability {
  public String buyable;
  public int availableQuantity;
  public String markForDeletion;
  public String published;
  public int threshold;

  public InventoryAvailability(String buyable, int availableQuantity, String markForDeletion, String published, int threshold) {
    this.buyable = buyable;
    this.availableQuantity = availableQuantity;
    this.markForDeletion = markForDeletion;
    this.published = published;
    this.threshold = threshold;
  }
}

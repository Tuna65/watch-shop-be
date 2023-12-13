package com.example.watchShop.enums;

public enum Permission {
  ADMIN,
  PARTNER,
  PROVIDER;

  public String getNameExport() {
    switch (this) {
      case ADMIN:
        return "quản trị viên";
      case PARTNER:
        return "quý đối tác";
      case PROVIDER:
        return "nhà cung cấp";
      default:
        return "";
    }
  }
}

package com.example.zadscraper;


public class FirmenDaten {

  String name = null;
  String KontoinformationsdiensteK = null;
  String ZahlungsauslösediensteZ = null;


  public FirmenDaten(String name, String kontoinformationsdiensteK, String zahlungsauslösediensteZ) {
    this.name = name;
    KontoinformationsdiensteK = kontoinformationsdiensteK;
    ZahlungsauslösediensteZ = zahlungsauslösediensteZ;
  }

  public String getName() {
    return name;
  }

  public String getKontoinformationsdiensteK() {
    return KontoinformationsdiensteK;
  }

  public String getZahlungsauslösediensteZ() {
    return ZahlungsauslösediensteZ;
  }

}

package com.example.zadscraper;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

public class Firma {

  String name;
  boolean Kontoinformationsdienste;
  Date KontoinformationsdiensteK;
  boolean Zahlungsauslösedienste;
  Date ZahlungsauslösediensteZ;

  public Firma(String name, boolean Kontoinformationsdienste, Date KontoinformationsdiensteK, boolean Zahlungsauslösedienste, Date ZahlungsauslösediensteZ) {
    this.name = name;
    this.Kontoinformationsdienste = Kontoinformationsdienste;
    this.KontoinformationsdiensteK = KontoinformationsdiensteK;
    this.Zahlungsauslösedienste = Zahlungsauslösedienste;
    this.ZahlungsauslösediensteZ = ZahlungsauslösediensteZ;

  }
  public static void main(String [] args)throws IOException {

    Document doc = Jsoup.connect("https://portal.mvp.bafin.de/database/ZahlInstInfo/zahlinst.do?d-3622331-p=1&id=150550&d-3622331-s=0&d-3622331-o=2").get();

    Elements tds = doc.select("table.displaytag tbody tr td.odd.Kontoinformationsdienste");
    for (Element e : tds) {
      System.out.println(e.text());
    }
  }
}
package com.example.zadscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

;

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

  public static void main(String[] args) throws IOException {

    Document doc = Jsoup.connect("https://portal.mvp.bafin.de/database/ZahlInstInfo/zahlinst.do?id=100339").get();

    Elements erlaubnisTabelle = doc.select("#erlaubnis");
    for (Element tabelle : erlaubnisTabelle) {
      //System.out.println(tabelle.text());

      Elements tbodys = tabelle.select("tbody");
      for (Element tbody : tbodys) {
        //System.out.println("tbody = " + tbody);

        Elements tablerows = tbody.select("tr");
        for (Element tr : tablerows) {
          Elements tablecells = tr.select("td");

          for (int i = 0; i < 1; i++) {
            TextNode textNode = (TextNode) tablecells.get(i).childNode(0);
            TextNode textNodeDate = (TextNode) tablecells.get(i + 1).childNode(0);
            System.out.println("textNode.text() = " + textNode.text());
            System.out.println("textNodeDate.text() = " + textNodeDate.text());
            if (textNode.toString().equals("Kontoinformationsdienste (§ 1 Abs. 1 Satz 2 Nr. 8 ZAG)")){
              boolean Kontoinformationsdienste = true;
              System.out.println(Kontoinformationsdienste);
            }
            if (textNode.toString().equals("Zahlungsauslösedienste (§ 1 Abs. 1 Satz 2 Nr. 7 ZAG)")){
              boolean Zahlungsauslösedienste = true;
              System.out.println(Zahlungsauslösedienste);
            }
          }
        }
      }
    }
  }
}
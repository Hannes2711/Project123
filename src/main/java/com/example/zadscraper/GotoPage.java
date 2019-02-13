package com.example.zadscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-11
 */
public class GotoPage {

  public static final String BAFIN_BASE_URL = "https://portal.mvp.bafin.de/database/ZahlInstInfo/";

  public static void main(String[] args) {
    Firma firma = new Firma();
    CsvWriter csvWriter = null;

    try {
      csvWriter = new CsvWriter();

      csvWriter.writeLine("Name;Kontoinformationsdienste;Zahlungsauslösedienste\n");

      int seitenZahl = 1;
      Document doc;

      while (seitenZahl > 0) {

        //System.out.println("Durchsuchen von Seite " + seitenZahl);
        doc = Jsoup.connect(BAFIN_BASE_URL + "suche.do?nameZahlungsinstitutButton=Suche+ZAG-Institute&d-4036871-p=" + seitenZahl).get();
        Elements select = doc.select("a[href^=zahlinst.do?id=]");

        int firmenAufDerSeite = 0;
        for (Element element : select) {
          firmenAufDerSeite++;

          String href = BAFIN_BASE_URL + element.attr("href");
          //System.out.println("href = " + href);

          FirmenDaten daten = firma.extractInformation(href);
          String merged = "\"" + daten.getName() + "\";" + daten.getKontoinformationsdiensteK() + ";" + daten.getZahlungsauslösediensteZ() + "\n";
          csvWriter.writeLine(merged);

          System.out.println(" [ " + daten.getName() + "; " + daten.getKontoinformationsdiensteK() + "; " + daten.getZahlungsauslösediensteZ() + "]");
          System.out.println(daten.getName() + "; " + daten.getKontoinformationsdiensteK() + "; " + daten.getZahlungsauslösediensteZ());

        }

        if (firmenAufDerSeite == 20) {
          seitenZahl++;
        } else {
          seitenZahl = 0;
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (csvWriter != null)  {
        csvWriter.closeFile();
      }
    }



  }
}
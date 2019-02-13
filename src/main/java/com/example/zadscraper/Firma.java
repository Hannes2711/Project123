package com.example.zadscraper;

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.nodes.TextNode;
        import org.jsoup.select.Elements;

        import javax.naming.Name;
        import java.io.IOException;




public class Firma {



  public FirmenDaten extractInformation(String href) throws IOException {

    String name = null;
    String kontoDatum = null;
    String zadDatum = null;

    Document doc = Jsoup.connect(href).get();

//für infos der Firma (Name, anschrift ...)
    Elements Namen = doc.select("#content");
    for (Element name1 : Namen) {

      Elements Infoa = name1.select("p");
      for (Element p : Infoa) {
        TextNode NamenderFirma = (TextNode) Infoa.get(0).childNode(0);
        name = NamenderFirma.toString();
      }
    }


    Elements erlaubnisTabelle = doc.select("#erlaubnis");
    for (
            Element tabelle : erlaubnisTabelle) {

      Elements tbodys = tabelle.select("tbody");
      for (Element tbody : tbodys) {

        Elements tablerows = tbody.select("tr");
        for (Element tr : tablerows) {
          Elements tablecells = tr.select("td");

          TextNode textNode = (TextNode) tablecells.get(0).childNode(0);
          TextNode textNodeDate = (TextNode) tablecells.get(1).childNode(0);

          if (textNode.toString().equals("Kontoinformationsdienste (§ 1 Abs. 1 Satz 2 Nr. 8 ZAG)")) {
            kontoDatum = textNodeDate.text();
          } else if (textNode.toString().equals("Zahlungsauslösedienste (§ 1 Abs. 1 Satz 2 Nr. 7 ZAG)")) {
            zadDatum = textNodeDate.text();
          }
        }
      }
    }


    FirmenDaten result = new FirmenDaten(name, kontoDatum, zadDatum);
    return result;
  }
}
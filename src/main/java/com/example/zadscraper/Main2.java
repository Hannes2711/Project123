package com.example.zadscraper;

import org.jsoup.*;
import org.jsoup.nodes.*;

import java.io.*;
import java.util.*;

<<<<<<<Updated upstream
		=======
>>>>>>> Stashed changes

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-11
 */
public class Main2 {

	public static void main(String[] args) throws IOException {
		System.out.println("Hallo du!");

<<<<<<< Updated upstream
		Document doc = Jsoup.connect("https://portal.mvp.bafin.de/database/ZahlInstInfo/suche.do?").get();

=======
		BafinCompaniesScraper bps = new BafinCompaniesScraper();
		List<String> allCompanyUrls = bps.getAllCompanyUrls();

		System.out.println("allCompanyUrls.size() = <" + allCompanyUrls.size() + ">");

		List<BafinCompanyPageScraper> results = new ArrayList<BafinCompanyPageScraper>();
		for (String companyUrl : allCompanyUrls) {
			System.out.println("companyUrl = <" + companyUrl + ">");
			results.add( new BafinCompanyPageScraper(companyUrl) );
		}

		CSVWriter csvWriter = new CSVWriter();
		csvWriter.writeFile("output.csv", results);
	}
>>>>>>> Stashed changes

	}
}

package com.example.zadscraper;

import java.io.*;
import java.util.*;

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-11
 */
public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Hello world!");

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

}

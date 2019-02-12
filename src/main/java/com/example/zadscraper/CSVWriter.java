package com.example.zadscraper;

import java.io.*;
import java.util.*;

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-12
 */
public class CSVWriter {

	public void writeFile (String fileName, List<BafinCompanyPageScraper> list) {

		System.out.println("NAME; KONTOINFORMATIONSDIENSTE; ZAHLUNGSINFORMATIONSDIENSTE;");

		for (BafinCompanyPageScraper scraper : list) {
			String line = (scraper.getCompanyName() + "; " +  scraper.getKontoinformationsdiensteString() + "; "+scraper.getZahlungsauslösediensteString()+ ";");
			System.out.println("line = <" + line + ">");
		}
	}

	private void test(List<String> lines, String fileName) throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter(fileName)) {
			for (String line : lines) {
				out.write(line);
				out.write("\n");
			}
		}
	}

}

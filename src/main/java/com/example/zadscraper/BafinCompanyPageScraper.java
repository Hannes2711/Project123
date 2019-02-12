package com.example.zadscraper;

import org.apache.commons.lang3.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-11
 */
public class BafinCompanyPageScraper {

	private Document document;

	private String companyName = null;
	private Date kontoinformationsdiensteDate = null;
	private Date zahlungsauslösediensteDate = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	private static String KONTOINFORMATIONSDIENSTE = "Kontoinformationsdienste";
	private static String ZAHLUNGSAUSLOESEDIENSTE = "Zahlungsauslösedienste";

	public BafinCompanyPageScraper(String url) throws IOException {
		document = Jsoup.connect(url).get();
		extractCompanyName();
		getDates();
	}

	private void getDates() {

		Element table = getErlaubnisTable();
		Elements rows = getRows(table);

		for (Element row : rows) {

			Elements columns = getColumns(row);
			if (columns.size() > 2) {

				TextNode node = (TextNode) columns.get(0).childNode(0);
				System.out.println("Value of column 0: <" + node.text() + ">.");

				if (StringUtils.containsIgnoreCase(node.text(), KONTOINFORMATIONSDIENSTE)) {
					System.out.println("Found column <"+KONTOINFORMATIONSDIENSTE+">, extracting date.");
					kontoinformationsdiensteDate = extractDate(columns);
				} else if (StringUtils.containsIgnoreCase(node.text(), ZAHLUNGSAUSLOESEDIENSTE)) {
					System.out.println("Found column <"+ZAHLUNGSAUSLOESEDIENSTE+">, extracting date.");
					zahlungsauslösediensteDate = extractDate(columns);
				}
			}
		}

	}

	private Date extractDate(Elements columns) {
		if (columns.size() < 2) {
			return null;
		}
		if (columns.get(1).childNodeSize() < 1) {
			return null;
		}

		TextNode node = (TextNode) columns.get(1).childNode(0);

		if (StringUtils.isNotBlank(node.text())) {
			try {
				System.out.println("Extracting date <" + node.text() + ">.");
				return sdf.parse(node.text());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private Element getErlaubnisTable() {

		Elements select = document.select("table#erlaubnis");
		System.out.println("Found <" + select.size() + "> table with id 'erlaubnis' on page.");

		if (select.size() > 0) {
			return select.first();
		}
		return null;
	}

	private Elements getRows(Element table) {

		Elements tbody = table.select("tbody");
		Elements rows = tbody.select("tr");
		System.out.println("Found <" + rows.size() + "> rows in table.");

		return rows;
	}

	private Elements getColumns(Element row) {

			Elements columns = row.select("td");
			System.out.println("Found <" + columns.size() + "> columns for row.");

			return columns;
	}

	private String extractCompanyName() {
		Elements select = document.select("h2:contains(Zahlungsinstitut (ZAG))");
		System.out.println("Found <"+select.size()+"> H2 header with 'Zahlungsdienst (ZAG)' on page.");
		if (select.size() > 0) {
			Node node = select.first().nextElementSibling().childNode(0);
			companyName = StringUtils.trim(node.toString());
		}
		return null;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Date getKontoinformationsdiensteDate() {
		return kontoinformationsdiensteDate;
	}

	public Date getZahlungsauslösediensteDate() {
		return zahlungsauslösediensteDate;
	}

	public String getKontoinformationsdiensteString() {
		if (kontoinformationsdiensteDate == null) {
			return "";
		}
		return sdf.format(kontoinformationsdiensteDate);
	}

	public String getZahlungsauslösediensteString() {
		if (zahlungsauslösediensteDate == null) {
			return "";
		}
		return sdf.format(zahlungsauslösediensteDate);
	}
}

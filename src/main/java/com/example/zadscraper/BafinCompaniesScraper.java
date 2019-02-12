package com.example.zadscraper;

import org.apache.commons.lang3.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.util.*;

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-11
 */
public class BafinCompaniesScraper {

	private String baseUrl = "https://portal.mvp.bafin.de/database/ZahlInstInfo/";

	public List<String> getAllCompanyUrls() throws IOException {
		List<String> companyUrls = new ArrayList();
		String nextUrl = null;

		Document page = getStartPage();
		while (null != page) {

			nextUrl = getNextPageLink(page);
			getCompanyUrls(page, companyUrls);

			page = null;
			if (StringUtils.isNotBlank(nextUrl)) {
				page = getPage(nextUrl);
			}
		}
		return companyUrls;
	}

	private Document getPage(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	private Document getStartPage() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("nameAgenten", "");
		map.put("nameZahlungsinstitut", "");
		map.put("filterObjektart", "0");
		map.put("nameZahlungsinstitutButton", "Suche ZAG-Institute");
		Document doc = Jsoup.connect(baseUrl + "suche.do").data(map).post();

		return doc;
	}

	private String getNextPageLink(Document doc) {
		Elements links = doc.select("a[href]:contains(vor)");
		System.out.println("Found <"+links.size()+"> 'vor' links on page.");

		if (links.size() > 0) {
			return baseUrl + links.first().attr("href");
		} else {
			return null;
		}
	}

	private void getCompanyUrls(Document doc, List<String> pageUrlList) {
		Elements companyLinks = doc.select("a[href^=zahlinst.do?id=]");
		System.out.println("Found <"+companyLinks.size()+"> companies on page.");

		for (Element link : companyLinks) {
			pageUrlList.add(baseUrl + link.attr("href"));
		}
	}

}

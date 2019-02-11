package com.example.zadscraper;

import org.jsoup.*;
import org.jsoup.nodes.*;

import java.io.*;

/**
 * @author hek
 * Copyright 2019 by Star Finanz-Software Entwicklungs und Vertriebs GmbH
 * Created on 2019-02-11
 */
public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Hello world!");

		Document doc = Jsoup.connect("https://portal.mvp.bafin.de/database/ZahlInstInfo/suche.do?").get();
		System.out.println(doc.title());

	}

}

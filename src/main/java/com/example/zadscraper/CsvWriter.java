package com.example.zadscraper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


public class CsvWriter {

  private PrintWriter writer;

  public CsvWriter() {
    try {
      writer = new PrintWriter(new File("zadscraper.csv"), "ISO-8859-1");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public void writeLine(String line) {

    writer.write(line);
    System.out.println("line = " + line);

  }

  public void closeFile() {
    writer.close();
  }

}
package com.example.zadscraper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class CsvWriter {

  private PrintWriter writer;

  public CsvWriter() {
    try {
      writer = new PrintWriter(new File("zadscraper.csv"));
    } catch (FileNotFoundException e) {
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
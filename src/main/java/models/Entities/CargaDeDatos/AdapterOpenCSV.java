package models.Entities.CargaDeDatos;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class AdapterOpenCSV implements AdapterLectorCSV {
  private CSVReader reader;

  public List<String[]> leerLineas(String path) {
    this.abrirLecturaCSV(path);
    List<String[]> filasCSV = new LinkedList<>();
    try {
      //parsing a CSV file into CSVReader class constructor
      filasCSV = reader.readAll();
    }
    catch (IOException | CsvException e) {
      throw new RuntimeException(e);
    }
    cerrarLecturaCSV();
    return filasCSV;
  }

  public void abrirLecturaCSV(String path) {
    try {
      CSVParser parser = new CSVParserBuilder()
              .withSeparator(';')
              .build();

      this.reader = new CSVReaderBuilder(new FileReader(path))
              .withSkipLines(0)
              .withCSVParser(parser)
              .build();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void cerrarLecturaCSV() {
    try {
      this.reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
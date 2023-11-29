package models.Entities.ValidadorPassword;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidacionDebiles implements Validacion{
  private final List<String> passwords = new ArrayList<>();
  private final File topPasswords;

  public ValidacionDebiles(String path){
    this.topPasswords = new File(path);
    this.cargarArchivo();
  }

  @Override
  public boolean esValida(String password) {
    return !this.passwords.contains(password);
  }

  private void cargarArchivo(){
    Scanner sc = null;
    try {
      sc = new Scanner(this.topPasswords);
      while (sc.hasNextLine()) {
        this.passwords.add(sc.nextLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert sc != null;
    sc.close();
  }
}
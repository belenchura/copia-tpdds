package models.Entities.ValidadorPassword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PasswordConfig {

  static private PasswordConfig passwordConfig = null;

  private final Properties prop = new Properties();
  private String direccionConfig = "./src/main/resources/password.config";
  private final int longitudMinima;
  private final int longitudMaxima;

  private final String caracteresValidos;
  private final String pathTop10000Passwords;


  private PasswordConfig(){
    this.cargarConfig();
    this.longitudMinima = Integer.parseInt(this.prop.getProperty("longitudMinima"));
    this.longitudMaxima = Integer.parseInt(this.prop.getProperty("longitudMaxima"));
    this.caracteresValidos = this.prop.getProperty("caracteresValidos");
    this.pathTop10000Passwords = this.prop.getProperty("pathTop10000Passwords");
  }

  static public PasswordConfig getPasswordConfig() {
    if (passwordConfig == null) {
      passwordConfig = new PasswordConfig();
    }
    return passwordConfig;
  }

  private void cargarConfig() {
    try (FileInputStream fis = new FileInputStream(this.direccionConfig)) {
      this.prop.load(fis);
    } catch (FileNotFoundException ex) {
      System.out.println("No se encontro el archivo");
    } catch (IOException ex) {
      System.out.println("Error de IO");
    }
  }

  public int getLongitudMinima(){
    return this.longitudMinima;
  }
  public int getLongitudMaxima(){
    return this.longitudMaxima;
  }

  public String getCaracteresValidos(){
    return this.caracteresValidos;
  }

  public String getPath(){
    return this.pathTop10000Passwords;
  }
}
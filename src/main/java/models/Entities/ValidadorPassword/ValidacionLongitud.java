package models.Entities.ValidadorPassword;

public class ValidacionLongitud implements Validacion{

  private int longitudMinima;
  private int longitudMaxima;
  public ValidacionLongitud(int longitudMinima,int longitudMaxima){
    this.longitudMinima = longitudMinima;
    this.longitudMaxima = longitudMaxima;
  }

  @Override
  public boolean esValida(String password) {
    int passwordLength = password.length();
    return passwordLength >= this.longitudMinima && passwordLength <= this.longitudMaxima;
  }
}
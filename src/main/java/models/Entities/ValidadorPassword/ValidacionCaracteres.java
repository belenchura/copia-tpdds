package models.Entities.ValidadorPassword;

public class ValidacionCaracteres implements Validacion{

  String caracteresPermitidos;

  public ValidacionCaracteres(String caracteres){
    this.caracteresPermitidos = caracteres;
  }

  @Override
  public boolean esValida(String password) {
    for (char c : password.toCharArray()) {
      if (!this.caracteresPermitidos.contains("" + c)) {
        System.out.println("Caracteres Invalidos");
        return false;
      }
    }
    return true;
  }
}
package models.Entities.ValidadorPassword;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPassword {
  private List<Validacion> validaciones = new ArrayList<>();

  public ValidadorPassword(List<Validacion> validaciones) {
    this.validaciones = validaciones;
  }

  public ValidadorPassword() {
    PasswordConfig config = PasswordConfig.getPasswordConfig();
    this.validaciones.add(new ValidacionLongitud(config.getLongitudMinima(), config.getLongitudMaxima()));
    this.validaciones.add(new ValidacionDebiles(config.getPath()));
    this.validaciones.add(new ValidacionCaracteres(config.getCaracteresValidos()));
  }

  public boolean esValida(String password) {
    return this.validaciones.stream().allMatch(validacion -> validacion.esValida(password));
  }

}
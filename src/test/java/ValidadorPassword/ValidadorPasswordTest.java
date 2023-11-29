package ValidadorPassword;
import models.Entities.ValidadorPassword.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorPasswordTest {
  ValidadorPassword validador;
  PasswordConfig config = PasswordConfig.getPasswordConfig();

  @BeforeEach
  void iniciarValidador(){
    List<Validacion> validaciones = new ArrayList<Validacion>();
    validaciones.add(new ValidacionLongitud(config.getLongitudMinima(), config.getLongitudMaxima()));
    validaciones.add(new ValidacionDebiles(config.getPath()));
    validaciones.add(new ValidacionCaracteres(config.getCaracteresValidos()));
    this.validador = new ValidadorPassword(validaciones);
  }

  @Test
  void contraseñaSeEncuentraEnTop10000() {
    ValidacionDebiles val = new ValidacionDebiles(config.getPath());
    assertFalse(val.esValida("qwerty"));
  }

  @Test
  void contraseñaEsMenorAOchoCaracteres() {
    ValidacionLongitud val = new ValidacionLongitud(config.getLongitudMinima(), config.getLongitudMaxima());
    assertFalse(val.esValida("hjfdkls"));
  }

  @Test
  void contraseñaEsMayorAOchoCaracteres() {
    ValidacionLongitud val = new ValidacionLongitud(config.getLongitudMinima(), config.getLongitudMaxima());
    assertTrue(val.esValida("hjfdklshjfdkls"));
  }

  @Test
  void contraseñaEsMayorASesentaYCuatroCaracteres() {
    ValidacionLongitud val = new ValidacionLongitud(config.getLongitudMinima(), config.getLongitudMaxima());
    assertFalse(val.esValida("hjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdklshjfdkls"));
  }

  @Test
  void contraseñaConCaracteresInvalidos() {
    ValidacionCaracteres val = new ValidacionCaracteres(config.getCaracteresValidos());
    assertFalse(val.esValida("abcaØ"));
  }

  @Test
  void contraseñaSinCaracteresInvalidos() {
    ValidacionCaracteres val = new ValidacionCaracteres(config.getCaracteresValidos());
    assertTrue(val.esValida("abca"));
  }

  @Test
  void contraseñaConCaracteresInvalidosPeroLoDemasSi() {
    assertFalse(validador.esValida("abcafdfØ"));
  }

  @Test
  void contraseñaEsValida() {
    assertTrue(validador.esValida("abcafdf6d"));
  }
}

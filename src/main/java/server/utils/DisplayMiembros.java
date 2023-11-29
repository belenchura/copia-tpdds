package server.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayMiembros {
  private Long miembro_id;
  private String nombreCompleto;
  public DisplayMiembros(Long id, String nombre, String apellido){
    this.miembro_id = id;
    this.nombreCompleto = apellido + ' ' + nombre;
  }
}

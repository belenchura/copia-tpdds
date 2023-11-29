## Descripción de Toma de Decisiones de Diseño

Para lograr una mayor cohesión, hicimos que la clase ServicoPublico contenga una lista de la clase Estacion y que, a su vez, cada estación incluya una lista de la clase Servicio. De esta manera, logramos que cada clase tenga menor cantidad de responsabilidades, mejorando la mantenibilidad, analizabilidad, modularidad y reutilizabilidad del código.

Pensamos formas distintas de modelar los diferentes roles del sistema (usuario, administrador de comunidad y proveedor):

La primera forma que pensamos es que sólo quede la clase usuario con un atributo "rol" al cual se le puede asignar el objeto AdministradorDeComunidad, el objeto Proveedor o null (si no es administrador ni proveedor). Ventaja: alta extensibilidad ya que existe facilidad en caso de agregarse nuevos roles. Desventaja: un proveedor nunca va a poder administrar una comunidad (porque o es proveedor o es administrador). Teóricamente se podría crear un usuario aparte, pero esto no es posible por la restricción de la consigna que dice que debe haber un usuario por persona física real (por lo que el proveedor tendría dos cuentas y esto no se respetaría la consigna).

Otra forma que se nos ocurrió es que el usuario y el proveedor queden como dos clases distintas. De esta manera, el usuario contendría los métodos del administrador de comunidad y el proveedor quedaría como una clase aparte. Ventaja: mayor simplicidad, evitando complejidad al modelar los roles. Desventaja: baja flexibilidad en caso de agregarse nuevos roles ya que es probable que debamos hacer grandes modificaciones en ese contexto.
Finalmente, optamos por esta última forma.

Por otro lado, se pensó en modelar al Administrador de la Comunidad como una clase que hereda los atributos de la clase Usuario. Ya que el administrador iba a poder contar con las mismas funcionalidades que tenía un usuario, y además, funciones propias que se pensaron que debía tener un administrador, como por ejemplo “Agregar usuario a Comunidad” o “Eliminar usuario de Comunidad”. Finalmente, se decidió evitar todo tipo de herencia y que esos métodos se encuentren dentro de la clase Usuario.

Además, logramos alta robustez al utilizar manejo de excepciones en las clases Comunidad, Proveedor y Usuario.

En la clase Usuario habíamos agregado un método solicitarAltaEn(Comunidad). Pero finalmente decidimos quitarla por cuestiones de simplicidad, ya que agregaba funcionalidad nueva que no apuntaba a la problemática.

Por último, para la verificación de contraseñas, utilizamos la clase ValidadorPassword, la cual guarda una lista de validaciones, las cuales implementan una misma interfaz.

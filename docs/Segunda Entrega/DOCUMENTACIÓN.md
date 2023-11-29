Descripción de Toma de Decisiones de Diseño

Clase Entidad:
 Para modelar el tipo de entidad barajamos 3 opciones:
La primera alternativa que pensamos fue la de modelar el tipoEntidad como string. Esta alternativa fue descartada por un problema de consistencia de datos.
La segunda opción que propusimos fue la de modelar el tipo de entidad como una clase abstracta con un atributo leyenda. Esta alternativa solucionaba el problema de consistencia de datos y nos permitía una mayor flexibilidad.
La tercera opción que planteamos fue hacerlo como atributo de tipo enum. Esto quitaba el problema de consistencia de datos pero agregaba el problema de que no se podía agregar ni quitar tipos de entidad en tiempo de ejecución, es decir, no es extensible en tiempo de ejecución. Finalmente, optamos por esta alternativa debido a varias razones: en primer lugar, porque se trata de un conjunto finito de datos y no existe una alta frecuencia al cambio. En segundo lugar, porque por el momento no queremos agregar ni quitar tipos de entidad en tiempo de ejecución. Y por último, porque los diferentes tipos de entidad no poseen un comportamiento distinto.

Para modelar la clase Entidad contemplamos distintas alternativas como herencia, interface y composición pero siempre teniendo en mente subdividir la clase Entidad en ServicioPúblico y Organización. Más adelante, quitamos los métodos que permitían esta división (debido a que eran casos de uso) y decidimos modelarla como una única clase concreta.

Clase Establecimiento:
Modelamos el tipoEstablecimiento de manera análoga al tipoEntidad.
El hecho de haber modelado la clase Establecimiento como clase concreta deriva directamente de haber modelado al tipoEstablecimiento como un enum.
Decidimos cambiar el modo de como estaba implementada la Localización,  y lo hicimos como una clase aparte para lograr mayor cohesión.

Clase Servicio:
Barajamos las siguientes posibilidades para modelar los Servicios:
Clase concreta: Agregaba complejidad y además, no nos daba la opción de modelar paquetes de paquetes.
Por último, decidimos modelar la clase utilizando el patrón Composite ya que nos permite tratar de manera polimórfica a los servicios simples (hojas) y paquetes de servicios (contenedores) a través de la interfaz “Servicio”.
	
Clases Persona e Interes:
En un principio se pensó en modelar a los intereses como un atributo de la clase Persona, pero se decidió separar la clase Persona como un value object, obteniendo así mayor flexibilidad y cohesión.

Clases EntidadPrestadora y OrganismoDeControl:
En primer lugar, consideramos modelar las clases por separado como clases concretas. Debido a la necesidad de tratarlas polimórficamente para poder instanciarlas a través de la lectura de datos de un archivo CSV, tuvimos que forzar que ambas clases implementen una interfaz en común.

Clase Localización:
 Para modelar la clase Localización barajamos 3 opciones:
En primer lugar, pensamos en modelarla como una clase concreta. Sin embargo, con la utilización de la API REST tuvimos que realizar rápidamente un refactor ya que no cumplía con nuestros requerimientos.
En segundo lugar, consideramos la utilización de herencia. Esta alternativa no aprovechaba ninguna ventaja de la herencia, ya que se sobreescribían todos los métodos, sin aprovechar en ningún momento los implementados por la clase padre. Existe un gran acoplamiento entre clases padre e hijo.
Por último, propusimos que los distintos tipos de localización implementen una interfaz común. Esta alternativa cumple con los requisitos y ofrece el menor acoplamiento posible entre clases.

Componente CSV:
Para la lectura del archivo se optó por utilizar un patrón adapter para, en caso de que la biblioteca utilizada quede deprecada, podamos fácilmente desacoplarnos de ella, y de esta manera, implementar o bien otra solución para realizar la carga de datos u otra biblioteca externa.
Por otra parte, para modelar las estrategias de instanciación, se implementó el patrón Strategy, el cual nos permite encapsular distintas formas de resolver la instanciación, ya sea para un Organismo de Control o para una Entidad Prestadora, en diferentes clases. Además, nos permite cambiar la estrategia de instanciación en el momento de ejecución.

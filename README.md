# CI-3641-Scala-Con
Pequeño programa en `Scala 3.1.3` que utiliza varios métodos de concurrencia 
para realizar el producto punto, y contar la cantidad de archivos que hay en un
directorio y sus subdirectorios.

Para el producto punto, se utilizan las siguientes implementaciones:
- Futures para cada componente con variables atómicas.
- Un solo Future para todo el cálculo.
- Colecciones paralelas.

Y para el conteo de archivos simplemente se utilizan los threads de Java.

### Requisitos
- Scala 3.1.3
- sbt

### Instrucciones de buildeo y ejecución
Para buildear es necesario ubicarse en el directorio raíz del proyecto y ejecutar
el siguiente comando:

	sbt compile

Para ejecutar el programa ejecute el siguiente comando en el directorio raíz del 
proyecto:

	sbt "run PATH"

Donde PATH es el directorio a explorar.

### Más información
- [Página oficial de Scala](https://www.scala-lang.org/ "Página oficial de Scala")
- [Manual de referencia sbt](https://www.scala-sbt.org/1.x/docs/index.html "Manual de referencia sbt")
- [Parallel Collections](https://docs.scala-lang.org/overviews/parallel-collections/overview.html "Parallel Collections")

# DulceEstacion_EJ4
Información que se debe conservar:
Dulce Estación se encuentra con un problema de organización, se debe organizar los siguientes aspectos dentro de un inventario:
Máquina de algodón de azúcar
Código de inventario
Marca
Modelo
Tarifa diaria
Disponibilidad
Potencia en vatios

Fuente de chocolate
Código de inventario
Marca
Modelo
Tarifa diaria
Disponibilidad
Capacidad máxima en Kg.

Máquina de palomitas
Código de inventario
Marca
Modelo
Tarifa diaria
Disponibilidad
Porción por hora
Carrito integrado (bool)

Dinero conservado
Días de alquiler
Clases

Clase Maquina
Atributos:
- codigo: String
Propósito: identificar la máquina.
- marca: String
Propósito: almacenar la marca.
- modelo: String
Propósito: almacenar el modelo.
# tarifaDiaria: double
Propósito: guardar el precio diario del alquiler. Las clases hijas pueden utilizarlo para calcular el costo.
Métodos:
# Maquina(codigo: String, marca: String, modelo: String, tarifaDiaria: double)
Propósito: inicializar los datos comunes de una máquina.
Parámetros: código, marca, modelo y tarifa diaria.
Resultado: crea e inicializa la parte común del objeto.
+ getCodigo(): String
Propósito: obtener el código de la máquina.
Resultado: un String.
+ estaDisponible(): boolean
Propósito: indicar si la máquina está disponible.
Resultado: true si está disponible y false en caso contrario.
+ getCategoria(): String
Propósito: obtener la categoría de la máquina.
Resultado: un String.
+ calcularCosto(dias: int): double
Propósito: calcular el costo del alquiler.
Parámetro: cantidad de días del alquiler.
Resultado: costo total de tipo double.

Clase MaquinaPalomitas
Atributos:
- porcionesPorHora: int
Propósito: almacenar la cantidad de porciones que produce por hora.
- tieneCarrito: boolean
Propósito: indicar si la máquina incluye carrito.
Métodos:
+ MaquinaPalomitas(codigo: String, marca: String, modelo: String, tarifaDiaria: double, porcionesPorHora: int, tieneCarrito: boolean)
Propósito: crear una máquina de palomitas.
Parámetros: los datos comunes de la máquina, las porciones producidas por hora y la existencia de carrito.
Resultado: un objeto MaquinaPalomitas inicializado.

+ getCategoria(): String
Propósito: devolver la categoría de la máquina.
Resultado: un String.
+ calcularCosto(dias: int): Double
Propósito: calcular el costo del alquiler de una máquina de palomitas.
Parámetro: número de días.
Resultado: costo de tipo Double.

Clase MaquinaAlgodon
Atributos:
- potenciaVatios: int.
Propósito: almacenar la potencia de la máquina en vatios.
Métodos:
+ MaquinaAlgodon(codigo: String, marca: String, modelo: String, tarifaDiaria: double, potenciaVatios: int)
Propósito: crear una máquina de algodón.
Parámetros: código, marca, modelo, tarifa diaria y potencia en vatios.
Resultado: un objeto MaquinaAlgodon inicializado.
+ getCategoria(): String
Propósito: devolver la categoría de la máquina.
Resultado: un String.
+ calcularCosto(dias: int): Double
Propósito: calcular el costo del alquiler de esta categoría.
Parámetro: número de días.
Resultado: costo de tipo Double.


Clase FuenteChocolate
Atributos:
- capacidadKg: double
Propósito: almacenar la capacidad de la fuente en kilogramos.
Métodos:
+ FuenteChocolate(codigo: String, marca: String, modelo: String, tarifaDiaria: double, capacidadKg: double)
Propósito: crear una fuente de chocolate.
Parámetros: código, marca, modelo, tarifa diaria y capacidad en kilogramos.
Resultado: un objeto FuenteChocolate inicializado.
+ getCategoria(): String
Propósito: devolver la categoría de la máquina.
Resultado: un String.
+ calcularCosto(dias: int): Double
Propósito: calcular el costo del alquiler de una fuente de chocolate.
Parámetro: número de días.
Resultado: costo de tipo Double.



Clase DulceEstacion
Atributos:
- maquina[]: array
Propósito: almacenar el conjunto de máquinas registradas.
- ingresosAcumulados: double
Propósito: guardar el total de ingresos obtenidos.
Métodos:
+ DulceEstacion()
Propósito: crear el objeto encargado de administrar las máquinas.
Resultado: una instancia de DulceEstacion.
+ registrarMaquina(maquina: Maquina): void
Propósito: agregar una máquina al arreglo.
Parámetro: un objeto de tipo Maquina.
Resultado: no devuelve ningún valor.


+ buscarMaquina(...)
Visibilidad: pública.
Propósito: localizar una máquina registrada.
El UML mostrado no permite observar completamente sus parámetros ni su tipo de retorno, por lo que no deben inventarse.
Clase Main
Método:
+ Main(): void
Visibilidad: pública.
Propósito: iniciar la ejecución del sistema.
Resultado: no devuelve ningún valor.
Justificación
La organización propuesta utiliza la herencia para separar los elementos comunes de las características particulares de cada categoría. La clase Maquina concentra la información compartida por todas las máquinas, como el código de inventario, la marca, el modelo, la tarifa diaria y la disponibilidad. A partir de esta clase se derivan MaquinaPalomitas, MaquinaAlgodon y FuenteChocolate, las cuales incorporan únicamente los atributos propios de su categoría. De esta manera, se evita repetir información y comportamiento común en cada clase.
Las decisiones relacionadas con el cobro se distribuyen entre las clases según la categoría de la máquina. Cada clase implementa el método calcularCosto(dias) de acuerdo con sus condiciones particulares. La máquina de palomitas considera el recargo diario cuando posee carrito integrado, la máquina de algodón aplica un cargo adicional cuando su potencia supera los 1000 vatios y la fuente de chocolate calcula un recargo según su capacidad máxima en kilogramos. Esto permite que cada objeto sea responsable de determinar su propio costo de alquiler.
La clase DulceEstacion administra el conjunto de máquinas y coordina las operaciones necesarias para realizar un alquiler. Esta clase localiza la máquina mediante su código, comprueba su disponibilidad y utiliza el método calcularCosto(dias) del objeto correspondiente para obtener el total. 
Al confirmarse el alquiler, se actualiza la disponibilidad de la máquina y el monto pagado se incorpora a los ingresos acumulados. Así, DulceEstacion se encarga de administrar la operación, mientras que cada máquina conserva la responsabilidad de calcular su costo según su categoría. Si en el futuro se incorpora una nueva categoría, sería necesario crear una nueva clase derivada de Maquina, agregar sus atributos particulares e implementar el cálculo de costo correspondiente. 

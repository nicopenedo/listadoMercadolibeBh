# listadoMercadolibreBh

## Enunciado
Se solicita crear un programa que liste marcas de motos, junto al precio promedio de todas las motos publicadas en mercadolibre para cada marca. Para ello se utilizará la api pública de mercadolibre.
Los pasos a realizar son los siguientes:
1. Obtener el id de la categoría de motos (no es necesario que el programa haga este paso, pero si hay que hacerlo manualmente):
https://developers.mercadolibre.com.ar/es_ar/categorias-y-publicaciones
2. Una vez obtenido ese id (podemos hardcodear este id en el programa), lo utilizaremos para listar todas las motos publicadas:
https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas
3. Para cada marca de moto (ej: Kawasaki, Yamaha, Honda) obtener el promedio de precio de todas las motos de esa marca.
Para el cálculo de este promedio sólo deben utilizarse las motos que sean nuevas (no usadas).
Mercadolibre devuelve la información paginada (paginar hasta el registro 900), llamar a cada página de forma secuencial se considera una solución correcta pero se valora el uso de paralelismo.
4. Imprimir por consola una lista de marcas de motos junto con el promedio de precio para cada marca.

## Características de la solución
- Implementación en forma de servicio con spring boot.
- Permite que se utilice la lógica solicitada para cualquier categoría (Si se define en el path una categoria especifica)
- En el caso de que no se defina una categoría específica, toma por defecto la categoría "Motos" = "MLA1763"
- Resultados impresos en consola y en la respuesta del servicio en formato JSON.
- Dos tipos de monedas:
  - ARS
  - USD
- Dos soluciones/proyectos:
  - Llamada secuencial: promPrecioMarcasML
  - Paralelismo: promPrecioMarcasMLParalelismo

## Pruebas
-  Los dos servicios responden al mismo puerto, comportamiento de llamada y respuesta del servicio.
-  Se sugiere utilizar POSTMAN para las pruebas
-  Método de petición GET
-  Host y puerto:
   -  localhost:8111
-  Path del servicio:
   - /examen/prompreciomarca/categoria                    --> Toma la categoría por defecto
   - /examen/prompreciomarca/categoria/{codigoCategoria}  --> Toma la categoría suministrada
-  Categorías de ejemplo
   -  "Motos" = "MLA1763"
   -  "Celulares y Teléfonos" = "MLA1051"
- Ejemplos:
   - `localhost:8111/examen/prompreciomarca/categoria`
   - `localhost:8111/examen/prompreciomarca/categoria/MLA1051`

## Resultados
Los dos servicios devuelven los mismos resultados, la diferencia principal se encuentra en la velocidad de respuesta. En este caso los resultados hacen referencia a la categoria "Motos"
### Respuesta en consola
```text
Resultado en consola del examen:
promedioPrecioPorMarcaARS={Jawa=319897.14, Guzzi=1620000.0, KTM=373247.5, Elpra=149987.5, Gilera=152353.5, RVM=298990.0, Emuv=195000.0, Triumph=1620000.0, 150=10378.0, Honda=342050.05, Beta=543787.5, CRONOS=489000.0, TVS=308147.22, Kymco=285420.71, Sunra=169914.76, okn=65000.0, CFMOTO=1542510.0, Zanella=150781.2, Suzuki=181863.33, galix=107000.0, City Coco=169990.0, Keller=76903.33, Bajaj=377322.6, Yamaha=491060.93, Keeway=199833.33, Corven=132673.85, Jianshe=117800.0, Moto Guzzi=2310000.0, GALIX=106920.0, SYM=199900.0, rvm=629990.0, Guerrero=197750.0, Pagani=56764.67, Nuuv=410643.0, Super Soco=696500.0, Aprilia=2950000.0, Motomel=143237.49, Galix=65000.0, Hero=229950.0, Keller Zanella Motomel=57990.0, Mondial=133539.0, RVM JAWA=1108000.0, Benelli=783952.57, Kawasaki=527496.67, VOGE=1452500.0},
promedioPrecioPorMarcaUSD={Moto Guzzi=18495.0, KTM=16272.62, MV Agusta=28000.0, Piaggio=9950.0, Supersoco=2199.0, Ducati=28777.83, BMW=28300.0, Honda=20885.71, Kymco=7625.0, CFMOTO=20500.0, Aprilia=18900.0, Can-Am=27586.57, Motomel=412.0, Suzuki=8700.0, Royal Enfield=7157.33, Husqvarna=16188.25, Yamaha=23325.0, Kawasaki=19690.62, Vespa=3650.0}
```
### Respuesta del servicio
```json
{
    "promedioPrecioPorMarcaARS": {
        "Jawa": 319897.14,
        "Guzzi": 1620000.0,
        "KTM": 373247.5,
        "Elpra": 149987.5,
        "Gilera": 152353.5,
        "RVM": 298990.0,
        "Emuv": 195000.0,
        "Triumph": 1620000.0,
        "150": 10378.0,
        "Honda": 342050.05,
        "Beta": 543787.5,
        "CRONOS": 489000.0,
        "TVS": 308147.22,
        "Kymco": 285420.71,
        "Sunra": 169914.76,
        "okn": 65000.0,
        "CFMOTO": 1542510.0,
        "Zanella": 150781.2,
        "Suzuki": 181863.33,
        "galix": 107000.0,
        "City Coco": 169990.0,
        "Keller": 76903.33,
        "Bajaj": 377322.6,
        "Yamaha": 491060.93,
        "Keeway": 199833.33,
        "Corven": 132673.85,
        "Jianshe": 117800.0,
        "Moto Guzzi": 2310000.0,
        "GALIX": 106920.0,
        "SYM": 199900.0,
        "rvm": 629990.0,
        "Guerrero": 197750.0,
        "Pagani": 56764.67,
        "Nuuv": 410643.0,
        "Super Soco": 696500.0,
        "Aprilia": 2950000.0,
        "Motomel": 143237.49,
        "Galix": 65000.0,
        "Hero": 229950.0,
        "Keller Zanella Motomel": 57990.0,
        "Mondial": 133539.0,
        "RVM JAWA": 1108000.0,
        "Benelli": 783952.57,
        "Kawasaki": 527496.67,
        "VOGE": 1452500.0
    },
    "promedioPrecioPorMarcaUSD": {
        "Moto Guzzi": 18495.0,
        "KTM": 16272.62,
        "MV Agusta": 28000.0,
        "Piaggio": 9950.0,
        "Supersoco": 2199.0,
        "Ducati": 28777.83,
        "BMW": 28300.0,
        "Honda": 20885.71,
        "Kymco": 7625.0,
        "CFMOTO": 20500.0,
        "Aprilia": 18900.0,
        "Can-Am": 27586.57,
        "Motomel": 412.0,
        "Suzuki": 8700.0,
        "Royal Enfield": 7157.33,
        "Husqvarna": 16188.25,
        "Yamaha": 23325.0,
        "Kawasaki": 19690.62,
        "Vespa": 3650.0
    }
}
```
### Tiempo de respuesta
#### Llamada secuencial (promPrecioMarcasML)
![](https://github.com/nicopenedo/listadoMercadolibeBh/blob/main/resultados/promPrecioMarcasML.png)
#### Paralelismo (promPrecioMarcasMLParalelismo)
![](https://github.com/nicopenedo/listadoMercadolibeBh/blob/main/resultados/promPrecioMarcasMLParalelismo.png)



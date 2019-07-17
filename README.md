NX01

===IMPORTANTE===
el proyecto necesita una base de datos, el dump está en la carpeta de database

el proyecto también define el día inicial en el applications.properties que se encuenta en 
src\main\resources

listado de servicios:

/api/WeatherPeriod/drought/count
retorna el conteo de días de sequía desde el día inicial hasta 10 años en el futuro

/api/WeatherPeriod/rain/count
retorna el conteo de días de lluvia con una lista de días de lluvia desde el día inicial hasta 10 años en el futuro

/api/WeatherPeriod/optimal/count
retorna el conteo de períodos óptimos desde el día inicial hasta 10 años en el futuro

/api/SolarSystemSnapshot/date/{date}
retorna un snapshot del día por fecha ingresada, admite cualquier fecha
formato de fecha: dd-MM-yy

/api/SolarSystemSnapshot/day/{day}
retorna un snapshot del día por el número de día ingresado, admite un rango desde 0 hasta 3652
# appGames

Aplicación que permite la gestión de un catálogo de Video Juegos permitiendo realizar un CRUD

## características

- Código escrito en Lenguaje [Kotlin](https://kotlinlang.org/)
- Implementación principios SOLID.
- Implementación del patrón de arquitectura MVVM.
- Implementación de principios de arquitectura Limpia (Clean Architecture).
- Dependencias de inyección
  con [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- Consumos de [REST API] con (https://www.freetogame.com/api/games)
- Llamadas de API segura con [Retrofit](https://github.com/square/retrofit)
- Manejo de trazas para visualización de consumos en Log
  con [OkHttpClient](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/)
- Diseño de interfaz declarativa
- Observación de cambios implementando [mutableStateOf] desde el ViewModel
- Navegación entre vistas implementando [NavController]-
- Pruebas unitarias con [JUnit][Mockk](https://mockk.io/)

## Requisitos

- Desarrollar una aplicación que permita gestionar de manera eficiente el catálogo de videojuegos
  descargado y permita a los usuario interactuar con el.
- Búsqueda en el catalogo descargado de videojuegos aplicando filtros por criterios como; Nombre,
  categoría y ítem del videojuego.
- Vista de detalle el usuario podrá ver el detalle del video juego seleccionado, mostrando los
  siguientes detalles; Title, thumbail, short_description
- Edición de registro, el usuario podrá editar el registro seleccionado
- Eliminación de ítem, el usuario podrá eliminar el ítem seleccionado
- No indispensable pero se reconoce si se agrega la implementan Test

## Instalacion

git clone  https://github.com/CarlosNirari/interactive-appGames.git


<h1 align="center">POKEDEX APP</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
Aplicación demostrativa para obtener la informacion base de los pokemon utilizando la api publica de https://pokeapi.co/
</p>
</br>

<p align="center">
<img src="/presentation/preview_app.jpg"/>
</p>

## Tecnologias y librerias usadas
- Minimum SDK level 24.
- Hilt for dependency injection.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
- Architecture
    - MVVM (View - DataBinding - ViewModel - Model)
    - Repository pattern.
    - Clean architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette) - loading images.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.


## Arquitectura
 MVVM con Clean Architecture

<p align="center">
<img src="/presentation/arch_prev_2.png"/>
</p>
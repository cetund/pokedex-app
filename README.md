<h1 align="center">POKEDEX APP</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
Aplicación demostrativa para obtener la informacion base de los pokemon utilizando la api publica de https://pokeapi.co/
</p>
</br>

<p align="center">
<img src="/presentation/home_prev.jpg"  width="220"/>
<img src="/presentation/about_prev.jpg"  width="220"/>
<img src="/presentation/stats_prev.jpg"  width="220"/>
<img src="/presentation/evolution_prev.jpg"  width="220"/>
</p>

<img src="/presentation/previewanimation.gif" align="right" width="320"/>


</br>
</br>

<p>
  
## Tecnologias y librerias usadas
- Lenguaje
    - Java 17
- Nivel minimo del SDK 24.
- Dagger hilt 2 para inyeccion de dependencias.
- JetPack
    - Lifecycle - para controlar los datos de observación cuando cambie el estado del lifecycle
    - ViewModel - para controlar datos relacionados con la UI, el mediador del lifecycle
- Arquitectura
    - MVVM (View - DataBinding - ViewModel - Model)
    - Repository pattern.
    - Clean architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - para construir REST APIs y paginacion de datos.
- [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette) - presentador de imagenes.
- [Material-Components](https://github.com/material-components/material-components-android) - Componentes Material design como ripple animation, cardView.
</p>

<br/>
</br>


<p>

## Diseño de arquitectura
MVVM con Clean Architecture
<img src="/presentation/arch_prev_2.png"/>

</p>




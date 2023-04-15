# LoginApiRest
_Escenario practico consumo de API REST usando Volley._

_Aplicación desarrollada en kotlin usando la arquitectura MVVC, que presenta información obtenida mediante un consumo API REST._

_Se implementó la librería Volley en su versión 1.2.1 para realizar el consumo._

_La aplicacion permite un login sencillo el cual habilta un boton de ingresar el cual te envia a una actividad en donde se presenta una lista de users._

_Los datos presentados son en la ventana de users es: img_profile, name, lastName, email._

## Instrucciones de instalacion

_El proyecto se encuentra en la rama master._

_La clonacion del proyecto se realiza mediente la siguiente Url: https://github.com/barbosaivan/LoginApiRest.git_

_El nivel de API minimo requerido para la instalacion del la aplicacion es: 24_

_El android gradle plugin version usado es: 7.4.0_

_La aplicacion requiere permisos de internet_

## Funcionalidad
_El correo admitido para un login y resgistro exito es: eve.holt@reqres.in_

_La aplicación muestra la información requerida de los users en contenedores tipo cardView_

_La aplicación cuenta con una interfaz escroleable que muestra un listado de gatos_

## Url API REST
_https://reqres.in/api/register_
_https://reqres.in/api/login_
_https://reqres.in/api/users?page=1_

## Formato de la informacion recibida
_https://reqres.in/api/register:_
_{
    "id": 4,
    "token": "QpwL5tke4Pnpja7X4"
}_

_https://reqres.in/api/login:_
_{
    "token": "QpwL5tke4Pnpja7X4"
}_

_https://reqres.in/api/users?page=1:_
_{
    "page": 2,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 7,
            "email": "michael.lawson@reqres.in",
            "first_name": "Michael",
            "last_name": "Lawson",
            "avatar": "https://reqres.in/img/faces/7-image.jpg"
        },
        {
            "id": 8,
            "email": "lindsay.ferguson@reqres.in",
            "first_name": "Lindsay",
            "last_name": "Ferguson",
            "avatar": "https://reqres.in/img/faces/8-image.jpg"
        },
        {
            "id": 9,
            "email": "tobias.funke@reqres.in",
            "first_name": "Tobias",
            "last_name": "Funke",
            "avatar": "https://reqres.in/img/faces/9-image.jpg"
        },
        {
            "id": 10,
            "email": "byron.fields@reqres.in",
            "first_name": "Byron",
            "last_name": "Fields",
            "avatar": "https://reqres.in/img/faces/10-image.jpg"
        },
        {
            "id": 11,
            "email": "george.edwards@reqres.in",
            "first_name": "George",
            "last_name": "Edwards",
            "avatar": "https://reqres.in/img/faces/11-image.jpg"
        },
        {
            "id": 12,
            "email": "rachel.howell@reqres.in",
            "first_name": "Rachel",
            "last_name": "Howell",
            "avatar": "https://reqres.in/img/faces/12-image.jpg"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}_

 
 ## Herramientas
 * androidx
 
 * google material
 
 * volley
 
 * Glide
 
 ## Dependencias implementasdas
 
* ViewModel

 _implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"_

* Dependecia andoroid ktx

_implementation("androidx.activity:activity-ktx:1.6.1")_

* volley

_implementation 'com.android.volley:volley:1.2.1'_

* Gluide

_implementation 'com.github.bumptech.glide:glide:4.15.1'_
 
 ## Autor
 _Ivan Barbosa Ortega_
 
 _Estudiante de Ingenieria de sistemas_

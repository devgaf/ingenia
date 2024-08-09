# **Travell optization**

Challenge para Ingenia

## **Documentacion**

> Se Utiliza Lombok para la generacion automatica de constructores, getters y setters

### Descripcion de Entidades

* **Station**


| Attribute | Type | Description |
| --- | --- | --- |
| id | int | Unique identifier of the station |
| name | string | Name of the station |

* **Path**

| Attribute | Type | Description |
| --- | --- | --- |
| id | int | Unique identifier of the path |
| cost | float | Cost of the path |
| sourceStation | Station | Origin station of the path |
| destinationStation | Station | Destination station of the path |

* **ShortestPathResult**

| Attribute | Type | Description |
| --- | --- | --- |
| id | path |list of paths |
| cost | float | Cost of the path |

------------------------------------
### RESTful Web Service

* **PUT /stations/$station_id**: Crea e inserta una station
```
Request Body: 
	 { 
	    "name":string 
	 }  
Response body
    {
        "status": "ok"
    }
```

* **PUT /paths/$path_id** : Crea e inserta un path
```
Request Body: 
	 { 
	    "cost":double, 
	    "source_id":long, 
	    "destination_id":long 
	 }  
Response body
    {
        "status": "ok"
    }
```
* **GET /paths/$source_id/$destination_id**: Calcula el camino mas optimo y costo 
```
Request Body: 
	 { 
	 }  
Response body
    {
        "path": [long], 
        "cost" : double
    }
```

------------------------------------
## Ejecucion del proyecto de forma Local

Clonar el proyecto

```bash
  git clone https://github.com/devgaf/travell-optization.git
```

Ir al directorio del proyecto

```bash
  cd travellOptization
```

Instalar depndencias

```bash
  mvn clean install
```

Poner en marcha el servidor

```bash
  mvn spring-boot:run
```
---
---

## 🚀 Acerca de mi
### Hola, soy Gaston Fernandez👋 
> *Desarrollador de sistemas*

Puede vistiar mi [LinkedIn] (https://www.linkedin.com/in/gastonfdz)
### 🛠 Skills
- Java 
    - Spring Boot
    - JPA
- SQL
- Angular 
- Bootstrap
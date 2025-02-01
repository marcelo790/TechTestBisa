# Documentación de la API de Blogs

## **Descripción**
Esta API permite gestionar blogs, autores y comentarios. Soporta la creación, edición y eliminación de blogs, además de mantener un historial de cambios en la información de cada blog.

---

## **Endpoints**

### **1. Blogs**

#### **1.1 Crear un blog**
- **POST** `/blogs`
- **Descripción:** Crea un nuevo blog.
- **Request Body (JSON):**
```json
{
  "title": "Mi primer blog",
  "theme": "Tecnología",
  "content": "Este es el contenido del blog",
  "periodicity": "Semanal",
  "allowsComments": true,
  "authorId": 1
}
```
- **Responses:**
  - `201 Created`: Blog creado correctamente.
  - `404 Not Found`: Error en la creación del blog.

#### **1.2 Obtener todos los blogs**
- **GET** `/blogs`
- **Descripción:** Retorna la lista de blogs.
- **Response:**
```json
[
  {
    "id": 1,
    "title": "Mi primer blog",
    "theme": "Tecnología",
    "content": "Este es el contenido del blog",
    "periodicity": "Semanal",
    "allowsComments": true,
    "author": {
      "id": 1,
      "firstName": "Juan",
      "lastName": "Pérez"
    }
  }
]
```

#### **1.3 Obtener un blog por ID**
- **GET** `/blogs/:id`
- **Descripción:** Retorna un blog según su ID.
- **Response:**
```json
{
  "id": 1,
  "title": "Mi primer blog",
  "theme": "Tecnología",
  "content": "Este es el contenido del blog",
  "allowsComments": true
}
```

#### **1.4 Actualizar un blog**
- **PUT** `/blogs/:id`
- **Descripción:** Actualiza los datos de un blog y registra los cambios en el historial.
- **Request Body (JSON):**
```json
{
  "title": "Nuevo Título",
  "content": "Contenido actualizado",
  "allowsComments": false
}
```
- **Response:**
```json
"Blog actualizado correctamente"
```

#### **1.5 Actualizar disponibilidad de comentarios**
- **PUT** `/blogs/:id/allows-comments`
- **Descripción:** Actualiza si un blog permite comentarios y lo registra en el historial.
- **Request Body (JSON):**
```json
{
  "allowsComments": false
}
```
- **Response:**
```json
"Disponibilidad de comentarios actualizada y registrada en el historial"
```

#### **1.6 Obtener historial de cambios de un blog**
- **GET** `/blogs/:id/history`
- **Descripción:** Retorna el historial de cambios del blog.
- **Response:**
```json
[
  {
    "id": 4,
    "blogId": 1,
    "field": "allowsComments",
    "oldValue": "true",
    "newValue": "false",
    "timestamp": "2025-02-01T15:15:00"
  }
]
```

---

### **2. Autores**

#### **2.1 Crear un autor**
- **POST** `/authors`
- **Descripción:** Crea un nuevo autor.
- **Request Body (JSON):**
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "country": "México"
}
```
- **Response:**
```json
"Autor creado correctamente"
```

#### **2.2 Obtener todos los autores**
- **GET** `/authors`
- **Descripción:** Retorna la lista de autores.

#### **2.3 Obtener un autor por ID**
- **GET** `/authors/:id`
- **Descripción:** Retorna un autor según su ID.

---

### **3. Comentarios**

#### **3.1 Agregar un comentario a un blog**
- **POST** `/blogs/:id/comments`
- **Descripción:** Agrega un comentario a un blog si los comentarios están habilitados.
- **Request Body (JSON):**
```json
{
  "name": "Pedro",
  "email": "pedro@example.com",
  "country": "Argentina",
  "rating": 8,
  "content": "Me gustó mucho este blog!"
}
```
- **Responses:**
  - `201 Created`: Comentario agregado correctamente.
  - `400 Bad Request`: No se pueden agregar comentarios a este blog.

#### **3.2 Obtener comentarios de un blog**
- **GET** `/blogs/:id/comments`
- **Descripción:** Retorna los comentarios de un blog.
- **Response:**
```json
[
  {
    "id": 1,
    "name": "Pedro",
    "content": "Me gustó mucho este blog!",
    "rating": 8
  }
]
```

---

## **Códigos de Estado**
- `200 OK` - Respuesta exitosa.
- `201 Created` - Recurso creado correctamente.
- `400 Bad Request` - Error en la solicitud.
- `404 Not Found` - Recurso no encontrado.
- `500 Internal Server Error` - Error en el servidor.



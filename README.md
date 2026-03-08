# PROYECTO LITERALURA - BIBLIOTECA DIGITAL

## DESCRIPCIÓN DEL PROYECTO
**LiterAlura** es una robusta aplicación de consola desarrollada en **Java 17** utilizando el framework **Spring Boot 3**. El sistema permite gestionar una biblioteca personal interactuando con la API internacional **Gutendex**.

El programa no solo busca libros en tiempo real, sino que los **almacena de forma persistente** en una base de datos **PostgreSQL**, permitiendo consultas avanzadas sobre autores y obras registradas.

---

## TECNOLOGÍAS UTILIZADAS
- **Lenguaje:** Java 17 (JDK 17+)
- **Framework:** Spring Boot 3.3.2
- **Persistencia:** Spring Data JPA / Hibernate
- **Base de Datos:** PostgreSQL 16+
- **Gestor de Dependencias:** Maven
- **API Externa:** [Gutendex API](https://gutendex.com/)

---

## FUNCIONALIDADES PRINCIPALES
El sistema cuenta con un menú interactivo con las siguientes opciones:

1. **`Buscar libro por título`** — Conecta con la API, muestra los datos y guarda el libro/autor en la base de datos (evita duplicados).
2. **`Listar libros registrados`** — Muestra todos los libros almacenados localmente.
3. **`Listar autores registrados`** — Muestra los autores con sus fechas y la **lista completa de sus libros** guardados.
4. **`Listar autores vivos en un determinado año`** — Filtra autores por cronología.
5. **`Listar libros por idioma`** — Filtra por códigos (`es`, `en`, `fr`, `pt`).

---

## CONFIGURACIÓN DE LA BASE DE DATOS (CRÍTICO)
Para que el programa funcione correctamente, es **obligatorio** configurar el entorno de base de datos siguiendo estos pasos:

### 1. Backup de PostgreSQL
En la raíz del proyecto se incluye un archivo de **Backup** llamado:
> **`literalura`**

### 2. Creación de la Base de Datos
Debe crear una base de datos en su servidor PostgreSQL con el nombre exacto:

| Parámetro | Valor |
|-----------|-------|
| Nombre BD | `literalura` |
| Usuario   | `postgres` |
| Password  | `root` |

### 3. Restauración
Utilice **pgAdmin 4** o la línea de comandos para restaurar el backup en la base de datos recién creada:

```bash
psql -U postgres -d literalura -f literalura
Nota: Si su contraseña de PostgreSQL no es root, debe modificar el archivo src/main/resources/application.properties antes de ejecutar el programa.

# 🚀 API de Gestión de Proyectos

Una API REST desarrollada con **Spring Boot** para la gestión de proyectos, usuarios y roles con autenticación JWT y base de datos PostgreSQL.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Uso con Docker](#-uso-con-docker)
- [API Endpoints](#-api-endpoints)
- [Autenticación](#-autenticación)
- [Testing](#-testing)
- [Configuración de Perfiles](#-configuración-de-perfiles)
- [Contribución](#-contribución)

## ✨ Características

- 🔐 **Autenticación JWT** - Sistema de autenticación seguro
- 👥 **Gestión de Usuarios** - CRUD completo de usuarios
- 🏗️ **Gestión de Proyectos** - Administración de proyectos
- 🎭 **Sistema de Roles** - ADMIN, LIDER, DESARROLLADOR
- 🗄️ **Base de Datos PostgreSQL** - Persistencia robusta
- 🐳 **Docker Support** - Contenedorización completa
- 📚 **Documentación OpenAPI** - Swagger UI integrado
- 🧪 **Testing** - Tests unitarios y de integración
- 🔒 **Seguridad** - Spring Security configurado

## 🏗️ Desarrollo del Proyecto

### Fase 1: Configuración Inicial

- ✅ **Spring Boot 3.5.5** con Java 17
- ✅ **Maven** como gestor de dependencias
- ✅ **Spring Security** para autenticación y autorización
- ✅ **Spring Data JPA** para persistencia de datos
- ✅ **H2 Database** para desarrollo y testing

### Fase 2: Entidades y Modelos

- ✅ **Entidad Usuario** con campos: id, nombre, email, password, roles
- ✅ **Entidad Rol** con campos: id, nombre
- ✅ **Entidad Proyecto** con campos: id, nombre, descripción
- ✅ **Relaciones Many-to-Many** entre Usuario y Rol
- ✅ **Repositorios JPA** para cada entidad

### Fase 3: Autenticación y Seguridad

- ✅ **JWT (JSON Web Tokens)** para autenticación stateless
- ✅ **JwtUtil** para generar y validar tokens
- ✅ **JwtRequestFilter** para interceptar requests
- ✅ **UserDetailsServiceImpl** para cargar usuarios
- ✅ **SecurityConfig** con configuración de endpoints protegidos

### Fase 4: Controladores y Servicios

- ✅ **AuthController** para login y registro
- ✅ **UsuarioController** con endpoints CRUD
- ✅ **ProyectoController** con endpoints CRUD
- ✅ **UsuarioService** con lógica de negocio
- ✅ **ProyectoService** con lógica de negocio

### Fase 5: DTOs y Validación

- ✅ **CrearUsuarioDataTransferObject** para registro
- ✅ **LoginRequest/Response** para autenticación
- ✅ **CrearProyectoDataTransferObject** para proyectos
- ✅ **Validaciones** con Spring Validation
- ✅ **Manejo de errores** personalizado

### Fase 6: Inicialización de Datos

- ✅ **DataInitializer** con CommandLineRunner
- ✅ **Roles predefinidos**: ADMIN, LIDER, DESARROLLADOR
- ✅ **Usuarios de prueba** con roles asignados
- ✅ **Proyecto inicial** para testing

### Fase 7: Testing

- ✅ **UsuarioServiceTest** con Mockito
- ✅ **UsuarioControllerTest** con MockMvc
- ✅ **ApiApplicationTests** de integración
- ✅ **Configuración de test** con H2 en memoria
- ✅ **@Transactional** para rollback automático

### Fase 8: Documentación API

- ✅ **OpenAPI 3.0** con Swagger UI
- ✅ **OpenApiConfig** para configuración
- ✅ **Anotaciones** en controladores
- ✅ **Documentación** de endpoints y modelos

### Fase 9: Configuración de Perfiles

- ✅ **Perfil DEV** con H2 Database
- ✅ **Perfil PROD** con PostgreSQL
- ✅ **Properties** específicos por ambiente
- ✅ **Variables de entorno** configurables

### Fase 10: Docker y Contenedorización

- ✅ **Dockerfile** multi-stage para optimización
- ✅ **docker-compose.yml** con PostgreSQL y API
- ✅ **Health checks** para base de datos
- ✅ **Volúmenes persistentes** para datos
- ✅ **.dockerignore** para optimizar build

### Fase 11: Optimizaciones y Mejoras

- ✅ **Dependencia PostgreSQL** agregada
- ✅ **Configuración de seguridad** mejorada
- ✅ **Manejo de errores** robusto
- ✅ **Logging** configurado
- ✅ **CORS** habilitado para frontend

### Fase 12: Documentación y README

- ✅ **README.md** completo y profesional
- ✅ **Instrucciones de instalación** detalladas
- ✅ **Comandos Docker** documentados
- ✅ **Troubleshooting** para problemas comunes
- ✅ **Ejemplos de uso** de la API

## 🔧 Problemas Resueltos Durante el Desarrollo

### Problema 1: Tests en Directorio Incorrecto

**Problema**: Los archivos de test estaban en `src/main/java` en lugar de `src/test/java`
**Solución**:

- Movidos `UsuarioServiceTest.java` y `UsuarioControllerTest.java` al directorio correcto
- Agregada anotación `@ExtendWith(MockitoExtension.class)` para habilitar Mockito
- Corregidos imports estáticos para assertions y métodos Mockito

### Problema 2: Dependencias de Testing Faltantes

**Problema**: Error "Mock cannot be resolved to a type" y "SpringBootTest cannot be resolved"
**Solución**:

- Agregada dependencia `spring-security-test` al `pom.xml`
- Configurados imports estáticos correctos para Mockito y JUnit
- Agregada anotación `@Transactional` para rollback automático en tests

### Problema 3: Configuración de Base de Datos Inconsistente

**Problema**: Docker Compose configurado para PostgreSQL pero properties para MySQL
**Solución**:

- Actualizado `aplication.prod.properties` para usar PostgreSQL
- Agregada dependencia PostgreSQL al `pom.xml`
- Corregidas URLs de conexión y configuraciones JPA

### Problema 4: Test de Controlador Fallando por Datos

**Problema**: Test fallaba con "Rol no encontrado" porque no existían roles en BD de test
**Solución**:

- Agregado `@BeforeEach` para crear roles necesarios en cada test
- Configurado `@Transactional` para aislamiento de tests

### Problema 5: Docker Compose Sin Health Checks

**Problema**: API intentaba conectarse a BD antes de que estuviera lista
**Solución**:

- Agregado healthcheck a PostgreSQL en docker-compose.yml
- Configurado `depends_on` con condición `service_healthy`
- Agregado `restart: unless-stopped` para mayor robustez

### Problema 6: Dockerfile No Optimizado

**Problema**: Construcción lenta y sin optimizaciones
**Solución**:

- Creado `.dockerignore` para excluir archivos innecesarios
- Agregado `clean` al comando Maven
- Optimizado multi-stage build para imagen final más pequeña

### Problema 7: Falta de Documentación

**Problema**: Proyecto sin documentación adecuada
**Solución**:

- Creado README.md completo con todas las secciones necesarias
- Documentados todos los endpoints y configuraciones
- Agregados ejemplos de uso y troubleshooting

## 🏛️ Decisiones de Arquitectura

### Patrón de Diseño: MVC + Service Layer

- **Controllers**: Manejan HTTP requests/responses
- **Services**: Contienen lógica de negocio
- **Repositories**: Acceso a datos con Spring Data JPA
- **DTOs**: Transferencia de datos entre capas

### Autenticación: JWT Stateless

- **Ventaja**: Escalable, no requiere sesiones en servidor
- **Implementación**: Token en header Authorization
- **Seguridad**: Filtro personalizado para validar tokens

### Base de Datos: Multi-perfil

- **Desarrollo**: H2 en memoria (rápido para testing)
- **Producción**: PostgreSQL (robusto y escalable)
- **Configuración**: Profiles de Spring Boot

### Testing: Estratégia Híbrida

- **Unit Tests**: Mockito para servicios
- **Integration Tests**: MockMvc para controladores
- **Database**: H2 en memoria con @Transactional

### Contenedorización: Docker Multi-stage

- **Build Stage**: Maven para compilar
- **Runtime Stage**: JRE ligero para ejecutar
- **Optimización**: Imagen final más pequeña

### Seguridad: Spring Security + JWT

- **Autenticación**: JWT tokens
- **Autorización**: Roles y permisos por endpoint
- **CORS**: Configurado para frontend
- **Password**: Encriptado con BCrypt

## 🎯 Logros del Proyecto

### ✅ Funcionalidades Implementadas

- **Sistema de Autenticación** completo con JWT
- **Gestión de Usuarios** con CRUD completo
- **Sistema de Roles** (ADMIN, LIDER, DESARROLLADOR)
- **Gestión de Proyectos** con endpoints REST
- **Base de Datos** configurada para desarrollo y producción
- **Testing** con cobertura de servicios y controladores
- **Documentación API** con Swagger/OpenAPI
- **Contenedorización** completa con Docker

### ✅ Calidad del Código

- **Arquitectura limpia** con separación de responsabilidades
- **Patrones de diseño** aplicados correctamente
- **Manejo de errores** robusto
- **Validaciones** de entrada implementadas
- **Logging** configurado apropiadamente
- **Código documentado** con comentarios claros

### ✅ DevOps y Deployment

- **Docker** multi-stage build optimizado
- **Docker Compose** con health checks
- **Configuración** por perfiles (dev/prod)
- **Variables de entorno** para configuración
- **Volúmenes persistentes** para datos
- **Restart policies** para alta disponibilidad

### ✅ Testing y Calidad

- **Tests unitarios** con Mockito
- **Tests de integración** con MockMvc
- **Configuración de test** aislada
- **Rollback automático** en tests
- **Cobertura** de casos de uso principales

### ✅ Documentación

- **README completo** con instrucciones detalladas
- **API documentada** con OpenAPI/Swagger
- **Ejemplos de uso** para todos los endpoints
- **Troubleshooting** para problemas comunes
- **Comandos Docker** documentados

## 🛠️ Tecnologías

### Backend

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Security**
- **Spring Data JPA**
- **Spring Session JDBC**

### Base de Datos

- **PostgreSQL 15** (Producción)
- **H2 Database** (Desarrollo/Testing)

### Herramientas

- **Maven** - Gestión de dependencias
- **Docker & Docker Compose** - Contenedorización
- **JWT** - Autenticación
- **OpenAPI/Swagger** - Documentación
- **Lombok** - Reducción de código boilerplate

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/gestion_proyectos/api/
│   │   ├── ApiApplication.java              # Clase principal
│   │   ├── config/
│   │   │   └── OpenApiConfig.java          # Configuración Swagger
│   │   ├── controller/
│   │   │   ├── AuthController.java         # Endpoints de autenticación
│   │   │   ├── ProyectoController.java     # Endpoints de proyectos
│   │   │   └── UsuarioController.java      # Endpoints de usuarios
│   │   ├── dto/
│   │   │   ├── CrearUsuarioDataTransferObject.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   └── ...                         # Otros DTOs
│   │   ├── entity/
│   │   │   ├── Proyecto.java               # Entidad Proyecto
│   │   │   ├── Rol.java                    # Entidad Rol
│   │   │   └── Usuario.java                # Entidad Usuario
│   │   ├── repository/
│   │   │   ├── ProyectoRepository.java     # Repositorio Proyecto
│   │   │   ├── RolRepository.java          # Repositorio Rol
│   │   │   └── UsuarioRepository.java      # Repositorio Usuario
│   │   ├── security/
│   │   │   ├── JwtRequestFilter.java       # Filtro JWT
│   │   │   ├── JwtUtil.java                # Utilidades JWT
│   │   │   ├── SecurityConfig.java         # Configuración de seguridad
│   │   │   └── UserDetailsServiceImpl.java # Servicio de detalles de usuario
│   │   ├── service/
│   │   │   ├── ProyectoService.java        # Lógica de negocio proyectos
│   │   │   └── UsuarioService.java         # Lógica de negocio usuarios
│   │   └── DataInitializer.java            # Inicialización de datos
│   └── resources/
│       ├── application.properties          # Configuración principal
│       ├── aplication.dev.properties       # Configuración desarrollo
│       └── aplication.prod.properties      # Configuración producción
└── test/
    └── java/com/gestion_proyectos/api/
        ├── controller/
        │   └── UsuarioControllerTest.java  # Tests del controlador
        ├── service/
        │   └── UsuarioServiceTest.java     # Tests del servicio
        └── ApiApplicationTests.java        # Tests de aplicación
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- **Java 17** o superior
- **Maven 3.6+**
- **PostgreSQL 15** (para producción)
- **Docker & Docker Compose** (opcional)

### 1. Clonar el Repositorio

```bash
git clone <repository-url>
cd api
```

### 2. Configurar Base de Datos

#### Opción A: PostgreSQL Local

```sql
CREATE DATABASE proyectosdb;
CREATE USER admin WITH PASSWORD 'password123';
GRANT ALL PRIVILEGES ON DATABASE proyectosdb TO admin;
```

#### Opción B: Usar Docker (Recomendado)

```bash
docker-compose up -d db
```

### 3. Configurar Variables de Entorno

Copia y modifica el archivo de configuración:

```bash
cp src/main/resources/application.properties src/main/resources/application-local.properties
```

### 4. Compilar y Ejecutar

```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar aplicación
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 🐳 Uso con Docker

### Construcción y Ejecución

```bash
# Construir y ejecutar todos los servicios
docker-compose up --build

# Ejecutar en segundo plano
docker-compose up -d --build

# Ver logs
docker-compose logs -f api

# Parar servicios
docker-compose down
```

### Comandos Útiles

```bash
# Reconstruir solo la API
docker-compose build api

# Reiniciar un servicio
docker-compose restart api

# Ver estado de servicios
docker-compose ps

# Conectarse a la base de datos
docker-compose exec db psql -U admin -d proyectosdb
```

## 📡 API Endpoints

### Autenticación

```
POST /auth/login          # Iniciar sesión
POST /auth/register       # Registrar usuario
```

### Usuarios

```
GET    /usuarios          # Listar usuarios
POST   /usuarios          # Crear usuario
GET    /usuarios/{id}     # Obtener usuario
PUT    /usuarios/{id}     # Actualizar usuario
DELETE /usuarios/{id}     # Eliminar usuario
```

### Proyectos

```
GET    /proyectos         # Listar proyectos
POST   /proyectos         # Crear proyecto
GET    /proyectos/{id}    # Obtener proyecto
PUT    /proyectos/{id}    # Actualizar proyecto
DELETE /proyectos/{id}    # Eliminar proyecto
```

### Documentación API

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## 🔐 Autenticación

### Roles Disponibles

- **ADMIN**: Acceso completo al sistema
- **LIDER**: Gestión de proyectos y equipos
- **DESARROLLADOR**: Acceso limitado a proyectos asignados

### Flujo de Autenticación

1. **Login**: `POST /auth/login`

```json
{
  "email": "admin@example.com",
  "password": "admin123"
}
```

2. **Respuesta**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "expiresIn": 3600
}
```

3. **Usar Token**: Incluir en header `Authorization: Bearer <token>`

### Usuarios por Defecto

| Email                | Password | Rol   |
| -------------------- | -------- | ----- |
| admin@example.com    | admin123 | ADMIN |
| caterine@example.com | lider123 | LIDER |

## 🧪 Testing

### Ejecutar Tests

```bash
# Todos los tests
mvn test

# Test específico
mvn test -Dtest=UsuarioServiceTest

# Tests con cobertura
mvn test jacoco:report
```

### Tipos de Tests

- **Unit Tests**: `UsuarioServiceTest.java`
- **Integration Tests**: `UsuarioControllerTest.java`
- **Application Tests**: `ApiApplicationTests.java`

### Configuración de Tests

Los tests usan:

- **H2 Database** en memoria
- **MockMvc** para tests de controladores
- **Mockito** para mocking
- **@Transactional** para rollback automático

## ⚙️ Configuración de Perfiles

### Desarrollo (`dev`)

```properties
# H2 Database en memoria
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

### Producción (`prod`)

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://db:5432/proyectosdb
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### Activar Perfil

```bash
# Desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Producción
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 🔧 Configuración Avanzada

### Variables de Entorno

```bash
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/proyectosdb
export SPRING_DATASOURCE_USERNAME=admin
export SPRING_DATASOURCE_PASSWORD=password123
```

### JWT Configuration

```properties
# application.properties
jwt.secret=mySecretKey
jwt.expiration=3600000
```

## 📊 Monitoreo y Logs

### Actuator Endpoints

```bash
# Health check
GET /actuator/health

# Info de la aplicación
GET /actuator/info

# Métricas
GET /actuator/metrics
```

### Logs

```bash
# Ver logs en tiempo real
docker-compose logs -f api

# Logs específicos
docker-compose logs api | grep ERROR
```

## 🚨 Troubleshooting

### Problemas Comunes

1. **Error de conexión a BD**:

   ```bash
   # Verificar que PostgreSQL esté corriendo
   docker-compose ps
   docker-compose logs db
   ```

2. **Puerto 8080 ocupado**:

   ```bash
   # Cambiar puerto en docker-compose.yml
   ports:
     - "8081:8080"
   ```

3. **Error de compilación**:
   ```bash
   # Limpiar y reconstruir
   mvn clean compile
   docker-compose build --no-cache
   ```

### Logs de Debug

```properties
# application.properties
logging.level.com.gestion_proyectos.api=DEBUG
logging.level.org.springframework.security=DEBUG
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Estándares de Código

- Usar **Java 17** features
- Seguir convenciones de **Spring Boot**
- Escribir **tests** para nuevas funcionalidades
- Documentar **endpoints** con OpenAPI
- Usar **Lombok** para reducir boilerplate

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Autores

- **Roberto Palacios** - _Desarrollo inicial_ - [Roberto Palacios](https://github.com/robpalacios1)

## 🙏 Agradecimientos

- Spring Boot Team
- PostgreSQL Community
- Docker Team
- OpenAPI/Swagger Community

---

**¡Gracias por usar la API! 🚀**

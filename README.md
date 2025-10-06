# Saúde Solidária – Spring Boot + MongoDB Atlas (Frontend estático)

Aplicação Spring Boot com endpoints REST que leem coleções no MongoDB e um frontend estático (HTML/CSS/JS) em `src/main/resources/static/style`.

## Requisitos
- Java 17+
- Maven 3.8+

## Configuração do Mongo
Por padrão a aplicação usa a URI do Atlas em `application.properties` (pode sobrescrever com `MONGODB_URI`).

### Rodar com a URI padrão (Atlas):
```
mvn spring-boot:run
```

### Rodar definindo outra URI:
**Windows (PowerShell)**
```
$env:MONGODB_URI="mongodb+srv://USUARIO:SENHA@HOST/dbname"
mvn spring-boot:run
```
**Linux/macOS**
```
export MONGODB_URI="mongodb+srv://USUARIO:SENHA@HOST/dbname"
mvn spring-boot:run
```

## Endpoints
- `GET /api/beneficiarios`
- `GET /api/doadores-individuais`
- `GET /api/doadores-corporativos`
- `GET /api/medicamentos`
- `GET /api/doacoes`

Acesse o frontend: `http://localhost:3000/`

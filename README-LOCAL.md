Local build & run (two options)

1) Using Docker (recommended if you don't have Maven locally)

Build and run with Docker:

```powershell
cd D:\Smart-Surveillance-Platform\Diagram
docker build -t drone-backend:local .
docker run -p 8080:8080 --rm drone-backend:local
```

Or with docker-compose:

```powershell
cd D:\Smart-Surveillance-Platform\Diagram
docker-compose up --build
```

Swagger UI will be available at:

http://localhost:8080/swagger-ui/index.html

2) Using Maven (if Maven is installed locally)

```powershell
cd D:\Smart-Surveillance-Platform\Diagram
mvn -B -DskipTests package
java -jar target\drone-backend-0.1.0.jar
```

Notes:
- Java 17 is required.
- If `mvn` or `docker` are not available on this machine, install them or use the Docker option on a machine with Docker.

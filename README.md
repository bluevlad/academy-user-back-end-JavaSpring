# Academy User Backend - Spring Boot REST API

Small Online Academy User backend service - A comprehensive educational platform management system built with Spring Boot 3.2.0.

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **MyBatis**: 3.0.4
- **Database**: MariaDB
- **JWT**: JJWT 0.11.5
- **Jakarta EE**: jakarta.servlet.*
- **API Documentation**: SpringDoc OpenAPI 2.3.0 (Swagger UI)

## Quick Start

### Prerequisites

- JDK 17+
- Maven 3.6+
- MariaDB

### Build & Run

```bash
# Build the project
mvn clean package

# Run development server
mvn spring-boot:run

# Run from JAR
java -jar target/academy-user-0.0.1-SNAPSHOT.jar
```

Server runs on: `http://localhost:8080`

### API Documentation (Swagger UI)

After starting the server, access the API documentation:

| URL | Description |
|-----|-------------|
| http://localhost:8080/swagger-ui.html | Swagger UI - Interactive API documentation |
| http://localhost:8080/v3/api-docs | OpenAPI 3.0 JSON specification |

**Features:**
- Browse all API endpoints grouped by module
- Test APIs directly in browser
- View request/response schemas
- Export OpenAPI specification

### Database Setup

1. Create MariaDB schema:
```sql
CREATE SCHEMA acm_basic;
```

2. Configure connection via environment variables:
```bash
export DB_HOST=127.0.0.1
export DB_PORT=3306
export DB_NAME=acm_basic
export DB_USERNAME=root
export DB_PASSWORD=your_password
```

### Testing

```bash
mvn test
```

## Architecture

### Layered Architecture Pattern

This project follows a **layered architecture with direct Mapper usage** (no DAO layer):

```
com.academy/
├── [module]/                           # Feature modules
│   ├── [Module]Api.java               # REST Controller
│   └── service/
│       └── [Module]Service.java       # Service (implements Serializable)
├── mapper/                             # MyBatis Mapper interfaces
│   └── [Module]Mapper.java
└── common/                             # Shared utilities
```

**Key Pattern**: Service classes directly inject Mapper interfaces - **NO DAO layer**.

### Package Structure

```
com.academy/
├── AcademyUserApplication.java        # Spring Boot main class
│
├── board/                             # Board management
│   ├── BoardApi.java
│   └── service/
│       └── BoardService.java
│
├── lecture/                           # Lecture/Category management
│   ├── CategoryApi.java
│   └── service/
│       └── CategoryService.java
│
├── login/                             # Authentication
│   ├── LoginApi.java
│   └── service/
│       └── LoginService.java
│
├── mapper/                            # MyBatis Mapper interfaces
│   ├── BoardMapper.java
│   ├── CategoryMapper.java
│   └── LoginMapper.java
│
├── common/                            # Common utilities
│   ├── CORSFilter.java               # CORS filter base class
│   ├── JwtUtil.java                  # JWT token utilities
│   ├── PaginationInfo.java           # Pagination helper
│   ├── CommonUtil.java               # Common utility methods
│   └── CommonVO.java                 # Common value object
│
└── config/                            # Configuration classes
    ├── OpenApiConfig.java            # Swagger/OpenAPI configuration
    └── CorsConfig.java               # CORS configuration
```

## API Modules

### Core Business Modules

| Module | Description | API Endpoints |
|--------|-------------|---------------|
| **board** | Board management | 6 APIs (list, view, main list, insert, update, delete) |
| **lecture/category** | Category management | 5 APIs (series subject, series prof, category tree, subject list, teacher list) |
| **login** | Authentication | 7 APIs (login, logout, check ID, categories, register, find ID, find password) |

## REST API Pattern

### Controller Pattern

All API controllers follow this standard pattern:

```java
@Tag(name = "Board", description = "게시판 관리 API")
@RestController
@RequestMapping("/api/board")
public class BoardApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardService boardService;

    @Autowired
    public BoardApi(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary = "게시판 리스트 조회", description = "페이징 처리된 게시판 목록을 조회합니다.")
    @GetMapping("/getBoardList")
    public JSONObject getBoardList(...) throws Exception {
        // Implementation
    }
}
```

### Key Characteristics

- **Extend CORSFilter**: All controllers extend `CORSFilter` for CORS support
- **Constructor Injection**: Use constructor-based dependency injection
- **JSONObject Response**: All endpoints return `JSONObject` from org.json.simple
- **Swagger Annotations**: Use `@Tag`, `@Operation`, `@Parameter` for documentation

### Standard Response Format

**Success Response:**
```json
{
  "retMsg": "OK",
  "list": [...],
  "totalCount": 100,
  "currentPage": 1,
  "totalPage": 10
}
```

**Error Response:**
```json
{
  "retMsg": "FAIL",
  "message": "Error description"
}
```

## Service Layer Pattern

### Direct Mapper Injection (No DAO)

Service classes directly use MyBatis Mapper interfaces:

```java
@Service
public class BoardService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardMapper.boardList(params);
    }
}
```

**Important**:
- Service classes implement `Serializable`
- NO DAO layer exists in this architecture
- Direct dependency on Mapper interfaces

## MyBatis Mapper Layer

### Mapper Interface Pattern

```java
package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);
    int boardListCount(HashMap<String, String> params);
    HashMap<String, Object> boardView(HashMap<String, String> params);
    void insertProcess(HashMap<String, String> params);
    void updateProcess(HashMap<String, String> params);
    void deleteBoardData(HashMap<String, String> params);
}
```

### XML Mapper Pattern

Location: `src/main/resources/mapper/[Module]Mapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.academy.mapper.BoardMapper">

    <select id="boardList" parameterType="hashMap" resultType="hashMap">
        SELECT *
        FROM TB_BOARD
        WHERE 1=1
        <if test="searchKeyword != null and searchKeyword != ''">
            AND SUBJECT LIKE CONCAT('%', #{searchKeyword}, '%')
        </if>
        ORDER BY REG_DT DESC
        LIMIT #{recordCountPerPage} OFFSET #{firstIndex}
    </select>

</mapper>
```

## Database Migration (Oracle to MariaDB)

Key SQL syntax conversions applied:

| Oracle | MariaDB |
|--------|---------|
| `NVL(value, default)` | `IFNULL(value, default)` |
| `SYSDATE` | `NOW()` |
| `TO_CHAR(date, 'YYYY-MM-DD')` | `DATE_FORMAT(date, '%Y-%m-%d')` |
| `ROWNUM <= n` | `LIMIT n` |
| `ROWNUM > n AND ROWNUM <= m` | `LIMIT m-n OFFSET n` |
| `DECODE(a, b, c, d)` | `CASE WHEN a = b THEN c ELSE d END` |
| `INSTR(str, substr) > 0` | `str LIKE CONCAT('%', substr, '%')` |
| `CONNECT BY PRIOR` | `WITH RECURSIVE ... CTE` |
| `SYS_GUID()` | `UUID()` |

## Configuration

### application.properties

```properties
# Database
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://${DB_HOST:127.0.0.1}:${DB_PORT:3306}/${DB_NAME:acm_basic}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

# MyBatis
mybatis.mapper-locations=classpath:/mapper/*.xml
mybatis.type-aliases-package=com.academy

# Server
server.port=8080

# Pagination
pageUnit=10

# JWT
jwt.secret=${JWT_SECRET:MySecretKeyForJwtTokenMySecretKeyForJwtToken}
jwt.expiration=3600000

# SpringDoc OpenAPI (Swagger)
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

## Authentication

### JWT Support

JWT utilities are available in `JwtUtil` class:

```java
@Component
public class JwtUtil {
    public String generateToken(String username, String userRole);
    public String extractUsername(String token);
    public String extractUserRole(String token);
    public boolean validateToken(String token);
}
```

## Adding a New Module

1. **Create Mapper Interface** in `com.academy.mapper`:
```java
@Mapper
public interface ExampleMapper {
    List<HashMap<String, Object>> exampleList(HashMap<String, String> params);
}
```

2. **Create MyBatis XML Mapper** in `src/main/resources/mapper/`:
```xml
<mapper namespace="com.academy.mapper.ExampleMapper">
    <select id="exampleList" parameterType="hashMap" resultType="hashMap">
        SELECT * FROM TB_EXAMPLE
    </select>
</mapper>
```

3. **Create Service** in `com.academy.[module].service`:
```java
@Service
public class ExampleService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final ExampleMapper exampleMapper;

    @Autowired
    public ExampleService(ExampleMapper exampleMapper) {
        this.exampleMapper = exampleMapper;
    }
}
```

4. **Create API Controller**:
```java
@Tag(name = "Example", description = "예시 API")
@RestController
@RequestMapping("/api/example")
public class ExampleApi extends CORSFilter {
    private final ExampleService exampleService;

    @Autowired
    public ExampleApi(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
}
```

## Troubleshooting

### Port Conflicts

If port 8080 is in use:
- Change port in `application.properties`: `server.port=8081`

### Database Connection Issues

- Verify MariaDB is running
- Check connection details in environment variables
- Ensure `acm_basic` schema exists

### BOM Encoding Errors

If you see compilation errors with `\ufeff`:
- Re-save files as UTF-8 without BOM

## License

This project is proprietary software.

---

## Copyright

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Small Online Academy User Backend Service built with Spring Boot 3.x, MyBatis, and MariaDB. Provides REST APIs for managing an online academy user system including boards, members, lectures, login, and categories.

**Tech Stack:**
- Java 17
- Spring Boot 3.2.0
- MyBatis 3.0.4
- MariaDB (acm_basic database)
- JJWT 0.11.5 for JWT token authentication
- SpringDoc OpenAPI 2.3.0 (Swagger UI)
- Maven for build management

**Migration Source:** willbesgosiWeb (EgovFramework) -> Spring Boot REST API

## Build and Run Commands

**Build:**
```bash
mvn clean package
```

**Run locally:**
```bash
mvn spring-boot:run
```

**Run built JAR:**
```bash
java -jar target/academy-user-0.0.1-SNAPSHOT.jar
```

**Run tests:**
```bash
mvn test
```

Default server port: 8080

## Database Setup

1. Create MariaDB schema: `acm_basic`
2. Configure connection in `src/main/resources/application.properties`
3. **Important:** Use environment variables or `.env` file for credentials (never commit secrets)
4. MyBatis mapper XMLs are in `src/main/resources/mapper/*.xml`

> See `CLAUDE.local.md` for local development credentials (not tracked in git)

### Database Migration Notes (Oracle to MariaDB)

Key SQL syntax conversions applied:
- `NVL()` → `IFNULL()`
- `SYSDATE` → `NOW()`
- `TO_CHAR(date, 'YYYY-MM-DD')` → `DATE_FORMAT(date, '%Y-%m-%d')`
- `ROWNUM` pagination → `LIMIT ... OFFSET ...`
- `DECODE()` → `CASE WHEN ... END`
- `INSTR(str, substr)` → `str LIKE CONCAT('%', substr, '%')`
- `CONNECT BY PRIOR` (hierarchical) → `WITH RECURSIVE` CTE
- `ROW_NUMBER() OVER()` → Same (MariaDB 10.2+ supports)
- `SUBSTR(str, -3)` → `RIGHT(str, 3)`
- `SYS_GUID()` → `UUID()`

## Architecture Overview

### Three-Tier Architecture Pattern

The codebase follows a layered architecture:

```
Api (Controller) → Service → Mapper (MyBatis Interface) → XML Mapper
```

### Package Structure

```
com.academy/
├── AcademyUserApplication.java    # Spring Boot main class
│
├── board/                         # Board module
│   ├── BoardApi.java             # REST Controller
│   └── service/
│       └── BoardService.java     # Business logic
│
├── lecture/                       # Lecture module
│   ├── CategoryApi.java          # Category REST Controller
│   └── service/
│       └── CategoryService.java  # Category service
│
├── login/                         # Login/Member module
│   ├── LoginApi.java             # Login REST Controller
│   └── service/
│       └── LoginService.java     # Login service
│
├── mapper/                        # MyBatis Mapper interfaces
│   ├── BoardMapper.java
│   ├── CategoryMapper.java
│   └── LoginMapper.java
│
├── common/                        # Common utilities
│   ├── CORSFilter.java           # CORS filter base class
│   ├── JwtUtil.java              # JWT utilities
│   ├── PaginationInfo.java       # Pagination helper
│   ├── CommonUtil.java           # Common utilities
│   └── CommonVO.java             # Common value object
│
└── config/                        # Configuration classes
    ├── OpenApiConfig.java        # Swagger configuration
    └── CorsConfig.java           # CORS configuration
```

### MyBatis Mappers

Mapper interfaces in `com.academy.mapper/`:
```java
@Mapper
public interface BoardMapper { ... }
```

Corresponding XML files in `src/main/resources/mapper/`:
```
BoardMapper.xml      # namespace="com.academy.mapper.BoardMapper"
CategoryMapper.xml   # namespace="com.academy.mapper.CategoryMapper"
LoginMapper.xml      # namespace="com.academy.mapper.LoginMapper"
```

### Common Components

Located in `com.academy.common/`:
- `CORSFilter`: Base class for API controllers with CORS configuration
- `JwtUtil`: JWT token generation, validation, and extraction
- `PaginationInfo`: Pagination helper (pageUnit=10, pageSize=10)
- `CommonUtil`: Common utility methods (null handling, IP extraction, etc.)
- `CommonVO`: Base value object for pagination parameters

## API Controller Conventions

All API controllers:
1. Extend `CORSFilter` for CORS support
2. Use `@RestController` and `@RequestMapping("/api/<module>")`
3. Return `JSONObject` (from org.json.simple)
4. Use Swagger annotations (`@Tag`, `@Operation`)
5. **Use `@ModelAttribute` with VO for request parameters**
6. Follow this response structure:
   ```java
   HashMap<String,Object> jsonObject = new HashMap<>();
   jsonObject.put("data", result);
   jsonObject.put("retMsg", "OK"); // or "FAIL"
   return new JSONObject(jsonObject);
   ```

### Request Parameter Convention (using @ModelAttribute with VO)

All API endpoints must use `@ModelAttribute("<Module>VO")` for request parameters:

```java
@GetMapping("/getList")
public JSONObject getList(
        @ModelAttribute("BoardVO") BoardVO boardVO,
        HttpServletRequest request) throws Exception {

    // Use VO fields directly
    String searchText = boardVO.getSearchText();
    int currentPage = boardVO.getCurrentPage();
    // ...
}
```

## Service Class Conventions

All Service classes must:
1. **Implement `Serializable`** for session serialization support
2. Use `@Service` annotation
3. Inject dependencies via constructor injection
4. Follow naming convention: `<Module>Service.java`

Example:
```java
@Service
public class BoardService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    // Business logic methods...
}
```

## API Endpoints

### Board API (`/api/board`)
- `GET /getBoardList` - 게시판 리스트 조회
- `GET /getBoardView` - 게시물 상세 조회
- `GET /getMainBoardList` - 메인 게시판 리스트 조회
- `POST /insertBoard` - 게시물 등록
- `POST /updateBoard` - 게시물 수정
- `POST /deleteBoard` - 게시물 삭제

### Category API (`/api/lecture/category`)
- `GET /getSeriesSubject` - 직렬-과목 리스트 조회
- `GET /getSeriesProf` - 직렬-과목-교수 리스트 조회
- `GET /getCategoryTree` - 카테고리 트리 리스트 조회
- `GET /getSubMainSubjectList` - 서브 메인화면 과목 목록 조회
- `GET /getSubMainTeacherList` - 서브 메인화면 교수 목록 조회

### Login API (`/api/login`)
- `POST /login` - 로그인 (JWT 토큰 발급)
- `POST /logout` - 로그아웃
- `GET /checkId` - 회원 ID 중복 체크
- `GET /getCategory` - 카테고리 목록 조회
- `POST /register` - 회원가입
- `POST /findId` - 아이디 찾기
- `POST /findPwd` - 비밀번호 찾기

## Swagger UI

After starting the server, access the API documentation:

| URL | Description |
|-----|-------------|
| http://localhost:8080/swagger-ui.html | Swagger UI |
| http://localhost:8080/v3/api-docs | OpenAPI JSON spec |

## Pagination

List APIs implement pagination using `PaginationInfo`:
```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(currentPage);
paginationInfo.setRecordCountPerPage(pageRow);
paginationInfo.setPageSize(10);

params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
params.put("recordCountPerPage", String.valueOf(recordCountPerPage));
```

## MyBatis XML Mapper Conventions

1. Use `LIMIT #{recordCountPerPage} OFFSET #{firstIndex}` for pagination
2. Dynamic SQL with `<if test="">` for optional fields
3. Auto-generate IDs using `<selectKey>` with UUID or MAX+1
4. Date fields use MariaDB `NOW()` function
5. Namespace must match the Mapper interface fully qualified name

## Authentication

JWT-based authentication:
- `JwtUtil` generates tokens with configurable expiration
- **Security Note:** JWT secret key should be externalized via environment variables
- Login API validates credentials and returns JWT token
- Protected endpoints should validate token via `JwtUtil.validateToken()`

Configuration in `application.properties`:
```properties
jwt.secret=${JWT_SECRET:MySecretKeyForJwtTokenMySecretKeyForJwtToken}
jwt.expiration=3600000
```

## Known Issues

**BOM Encoding:** If encountering compile errors with `\ufeff` character, ensure source files are saved as UTF-8 without BOM.

## Adding New Features

When adding new API endpoints or modules:

1. **Create VO class** in `<module>/service/<Module>VO.java` extending `CommonVO`
2. Create Mapper interface in `com.academy.mapper/<Module>Mapper.java` with `@Mapper`
3. Create XML mapper in `src/main/resources/mapper/<Module>Mapper.xml`
4. Create Service in `<module>/service/<Module>Service.java` with `@Service` **implementing `Serializable`**
5. Create API Controller in `<module>/<Module>Api.java` extending `CORSFilter`
6. **Use `@ModelAttribute("<Module>VO")` for all request parameters**
7. Add Swagger annotations for documentation
8. Use constructor injection for dependencies

### VO Class Convention

All VO classes must:
1. Extend `CommonVO` (provides pagination fields: currentPage, pageRow, userId)
2. Implement `Serializable` (inherited from CommonVO)
3. Include module-specific fields with getters/setters
4. Be located in `<module>/service/<Module>VO.java`

Example:
```java
package com.academy.sample.service;

import com.academy.common.CommonVO;

public class SampleVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    private String sampleId;
    private String sampleName;
    private String searchText;

    // Getters and Setters
    public String getSampleId() { return sampleId; }
    public void setSampleId(String sampleId) { this.sampleId = sampleId; }
    // ...
}
```

## Environment Variables

```bash
DB_HOST=127.0.0.1
DB_PORT=3306
DB_NAME=acm_basic
DB_USERNAME=root
DB_PASSWORD=your_password_here
JWT_SECRET=your_jwt_secret_here
```

# API 파일 정리 가이드

## 목적
모든 *Api.java 파일에서 HttpServletRequest와 setParam 메서드를 제거

## 완료된 파일
- ✅ BoardApi.java
- ⚠️ TeacherApi.java (부분 완료)
- ⚠️ TeacherZoneApi.java (부분 완료)

## 남은 파일 목록 (38개)

### 우선순위 높음
- [ ] LoginApi.java
- [ ] CategoryApi.java
- [ ] LectureApi.java
- [ ] MypageApi.java

### 나머지 파일
- [ ] InterviewApi.java
- [ ] CartApi.java
- [ ] PayApi.java
- [ ] OpenLectureApi.java
- [ ] SearchApi.java
- [ ] BookApi.java
- [ ] RentApi.java
- [ ] BoardCmmntyApi.java
- [ ] BoardCounselRoomApi.java
- [ ] BoardCustomerOnApi.java
- [ ] BoardExamGuideApi.java
- [ ] BoardExamInfoOnApi.java
- [ ] BoardLibraryApi.java
- [ ] BoardFaqApi.java
- [ ] BoardNoticeApi.java
- [ ] BookCmmntApi.java
- [ ] CoopApi.java
- [ ] EventApi.java
- [ ] EventOffApi.java
- [ ] EventOnApi.java
- [ ] SurveyApi.java
- [ ] IndexApi.java
- [ ] PackageLectureApi.java
- [ ] LectureReplyApi.java
- [ ] MouigosaApi.java
- [ ] OffererApi.java
- [ ] StatsApi.java
- [ ] MyLectureApi.java
- [ ] PassLectureApi.java
- [ ] CounselApi.java
- [ ] CouponApi.java (mypage)
- [ ] CartApi (mypage).java
- [ ] PayApi (mypage).java
- [ ] PrivateInfoApi.java
- [ ] NoteApi.java

## 변경 단계

### 1단계: Import 제거
```java
// 제거
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
```

### 2단계: 모든 메서드에서 HttpServletRequest 파라미터 제거
```java
// 변경 전
public JSONObject methodName(
        @ModelAttribute("VO") VO vo,
        HttpServletRequest request) throws Exception {

// 변경 후
public JSONObject methodName(
        @ModelAttribute("VO") VO vo) throws Exception {
```

### 3단계: setParam 호출 제거
각 메서드 내부에서:
```java
// 제거
setParam(params, request);
```

### 4단계: setParam 메서드 전체 삭제
파일 끝부분의 private 메서드 삭제:
```java
// 전체 삭제
private void setParam(HashMap<String, String> params, HttpServletRequest request) {
    // ... 모든 내용
}
```

### 5단계: request 객체 사용 코드 제거/수정
```java
// 제거 또는 수정
request.getRemoteAddr()
request.getParameter()
```

## 자동화 스크립트 (PowerShell - Windows)

```powershell
# API 파일 목록 가져오기
$apiFiles = Get-ChildItem -Path "src/main/java/com/academy" -Recurse -Filter "*Api.java"

foreach ($file in $apiFiles) {
    $content = Get-Content $file.FullName -Raw

    # Import 제거
    $content = $content -replace 'import jakarta\.servlet\.http\.HttpServletRequest;[\r\n]*', ''
    $content = $content -replace 'import jakarta\.servlet\.http\.HttpSession;[\r\n]*', ''
    $content = $content -replace 'import javax\.servlet\.http\.HttpServletRequest;[\r\n]*', ''
    $content = $content -replace 'import javax\.servlet\.http\.HttpSession;[\r\n]*', ''

    # 메서드 파라미터에서 HttpServletRequest 제거
    $content = $content -replace ',\s*HttpServletRequest\s+request\)', ')'

    # setParam 호출 제거
    $content = $content -replace '\s*setParam\(params,\s*request\);[\r\n]*', ''

    # 파일 저장
    Set-Content -Path $file.FullName -Value $content -NoNewline

    Write-Host "Processed: $($file.Name)"
}
```

## 주의사항

1. **세션 정보**: setParam에서 세션에서 가져오던 USER_ID 등의 정보는 다른 방식으로 처리해야 합니다 (JWT, Spring Security 등)

2. **request.getRemoteAddr()**: IP 주소가 필요한 경우 다른 방법으로 가져와야 합니다

3. **request.getParameter()**: 쿼리 파라미터는 VO 객체나 @RequestParam으로 처리해야 합니다

4. **테스트**: 변경 후 각 API의 기능이 정상 작동하는지 테스트 필요

## 마이그레이션 후 고려사항

### 인증/인가 처리
- Spring Security 적용 검토
- JWT 토큰 기반 인증 구현
- @AuthenticationPrincipal을 통한 사용자 정보 주입

### 예시
```java
@GetMapping("/api/mypage")
public JSONObject getMypage(
        @ModelAttribute("MypageVO") MypageVO vo,
        @AuthenticationPrincipal UserDetails userDetails) {

    String userId = userDetails.getUsername();
    // ... 로직
}
```

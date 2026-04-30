# 🛒 Commerce App

## 📖 목차
1. [프로젝트 소개](#프로젝트-소개)
2. [팀 소개](#팀소개)
3. [프로젝트 계기](#프로젝트-계기)
4. [주요 기능](#주요-기능)
5. [개발 기간](#개발-기간)
6. [기술 스택](#기술-스택)
7. [서비스 구조](#서비스-구조)
8. [와이어 프레임](#와이어-프레임)
9. [API 명세서](#API-명세서)
10. [ERD](#ERD)
11. [프로젝트 파일 구조](#프로젝트-파일-구조)
12. [Trouble Shooting](#trouble-shooting)

---

## 🧾 프로젝트 소개
저희 프로젝트는 고객, 상품, 주문 데이터를 효율적으로 관리하는 시스템을 구축하는 프로젝트입니다.
이번 프로젝트를 통해 Spring Boot 기반 백엔드 구조와 데이터 흐름을 이해하여
관리자가 보다 쉽고 정확하게 각 데이터들을 관리할 수 있도록 하는게 저희 팀의 목표입니다.

---

## 👥 팀소개
| 이름 | 역할 |
|------|------|
| 박정원 | 팀장 |
| 강재구 | 관리자 기능 구현 담당 |
| 최준영 | 고객 기능 구현 담당 |
| 정지수 | 상품 기능 구현 담당 |
| 이지영 | 주문 기능 구현 담당 |
---

## 💡 프로젝트 계기
이커머스 서비스의 핵심인 고객 및 상품 관리 기능을 직접 구현해보며
팀 프로젝트를 통해 협업 능력을 배우고 향상 시키기 위해 이 프로젝트를 시작했습니다.

---

## 🚀 주요 기능

### 👨‍💼 관리자 관리
- 관리자 회원가입
- 로그인 / 로그아웃
- 관리자 리스트 조회 (페이징, 검색, 정렬)
- 관리자 상세 조회
- 관리자 내 프로필 조회
- 관리자 가입 승인 / 거부
- 관리자 정보 수정 
- 관리자 역할 변경 (슈퍼 관리자 / 운영 관리자 / CS 관리자)
- 관리자 상태 변경 (활성 / 비활성 / 정지 / 승인대기 / 거부)
- 관리자 내 프로필 수정
- 관리자 비밀번호 변경
- 관리자 삭제

---

### 👤 고객 관리
- 고객 회원가입
- 고객 로그인 / 로그아웃
- 고객 목록 조회 (페이징, 검색, 정렬)
- 고객 상세 조회
- 고객 정보 수정
- 고객 상태 변경 (활성 / 비활성 / 정지)
- 고객 삭제

---

### 📦 상품 관리
- 상품 등록 
- 상품 목록 조회 (페이징, 검색, 정렬)
- 상품 상세 조회
- 상품 수정
- 상품 삭제
- 재고 기반 상태 자동 변경 (판매중 / 품절 / 단종)

---  

### 🧾 주문 관리
- CS 주문 생성
- 고객 주문 생성
- 주문 리스트 조회 (페이징, 검색, 정렬)
- 주문 상세 조회
- 주문 상태 변경 (준비중 / 배송중 / 배송 완료 / 취소됨)
- 주문 취소

---

## 📅 개발기간
- 2026.04.23 ~ 2026.04.30

---

## 🛠 기술스택

### Backend
- Java 17
- Spring Boot
- Spring Data JPA

### Database
- MySQL

### Tool
- IntelliJ IDEA
- Postman
- GitHub

---

## 🏗 서비스 구조

Controller → Service → Repository → Database

- Controller: 요청/응답 처리
- Service: 비즈니스 로직 처리
- Repository: DB 접근


## 📑 API 명세서


## 🗄 ERD
<img width="1536" height="1024" alt="Image 2026년 4월 30일 오후 07_57_31" src="https://github.com/user-attachments/assets/dd93000c-c741-4a54-8794-657844cb105b" />

## 📁 프로젝트 파일 구조
```text
com.commerceapp
 ├── admin
 │   ├── controller
 │   ├── dto
 │   ├── entity
 │   ├── enums
 │   ├── repository
 │   └── service
 │
 ├── customer
 │   ├── controller
 │   ├── dto
 │   ├── entity
 │   ├── enums
 │   ├── repository
 │   └── service
 │
 ├── product
 │   ├── controller
 │   ├── dto
 │   ├── entity
 │   ├── enums
 │   ├── repository
 │   └── service
 │
 ├── order
 │   ├── controller
 │   ├── dto
 │   ├── entity
 │   ├── enums
 │   ├── repository
 │   └── service
 │
 └── common            // 여러 도메인에서 공통으로 사용하는 기능 모음
     ├── config        // 공통 설정 (Global Handler, PasswordEncoder)
     ├── entity        // 공통 엔티티 (BaseEntity)
     ├── exception     // 예외 처리 (Custom Exception)
     └── init          // 초기 데이터 세팅
```

## ⚠️ Trouble Shooting

### 1️⃣ 관리자 권한 없이 API에 접근 가능했던 문제

#### 문제 상황

주문 상세 조회 API에서 로그인하지 않은 사용자도 접근이 가능한 문제가 있었습니다.
→ 백오피스인데도 보안이 전혀 걸려있지 않은 상태

#### 원인

컨트롤러에서 인증/인가 체크가 없었고,
서비스 로직에서도 관리자 검증이 누락되어 있었습니다.

```java
@GetMapping("/{orderId}")
public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable Long orderId) {
    return ResponseEntity.ok(orderService.getOrderDetail(orderId));
}
```

#### 해결 방법

- 세션 기반 `AdminLoginSession`을 전달하도록 구조 변경
- 서비스에 권한 검증 로직 추가

```java
private void validAdmin(AdminLoginSession loginSession){
    if (loginSession == null){
        throw new UnauthorizedException("관리자 로그인이 필요합니다.");
    }
    if (!AdminRole.SUPER.getDisplayName().equals(loginSession.getRole())){
        throw new ForbiddenException("권한이 없습니다.");
    }
}
```

#### 결과

- 로그인 하지 않은 사용자 → 401 Unauthorized
- 일반 관리자 → 403 Forbidden
- 슈퍼 관리자만 접근할 수 있도록 개선

#### 배운 점

"기능 구현보다 인증/인가 설계가 먼저다."

---

### 2️⃣ 500 에러가 먼저 터지던 문제 (예외 처리 순서)

#### 문제 상황

로그인이 안 된 상태에서 API를 호출하면
"로그인이 필요합니다."가 아니라 **500 에러가 발생**

#### 원인

`loginSession.getRole()` 호출 시
`loginSession == null`인데 접근해서 **NullPointerException 발생**

```java
if (!AdminRole.SUPER.getDisplayName().equals(loginSession.getRole()))
```

#### 해결 방법

- null 체크를 가장 먼저 수행하도록 순서 변경

```java
if (loginSession == null){
    throw new UnauthorizedException("관리자 로그인이 필요합니다.");
}
```

#### 결과

- 500 → 401 / 403으로 정상적인 HTTP 응답 반환

#### 배운 점

"예외를 발생시키는 것도 중요하지만, 순서가 더 중요하다."

---

### 3️⃣ 이메일 중복으로 회원가입 실패한 문제

#### 문제 상황

회원가입 시 DB에서 아래 에러 발생

```
Duplicate entry 'xxx@test.com'
```

#### 원인

DB에는 Unique 제약 조건이 있었지만
서버에는 사전 검증이 없어서 DB 에러 발생

#### 해결 방법

- 저장 전에 이메일 존재 여부 체크

```java
if (customerRepository.existsByEmail(email)) {
    throw new BadRequestException("이미 존재하는 이메일입니다.");
}
```

#### 결과

- DB 에러 → 사용자 친화적 메시지로 개선

#### 배운 점

"DB 제약조건만 믿지 말고 비즈니스 로직에서 한 번 더 검증해야 한다."

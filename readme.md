이 프로젝트는 JWT(JSON Web Token)를 활용한 인증 시스템이 구현된 스프링부트 블로그 애플리케이션입니다. 주요 기능들을 설명하겠습니다:

1. 인증 및 보안 기능
- JWT 기반 인증 시스템 구현
- 액세스 토큰과 리프레시 토큰을 사용한 인증 관리
- BCrypt를 사용한 비밀번호 암호화
- Spring Security를 통한 보안 설정
- 토큰 기반의 인증 필터 구현 (TokenAuthenticationFilter)

2. 사용자 관리
- 회원가입 기능 (/signup)
- 로그인 기능 (/login)
- 로그아웃 기능 (/logout)
- 사용자 정보 관리 (UserService)

3. 블로그 게시물 관리
- 게시물 CRUD 기능
    - 게시물 작성 (POST /api/articles)
    - 게시물 조회 (GET /api/articles, GET /api/articles/{id})
    - 게시물 수정 (PUT /api/articles/{id})
    - 게시물 삭제 (DELETE /api/articles/{id})
- 게시물 목록 보기
- 개별 게시물 상세 보기

4. API 구조
- RESTful API 설계
- 컨트롤러 계층 분리
    - BlogApiController: API 엔드포인트 처리
    - BlogViewController: 뷰 관련 처리
    - UserApiController: 사용자 관련 API 처리
    - TokenApiController: 토큰 관련 API 처리

5. 토큰 관리
- 토큰 생성 및 검증 (TokenProvider)
- 리프레시 토큰을 통한 새로운 액세스 토큰 발급
- 토큰 만료 시간 관리
- 토큰 저장소 구현 (RefreshTokenRepository)

이 프로젝트는 MVC 패턴을 따르고 있으며, JPA를 사용한 데이터 접근 계층, 서비스 계층, 그리고 컨트롤러 계층이 잘 분리되어 있는 구조를 가지고 있습니다.
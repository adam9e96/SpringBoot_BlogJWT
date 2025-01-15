package com.adam9e96.BlogStudy.config;

import com.adam9e96.BlogStudy.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * HTTP 요청이 들어올 때마다 실행되는 커스텀 토큰 인증 필터 클래스
 * <p>
 * OnePerRequestFilter를 상속받아 구현 모든 HTTP 요청마다 한 번씩 실행됨
 */
@RequiredArgsConstructor
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // ==== 토큰 추출 ===== //
        // 요청 헤더의 Authorization 키의 값 조회
        // HTTP 요청 헤더에서 "Authorization" 값만 가져옴
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        log.info("Authorization Header: {}", authorizationHeader);  // 헤더 로그

        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);
        log.info("Extracted Token: {}", token);  // 추출된 토큰 로그


        // 가져온 토큰이 유효한지 확인하고, 유효한 때는 인증 정보 설정
        // === 토큰 검증 및 인증 === //
        /*
         * 1. 추출한 토큰이 유효한지 검사
         * 2. 유효한 토큰이면 사용자 정보를 추출하여 Authentication 객체 생성
         * 3. SecurityContextHolder에 인증 정보를 저장하여 이후 요청 처리과정에서 인증된 사용자임을 알림
         */
        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Valid Token. Authentication successful");
        } else {
            log.info("Invalid Token. Authentication failed");
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        // "Bearer " 접두사 제거 하여 실제 토큰만 추출
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}

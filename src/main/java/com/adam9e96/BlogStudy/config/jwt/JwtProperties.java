package com.adam9e96.BlogStudy.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 관련 설정을 담는 프로퍼티 클래스입니다.
 * application.properties 또는 application.yml 파일에서 'jwt'로 시작하는 설정 값을 매핑합니다.
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt") // 'jwt' 접두어가 있는 프로퍼티를 이 클래스의 필드에 바인딩
public class JwtProperties {

    /**
     * JWT 토큰의 발급자(issuer)를 지정합니다.
     * 일반적으로 토큰을 발급한 서버의 식별자 역할을 합니다.
     */
    private String issuer;

    /**
     * JWT 토큰을 서명하는 데 사용되는 비밀 키(secret key)입니다.
     * 이 키는 토큰의 무결성과 신뢰성을 보장하는 데 사용되므로 안전하게 관리해야 합니다.
     */
    private String secretKey;
}

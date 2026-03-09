package com.spring.token.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.spring.token.config.jwt.JwtProperties;

import lombok.RequiredArgsConstructor;

// redis 1. 	-->JwtAuthenticationFilter
/*
 * Redis 기반 토큰 관리 서비스
 * 
 * - key 구조
 * RT: {username} -> TTL : 7일
 * BL: {accessToken} -> 로그아웃시 더이상 활용 x, TTL: AT 만료 시간
 */
@RequiredArgsConstructor
@Service
public class TokenRedisService {
	
	private final RedisTemplate<String, String> redisTemplate;
	
	private static final String RT_PREFIX = "RT:";
	private static final String BL_PREFIX = "BL:";
	
	
	// ===Refresh Token ---
	// 로그인 성공시, RT를 저장
	public void saveRefreshToken(String username, String refreshToken, long ttlseconds) {
		redisTemplate.opsForValue().set(
				RT_PREFIX + username, 
				refreshToken, 
				ttlseconds, 
				TimeUnit.SECONDS);
	}
	
	
	
	// redis 5.		--> SecurityApiController
	// 로그아웃시, RT 삭제
	public void deleteRefreshToken(String username) {
		redisTemplate.delete(RT_PREFIX + username);
	}
	
	// ===BlackList : Access Token ---
	// 로그아웃시, AT -> 블랙리스트 등록
	public void addToBlackList(String accessToken, long remainingTtlSeconds) {
		redisTemplate.opsForValue().set(
				BL_PREFIX + accessToken,
				"logout",
				remainingTtlSeconds,
				TimeUnit.SECONDS);
	}
	
	// redis 8-1		--> controller
	// 요청시, Redis 저장된 RT 일치 검증
	public boolean isRefreshTokenValid(String username, String refreshToken) {
		String stored = redisTemplate.opsForValue().get(RT_PREFIX + username);
		return stored != null && stored.equals(refreshToken);
	}
	
	
	// 8-4 
		// RT Rotaion : 기존 Redis RT 삭제 -> 새 RT 저장
		public void rotateRefreshToken(String username, String newRefreshToken, long ttlSeconds) {
			redisTemplate.delete(RT_PREFIX + username);
			saveRefreshToken(username, newRefreshToken, ttlSeconds);
		}	
	
	// redis 7-1
	// AT가 블랙리스트 여부 확인
	public boolean isBlackListed(String accessToken) {
		return Boolean .TRUE.equals(redisTemplate.hasKey(BL_PREFIX + accessToken));
	}


	
	}
	
	
	
	


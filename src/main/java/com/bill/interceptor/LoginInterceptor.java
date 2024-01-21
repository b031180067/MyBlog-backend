package com.bill.interceptor;

import com.bill.util.JwtUtil;
import com.bill.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 登入驗證攔截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * true是通過，false是不通過
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 取得jwt token
		String token = request.getHeader("Authorization");
		try {
			// 檢查redis裡是否有此token
			String redisToken = stringRedisTemplate.opsForValue().get(token);
			if (redisToken == null) {
				throw new RuntimeException();
			}
			// 解析token
			Map<String, Object> claims = JwtUtil.parseToken(token);
			// 將數據存放到ThreadLocal
			ThreadLocalUtil.set(claims);
			return true;
		} catch (Exception e) {
			// 設置狀態碼401 未授權
			response.setStatus(401);
			return false;
		}
	}

	/**
	 * 資料回傳前，將ThreadLocal裡的資料清空
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadLocalUtil.remove();
	}

}

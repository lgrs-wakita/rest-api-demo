package com.example.restapidemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.example.restapidemo.common.Public;
import com.example.restapidemo.common.SessionNames;
import com.example.restapidemo.data.entity.User;
import com.example.restapidemo.exception.UnauthorizedException;
import com.example.restapidemo.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Interceptor
 *
 * @author Daisuke Wakita
 */
@Component
@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {

			log.trace("Interceptor#preHandle");
			log.trace("[request.getRequestURI()]{}", request.getRequestURI());

			if (handler instanceof ResourceHttpRequestHandler) {
				return true;
			}

			// コントローラのメソッド
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			log.trace("[handlerMethod]{}", handlerMethod);

			// ログイン不要ならチェックしない
			if (handlerMethod.getMethodAnnotation(Public.class) != null) {
				return true;
			}

			// ログインユーザー取得
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(SessionNames.LOGIN_USER.name());
			log.trace("[loginUser]{}", user);

			if (user == null) {
				log.trace("ログインしていない");
				throw new UnauthorizedException();
			}
			user = userService.get(user.getId());
			log.trace("[loginUser]{}", user);

			if (user == null) {
				log.trace("ログイン不可または削除されたユーザー");
				throw new UnauthorizedException();
			}

			// セッションにセットしなおす
			session.setAttribute(SessionNames.LOGIN_USER.name(), user);

			return true;

		} catch (Exception e) {
			throw e;
		}
	}

}

/*
 * Created on Tue Dec 27 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.repository.account.Account;

public class AuthResolver implements HandlerInterceptor, HandlerMethodArgumentResolver {

    @Autowired
    @Setter
    private APIAuthenticator authenticator;

    @Autowired
    @Setter
    private AccountService service;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            if (handlerMethod.getMethodAnnotation(WithAuth.class) == null) {
                return true;
            }

            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return false;
            }

            Integer accountId = authenticator.authenticate(authHeader);
            if (accountId == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return false;
            }

            Account account = service.getValid(accountId);
            if (account == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return false;
            }

            request.setAttribute("authAccount", account);
        }

		return true;
	}

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthAccount.class);
    }

    @Override
    @Nullable
    public Object resolveArgument(
        MethodParameter parameter,
        @Nullable ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        @Nullable WebDataBinderFactory binderFactory
    ) throws Exception {
        if (!Account.class.equals(parameter.getParameterType())) {
            throw new IllegalArgumentException("Parameter type with @AuthAccount must be Account");
        }

        Account account = (Account) webRequest.getAttribute("authAccount", WebRequest.SCOPE_REQUEST);
        if (account == null) {
            throw new IllegalArgumentException("authAccount is not set");
        }

        return account;
    }
}

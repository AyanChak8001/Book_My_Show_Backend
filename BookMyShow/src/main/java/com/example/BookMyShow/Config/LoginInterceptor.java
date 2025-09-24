package com.example.BookMyShow.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * Called before the controller actually handles the request.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Incoming request: method={} uri={} from={}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true; // continue processing
    }

    /**
     * Called after handler returns but before view is rendered.
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        // no-op for now; could add timing or response enrichment
    }

    /**
     * Called after the complete request has finished.
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) throws Exception {
        Object start = request.getAttribute("startTime");
        if (start instanceof Long) {
            long duration = System.currentTimeMillis() - (Long) start;
            log.info("Completed request: method={} uri={} status={} timeMs={}",
                    request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
        } else {
            log.info("Completed request: method={} uri={} status={}", request.getMethod(), request.getRequestURI(), response.getStatus());
        }
        if (ex != null) {
            log.error("Request raised exception", ex);
        }
    }
}

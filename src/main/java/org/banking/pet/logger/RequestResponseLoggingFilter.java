package org.banking.pet.logger;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long start = System.currentTimeMillis();

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        log.info("-> [{}] {} from IP {}", req.getMethod(), req.getRequestURI(), req.getRemoteAddr());

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - start;
        log.info("<- [{}] {} responded with status {} in {} ms",
                req.getMethod(),
                req.getRequestURI(),
                res.getStatus(),
                duration);
    }
}

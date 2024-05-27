package id.my.hendisantika.springdocjwt.security;

import id.my.hendisantika.springdocjwt.config.WebSecurityConfig;
import id.my.hendisantika.springdocjwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        boolean isInvalidToken = false;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            isInvalidToken = true;
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } else {
            isInvalidToken = true;
        }

        if (isInvalidToken) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        }

        filterChain.doFilter(request, response);
    }

    //	@Override
    //	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
    //			throws IOException, ServletException {
    //		final String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
    //		final String jwt;
    //		final String userEmail;
    //
    //		String path = ((HttpServletRequest) request).getRequestURI();
    //		String[] allowedPaths = WebSecurityConfig.PUBLIC_REQUEST_MATCHERS;
    //		for (var allowedPath : allowedPaths) {
    //			allowedPath = allowedPath.replace("*", "");
    //			if (path.startsWith(allowedPath)) {
    //				filterChain.doFilter(request, response);
    //				return;
    //			}
    //		}
    //
    //		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
    //			filterChain.doFilter(request, response);
    //			return;
    //		}
    //		jwt = authHeader.substring(7);
    //		userEmail = jwtService.extractUsername(jwt);
    //		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
    //			if (jwtService.isTokenValid(jwt, userDetails)) {
    //				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    //						userDetails,
    //						null,
    //						userDetails.getAuthorities()
    //						);
    //				authToken.setDetails(
    //						new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request)
    //						);
    //				SecurityContextHolder.getContext().setAuthentication(authToken);
    //			}
    //		}
    //		filterChain.doFilter(request, response);
    //
    //	}

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        //String path = request.getRequestURI();
        //return "/health".equals(path);

        String path = request.getRequestURI();
        String[] allowedPaths = WebSecurityConfig.PUBLIC_REQUEST_MATCHERS;
        for (var allowedPath : allowedPaths) {
            allowedPath = allowedPath.replace("/*", "");
            allowedPath = allowedPath.replace("/**", "");
            if (path.contains(allowedPath)) {
                return true;
            }
        }

        return false;
    }
}

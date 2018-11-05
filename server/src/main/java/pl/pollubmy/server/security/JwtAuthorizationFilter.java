package pl.pollubmy.server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.pollubmy.server.security.SecurityParameters.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    @Autowired
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER);
        if (header == null || !header.startsWith(PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuth = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {

        String token = request.getHeader(HEADER);

        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(PREFIX, ""))
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null);
            }

            return null;
        }

        return null;

        /*if (token == null) return null;

        String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(PREFIX, "")).getBody().getSubject();

        org.springframework.security.core.userdetails.UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        User user = customUserDetailsService.loadFromDBUserByLoginOrEmail(username);
        return username != null ? new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities()) : null;*/
    }
}
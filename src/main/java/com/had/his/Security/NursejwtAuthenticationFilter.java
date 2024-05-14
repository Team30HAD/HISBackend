package com.had.his.Security;


<<<<<<< HEAD
import com.had.his.DAO.TokenDAO;
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
import com.had.his.UserDetailsService.NurseDetailsService;
import com.had.his.UserDetailsService.ServiceDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class NursejwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private NurseDetailsService serviceDetails;
<<<<<<< HEAD
    private HttpSession httpSession;
    @Autowired
    private TokenDAO tokenDAO;

    public NursejwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, NurseDetailsService serviceDetails, HttpSession httpSession) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.serviceDetails = serviceDetails;
        this.httpSession = httpSession;
=======

    public NursejwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, NurseDetailsService serviceDetails) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.serviceDetails = serviceDetails;
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

<<<<<<< HEAD

=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
        String token;
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        String user = jwtTokenProvider.getuserfromtoken(token);

<<<<<<< HEAD

        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = serviceDetails.loadUserByUsername(user);
            String expecteduser=tokenDAO.findUserByToken(token);

            if (userDetails != null && jwtTokenProvider.validateToken(token, userDetails)&& (expecteduser.equals(user)) ){

                String requestIpAddress = getClientIpAddress(request);
                System.out.println(requestIpAddress);

                String tokenIpAddress = tokenDAO.findipAdress(user);
                if (tokenIpAddress == null) {
                    tokenDAO.saveip(user, requestIpAddress);
                } else if (!tokenIpAddress.equals(requestIpAddress)) {
                    tokenDAO.deleteip(tokenIpAddress);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Already Logged In other device");
                    return;
                }

=======
        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = serviceDetails.loadUserByUsername(user);

            if (userDetails != null && jwtTokenProvider.validateToken(token, userDetails)) {
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid user");
<<<<<<< HEAD
                return;
=======
                return; // Return after sending error response
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
            }
        }

        filterChain.doFilter(request, response);
    }
<<<<<<< HEAD

    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_FORWARDED");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("REMOTE_ADDR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
}

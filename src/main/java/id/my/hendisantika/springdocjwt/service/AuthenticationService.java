package id.my.hendisantika.springdocjwt.service;

import id.my.hendisantika.springdocjwt.model.AuthenticationRequest;
import id.my.hendisantika.springdocjwt.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AuthenticationService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userDetailsService.loadUserByUsername(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        var response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }
}

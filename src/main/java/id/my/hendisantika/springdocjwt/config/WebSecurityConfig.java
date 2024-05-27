package id.my.hendisantika.springdocjwt.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:11
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class WebSecurityConfig {

    //@Autowired
    //private JwtAuthenticationFilter jwtAuthenticationFilter;
    public final static String[] PUBLIC_REQUEST_MATCHERS = {"/api/v1/auth/**", "/api-docs/**", "/swagger-ui/**"};
}

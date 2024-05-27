package id.my.hendisantika.springdocjwt.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:04
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
}

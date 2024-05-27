package id.my.hendisantika.springdocjwt.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:03
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bookName;
    private String Author;
}

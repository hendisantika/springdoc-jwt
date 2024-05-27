package id.my.hendisantika.springdocjwt.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:17
 * To change this template use File | Settings | File Templates.
 */
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Book", description = "The Book API. Contains all the operations that can be performed on a book.")
@RequestMapping("/api/book")
public class BookController {
}

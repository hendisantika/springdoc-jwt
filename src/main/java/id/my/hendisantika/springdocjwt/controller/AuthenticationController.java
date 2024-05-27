package id.my.hendisantika.springdocjwt.controller;

import id.my.hendisantika.springdocjwt.model.AuthenticationRequest;
import id.my.hendisantika.springdocjwt.model.AuthenticationResponse;
import id.my.hendisantika.springdocjwt.model.Book;
import id.my.hendisantika.springdocjwt.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:15
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate to get access JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Authentication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "400", description = "Failed Authentication",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Failed Authentication",
                    content = @Content)})
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

package org.jacen.todo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jacen.todo.model.Auth;
import org.jacen.todo.types.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

class LoginRequest {
    public String username;
    public String password;
}

class JWTResponse {
    public String accessToken;
    public String refreshToken;
    JWTResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

@Tag(name = "Auth", description = "Auth API")
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    public AuthController() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.mongoDatabase = mongoClient.getDatabase("todo");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKey = "-----BEGIN RSA PRIVATE KEY-----" +
                "MIIEogIBAAKCAQBdGIK8YN0bLA9u6dWro1I27ti6LpD3cwY6+o5kjin60LQ0dRcZ" +
                "UFwJzUF1g0v6Ucon/FgihZ+wHNPvyNQVVBXh0BooSQq6wWBlAjpPezuxQo5HPlsP" +
                "a0rXe0DtrLZke2FLDIcrZT99BrE0/HBEftZ4kM3Vn1FTZy0COc8Re1H12cZpSVbk" +
                "IaSwz5NiPK3s6kGBG77z5aQ15VcZwKiTzZ0ui7OxTvbcecIFntc2+19rU60LOKvU" +
                "uWMb6b+kQ2J2ZJqkv2LhE/rzVb5TBwVuTSsCweuoP1VKxBt49UI8Ie6xQQO6VdAh" +
                "X91NaKMUA87C9WjZ8zxVDMK8SzwBk6+I6sAvAgMBAAECggEAWhAeWnQzM9haTej2" +
                "h4gHP5GuJeBq6pliy6geHXXTcwx5ac5lPF4bzHe//hm5ZT+/HRPph+xR4qTg/DcD" +
                "xmaEjOMwiwyuzqHJya5wDvifdQt4bcALhE0+KFzbIZRXF7qtE1fZZMgcta3FvgcB" +
                "7aHvhtA/8Yyhttg4GNEoXDc7wWV0PAAxfp9Yor7bRk67hAG37FxXcUeqP9fiRjkw" +
                "DFsth3jWSd7amoD4C3q5DMTxfjWQA5s4fP0RhIqKszM/1094E0mnpKd4XVPqF6mg" +
                "GjXIewHsvsBgL6O33zQ7B+3Yj7GnHcdiNiWcsMQXV5E6ZeOUcmES/o4sl2PLW3KR" +
                "sOgr0QKBgQCleMyDwdF4dT36nVugSHYfuGqnQnffDp6HXwWri7XwMKu9kpeTd9N2" +
                "C5ATnEdkRR6e+g6p9LrmMNZ3Ywq5JHjGFG6o7GduJ/lU1H63Nd/4KSlIayFLq0bh" +
                "IXtphgsZ6Mh2Kjt6N42JeGslt+VjImsYTukidqJEMNtY/OpV8FJypQKBgQCQBw6I" +
                "Pz5/pMkwJ6h2Ejn06Ig/aOHr2l2NK8wK7FktHT8bfYaR+ZhS88F2tCxN5M7Vvn2c" +
                "tG/XUQUGKICocAFMIDiVyNgSk8S3jaadGiThUSbW3tcGvXEA7fhALGILQxBDZnSq" +
                "7PKPsZ3V4mUrbDgxp5iglcXGTg/h/NQhD9qTQwKBgHRZxmR26lVrCxOnhf/kM7sG" +
                "L2hAoo0jeHDzbcc7y8iCUKBR7WbyAKymYL6JSuHEgE2Ewr7aqHinqoX0DFw2uv18" +
                "7Hq3D9szHGx7pkYpCb0zJCYZviW433P52Q9QA4pKVnpQI9ZCLbGWDLFO52qXjvpe" +
                "sitbg6mzOOkPna6j9zVJAoGAZMsLeH4KFvoTcgfO9RI8TRCOiRG1SYFABxOnb92H" +
                "avMUvwBRtMafuy2rLo9YkgqWVS9CaPA9dMnWYkDmCPqZbdT+xJQR4q5W9L3E5Bzv" +
                "wl8wdx7sDGN3OddP7FkjJA6CyyhgbV31QmpistQFSSYrDVTkkz09c1Id+2EccybN" +
                "KFECgYEAo7WIMiy03J5KNAuyxAvjSnmznOzHlncMQvrKtQb/nOjhMXDTtelv+EI9" +
                "zEZj+CN5TtxRh7EY9gjHENpeBxTeFlU3aGRmSi2DuJ6dP6UQfm4fRXknBggSQd5z" +
                "js6CDoZ+RbJXByr+2tLdR0So1CBeCIBZZTs7JcY/VP4S+lQ+7Ns=" +
                "-----END RSA PRIVATE KEY-----";
        String publicKey = "-----BEGIN PUBLIC KEY-----" +
                "MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQBdGIK8YN0bLA9u6dWro1I2" +
                "7ti6LpD3cwY6+o5kjin60LQ0dRcZUFwJzUF1g0v6Ucon/FgihZ+wHNPvyNQVVBXh" +
                "0BooSQq6wWBlAjpPezuxQo5HPlsPa0rXe0DtrLZke2FLDIcrZT99BrE0/HBEftZ4" +
                "kM3Vn1FTZy0COc8Re1H12cZpSVbkIaSwz5NiPK3s6kGBG77z5aQ15VcZwKiTzZ0u" +
                "i7OxTvbcecIFntc2+19rU60LOKvUuWMb6b+kQ2J2ZJqkv2LhE/rzVb5TBwVuTSsC" +
                "weuoP1VKxBt49UI8Ie6xQQO6VdAhX91NaKMUA87C9WjZ8zxVDMK8SzwBk6+I6sAv" +
                "AgMBAAE=" +
                "-----END PUBLIC KEY-----";
        byte[] privateKeyData = Base64.getDecoder().decode((privateKey.getBytes()));
        X509EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(privateKeyData);
        KeyFactory privateKeyFact = KeyFactory.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKeyFact.generatePrivate(privateKeySpec);

        byte[] publicKeyData = Base64.getDecoder().decode((publicKey.getBytes()));
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyData);
        KeyFactory publicKeyFact = KeyFactory.getInstance("RSA");
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKeyFact.generatePublic(publicKeySpec);
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            Date now = new Date();
            String accessToken = JWT.create()
                    .withIssuer("waffle-jacen")
                    .withClaim("username", "jacen")
                    .withIssuedAt(now)
                    .withExpiresAt(new Date(now.getTime() + 1000 * 60))
                    .sign(algorithm);
            String refreshToken = JWT.create()
                    .withIssuer("waffle-jacen")
                    .withClaim("username", "jacen")
                    .withIssuedAt(now)
                    .withExpiresAt(new Date(now.getTime() + 1000 * 60 * 60))
                    .sign(algorithm);
            return ResponseEntity.ok(new JWTResponse(accessToken, refreshToken));
        } catch (JWTCreationException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse> logout() {
        return ResponseEntity.ok(new APIResponse<>(true, "logout"));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register() {
        return ResponseEntity.ok(new APIResponse<>(true, "register"));
    }
}

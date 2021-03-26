package com.rnd.corp.springpocketapi.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sun.istack.NotNull;

public class JwtHelper {

    private static final String SECRET = "monbeausecret";
    private static final String ISSUER = "Users M1if13";
    private static final long LIFETIME = 3600000; // Durée de vie d'un token : 60 minutes
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    /**
     * Vérifie l'authentification d'un utilisateur grâce à un token JWT
     *
     * @param token  le token à vérifier
     * @param origin l'origine de la requête HTTP (nécessaire pour vérifier si l'origine de la requête est la même
     *               que celle du token)
     * @return un booléen qui indique si le token est bien formé et valide (pas expiré) et si l'utilisateur est
     *     authentifié
     */
    public static String verifyToken(String token, @NotNull String origin)
        throws NullPointerException, JWTVerificationException {
        JWTVerifier authenticationVerifier = JWT.require(algorithm)
                                                .withIssuer(ISSUER)
                                                .withAudience(origin) // Non-reusable verifier instance
                                                .build();

        authenticationVerifier.verify(
            token); // Lève une NullPointerException si le token n'existe pas, et une JWTVerificationException
        // s'il est invalide
        DecodedJWT jwt = JWT.decode(
            token); // Pourrait lever une JWTDecodeException mais comme le token est vérifié avant, cela ne
        // devrait pas arriver
        return jwt.getClaim("sub").asString();
    }

    /**
     * Crée un token avec les caractéristiques de l'utilisateur
     *
     * @param subject le login de l'utilisateur
     * @param origin  l'origine de la requête HTTP
     * @return le token signé
     * @throws JWTCreationException si les paramètres ne permettent pas de créer un token
     */
    public static String generateToken(String subject, String origin) throws JWTCreationException {
        return JWT.create()
                  .withIssuer(ISSUER)
                  .withSubject(subject)
                  .withAudience(origin)
                  .withExpiresAt(new Date(new Date().getTime() + LIFETIME))
                  .sign(algorithm);
    }

    public static String auth(String login, String origin) {
        return generateToken(login, origin);
    }

    public static boolean isAuth(String token, String origin) {
        String realToken = token.split(" ")[1];
        return verifyToken(realToken, origin) != null;
    }
}

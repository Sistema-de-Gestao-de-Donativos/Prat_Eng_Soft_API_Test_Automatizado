package prat_eng_soft_api_test_automatizado.utils;

import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class jwtTokenGerasor {

    private PrivateKey privateKey;

    /* 
    public JwtGenerator(String privateKeyPath) throws Exception {
        this.privateKey = loadPrivateKey(privateKeyPath);
    }

    private PrivateKey loadPrivateKey(String path) throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(path)))
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return java.security.KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public String generateToken(String userId, String email, String role) {
        Algorithm algorithm = Algorithm.RSA256(null, privateKey);
        return JWT.create()
                .withSubject(userId)
                .withClaim("email", email)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 dia em milissegundos
                .sign(algorithm);
    }*/
    
}

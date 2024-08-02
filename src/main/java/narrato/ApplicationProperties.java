package narrato;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "narrato")
public record ApplicationProperties(OpenAPIProperties openapi, CorsProperties cors, JwtProperties jwt) {

    public record JwtProperties(
            @DefaultValue("Narrato") String issuer,
            @DefaultValue("Authorization") String header,
            @DefaultValue("604800") Long expiresIn,
            @NotEmpty String secret) {}

    public record OpenAPIProperties(
            @DefaultValue("Narrato API") String title,
            @DefaultValue("Narrato API Swagger Documentation") String description,
            @DefaultValue("v1.0.0") String version,
            Contact contact) {

        public record Contact(@DefaultValue("Narrato") String name, @DefaultValue("support@narrato.in") String email) {}
    }

    public record CorsProperties(
            @DefaultValue("/api/**") String pathPattern,
            @DefaultValue("*") String allowedOrigins,
            @DefaultValue("*") String allowedMethods,
            @DefaultValue("*") String allowedHeaders) {}
}

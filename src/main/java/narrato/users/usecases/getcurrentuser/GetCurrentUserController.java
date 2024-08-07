package narrato.users.usecases.getcurrentuser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GetCurrentUserController {
    private final AuthService authService;

    GetCurrentUserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/api/user")
    @Operation(summary = "Get current user", tags = "User and Authentication")
    @SecurityRequirement(name = "Token")
    UserResponse getCurrentUser() {
        var loginUser = authService.getCurrentUserOrThrow();
        return UserResponse.from(loginUser);
    }
}

package narrato.users.usecases.updateuser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.LoginUser;
import narrato.users.usecases.shared.models.UserResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UpdateUserController {
    private final UpdateUser updateUser;
    private final AuthService authService;

    UpdateUserController(UpdateUser updateUser, AuthService authService) {
        this.updateUser = updateUser;
        this.authService = authService;
    }

    @PutMapping("/api/user")
    @Operation(summary = "Update current user", tags = "User and Authentication")
    @SecurityRequirement(name = "Token")
    UserResponse update(@RequestBody @Valid UpdateUserPayload payload) {
        LoginUser loginUser = authService.getCurrentUserOrThrow();
        return updateUser.execute(loginUser, payload.user());
    }

    record UpdateUserPayload(@Valid UpdateUserCmd user) {}
}

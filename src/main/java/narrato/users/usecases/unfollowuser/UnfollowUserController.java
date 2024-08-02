package narrato.users.usecases.unfollowuser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.LoginUser;
import narrato.users.usecases.shared.models.ProfileResponse;
import narrato.users.usecases.shared.repo.GetProfileRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UnfollowUserController {
    private final AuthService authService;
    private final UnfollowUserRepository unfollowUser;
    private final GetProfileRepository getProfileRepository;

    UnfollowUserController(
            AuthService authService, UnfollowUserRepository unfollowUser, GetProfileRepository getProfileRepository) {
        this.authService = authService;
        this.unfollowUser = unfollowUser;
        this.getProfileRepository = getProfileRepository;
    }

    @DeleteMapping("/api/profiles/{username}/follow")
    @Operation(summary = "Unfollow User", tags = "Profile")
    @SecurityRequirement(name = "Token")
    ProfileResponse unfollowUser(@PathVariable("username") String username) {
        LoginUser loginUser = authService.getCurrentUserOrThrow();
        unfollowUser.unfollow(loginUser, username);
        var profile = getProfileRepository.findProfile(loginUser, username).orElseThrow();
        return new ProfileResponse(profile);
    }
}

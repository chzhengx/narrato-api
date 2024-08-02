package narrato.users.usecases.followuser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.LoginUser;
import narrato.users.usecases.shared.models.ProfileResponse;
import narrato.users.usecases.shared.repo.GetProfileRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FollowUserController {
    private final AuthService authService;
    private final FollowUserRepository followUser;
    private final GetProfileRepository getProfileRepository;

    FollowUserController(
            AuthService authService, FollowUserRepository followUser, GetProfileRepository getProfileRepository) {
        this.authService = authService;
        this.followUser = followUser;
        this.getProfileRepository = getProfileRepository;
    }

    @PostMapping("/api/profiles/{username}/follow")
    @Operation(summary = "Follow User", tags = "Profile")
    @SecurityRequirement(name = "Token")
    ProfileResponse followUser(@PathVariable("username") String username) {
        LoginUser loginUser = authService.getCurrentUserOrThrow();
        followUser.follow(loginUser, username);
        var profile = getProfileRepository.findProfile(loginUser, username).orElseThrow();
        return new ProfileResponse(profile);
    }
}

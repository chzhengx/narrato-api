package narrato.users.usecases.getprofile;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;
import narrato.shared.ResourceNotFoundException;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.Profile;
import narrato.users.usecases.shared.models.ProfileResponse;
import narrato.users.usecases.shared.repo.GetProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GetProfileController {
    private final AuthService authService;
    private final GetProfileRepository getProfile;

    GetProfileController(AuthService authService, GetProfileRepository getProfile) {
        this.authService = authService;
        this.getProfile = getProfile;
    }

    @GetMapping("/api/profiles/{username}")
    @Operation(summary = "Get User Profile", tags = "Profile")
    ResponseEntity<ProfileResponse> getProfile(@PathVariable String username) {
        Optional<Profile> optionalProfile = getProfile.findProfile(authService.getCurrentUser(), username);
        return optionalProfile
                .map(p -> ResponseEntity.ok(new ProfileResponse(p)))
                .orElseThrow(() -> new ResourceNotFoundException("Profile with username " + username + " not found"));
    }
}

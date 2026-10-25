package es.twinteach.php2bash.api;

import es.twinteach.php2bash.service.SecurityAssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/security")
public class SecurityController {
    private final SecurityAssessmentService service;

    public SecurityController(SecurityAssessmentService service) {
        this.service = service;
    }

    @PostMapping("/assess")
    public SecurityAssessmentService.Assessment assess(@RequestBody AssessmentRequest request) {
        try {
            return service.assess(request.version(), request.runtime());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public record AssessmentRequest(String version, String runtime) { }
}

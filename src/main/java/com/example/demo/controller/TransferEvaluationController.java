@RestController
@RequestMapping("/api/transfer-evaluations")
public class TransferEvaluationController {

    private final TransferEvaluationService service;

    public TransferEvaluationController(TransferEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public TransferEvaluationResult evaluate(
            @PathVariable Long sourceCourseId,
            @PathVariable Long targetCourseId) {

        return service.evaluateTransfer(sourceCourseId, targetCourseId);
    }

    @GetMapping("/{id}")
    public TransferEvaluationResult get(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<TransferEvaluationResult> getForCourse(
            @PathVariable Long courseId) {

        return service.getEvaluationsForCourse(courseId);
    }
}

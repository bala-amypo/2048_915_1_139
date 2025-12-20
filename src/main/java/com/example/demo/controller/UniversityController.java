@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public University create(@RequestBody University u) {
        return service.createUniversity(u);
    }

    @PutMapping("/{id}")
    public University update(@PathVariable Long id, @RequestBody University u) {
        return service.updateUniversity(id, u);
    }

    @GetMapping("/{id}")
    public University get(@PathVariable Long id) {
        return service.getUniversityById(id);
    }

    @GetMapping
    public List<University> getAll() {
        return service.getAllUniversities();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateUniversity(id);
    }
}

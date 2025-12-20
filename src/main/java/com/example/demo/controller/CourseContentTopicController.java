@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {

    private final CourseContentTopicService service;

    public CourseContentTopicController(CourseContentTopicService service) {
        this.service = service;
    }

    @PostMapping
    public CourseContentTopic create(@RequestBody CourseContentTopic topic) {
        return service.createTopic(topic);
    }

    @PutMapping("/{id}")
    public CourseContentTopic update(@PathVariable Long id,
                                     @RequestBody CourseContentTopic topic) {
        return service.updateTopic(id, topic);
    }

    @GetMapping("/{id}")
    public CourseContentTopic get(@PathVariable Long id) {
        return service.getTopicById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseContentTopic> getByCourse(@PathVariable Long courseId) {
        return service.getTopicsForCourse(courseId);
    }
}

package com.lambdacode.spring.boot.crud.Topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Topic topic = topicService.getTopicById(id);
        return topic != null ? ResponseEntity.ok(topic) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic, @PathVariable Long courseId) {
        // Set the course for the topic using courseId and save it
        // Implement the logic to link the topic to the specific course
        // ...

        Topic addedTopic = topicService.addTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic updatedTopic) {
        Topic topic = topicService.updateTopic(id, updatedTopic);
        return topic != null ? ResponseEntity.ok(topic) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok("Topic deleted successfully");
    }

    // Other methods as needed

}
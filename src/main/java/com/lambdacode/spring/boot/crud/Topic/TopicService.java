package com.lambdacode.spring.boot.crud.Topic;

import java.util.*;
import org.springframework.stereotype.Service;


@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Long id, Topic updatedTopic) {
        if (topicRepository.existsById(id)) {
            updatedTopic.setId(id);
            return topicRepository.save(updatedTopic);
        }
        return null;
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
    // Other methods as needed
}
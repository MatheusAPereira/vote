package br.com.voting.vote.services;

import br.com.voting.vote.dtos.TopicDTO;
import br.com.voting.vote.models.Topic;

import java.util.List;

public interface TopicService {
    void createTopic (TopicDTO topicDTO);
    List<Topic> findAll();
    Topic findById(String id);
    void updateTopic (TopicDTO topicDTO, String id);
    void deleteTopic (String id);
}

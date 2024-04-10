package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.TopicDTO;
import br.com.voting.vote.exception.NotFoundException;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.repositories.TopicRepository;
import br.com.voting.vote.services.TopicService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.repository = topicRepository;
    }

    @Transactional
    @Override
    public void createTopic(TopicDTO topicDto){
        Topic topic = new Topic();
        topic.setName(topicDto.getName());

        repository.save(topic);
    }

    @Override
    public List<Topic> findAll() {
        return repository.findAll();
    }

    @Override
    public Topic findById(String id) {
        return repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new NotFoundException("Topico não encontrado"));
    }

    @Transactional
    @Override
    public void updateTopic(TopicDTO topicDTO, String id){
        Topic topic = findById(id);
        if (topic != null){
            //topic.setId(Long.parseLong(topicDTO.getId()));
            topic.setName(topicDTO.getName());
            repository.save(topic);
        }

    }

    @Override
    public void deleteTopic(String id) {
        Topic topic = findById(id);
        repository.delete(topic);
    }
}

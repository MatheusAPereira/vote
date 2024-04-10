package br.com.voting.vote.controllers;

import br.com.voting.vote.dtos.TopicDTO;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<Topic>> getAll(){
        return ResponseEntity.ok(topicService.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Topic> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(topicService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TopicDTO topicDTO){
        topicService.createTopic(topicDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> update(@RequestBody TopicDTO topicDTO, @PathVariable("id") String id){
        System.out.print(id);
        topicService.updateTopic(topicDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") String id){
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

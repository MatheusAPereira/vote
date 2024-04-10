package br.com.voting.vote.controllers;

import br.com.voting.vote.dtos.VoteDTO;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.models.VoteResult;
import br.com.voting.vote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody VoteDTO voteDTO){
        voteService.saveVote(voteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Vote> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(voteService.findVoteById(id));
    }

    @GetMapping("/result/{topicId}")
    public ResponseEntity<VoteResult> getResult(@PathVariable("topicId") String id){
        return ResponseEntity.ok(voteService.voteCounter(id));
    }
}

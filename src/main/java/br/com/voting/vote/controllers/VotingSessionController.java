package br.com.voting.vote.controllers;

import br.com.voting.vote.dtos.AssociateDTO;
import br.com.voting.vote.dtos.VotingSessionDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.VotingSession;
import br.com.voting.vote.services.VotingSessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/voting-session")
public class VotingSessionController {

    @Autowired
    VotingSessionService votingSessionService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody VotingSessionDTO votingSessionDTO) {
        votingSessionService.createSession(votingSessionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<VotingSession> getSessionId(@PathVariable("id") String id){
        return ResponseEntity.ok(votingSessionService.findSessionById(id));
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> Delete(@PathVariable("id") String id){
        votingSessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}

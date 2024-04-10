package br.com.voting.vote.services;

import br.com.voting.vote.dtos.VotingSessionDTO;
import br.com.voting.vote.models.VotingSession;

public interface VotingSessionService {
    void createSession (VotingSessionDTO votingSessionDTO);
    VotingSession findSessionById (String id);
    void updateSession (VotingSessionDTO votingSessionDTO);
    void deleteSession (String id);
}

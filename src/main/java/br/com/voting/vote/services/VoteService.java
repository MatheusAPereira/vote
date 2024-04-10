package br.com.voting.vote.services;


import br.com.voting.vote.dtos.VoteDTO;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.models.VoteResult;

public interface VoteService {
    void saveVote(VoteDTO voteDTO);
    Vote findVoteById(String id);
    VoteResult voteCounter(String topicId);
}

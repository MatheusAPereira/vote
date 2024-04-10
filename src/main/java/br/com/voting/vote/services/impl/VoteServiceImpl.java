package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.VoteDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.exception.ExpiredException;
import br.com.voting.vote.exception.HasAlreadyVotedException;
import br.com.voting.vote.exception.NotFoundException;
import br.com.voting.vote.exception.NotValidException;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.models.VoteResult;
import br.com.voting.vote.repositories.VoteRepository;
import br.com.voting.vote.services.AssociateService;
import br.com.voting.vote.services.TopicService;
import br.com.voting.vote.services.VoteService;
import br.com.voting.vote.services.VotingSessionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Autowired
    private TopicService topicService;
    @Autowired
    private VotingSessionService votingSessionService;
    @Autowired
    private AssociateService associateService;


    @Transactional
    @Override
    public void saveVote(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setAssociate(associateService.findById(voteDTO.getAssociateId()));
        vote.setVotingSession(votingSessionService.findSessionById(voteDTO.getSessionId()));
        vote.setTopic(topicService.findById(voteDTO.getTopicId()));
        if (voteRepository.existsByAssociateAndTopic(vote.getAssociate(), vote.getTopic())){
            throw new HasAlreadyVotedException("Associado já votou");
        }
        if (vote.getVotingSession().getStatus().equals(StatusVotingSession.CLOSE)){
            throw new ExpiredException("A sessão de votação já foi encerrada");
        }
        try{
            vote.setTypeVote(TypeVote.valueOf(voteDTO.getVote()));
        } catch (Exception e){
            throw new NotValidException("Formato inválido de voto");
        }

        voteRepository.save(vote);
    }

    @Override
    public Vote findVoteById(String id) {
        Optional<Vote> optional = voteRepository.findById(Long.valueOf(id));
        if (optional.isEmpty()){
            throw new NotFoundException("Voto não encontrado");
        }
        return optional.get();
    }

    @Override
    public VoteResult voteCounter(String topicId) {
        VoteResult voteResult = new VoteResult();
        voteResult.setTopicName(topicService.findById(topicId).getName());
        voteResult.setYes(Long.valueOf(voteRepository.countByTopicAndTypeVote(topicService.findById(topicId), TypeVote.SIM)));
        voteResult.setNo(Long.valueOf(voteRepository.countByTopicAndTypeVote(topicService.findById(topicId), TypeVote.NAO)));
        return voteResult;
    }

}

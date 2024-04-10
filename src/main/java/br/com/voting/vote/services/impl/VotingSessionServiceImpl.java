package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.VotingSessionDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.exception.NotFoundException;
import br.com.voting.vote.exception.NotValidException;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.models.VotingSession;
import br.com.voting.vote.repositories.VotingSessionRepository;
import br.com.voting.vote.services.TopicService;
import br.com.voting.vote.services.VotingSessionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository sessionRepository;

    public VotingSessionServiceImpl(VotingSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Autowired
    private TopicService topicService;

    @Transactional
    @Override
    public void createSession(VotingSessionDTO votingSessionDTO) {
        VotingSession votingSession = new VotingSession();
        votingSession.setStartSession(LocalDateTime.now());
        if (votingSessionDTO.getEndSession() == null || votingSessionDTO.getEndSession().isEmpty()) {
            votingSession.setEndSession(votingSession.getStartSession().plusMinutes(1));
        } else {
            try{
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime endDateTime = LocalDateTime.parse(votingSessionDTO.getEndSession(), parser);
                votingSession.setEndSession(endDateTime);
            } catch (Exception e){
                throw new NotValidException("Formato de data/hora inválido");
            }

        }
        Topic topic = topicService.findById(votingSessionDTO.getTopicId());
        if (topic == null){
            throw new NotFoundException("Pauta não encontrada");
        }
        votingSession.setTopic(topic);
        votingSession.setStatus(StatusVotingSession.OPEN);
        sessionRepository.save(votingSession);
    }

    @Override
    public VotingSession findSessionById(String id) {
        VotingSession votingSession = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sessão de votação não encontrada"));
        if (votingSession.getEndSession().isBefore(LocalDateTime.now())){
            VotingSessionDTO votingSessionDTO = new VotingSessionDTO();
            votingSessionDTO.setId(String.valueOf(votingSession.getId()));
            votingSessionDTO.setStartSession(String.valueOf(votingSession.getStartSession()));
            votingSessionDTO.setEndSession(String.valueOf(votingSession.getEndSession()));
            votingSessionDTO.setTopicId(String.valueOf(votingSession.getTopic().getId()));
            votingSessionDTO.setStatus(StatusVotingSession.CLOSE.name());
            updateSession(votingSessionDTO);
            votingSession.setStatus(StatusVotingSession.CLOSE);
        }
        return votingSession;
    }

    @Transactional
    @Override
    public void updateSession(VotingSessionDTO votingSessionDTO) {
        VotingSession votingSession = new VotingSession();
        votingSession.setId(Long.valueOf(votingSessionDTO.getId()));
        votingSession.setStartSession(LocalDateTime.parse(votingSessionDTO.getStartSession()));
        votingSession.setEndSession(LocalDateTime.parse(votingSessionDTO.getStartSession()));
        votingSession.setTopic(topicService.findById(votingSessionDTO.getTopicId()));
        votingSession.setStatus(StatusVotingSession.valueOf(votingSessionDTO.getStatus()));
        sessionRepository.save(votingSession);
    }

    @Override
    public void deleteSession(String id) {
        sessionRepository.deleteById(id);
    }
}

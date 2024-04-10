package br.com.voting.vote.repositories;

import br.com.voting.vote.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, String> {
}

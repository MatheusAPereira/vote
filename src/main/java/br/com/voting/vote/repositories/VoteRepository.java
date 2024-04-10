package br.com.voting.vote.repositories;

import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByAssociateAndTopic(Associate associate, Topic topic);
    int countByTopicAndTypeVote(Topic topic, TypeVote typeVote);
}

package br.com.voting.vote.repositories;

import br.com.voting.vote.models.Associate;
import br.com.voting.vote.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}

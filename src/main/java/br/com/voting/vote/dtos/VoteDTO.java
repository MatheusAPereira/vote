package br.com.voting.vote.dtos;

import jakarta.validation.constraints.NotBlank;

public class VoteDTO {

    String id;
    @NotBlank
    String associateId;
    @NotBlank
    String topicId;
    @NotBlank
    String sessionId;
    @NotBlank
    String vote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}

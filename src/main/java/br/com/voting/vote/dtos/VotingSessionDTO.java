package br.com.voting.vote.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VotingSessionDTO {

    private String id;
    @NotBlank
    private String topicId;
    private String startSession;
    private String endSession;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getStartSession() {
        return startSession;
    }

    public void setStartSession(String startSession) {
        this.startSession = startSession;
    }

    public String getEndSession() {
        return endSession;
    }

    public void setEndSession(String endSession) {
        this.endSession = endSession;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

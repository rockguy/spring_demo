package demo.dto;

import demo.model.Request;

public class RequestDto {

    private long requestId;
    private String login;
    private String email;
    private String fullName;
    private String status;

    public RequestDto(Request request) {
        this.login = request.getUser().getLogin();
        this.email = request.getUser().getEmail();
        this.fullName = request.getUser().getFullName();
        this.requestId = request.getId();
        this.status = request.getStatus();
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

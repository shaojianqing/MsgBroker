package msg.broker.producer.domain;

public class UserLoginStatus {

    private String userId;

    private String userName;

    private String loginStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "UserLoginStatus{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                '}';
    }
}

package iris.front.subscribe;

import javax.validation.constraints.NotNull;

public class SubscribeRequest {
    @NotNull
    String url;
    @NotNull
    String email;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

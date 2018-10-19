package iris.front.subscribe;

import javax.validation.constraints.NotNull;

public class SubscribeRequest {
    @NotNull
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

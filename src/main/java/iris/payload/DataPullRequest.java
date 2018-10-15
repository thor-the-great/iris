package iris.payload;

import javax.validation.constraints.NotNull;

public class DataPullRequest {
    @NotNull
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

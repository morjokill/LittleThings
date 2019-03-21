package site_parse;

public class Site {
    private String title;
    private String link;
    private String tags;
    private String body;

    public Site() {
    }

    public Site(String title, String link, String tags, String body) {
        this.title = title;
        this.link = link;
        this.tags = tags;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getTags() {
        return tags;
    }

    public String getBody() {
        return body;
    }
}

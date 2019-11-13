package little.things.acmp.utils;

public enum Paths {
    RESOURCES_PATH("actual_little_things/src/main/resources/acmp/");

    private String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

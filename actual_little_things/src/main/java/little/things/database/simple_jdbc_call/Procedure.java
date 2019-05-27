package little.things.database.simple_jdbc_call;

public enum Procedure {
    TEST_PROCEDURE("TEST_PROCEDURE"),
    TEST_WITH_PARAM("TEST_WITH_PARAM");

    private String name;

    Procedure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

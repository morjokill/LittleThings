package little.things.database.simple_jdbc_call;

import org.postgresql.Driver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Dao {
    private static final String SCHEMA_NAME = "public";
    private static final String PACKAGE_NAME = "";

    private final Map<Procedure, SimpleJdbcCall> mapOfReusableCallObjects = new HashMap<>();
    private JdbcTemplate template;

    public Dao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        new Thread(() -> initMapOfProcedureCalls(SCHEMA_NAME, PACKAGE_NAME, template)).start();
    }

    public String executeTestProcedure() {
        Map<String, Object> stringObjectMap = executeProcedureWithParams(Procedure.TEST_PROCEDURE, null);
        return (String) stringObjectMap.get("returnvalue");
    }

    public String executeTestWithParamProcedure(String param) {
        Map<String, Object> params = new HashMap<>();
        params.put("$1", param);
        Map<String, Object> stringObjectMap = executeProcedureWithParams(Procedure.TEST_WITH_PARAM, params);
        return (String) stringObjectMap.get("returnvalue");
    }

    private void initMapOfProcedureCalls(String schemaName, String packageName, JdbcTemplate jdbcTemplate) {
        System.out.println("Initializing procedure calls");
        try {
            Procedure[] values = Procedure.values();

            for (Procedure value : values) {
                getOrInitJdbcCallObject(value, schemaName, packageName, jdbcTemplate);
            }
        } catch (Exception e) {
            System.out.println("Could not init procedure calls: " + e);
        }
    }

    private SimpleJdbcCall getOrInitJdbcCallObject(Procedure procedure, String schemaName,
                                                   String packageName, JdbcTemplate jdbcTemplate) {
        if (mapOfReusableCallObjects.containsKey(procedure)) {
            return mapOfReusableCallObjects.get(procedure);
        } else {
            SimpleJdbcCall procedureCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(procedure.getName())
                    .withCatalogName(packageName)
                    .withSchemaName(schemaName)
                    .withReturnValue();
            mapOfReusableCallObjects.put(procedure, procedureCall);
            return procedureCall;
        }
    }

    private Map<String, Object> executeProcedureWithParams(Procedure procedure, Map<String, Object> stringProcedureParamMap) {
        SimpleJdbcCall procedureCall = getOrInitJdbcCallObject(procedure, SCHEMA_NAME, PACKAGE_NAME, template);
        SqlParameterSource params = new MapSqlParameterSource(stringProcedureParamMap);
        return procedureCall.execute(params);
    }

    public static void main(String[] args) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        Dao dao = new Dao(dataSource);
        System.out.println(dao.executeTestProcedure());
        System.out.println(dao.executeTestWithParamProcedure("русские вперед!!"));
    }
}

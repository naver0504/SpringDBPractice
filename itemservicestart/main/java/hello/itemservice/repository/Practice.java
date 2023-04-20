package hello.itemservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Slf4j
public class Practice {

    private final SimpleJdbcInsert jdbcInsert;


    public Practice(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("member_id");
        log.info("Practice={}", jdbcInsert);

    }
}

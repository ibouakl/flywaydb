package fr.ibouakl.java.sample.flyway;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.ibouakl.java.sample.flyway.conf.FlywayModuleConfigTest;



/**
 * @author BOUAKLI
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    FlywayModuleConfigTest.class
})
public class MainTest {
    
    @Inject
    JdbcTemplate dbJdbcTemplate;
    
    
    @Inject
    private ApplicationContext context;
    
    private final Main main = new Main();
    
    @Before
    public void before() {
        context.getAutowireCapableBeanFactory().autowireBean(main);
    }
    
    @Test
    public void testParseArgumentError1() throws IOException {
        int exitCode = main.run("-o");
        assertEquals(ReturnCode.WRONG_PARAMS.getExitCode(), exitCode);
    }
    

    
    /**
     * Failed to parse program arguments. Reason: Unrecognized option: -n
     * 
     * @throws IOException
     */
    @Test
    public void testParseArgumentErro2() throws IOException {
        int exitCode = main.run("-n");
        assertEquals(ReturnCode.WRONG_PARAMS.getExitCode(), exitCode);
    }
    
    /**
     * Failed to parse program arguments
     * 
     * @throws IOException
     */
    @Test
    public void testParseArgumentErro3() throws IOException {
        int exitCode = main.run("toto");
        assertEquals(ReturnCode.WRONG_PARAMS.getExitCode(), exitCode);
    }
   
    @Test
    public void testUnkownProgramOption1() throws IOException {
        int exitCode = main.run("-o", "rapair");
        assertEquals(ReturnCode.UNKNOWN_OPERATION.getExitCode(), exitCode);
    }
    
    @Test
    public void testUnkownProgramOption2() throws IOException {
        int exitCode = main.run("-o", "migration");
        assertEquals(ReturnCode.UNKNOWN_OPERATION.getExitCode(), exitCode);
    }

    @Test
    public void testhelp1() throws IOException {
        int exitCode = main.run("-h");
        assertEquals(ReturnCode.SUCCESS.getExitCode(), exitCode);
    }
    
    @Test
    public void testhelp2() throws IOException {
        int exitCode = main.run("--help");
        assertEquals(ReturnCode.SUCCESS.getExitCode(), exitCode);
    }
    
    @Test
    public void testVserion1() throws IOException {
        int exitCode = main.run("-v");
        assertEquals(ReturnCode.SUCCESS.getExitCode(), exitCode);
    }
    
    @Test
    public void testVserion2() throws IOException {
        int exitCode = main.run("--version");
        assertEquals(ReturnCode.SUCCESS.getExitCode(), exitCode);
    }
    
    @Test
    public void testInfo() throws IOException {
        int exitcode = main.run("-o", "info");
        assertEquals(exitcode, 0);
    }
    
    @Test
    public void testMigrate() throws IOException {
        int exitcode = main.run("-o", "migrate");
        assertEquals(exitcode, 0);
        List<Person> persons = getPersons();
        assertEquals(3, persons.size());
    }
    
    private List<Person> getPersons() {
        return dbJdbcTemplate.query("select * from PERSON", new RowMapper<Person>() {
            @Override
            public Person mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                Person person = new Person(id, name);
                return person;
            }
        });
    }
    
    private class Person {
        private int id;
        private String name;
        public Person(final int id, final String name) {
            this.id = id;
            this.name = name;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + id;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person other = (Person)obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (id != other.id)
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

        private MainTest getOuterType() {
            return MainTest.this;
        }
        
  
        
    }
    
}

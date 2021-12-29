package com.example.springbatchexample;
import com.example.springbatchexample.dto.Persona;
import com.example.springbatchexample.listener.JobListener;
import com.example.springbatchexample.processor.PersonaItemProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SpringbatchexampleApplicationTests {

    @Spy
    Persona persona ;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    JobListener jobListener;

    @Mock
    JobListener jobListenerMock;

    @InjectMocks
    PersonaItemProcessor personaProc;

    @Test
    public void PersonTest() {
        persona.setPrimerNombre("Julio");
        persona.setSegundoNombre("Osorio");
        persona.setTelefono("997167960");

        assertEquals(persona.getPrimerNombre(),"Julio");
        assertEquals(persona.getSegundoNombre(),"Osorio");
        assertEquals(persona.getTelefono(),"997167960");
       
    }
    
    @Test
    public void convertedPersonTest() throws Exception {

        persona.setPrimerNombre("Julio");
        persona.setSegundoNombre("Osorio");
        persona.setTelefono("a");

        Persona personaC = new Persona();
        personaC.setPrimerNombre("JULIO");
        personaC.setSegundoNombre("OSORIO");
        personaC.setTelefono("a");

        assertNotEquals(personaC, personaProc.process(persona));
    }

    @Test
    public void afterJobTest() {
        JobExecution jobExecution=new JobExecution(1l);
        jobExecution.setStatus(BatchStatus.COMPLETED);
        Mockito.lenient().doNothing().when(jobListenerMock).afterJob(jobExecution);
        jobListener.afterJob(jobExecution);
    }

    @Test
    public void jobListenerQueryNullTest()  {
       Mockito.lenient().when(jdbcTemplate.queryForObject("SELECT primer_nombre, segundo_nombre, telefono FROM persona",
              (Object.class))).thenReturn(Object.class);
        jobListener.selectQuery();
    }

    @Test
    public void personCountTest() {
        Persona personaTest = new Persona();
        ReflectionTestUtils.setField(personaTest, "jdbcTemplate", jdbcTemplate);
        Mockito.when(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM PERSON", Integer.class)).thenReturn(40);
        assertEquals(40, personaTest.getCountPerson());
    }


}
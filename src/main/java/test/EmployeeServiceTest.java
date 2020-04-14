package test;

import com.aj.dropwizardmysql.dao.EmployeeDao;
import com.aj.dropwizardmysql.service.TestService;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private TestService testService;

    private EmployeeDao employeeDao;

    public EmployeeServiceTest() {
        employeeDao = mock(EmployeeDao.class);
        testService =  new TestService(employeeDao);
    }

    @Before
    public void setup() {
        System.out.println("In Before");
    }


    @Test
    public void returnSumTest() {


        int sum = testService.returnSum(2, 2, 3);
        assertEquals(sum, 7);

        int sum1 = testService.returnSum(1, 2 ,3);
        assertEquals(sum1,1);
    }

    @Test
    public void deleteEmployeeTest() {
          when( employeeDao.deleteEmployee(1)).thenReturn(1);

          String result = testService.deleteEmployee(1);
        assertEquals(result, "SUCCESS");

        when( employeeDao.deleteEmployee(2)).thenReturn(0);

try {
    testService.deleteEmployee(2);
} catch (Exception e) {
    assertEquals(e.getMessage(), "HTTP 404 Not Found");
}

        when( employeeDao.deleteEmployee(3)).thenReturn(10);

        try {
            testService.deleteEmployee(3);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "HTTP 500 Internal Server Error");
        }

    }
}

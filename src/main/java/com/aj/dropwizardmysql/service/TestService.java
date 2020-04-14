package com.aj.dropwizardmysql.service;

import com.aj.dropwizardmysql.dao.EmployeeDao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class TestService {

    private EmployeeDao employeeDao;

    public TestService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int returnSum(int a, int b, int c) {

        if (a % 2 == 0) {
            return a + b + c;
        }
        else {
            return a;
        }
    }

    public String deleteEmployee(final int id) {
        int result = employeeDao.deleteEmployee(id);
        switch (result) {
            case 1:
                return "SUCCESS";
            case 0:
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            default:
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

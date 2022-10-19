package dao;

import model.Customer;

import java.util.List;

public interface ICustomerDAO {
    void insertCustomer(Customer customer);

    Customer selectCustomer(String id);

    Customer selectCustomerByPhoneNumber(String phoneNumber);

    Customer selectCustomerByEmail(String emailStr);

    List<Customer> selectAllCustomersPaggingFilter(int offset, int noOfRecords, String q);

    List<Customer> selectAllCustomer();

    boolean deleteCustomer(String id);

    boolean updateCustomer(Customer customer);

    public int getNoOfRecords();

    Customer selectCustomerByEmailAndPassword(String email, String password);
}

package models;

import java.util.List;

/**
 * Created by jonathan on 3/5/18.
 */

public class Seller extends Person {
    public int id;
    public List<Customer> customers;
    public Agency agency;
    public List<Address> addresses;
}

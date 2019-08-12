package com.tinlm.snef.repository;

import com.tinlm.snef.model.Customer;

interface AccountDAO {
    Object login(String username, String password);
    Boolean createAccount(String username, String password, String firstname, String lastname);
    Boolean updateAccount(String username, String firstname, String lastname, String phone, String address, int gender);
    Boolean updatePassword(String username, String password);

}

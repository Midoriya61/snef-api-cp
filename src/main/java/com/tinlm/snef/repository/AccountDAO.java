package com.tinlm.snef.repository;

import com.tinlm.snef.model.Customer;

interface AccountDAO {
    Object login(String username, String password);
    Boolean createAccount(String username, String password, String firstname, String lastname);
    Boolean updateAccount(int accountId, String phone, String address);
    Boolean updatePassword(int accountId, String password);

}

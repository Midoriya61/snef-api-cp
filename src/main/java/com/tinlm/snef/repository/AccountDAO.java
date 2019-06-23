package com.tinlm.snef.repository;

import com.tinlm.snef.model.Customer;

interface AccountDAO {
    Object loginCustomer(String username, String password);
}

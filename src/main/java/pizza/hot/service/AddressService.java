package pizza.hot.service;

import pizza.hot.model.Address;

import java.util.List;

public interface AddressService {
    void saveAddress(Address address);
    List<Address> findAll();
    void deleteAddressById(Long id);
}

package pizza.hot.dao;

import pizza.hot.model.Address;
import pizza.hot.model.Drink;

import java.util.List;

public interface AddressDao {
    void saveAddress(Address address);
    List<Address> findAll();
    void deleteAddressById(Long id);
    Address getAddressById(Long id);
}

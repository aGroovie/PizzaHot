package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.AddressDao;
import pizza.hot.model.Address;
import pizza.hot.service.AddressService;

import java.util.List;

@Service
public class AdressServiceImpl implements AddressService {

    AddressDao addressDao;

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public void saveAddress(Address address) {
        addressDao.saveAddress(address);
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public void deleteAddressById(Long id) {
        addressDao.deleteAddressById(id);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressDao.getAddressById(id);
    }


}

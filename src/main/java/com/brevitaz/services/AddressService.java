package com.brevitaz.services;

import com.brevitaz.entity.Address;
import com.brevitaz.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address){
       Address address1= addressRepository.getByHouseNoAndLandMarkAndCity(address.getHouseNo(),address.getLandMark(),address.getCity());
       if(address1 == null) {
           return addressRepository.save(address);
       }
       return address1;
    }


//    public void checkAddress(Address address) throws SQLException {
//        if (addressRepository.checkAddress(address)) {
//            addressRepository.insertAddressDetails(address);
//        }
//    }
    public List<Address> viewAddress(){
        return addressRepository.findAll();
    }
//
//    public int getViewById(Address address) throws SQLException {
//        return addressRepository.getAddressId(address);
//
//    }
//    public Address ViewById(int addressId){
//    return addressRepository.addressViewById(addressId);
//    }

}

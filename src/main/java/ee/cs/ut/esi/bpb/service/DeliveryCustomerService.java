package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryCustomer;
import ee.cs.ut.esi.bpb.repository.DeliveryCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryCustomerService {

    @Autowired
    private DeliveryCustomerRepository customerRepo;

    public Optional<DeliveryCustomer> fetchCustomerById(int id) {
        return customerRepo.findById(id);
    }

    public List<DeliveryCustomer> fetchAllCustomers() {
        return customerRepo.findAll();
    }

    public DeliveryCustomer createCustomer(DeliveryCustomer customer) {
        return customerRepo.save(customer);
    }

}

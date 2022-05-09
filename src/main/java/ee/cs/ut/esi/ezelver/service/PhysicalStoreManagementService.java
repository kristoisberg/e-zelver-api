package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.model.Store;
import ee.cs.ut.esi.ezelver.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhysicalStoreManagementService {
    private final StoreRepository storeRepository;

    public List<Store> fetchAllStores() {
        return storeRepository.findAll();
    }
}

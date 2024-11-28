package org.manage.scms.service;

import org.manage.scms.dto.SupplierDto;
import org.manage.scms.exception.SupplierNotFoundException;
import org.manage.scms.model.Supplier;
import org.manage.scms.repository.ProductRepository;
import org.manage.scms.repository.SupplierRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.manage.scms.util.SupplierUtil.convertSupplierDtoToEntity;

@Service
public class SupplierService {

    private final String EXCEPTION_MSG = "Supplier not found by ID: ";

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier addSupplier(SupplierDto supplierDto) {
        return supplierRepository.save(convertSupplierDtoToEntity(supplierDto));
    }

    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }

    public Supplier updateSupplier(SupplierDto supplierDto, Long id) throws SupplierNotFoundException {
        Supplier newSupplier = convertSupplierDtoToEntity(supplierDto);
        Supplier existingSupplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(EXCEPTION_MSG + id));

        existingSupplier.setName(newSupplier.getName());
        existingSupplier.setAddress(newSupplier.getAddress());
        existingSupplier.setPhoneNumber(newSupplier.getPhoneNumber());
        existingSupplier.setProducts(newSupplier.getProducts());
        existingSupplier.setEmail(newSupplier.getEmail());

        return supplierRepository.save(existingSupplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) throws SupplierNotFoundException {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(EXCEPTION_MSG + id));
    }
}

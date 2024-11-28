package org.manage.scms.controller;

import org.manage.scms.dto.SupplierDto;
import org.manage.scms.model.Supplier;
import org.manage.scms.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/addSupplier")
    public ResponseEntity<Supplier> addSupplier(@RequestBody SupplierDto supplierDto) {
        try {
            Supplier supplier = supplierService.addSupplier(supplierDto);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/deleteSupplier")
    public ResponseEntity<String> deleteSupplier(@RequestParam Long supplierId) {
        try {
            supplierService.deleteSupplierById(supplierId);
            return ResponseEntity.ok("Supplier deleted successfully!!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/updateSupplier")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody SupplierDto supplierDto, @RequestParam Long supplierId) {
        try {
            Supplier supplier = supplierService.updateSupplier(supplierDto, supplierId);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/getAllSuppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        try {
            List<Supplier> supplier = supplierService.getAllSuppliers();
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/getSupplierById")
    public ResponseEntity<Supplier> getSupplierById(@RequestParam Long supplierId) {
        try {
            Supplier supplier = supplierService.getSupplierById(supplierId);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

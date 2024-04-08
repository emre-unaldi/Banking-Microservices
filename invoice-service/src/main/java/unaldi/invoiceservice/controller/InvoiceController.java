package unaldi.invoiceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.service.abstracts.InvoiceService;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestController
@RequestMapping("api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<DataResult<InvoiceDTO>> save(@RequestBody InvoiceSaveRequest invoiceSaveRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.invoiceService.save(invoiceSaveRequest));
    }

    @PutMapping
    public ResponseEntity<DataResult<InvoiceDTO>> update(@RequestBody InvoiceUpdateRequest invoiceUpdateRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.invoiceService.update(invoiceUpdateRequest));
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Result> deleteById(@PathVariable Long invoiceId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.invoiceService.deleteById(invoiceId));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<DataResult<InvoiceDTO>> findById(@PathVariable Long invoiceId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.invoiceService.findById(invoiceId));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<InvoiceDTO>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.invoiceService.findAll());
    }
}

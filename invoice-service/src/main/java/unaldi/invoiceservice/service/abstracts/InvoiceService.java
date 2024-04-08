package unaldi.invoiceservice.service.abstracts;

import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.utils.client.dto.UserResponse;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface InvoiceService {
    DataResult<InvoiceDTO> save(InvoiceSaveRequest invoiceSaveRequest);
    DataResult<InvoiceDTO> update(InvoiceUpdateRequest invoiceUpdateRequest);
    Result deleteById(Long invoiceId);
    DataResult<InvoiceDTO> findById(Long invoiceId);
    DataResult<List<InvoiceDTO>> findAll();
    DataResult<UserResponse> findInvoiceUserByUserId(Long userId);
}

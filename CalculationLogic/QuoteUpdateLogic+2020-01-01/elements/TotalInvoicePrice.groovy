/**
 * Calculating Total InvoicePrice = InvoicePrice * Quantity
 * @return TotalInvoicePrice
 */

// If any of value is null
if (null in [out.Quantity,out.InvoicePrice]) {
    api.addWarning("Unable to calculate TotalInvoice Price: missing parameters")
    return null
}

else {
    return (out.InvoicePrice * out.Quantity) // TotalInvoicePrice
}
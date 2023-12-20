/**
 * If Customer Currency is EU converting TotalInvoicePrice to USD with ExchangeRate
 * Else If Customer Currency is USD returning TotalInvoicePrice
 * @return TotalInvoicePrice in USD
 */

// If any of value is null raise Warning
if (out.TotalInvoicePrice == null || out.ExchangeRate == null) {
    api.addWarning("Unable to convert Invoice Price to EUR : (TotalInvoicePrice is null or ExchangeRate is not found)")
    return null
}

else {
    // If Currency is EUR Convert to USD and return
    if (out.Currency == "EUR") {
        return (out.TotalInvoicePrice * out.ExchangeRate)
    }
    // If Currency is USD return
    else if (out.Currency == "USD") {
        return out.TotalInvoicePrice
    }
}
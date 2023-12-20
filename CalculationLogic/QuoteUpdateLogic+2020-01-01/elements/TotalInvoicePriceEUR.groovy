/**
 * If Customer Currency is USD converting TotalInvoicePrice to EU with ExchangeRate
 * Else If Customer Currency is EU returning TotalInvoicePrice
 * @return TotalInvoicePrice in EU
 */

// If any of value is null raise Warning
if (out.TotalInvoicePrice == null || out.ExchangeRate == null) {
    api.addWarning("Unable to convert Invoice Price to EUR : (TotalInvoicePrice is null or ExchangeRate is not found)")
    return null
}

else {
    // If Currency is USD convert to EU and return
    if (out.Currency == "USD") {
        return (out.TotalInvoicePrice * out.ExchangeRate)
    }
    // If currency is EU return
    else if (out.Currency == "EUR") {
        return out.TotalInvoicePrice
    }

}
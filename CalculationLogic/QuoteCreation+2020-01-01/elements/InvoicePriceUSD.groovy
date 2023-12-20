
// Display Invoice Price in USD

// Checking InvoicePrice and ExchangeRate
if (out.InvoicePrice == null || out.ExchangeRate == null) {
    api.addWarning("Unable to convert Invoice Price to USD")
    return null
}

else {
    // If Currency is EUR - convert to USD using ExchangeRate
    if (out.Currency == "EUR") {
        return (out.InvoicePrice * out.ExchangeRate)
    }
    // If currency is USD - Display InvoicePrice
    else if (out.Currency == "USD") {
        return out.InvoicePrice
    }
}
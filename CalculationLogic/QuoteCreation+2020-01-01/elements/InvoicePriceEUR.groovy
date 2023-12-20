
//Display Invoice Price in EU

//Checking null in InvoicePrice or ExchangeRate
if (out.InvoicePrice == null || out.ExchangeRate == null) {
    api.addWarning("Unable to convert Invoice Price to USD")
    return null
}

else {
    // If Currency is USD
    if (out.Currency == "USD") {
            return (out.InvoicePrice * out.ExchangeRate) // converting to EU using ExchangeRate
        }

    // If Currency is EU then Display InvoicePrice
    else if (out.Currency == "EUR") {
        return out.InvoicePrice
    }

}
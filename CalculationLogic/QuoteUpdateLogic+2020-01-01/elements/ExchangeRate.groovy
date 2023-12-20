/**
 * Getting ExchangeRate Based on Currency of Customer and valid of Today
 * @return ExchangeRate
 */

//Today Date
def todayDate = new Date().format("yyyy-MM-dd")

// As Given all quotes values are assumed to be in USD
def ToCurrency = "USD"

if (out.Currency == "EUR")
    ToCurrency = "USD"

else if (out.Currency == "USD")
    ToCurrency = "EUR"



//Filters to set Valid To and Currency of Customer
def filters = [
        Filter.greaterOrEqual("attribute1",todayDate),// Valid To
        Filter.equal("key1",out.Currency), //From currency
        Filter.equal("key2",ToCurrency)
]




// Fetching Exchange Rate using lookupTableValues from ExchangeRate Table
def ExchangeRate = api.findLookupTableValues("ExchangeRate",null,*filters)?.find()?.attribute2

// If value not found
if (ExchangeRate == null) {
    api.addWarning("Exchange Rate is not found for given filter value in company parameter table")
    return null
}

return ExchangeRate as BigDecimal
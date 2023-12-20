/**
 Exchange Rate (Company Parameter)
 Access Rules:
 Search by Date value that is Less Than or Equal to Today
 Search by Currency (using Customer Currency)
 Retrieve Cost value
 */

// Today Date
def todayDate = new Date().format("yyyy-MM-dd")

// Table id of ExchangeRate Parameter Table
def ExchangeRateTableId = api.findLookupTable("ExchangeRate")?:null

if (ExchangeRateTableId == null) {
    api.addWarning("ExchangeRate parameter table is not found")
    return null
}

// As Given all quotes values are assumed to be in USD
def ToCurrency = "USD"

if (out.Currency == "EUR")
     ToCurrency = "USD"

else if (out.Currency == "USD")
    ToCurrency = "EUR"


// Filters
def filters = [
        Filter.equal("lookupTable.id",ExchangeRateTableId.id), //TableId
        Filter.greaterOrEqual("attribute1",todayDate),// Valid To
        Filter.equal("key1",out.Currency), //From currency
        Filter.equal("key2",ToCurrency) // To Currency

]

// ExchangeRate for the Currency using using api.find with above Filters
def ExchangeRate = api.find('MLTV3',0,1,null,
                                         ["attribute2"],*filters)?.find()?.attribute2

// If Exchange Rate is not found
if (ExchangeRate == null) {
    api.addWarning("Exchange Rate is not found for given filter value in company parameter table")
    return null
}

return ExchangeRate as BigDecimal
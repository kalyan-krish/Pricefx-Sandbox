/**

 Get Exchange Rate value using api.findLookupTableValues()

 From USD to EUR

 Valid for your today’s date, use api.targetDate()

 */

// TargetDate
def date = api.targetDate().format("yyyy-MM-dd")

// Filters
def filters = [
        Filter.equal("key1","USD"),//From
        Filter.equal("key2","EUR"), // To
        Filter.lessOrEqual("key3",date), // valid From
        Filter.greaterOrEqual("attribute1",date), // valid To
]

// ExchangeRate with above filters
def ExchangeRate = api.findLookupTableValues("ExchangeRate",*filters)?.find()?.attribute2

// Raising Error if not found
if (ExchangeRate == null) {
    api.addWarning("Valid Exchange Rate is not available for given filters")
    return null
}


return ExchangeRate




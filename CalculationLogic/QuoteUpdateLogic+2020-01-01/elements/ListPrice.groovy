/**
 * To Get ListPrice from ListPrice PX Table Greater than or equal to TargetDate for the product in Context
 * @return ListPrice
 */

//TargetDate or QuoteEffectiveDate
def targetDate = api.targetDate()?.format("yyyy-MM-dd")

// Filter to set productId from Context, TargetDate and Currency of Customer(HeaderInput)
def filters =[
        Filter.equal("sku",api.product("sku")), // product ID
        Filter.greaterOrEqual("attribute1",targetDate), // valid From
        Filter.equal("attribute3",GetCustomerCurrency.getCurrency()) // currency
]

//ListPrice from PX Table ListPrice with above Filters
def listPrice = api.productExtension("ListPrice",*filters)?.find()?.attribute2

// If ListPrice is null
if (listPrice == null) {
    api.addWarning("ListPrice is not found for the product and targetDate and currency")
    return null
}

// If OverRidePrice input is given any value it overRides the ListPrice
if (input.OverRidePrice > 0) {
    listPrice = input.OverRidePrice
}

return listPrice as BigDecimal




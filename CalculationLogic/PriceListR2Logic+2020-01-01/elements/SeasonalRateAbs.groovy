/**
 * Fetching Seasonal Rate based on Region,ProductGroup and TargetDate
 * @ Return SeasonalRate
 */

// CustomerId input
def CustomerId = input.customerId

// Region of Customer from Customer MasterData
def Region = api.customer("Region", CustomerId as String)?:null

// if Region is null
if (Region == null) {
    api.addWarning("Region is not found for the Customer")
    return null
}

api.trace(Region)
// ProductGroup of Product in Context from Product master Data
def ProductGroup = api.product("ProductGroup")?:null

// if productGroup not found
if (ProductGroup == null) {
    api.addWarning("ProductGroup is not found for the Product")
    return null
}

api.trace(ProductGroup)

// Target Date of PriceList
def targetDate = api.targetDate()?.format("yyyy-MM-dd")

api.trace(targetDate)

// Filters with Fetched Region,ProductGroup and TargetDate
def filters = [
        Filter.equal("key1",Region), // Region
        Filter.equal("key2",ProductGroup), //productGroup
        Filter.lessOrEqual("key3",targetDate) // valid From
]

// Seasonal Rate % from Seasonal Rate Parameter Table sortBy TargetDate to get Latest SeasonalRate
def SeasonalRatePct = api.findLookupTableValues("SeasonalRate","-key3",*filters)?.find()?.attribute1

// If value % not found
if (SeasonalRatePct == null){
    api.addWarning("Seasonal Rate is not found for the Region,ProductGroup and TargetDate")
    return null

}

api.trace(SeasonalRatePct)
// SeasonalRateAbs = BasePrice * SeasonalRate %
def SeasonalRateAbs = (out.BasePrice * SeasonalRatePct)

return SeasonalRateAbs
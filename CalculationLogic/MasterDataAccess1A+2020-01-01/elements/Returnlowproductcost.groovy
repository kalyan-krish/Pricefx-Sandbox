/**
 Return the Lowest Product Cost value of all records using api.find()

 Use api.find()'s sortBy parameter.

 Limit query results to 1 record
 */

// Filter with ProductCost Product Extension Table name
def filters = [
        Filter.equal("name","ProductCost") // ProductExtension Table name
]

// Using api.find and sortBy productCost (attribute1) Ascending
def lowestProductCost = api.find("PX",0,api.getMaxFindResultsLimit(),
                                        "attribute1",*filters)?.find()?.attribute1

// Raise Warning if lowestProductCost is null
if (lowestProductCost == null) {
    api.addWarning("ProductCost is not available")
    return null
}

return lowestProductCost
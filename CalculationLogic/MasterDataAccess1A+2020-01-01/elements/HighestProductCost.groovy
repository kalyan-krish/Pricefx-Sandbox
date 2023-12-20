/**
 Bonus task: Get the Highest Product Cost value of all records using api.find()'s fields parameter
 with aggregation
 */
// Filter with ProductCost table
def filter = [
        Filter.equal("name","ProductCost")
]
// Getting HighestProduct with api.find With Aggregation using Max
def HighestProductCost = api.find('PX',0,1,null,["attribute1":"MAX"],
                                             true,*filter)?.find()?.attribute1

// Raising Warning
if (HighestProductCost == null) {
    api.addWarning("Highest Product Cost is not found")
    return null
}


return HighestProductCost
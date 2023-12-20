/**
 Return Average Cost value of Product Cost record using api.find() :

 for Sku: MB-0002

 */

// Filter with ProductCost Table and Sku: MB-0002
def filters = [
        Filter.equal("name","ProductCost"),
        Filter.equal("sku","MB-0002")
]

// AvgCost(attribute1) from ProductCost Table
def avgCost = api.find("PX",0,1,"sku",
                                    *filters)?.find()?.attribute1

// Raising Warning if AvgCost is null
if (avgCost == null) {
    api.addWarning("Average cost is not available for product MB-0002")
    return null
}

return avgCost

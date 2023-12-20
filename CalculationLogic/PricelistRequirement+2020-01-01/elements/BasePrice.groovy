/**
 * Use Average cost from Product Cost extension table as the base for the calculation
 */

// Assume AvgCost from ProductCost ProductExtension Table as Base Price (attribute1 is AvgCost)
def BasePrice = api.productExtension("ProductCost")?.find()?.attribute1

//Raising Warning if BasePrice is Null
if (BasePrice == null) {
    api.addWarning("Average Cost is not available in Product Extension table for this Product")
    return null
}

return BasePrice
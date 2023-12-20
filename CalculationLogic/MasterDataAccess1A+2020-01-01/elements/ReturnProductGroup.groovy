/**
 * Return Product Group for the Product MB-0001
 */

//Filters
def filters = [
        Filter.equal("sku","MB-0001") // productId
]

// Fetching ProductGroup using api.find
def productGroup = api.find("P",0,1,"sku",
                                                            *filters)?.find()?.attribute1
//Raising Warning if productGroup is null
if (productGroup == null){
    api.addWarning("This product or attribute of the product is not available")
    return null
}


// Else Return ProductGroup
return productGroup


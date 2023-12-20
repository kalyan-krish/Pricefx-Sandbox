/**
  Return the List Price value of all records using api.find()

 Use api.find()'s sortBy parameter.

 Limit query results to 1 record
 */

// Filters equating to Product Extension (ListPrice)
def filters = [
        Filter.equal("name","ListPrice")
]

// Fetching LowestListPrice using api.find and sortBy ListPrice(attribute2) Ascending order
def lowestListPrice = api.find("PX",0,1,"attribute2",*filters)?.find()?.attribute2

// Raising Warning if lowestListPrice is null
if (lowestListPrice == null) {
    api.addWarning("Lowest price is not available")
    return null
}


return lowestListPrice

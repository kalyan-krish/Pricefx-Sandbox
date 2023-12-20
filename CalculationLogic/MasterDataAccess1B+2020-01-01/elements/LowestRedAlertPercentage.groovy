/**
 Get Names of Price Strategy records using api.findLookupTableValues() with

 The lowest Red Alert percentage

 Limit fields of API returned object to name

 Hint: There are multiple records for the lowest Red Alert value.
 Use sortBy parameter to get the lowest value, then fetch names of records with that value
 assert yourReturnObject == [[name:"Sausage"],[name:"Toppings"]]

 */

// Lowest RedAlert
def lowestRedAlert = api.findLookupTableValues("PriceStrategy","RedAlert")?.find()?.attribute2


// Filter by Lowest Red Alert
def filters = [
        Filter.equal("RedAlert",lowestRedAlert)
]
// PriceStrategy name with lowest Red Alert
def PriceStrategyWithLowestRedAlert = api.findLookupTableValues("PriceStrategy",["name"],
                                                "RedAlert",*filters)?:null

// Raising Warning if not found
if (PriceStrategyWithLowestRedAlert == null) {
    api.addWarning("Price Strategy is not available with lowest RedAlert")
    return null
}

//Asserting with outputs
assert PriceStrategyWithLowestRedAlert == [[name:"Sausage"],[name:"Toppings"]]




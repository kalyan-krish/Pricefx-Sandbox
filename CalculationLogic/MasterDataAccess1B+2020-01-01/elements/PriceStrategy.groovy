/**
 Get Price Strategy record’s attributes values using api.vLookup()

 For Business unit: Meals

 Fetch only Critical alert and Red Alert values

 Hint: Get the data with single API call,
 using vLookup(String parameterName, List<String> attributeNames, Map keys); method signature.
 assert yourReturnedObject == [CriticalAlert:0.0, RedAlert:0.35]

 */


// BusinessUnit
def BusinessUnit = "Meals"

//keys
def key = [
        "BusinessUnit" : BusinessUnit
]

// PriceStrategy using VLookup
def PriceStrategyValues = api.vLookup("PriceStrategy",["CriticalAlert","RedAlert"],key)?:null

//Raising warning if not found
if (PriceStrategyValues == null) {
    api.addWarning("Price Strategy values not available for Meals Business Unit")
    return null
}

//Asserting with output
assert PriceStrategyValues == [CriticalAlert:0.0, RedAlert:0.35]: "Values not matching"
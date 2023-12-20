/**
 Get all Product Life Cycle values from Attribute Adjustment table
 using api. vLookup() or api.findLookupTableValues()
 */

//fields
def fields = ["ProductLifeCycle","AttributeAdj"]

//Fetching ProductLifeCycles from AttributeAdj table
def ProductLifeCycleValues =  api.findLookupTableValues("AttributeAdj",fields,"name")?:null

// Raising Warning if not found
if (ProductLifeCycleValues == null) {
    api.addWarning("AttributeAdj Table or attributes are not found")
    return null
}

return ProductLifeCycleValues
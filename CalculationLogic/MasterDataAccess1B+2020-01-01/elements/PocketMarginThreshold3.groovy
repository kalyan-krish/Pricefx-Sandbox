/**
 Get Pocket Margin Alert Threshold value using api.findLookupTableValues()

 For Product Group: Beef

 with Critical severity

 */

// Filters with productGroup and Critical Severity
def filters = [
        Filter.equal("key1","Beef"),
        Filter.equal("key2","Critical"),
]

// Fetching Pocket Margin Threshold values with above filters
def PocketMarginThreshold = api.findLookupTableValues("PocketMarginAlertThreshold","Threshold",*filters)
                                            ?.find()?.attribute1
//Raising Warning if not found
if (PocketMarginThreshold == null) {
    api.addWarning("PocketMargin Threshold not available for given product group and severity")
    return null
}


return PocketMarginThreshold
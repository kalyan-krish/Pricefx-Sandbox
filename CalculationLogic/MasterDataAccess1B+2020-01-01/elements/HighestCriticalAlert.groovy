/**
 Get Price Strategy record(s) with:

 The highest Critical Alert percentage

 */

//fields Required
def fields = ["BusinessUnit","CriticalAlert","RedAlert","YellowAlert"]

// Using api.findLookupTableValues sortBy CriticalAlert Descending order
def HighestCriticalAlert = api.findLookupTableValues("PriceStrategy",fields,"-CriticalAlert")?.find()

// Raising Warning if not found
if (HighestCriticalAlert == null) {
    api.addWarning("HighestCritical Alert is not available")
    return null
}

return HighestCriticalAlert


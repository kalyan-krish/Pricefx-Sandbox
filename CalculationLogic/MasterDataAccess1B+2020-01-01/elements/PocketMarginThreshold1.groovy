/**
 Get Pocket Margin Alert Threshold value using api.vLookup()

 For Product Group: Lamb

 with Yellow Alert Severity

 */

// productGroup
def ProductGroup1 = "Lamb"

// Alert Severity
def Alert1 = "Yellow"

// keys
def keys1 = [
        "ProductGroup" : ProductGroup1,
        "AlertSeverity": Alert1,
]

// using Api.vLookup
def pocketMarginThreshold1 = api.vLookup("PocketMarginAlertThreshold","Threshold",keys1)?:null

// Raising Warning if pocketMargin Threshold is null
if (pocketMarginThreshold1 == null) {
    api.addWarning("Pocket Margin Threshold is not available for given Threshold and ProductGroup")
    return null
}

return pocketMarginThreshold1

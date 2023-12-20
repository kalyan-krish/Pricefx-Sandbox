/**
 Get Pocket Margin Alert Threshold value using api.vLookup()

 For Product Group: Beef

 with Red Alert Severity

 Note: Notice how percentage value is returned
 */


//productGroup
def ProductGroup = "Beef"

// Alert
def Alert = "Red"

//keys
def keys = [
        "ProductGroup" : ProductGroup,
        "AlertSeverity": Alert,
]

// Threshold with vLookup
def pocketMarginThreshold2 = api.vLookup("PocketMarginAlertThreshold","Threshold",keys)?:null

//Warnings if not found
if (pocketMarginThreshold2 == null) {
    api.addWarning("Pocket Margin Threshold is not available for given Threshold and ProductGroup")
    return null
}

return pocketMarginThreshold2
/**

 Go back to Task 6 and correct your code if you only used sortBy to conveniently get the result.

 Note: Write your code generic and sophisticated.
 Even while working with simple/sample data.

 */

// Fetching Table
def table = api.findLookupTable("PriceStrategy")?:null
// Raising Warning if not found
if (table == null || table.id == null) {
    api.addWarning("PriceStrategy Table or ID is not found")
    return null
}

// filter with lookup Table id
def filter = [
        Filter.equal("lookupTable.id",table.id)
]

// HighestCriticalAlert using Api.find with Aggregation (Critical = attribute1)
def HighestCriticalAlert = api.find('MLTV',0,1,null,
                                                  ["CriticalAlert":"MAX"],true,*filter)?.find()?.CriticalAlert



if (HighestCriticalAlert == null) {
    api.addWarning("HighestCritical Alert is not found")
    return null
}

// Fetching Total PriceStrategy Record for High Critical Alert

//fields Required
def fields = ["BusinessUnit","CriticalAlert","RedAlert","YellowAlert"]

def filter2 = [Filter.equal("CriticalAlert",HighestCriticalAlert)]

def HighestCriticalAlertRecord = api.findLookupTableValues("PriceStrategy",fields,null,*filter2)?:null

return HighestCriticalAlertRecord
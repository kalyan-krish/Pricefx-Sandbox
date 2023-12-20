/**
 * Return Id of Approval Levels Revenue using findLookupTable().
 *
 */


// Fetching TableId of ApprovalLevelsRevenue
def table = api.findLookupTable("ApprovalLevelsRevenue")?.getAt("id")?:null

// Raising Warning if table is Null
if (table == null) {
    api.addWarning("Table is not available")
    return null
}

return table


/**
 Return all values of Approval Levels Revenue table using api.find()

 Limit fields of API returned object to Revenue Threshold only.

 Hint:	 use table id from task 10
 assert yourReturnObject == [[attribute1:"10000"],[attribute1:"50000"]]

 */

//Filter with Table Id of ApprovalRevenue from previous Element
def filters = [
        Filter.equal("lookupTable.id",out.ReturnApprovalLevelsRevenueID)
]

// Fetching Approval Levels Thresholds (attribute1)
def ApprovalLevels = api.find("MLTV",0,api.getMaxFindResultsLimit(),"name",
                                              ["attribute1"],*filters)?:null

// Raising Warning if ApprovalLevels are null
if (ApprovalLevels == null) {
    api.addWarning("Approval levels are not available in table")
    return null
}

// Asserting the ApprovalLevels Map fetched using api.find
assert ApprovalLevels == [["attribute1" : "10000"], ["attribute1" : "50000"]] :"Threshold Values are not Matching"





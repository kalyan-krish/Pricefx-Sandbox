
/*
Requirement: Retrieve total Margin across all transactions for customer CD-00001

Steps: 1. Initialize Query with Rollup = True (Aggregation)
       3. Where / Filter to Customer CD-00001
 */


//DataMart Context
def dmCtx = api.getDatamartContext()
def dm = dmCtx.getDatamart("Transaction") // Getting DataMart Transaction
if (dm == null) {
    api.addWarning("Transaction DataMart is not available") // Raising Warning if DataMart is not available
    return null
}

//Filter with CustomerId = CD-00001
def filter = [
        Filter.equal("CustomerId","CD-00001")
]
// NewQuery with rollup = True
def DataQuery = dmCtx.newQuery(dm,true)
DataQuery.select("Margin","Margin") //Margin
DataQuery.where(*filter) // where with defined Filter

def QueryResult = dmCtx.executeQuery(DataQuery) // Executing Query
def TotalMargin = QueryResult?.getData()?.find()?.Margin // Fetching Margin from Result

def RoundedTotalMargin = libs.SharedLib.RoundingUtils.round(TotalMargin as BigDecimal,4)

return RoundedTotalMargin
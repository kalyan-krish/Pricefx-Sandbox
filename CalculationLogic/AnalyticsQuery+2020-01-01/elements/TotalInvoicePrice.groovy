

/*
Requirement: Summarize total Invoice Price for each Business Unit in the last calendar year.

Steps: 1. Initialize Query with Rollup = True (Aggregation)
       2. Group by BusinessUnit
       3. Where / Filter to Last Calender Year

 */

//DataMart Context
def dmCtx = api.getDatamartContext()
def dm = dmCtx.getDatamart("Transaction")?:null // Getting DataMart Transaction
if (dm == null) {
    api.addWarning("Transaction DataMart is not available") // Raising Warning if DataMart is not available
    return null
}
def currentYear = new Date()?.format("yyyy") as BigInteger   // Fetching Previous year from currentYear
BigInteger previousYear = currentYear - 1

// Defining Filters
def filters = [
        Filter.equal("InvoiceDateYear",previousYear), // InvoiceDateYear = previousYear
        Filter.notEqual("BusinessUnit",null) // BusinessUnit != null for exception handling
]

// Defining NewQuery with Rollup and with group by BusinessUnit
def dataQuery = dmCtx.newQuery(dm,true)
                               .select("BusinessUnit","BusinessUnit")
dataQuery.select("InvoicePrice","InvoicePrice") // Aggregates InvoicePrice for Each Business Unit
dataQuery.where(*filters) // where with filters defined above
def QueryResult = dmCtx.executeQuery(dataQuery) // Executing Query

api.trace(QueryResult?.getData())
//Iterating through result using Closure
QueryResult?.getData()?.forEach {row ->
    api.trace("BusinessUnit",row.BusinessUnit) // BusinessUnit
    api.trace("InvoicePrice",row.InvoicePrice) // Total InvoicePrice
}







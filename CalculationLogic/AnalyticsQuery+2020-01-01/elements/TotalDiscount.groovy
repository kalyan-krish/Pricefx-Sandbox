

/*
Requirements : Display a report of total Discount provided per each Customer per Month.
               Consider only transactions with positive Invoice Price.
               Order the result by Customer and by Month for easy readability.


Steps: 1. Initialize Query with Rollup = True (Aggregation)
       2. Group by Customer and Month
       3. Filter so that query with consider only Positive Invoice Price
       4. Order by Customer and Month
 */




//DataMart Context
def dmCtx = api.getDatamartContext()
def dm = dmCtx.getDatamart("Transaction")?:null // Getting DataMart Transaction
if (dm == null) {
    api.addWarning("Transaction DataMart is not available") // Raising Warning if DataMart is not available
    return null
}

// Defining Filter with InvoicePrice > 0 (positive)
def filter = [
         Filter.greaterThan("InvoicePrice",0),
         Filter.notEqual("InvoiceDateMonth",null)
]

// Defining NewQuery with Rollup and with group by Customer and Month
def dataQuery = dmCtx.newQuery(dm,true)
        .select("CustomerId","Customer").select("InvoiceDateMonth","Month")

dataQuery.select("Discount","Discount") // Aggregates Discount for each group combination
dataQuery.where(*filter) // where with filter defined above
dataQuery.orderBy("CustomerId")
dataQuery.orderBy("InvoiceDateMonth")
def QueryResult = dmCtx.executeQuery(dataQuery) // Executing Query

api.trace(QueryResult?.getData())
//Iterating through result using Closure
QueryResult?.getData()?.forEach { row ->
    if (!(null in [row.Customer, row.Month, row.Discount])) {
        api.trace("Customer", row.Customer) // Customer
        api.trace("Month", row.Month) // Month
        api.trace("Discount",row.Discount) // Total Discount
    }
}


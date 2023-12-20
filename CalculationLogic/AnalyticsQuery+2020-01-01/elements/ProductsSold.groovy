
/*
Requirement : Report how many distinct Products have we sold per Customer in last 365 days.

Steps: 1. Initialize Query with Rollup = True (Aggregation)
       2. Calculate date in last 365days from Current Date
       3. Group by Customer
       4. Filter to Last 365 days
 */

//DataMart Context
def dmCtx = api.getDatamartContext()
def dm = dmCtx.getDatamart("Transaction")?:null // Getting DataMart Transaction
if (dm == null) {
    api.addWarning("Transaction DataMart is not available") // Raising Warning if DataMart is not available
    return null
}

//Date of 365 days Ago to current date
Date CurrentDate = new Date()
def calender = api.calendar()
calender.time = CurrentDate
calender.add(Calendar.DAY_OF_YEAR,-365)
def date365DaysAgo = calender.time.format("yyyy-MM-dd")
def TodayDate = new Date().format("yyyy-MM-dd")

// Defining Filter with InvoiceDate to Last 365days
def filters = [
        Filter.greaterOrEqual("InvoiceDate",date365DaysAgo),
        Filter.lessOrEqual("InvoiceDate",TodayDate)

]

// Defining NewQuery with Rollup
def dataQuery = dmCtx.newQuery(dm,true).select("CustomerId","Customer") // group by Customer

dataQuery.select("COUNTDISTINCT(ProductId)","ProductsSold") // Distinct Products Count
dataQuery.where(*filters) // where with filter defined above

def QueryResult = dmCtx.executeQuery(dataQuery) // Executing Query

api.trace(QueryResult?.getData())
//Iterating through result using Closure
QueryResult?.getData()?.forEach { row ->
    if (row.Customer != null) {
            api.trace("Customer",row.Customer)
            api.trace("CountOfProductsSold",row.ProductsSold)
    }
}


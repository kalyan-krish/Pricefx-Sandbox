
/*
Requirement : Retrieve sample of last 10 rows (according to Invoice Date) available in the dataMart
              show all fields
              limit the result to 10 rows

 Steps : 1. Initialize new query with rollup false (No Aggregation)
         2. Select All Fields
         3. Write a SQL Query to Select all Fields and order by InvoiceDate Desc and Limit 10 (to get Last 10 Records)
         4. ExecuteSql Query

 */


def dmCtx = api.getDatamartContext()
def dm = dmCtx.getDatamart("Transaction")?:null // Getting DataMart Transaction
if (dm == null) {
    api.addWarning("Transaction DataMart is not available") // Raising Warning if DataMart is not available
    return null
}

def q1 = dmCtx.newQuery(dm,false) // New Query

q1.selectAll() // select all columns

// SQL Query to return last 10 rows order by InvoiceDate
def sql = '''

SELECT * FROM T1
ORDER BY T1.InvoiceDate DESC 
LIMIT 10

'''

//Execute SQL Query
def result = dmCtx.executeSqlQuery(sql,q1)

// Return Result in Matrix
return result ? result.toResultMatrix() : null
/**
 ListPrice (Product Extension)
 Access rules:
 Search by sku
 Search by Valid From date is bigger or equal to effective/target date of the quote
 Search by Valid From date is closest to the quote’s target date
 Search by Currency matching Customer Currency value
 Retrieve the List Price
 */

// TargetDate or Quote Effective Date
def targetDate = api.targetDate()?.format("yyyy-MM-dd")

//Filters
def filters =[
        Filter.equal("name","ListPrice"), // Product Extension Table
        Filter.equal("sku",api.product("sku")), // product ID
        Filter.greaterOrEqual("attribute1",targetDate), // valid From
        Filter.equal("attribute3",GetCustomerCurrency.getCurrency()) // currency

]

def fields = ["ListPrice"]

// ListPrice from ListPrice PX using api.find using above filters
def listPrice = api.find("PX",0,1,"attribute1",fields,*filters)?.find()?.ListPrice

// If ListPrice is not found
if (listPrice == null) {
    api.addWarning("ListPrice is not found for the product and targetDate and currency")
    return null
}

return listPrice as BigDecimal



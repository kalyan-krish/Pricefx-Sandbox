/**
 Return Region value of Customer record using api.find() :

 for Customer Name: "Spagetti M"

 Limit fields of API returned object to Customer Name and Region
 */

// Filters with Customer Name Spagetti M
def filters = [
        Filter.equal("name","Spagetti M")
]

// Fields Required CustomerName(name), Region(attribute5)
def fields = ["name","Region"]

// Fetching Region with api.find (C typeCode for Customers)
def Region = api.find("C",0,1,"name",fields,*filters)?.find()

// Raising Warning if Region is null
if (Region == null) {
    api.addWarning("Region is not available for Spagetti M")
    return null
}

return Region



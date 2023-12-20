/**
 Return Average Cost value from Product Cost table using api.find()

 For sku from the context
 */

// ProductId of product in Context
def productId = api.product("ProductId")?:null

// If Id not found
if (productId == null) {
    api.addWarning("Product is not selected in Context")
    return null
}

// filters
def filters = [
        Filter.equal("name","ProductCost"), //PX Table name
        Filter.equal("sku",productId) // product id
]

// AvgCost from ProductCost PX Table for product in context using api.find
def AverageCost = api.find('PX',0,1,null,*filters)?.find()?.attribute1

// if AvgCost is not found
if (AverageCost == null) {
    api.addWarning("ProductCost is not available for the product")
    return null
}

return AverageCost
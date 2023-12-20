/**
 Return Product Lifecycle using api.product()

 For sku "MB-0001"

 */

// Product Life Cycle for the product - MB-0001
def productLifeCycle = api.product("ProductLifeCycle","MB-0001")?:null

// if lifeCycle not found
if (productLifeCycle == null) {
    api.addWarning("Product LifeCycle is not available for the product")
    return null
}

return productLifeCycle
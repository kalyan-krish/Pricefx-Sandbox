/**
 Return Product Label using api.product()

 For sku from the context
 */

// product Label for the product in context
def productLabel = api.product("Label")?:null

// If label not found
if (productLabel == null) {
    api.addWarning("Product Label is not available for the product")
    return null
}

return productLabel
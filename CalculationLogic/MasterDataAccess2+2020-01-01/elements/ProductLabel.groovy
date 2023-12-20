/**
 Return Product Label using api.currentItem()

 For sku from the context
 */

//Product Label using api.currentItem
def productLabel = api.currentItem("Label")?:null

// If Label not found
if (productLabel == null) {
    api.addWarning("Product label is not available for the product")
    return null
}

return productLabel
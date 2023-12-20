/**
 Return Average Cost value from Product Cost table using api.productExtension()

 For sku from the context
 */

// Average Cost of Product in context from ProductCost PX Table
def AverageCost = api.productExtension("ProductCost")?.find()?.attribute1

// If AvgCost not found
if (AverageCost == null) {
    api.addWarning("Product Cost Table or attribute selected is not available")
    return null
}


return AverageCost


// Header input Sales Discount %
def headerInputSalesDiscount = input.SalesDiscountPct

// DebugMode set SalesDiscount % to 5%
if (api.isDebugMode()) {
    headerInputSalesDiscount = 0.05
}

// Retuning Header input and set Default to Zero
return headerInputSalesDiscount?:0
''''
Return Seasonal Rate value using api.find():

Search by Product Group - using Product Group from task 1.

Search by Region - using Region from Task 2.

Search by Date - valid on Date from Task 3.

You can use api.findLookupTable() to get your table id'
'''


def SeasonalRateTableId = api.findLookupTable("SeasonalRate")?.id

def Region = out.ReturnCustomerRegion?.getAt("Region")

def Filters = [
        Filter.equal("lookupTable.id",SeasonalRateTableId),
        Filter.equal("key1",Region),
        Filter.equal("key2",out.ReturnProductGroup),
        Filter.lessOrEqual("key3",out.GetTargetDate)
]

def SeasonalRate = api.find('MLTV3',0,1,null,*Filters)?.find()?.attribute1


if (SeasonalRate == null) {
    api.addWarning("SeasonalRate is not found ")
    return null
}

return SeasonalRate
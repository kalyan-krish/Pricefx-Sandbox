/**
 * Return all data from Competition Data table using api.find() in a loop.
 */

def start = 0 // start
def data = null

//Using while loop to fetch all Competition Data
while (data = api.find("PCOMP",start,api.getMaxFindResultsLimit(),"sku",Filter.equal("competitionType","Online")?:null)
) {
    // Raising Warning if data is null
    if (data == null) {
        api.addWarning("Competition data is null")
        return null
    }

    start += data.size()

    api.trace("Competition Data:",data)

}

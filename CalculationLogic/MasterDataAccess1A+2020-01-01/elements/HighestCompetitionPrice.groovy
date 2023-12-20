/**
 Return the Highest Price value of all records in Competition Data using api.find()

 Use api.find()'s sortBy parameter.

 Limit query results to 1 record
 */

// Fetching Highest Competition Price value using sort by Descending order
// PCOMP (TypeCode for Competition Data)
// Filter by Competition Type Online

def highestCompetitionPrice = api.find("PCOMP",0,1,
        "-price",Filter.equal("competitionType","Online"))?.find()?.price

// Raise Warning
if (highestCompetitionPrice == null) {

    api.addWarning("Competition Price is missing")
    return null
}

return highestCompetitionPrice






/**
 * TimeFrame Header Input (No of Months of Historical Required to compare Deviation of Current Product price)
 * @return TimeFrame(No of Months)
 */

// Header Input TimeFrame
def HeaderInputTimeFrame = input.TimeFrame

// DebugMode with value 3 months
if (api.isDebugMode()) {
    HeaderInputTimeFrame = 3
}

// If headerInput TimeFrame > 24 months raise warning
if (HeaderInputTimeFrame > 24) {
    api.addWarning("TimeFrame(No of Months) is limited to 24 months you have Exceed the Limit: Taking Default value 0")
}

// return and set default value to 0 months
return HeaderInputTimeFrame?:0
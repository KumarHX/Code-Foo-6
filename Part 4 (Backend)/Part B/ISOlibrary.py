import re
import datetime

"""
This program is a library that converts different date formats to the ISO format. 

Author: Pranav Kumar
Date: April 2, 2016

"""
PM = False
GMT = False
days = None
years = None
monthValue = None

"""
This method adds time if the date has a PM

param: inputted Date

"""

def handlePM(date):
    date = date.split("T")
    timeValues = date[1].split(":")
    return date[0] + "T" + str(int(timeValues[0]) + 12) + ":" + timeValues[1] + ":" + timeValues[2]


"""
This method gets the inputted date and prints out the ISO formatted date

param: inputted Date

"""

def convertToISO(input):
    now = datetime.datetime.now()
    ISODate = convertDate(input)
    if ISODate is None:
        ISODate = ""
    if days:
        ISODate = days + ISODate
    if monthValue:
        ISODate = monthValue + "-" + ISODate
    if years:
        ISODate = years + "-" + ISODate
    #for case: March 20
    if days and years is None:
        ISODate = str(now.year) + "-" + ISODate
    #for case: March, 2016
    if years and monthValue and days is None:
        ISODate = ""
        ISODate = years + "-" + monthValue + "-" + addZero(now.day)
    ISODate = addAbsentValues(ISODate)
    if PM:
        ISODate = handlePM(ISODate)
    if GMT:
        ISODate = ISODate + "+00:00"
    return ISODate

#Checks for months in dates
months = [None, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
monthAbbr = [None, "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"]

"""
This method handles the case "20th" be removing 'th' to be left with the number

param: day + th

"""
def thCase(value):
    global days
    days = value[:-2]

"""
This method fixes the incorrect ISO format 2016-20-03T16:05:07-08:00 bt swirching the month and day

param: incorrect ISO date

"""

def convertISOError(date):
    date = date.split("T")
    dateValues = date[0].split("-")
    return dateValues[0] + "-" + dateValues[2] + "-" + dateValues[1] + "T" + date[1]

"""
This method converts the slash format date (ie. 3/20/2016 or 20/3/2016) to ISO format

param: slash format date
return: ISO formatted date

"""

def slashesToISO(slashedDate):
    #date seperated with "/"
    if "/" in slashedDate:
        slashedDate = slashedDate.split("/")
        #for the day/month/year format
        if int(slashedDate[0]) < 13 and int(slashedDate[1]) < 32:
            slashedDate[1] = addZero(int(slashedDate[1]))
            slashedDate[0] = addZero(int(slashedDate[0]))
            ISODate = slashedDate[2] + "-" + slashedDate[0] + "-" + slashedDate[1]
            return ISODate
        #for the month/day/year format
        elif int(slashedDate[0]) < 32 and int(slashedDate[1]) < 13:
            slashedDate[1] = addZero(int(slashedDate[1]))
            slashedDate[0] = addZero(int(slashedDate[0]))
            ISODate = slashedDate[2] + "-" + slashedDate[1] + "-" + slashedDate[0]
            return ISODate
        else:
            print("Not a valid slash format input")
            return None
    #date seperated with "."
    elif "." in slashedDate:
        slashedDate = slashedDate.split(".")
        ISODate = slashedDate[0] + "-" + slashedDate[1] + "-" + slashedDate[2]
        return ISODate
    else:
        return None

"""
This method appends a zero to the start of a one digit number

param: one digit number
return: append zero at the start

"""
def addZero(number):
    value = str(number)
    if(len(value) == 1):
        value = "0" + value
    return value

"""
This method returns the time format for ISO

param: time (ie. 4:05:07 PM)
return: time in ISO format

"""    
def timeAdded(time):
    time = time.split(":");
    for values in time:
        if int(values) > 60 or int(values) < 0:
            print("Not a valid time format input")
            return ""
    for i in xrange(len(time)):
        time[i] = "%02d"%(int(time[i]))
    while len(time) < 3:
        time+=["00"]
    return ":".join(time)

"""
This method adds the values not provided by the input. 0 for missing times and current date for missing dates

param: ISO formatted date
return: ISO formatted date with blanks filled in

"""    

def addAbsentValues(date):
    if "T" not in date:
        date = date + "T00:00:00"
    elif (date.index('T') == 0):
        now = datetime.datetime.now()
        date = str(now.year) + "-" + addZero(now.month) + "-" + addZero(now.day) + date
    return date

"""
This method adds 12 hours for PM dates

param: ISO formatted date
return: ISO formatted date + 12 hours

"""    
def handlePM(date):
    date = date.split("T")
    timeValues = date[1].split(":")
    return date[0] + "T" + str(int(timeValues[0]) + 12) + ":" + timeValues[1] + ":" + timeValues[2]

"""
This method handles cases for raw numbers going through the function (either days, years or "20160320" case)

param: raw number
return: if "20160320" case return ISO formatted else no return

"""    

def noSeperation(value):
    #handles this case: 20160320
    if len(value) > 7:
        year = value[:4]
        month = value[4:6]
        day = value[6:]
        return year + "-" + month + "-" + day
    #checks if day
    elif int(value) < 32 and int(value) > 0:
        global days
        days = value
        return None
    #checks if year
    elif int(value) > 1000 and int(value) < 9999:
        global years
        years = value
        return None

# REGEX's
stroke = re.compile('[0-9]+[/|\.][0-9]+[/|\.][0-9]+')
time = re.compile('[0-9]+:[0-9]+')#[A|P]M
nanStroke = re.compile('[0-9]+')
timezone = re.compile('[-|+][0-9]+')
ISO8601 = re.compile('[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+(.*)')

"""
This method converts the date to the ISO formatted date with the use of helper functions

param: date passed in
return: ISO formatted date

"""

def convertDate(unformattedDate):
    #remove commas from the inputted date
    unformattedDate = unformattedDate.replace(",","")
    global PM
    global monthValue
    global GMT
    #split the date based on spaces
    unformattedDate = unformattedDate.split()
    ISOFormattedDate = ""
    for section in unformattedDate:
        #Converts for the input (2016-20-03T16:05:07-08:00)
        if(ISO8601.match(section)):
            ISOFormattedDate = convertISOError(section)
        elif(stroke.match(section)):
            ISOFormattedDate = slashesToISO(section)
        elif(time.match(section)):
            if ISOFormattedDate:
                ISOFormattedDate = ISOFormattedDate + "T" + timeAdded(section)
            else:
                ISOFormattedDate = "T" + timeAdded(section)
        elif timezone.match(section):
            ISOFormattedDate = section[:-2]
        elif section in months:
            monthValue = "%02d"%(months.index(section))
        elif section.upper() in monthAbbr:
            monthValue = "%02d"%(monthAbbr.index(section.upper()))
        elif section == "PM":
            PM = True
        elif "th" in section:
            thCase(section)
        elif nanStroke.match(section):
            ISOFormattedDate = noSeperation(section)
        elif "GMT" in section:
            GMT = True
    return ISOFormattedDate

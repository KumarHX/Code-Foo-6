import re

"""
This program is a library that converts different date formats to the ISO format. 

Author: Pranav Kumar
Date: April 2, 2016

"""
PM = False

# REGEX's
stroke = re.compile('[0-9]+[/|\.][0-9]+[/|\.][0-9]+')
time = re.compile('[0-9]+:[0-9]+')#[A|P]M
nanStroke = re.compile('[0-9]+')
timezone = re.compile('[-|+][0-9]+')
ISO8601 = re.compile('[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+(.*)')

"""
This method gets the inputted date and prints out the ISO formatted date

param: inputted Date

"""

def convertToISO(input):
    ISODate = convertDate(input)
    ISODate = addAbsentValues(ISODate)
    if PM:
        ISODate = handlePM(ISODate)
    print(ISODate)

"""
This method converts the date to the ISO formatted date with the use of helper functions

param: date passed in
return: ISO formatted date

"""
months = [None, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
monthAbbr = [None, "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"]
days = [None, "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]


def convertDate(unformattedDate):
    #remove commas from the inputted date
    unformattedDate = unformattedDate.replace(",","")
    global PM
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
            ISOFormattedDate = ISOFormattedDate + "T" + timeAdded(section)
        elif timezone.match(section):
            ISOFormattedDate = section[:-2]
        elif section in months:
            ISOFormattedDate = ISOFormattedDate + "%02d"%(months.index(section))
        elif section.upper() in monthAbbr:
            ISOFormattedDate = ISOFormattedDate + "%02d"%(monthAbbr.index(section.upper()))
        elif section in days:
            continue
        elif section == "PM":
            PM = True



    return ISOFormattedDate


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

def addAbsentValues(date):
    if "T" not in date:
        date = date + "T00:00:00"
    elif date[:1] == "T":
        date = "2000-00-00"+ date
    return date

def handlePM(date):
    date = date.split("T")
    timeValues = date[1].split(":")
    return date[0] + "T" + str(int(timeValues[0]) + 12) + ":" + timeValues[1] + ":" + timeValues[2]


#testing
convertToISO("3/20/2016 4:05:07 PM")

import dateutil.parser as parser
text = '3/20/2016 4:05:07 PM'
date = (parser.parse(text))
print("Correct: " + date.isoformat())



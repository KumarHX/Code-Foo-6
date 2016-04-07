import ISOlibrary

"""
This program tests the ISOlibrary.

Author: Pranav Kumar
Date: April 2, 2016

"""

print('Welcome to the ISOLibrary tester! This program allows you to input date values and converts them to ISO format. \n\n')
print('These are the types of date formats that are supported by this library:')
print('a. 3/20/2016')
print('b. 4:05:07 PM')
print('c. Sunday, March 20, 2016')
print('d. Sunday, March 20, 2016 4:05 PM')
print('e. Sunday, March 20, 2016 4:05:07 PM')
print('f. Sunday 20th of March 2016 04:05:07 PM')
print('g. Sunday, MAR 20, 2016')
print('h. 3/20/2016 4:05 PM')
print('i. 3/20/2016 4:05:07 PM')
print('j. March 20, 2016')
print('k. March 20')
print('l. March, 2016')
print('m. Sun, 20 Mar 2016 16:05:07 GMT')
print('n. Sun, 20 Mar 2016 16:05:07 -0800')
print('o. 20160320 16:05:07')
print('p. 20160320')
print('q. 2016.03.20')
print('r. 20/03/2016')
print('s. 20 March 2016')
print('t. 2016-20-03T16:05:07-08:00\n\n')

date_inputted = raw_input("Please enter a date to be converted to ISO format: ")
ISODate = ISOlibrary.convertToISO(date_inputted)
print(ISODate)
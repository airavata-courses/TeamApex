#!/bin/bash
#
# Script to send mail to user for job monitoring on Karst.
# 
# USAGE: sendEmail.sh <JOB_ID>
#
# @Author: Mangirish Wagle (Team_Apex)
#

source ./sendmail.properties

mailFile=report.mail
jobId=$1

if [ -z $1 ];then
	/bin/echo "ERROR: Insufficient Arguments [USAGE: sendEmail.sh <JOB_ID>]."
	exit 1
fi

# Clear Mail File.
cat /dev/null > $mailFile

# Creating a mail headers
/bin/echo "From: $mailfrom" >> $mailFile
/bin/echo "To: $mailto" >> $mailFile
/bin/echo "Subject: Job ID: $jobId $subject" >> $mailFile

# Adding job status report.
/bin/echo "Following is the status of Job $jobId:-" >> $mailFile
/usr/local/bin/qstat $jobId >> $mailFile

# Send the mail notification.
/usr/sbin/sendmail $mailto < $mailFile

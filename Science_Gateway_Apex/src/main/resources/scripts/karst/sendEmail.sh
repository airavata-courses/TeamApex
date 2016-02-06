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
	echo "ERROR: Insufficient Arguments [USAGE: sendEmail.sh <JOB_ID>]."
	exit 1
fi

# Clear Mail File.
cat /dev/null > $mailFile

# Creating a mail headers
echo "From: $mailfrom" >> $mailFile
echo "To: $mailto" >> $mailFile
echo "Subject: Job ID: $jobId $subject" >> $mailFile

# Adding job status report.
echo "Following is the status of Job $jobId:-" >> $mailFile
qstat $jobId >> $mailFile

# Send the mail notification.
sendmail $mailto < $mailFile

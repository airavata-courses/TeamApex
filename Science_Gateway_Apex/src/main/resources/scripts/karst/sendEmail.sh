#!/bin/bash
#
# Script to send mail to user for job monitoring on Karst.
# 
# USAGE: sendEmail.sh <JOB_NAME> [<USER_NAME>]
#
# @Author: Mangirish Wagle (Team_Apex)
#

source ./sendmail.properties

mailFile=report.mail
jobName=$1
if [ -z $1 ];then
        /bin/echo "ERROR: Insufficient Arguments [USAGE: sendEmail.sh <JOB_ID>]."
        exit 1
fi

# Clear Mail File.
cat /dev/null > $mailFile

# Creating a mail headers
/bin/echo "From: $mailfrom" >> $mailFile
/bin/echo "To: $mailto" >> $mailFile
/bin/echo "Subject: Job Name: $jobName $subject" >> $mailFile

user=""

if [ ! -z $2  ];then
        user=$2
else
        user=`whoami`
fi

#/bin/echo "Username: $user"

# Adding job status report.
/bin/echo "Following is the status of Job $jobId:-" >> $mailFile

/bin/echo "                                                                                  Req'd    Req'd       Elap" >> $mailFile
/bin/echo "Job ID                  Username    Queue    Jobname          SessID  NDS   TSK   Memory   Time    S   Time" >> $mailFile
/bin/echo "----------------------- ----------- -------- ---------------- ------ ----- ------ ------ --------- - ---------" >> $mailFile

qstat -u $user | grep $jobName >> $mailFile

#/bin/echo $jobDetails

# Send the mail notification.
/usr/sbin/sendmail $mailto < $mailFile

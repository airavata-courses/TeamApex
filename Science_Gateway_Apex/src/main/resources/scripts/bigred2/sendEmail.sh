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

mail_boundary="ZZ_/asdfasf34.34t3refSDFG34534fdfgefokosdf"

# Clear Mail File.
cat /dev/null > $mailFile

# Creating a mail headers
/bin/echo "From: $mailfrom" >> $mailFile
/bin/echo "To: $mailto" >> $mailFile
/bin/echo "Subject: Job Name: $jobName $subject" >> $mailFile
/bin/echo "Mime-Version: 1.0" >> $mailFile
/bin/echo "Content-Type: multipart/mixed; boundary=\"$mail_boundary\"" >> $mailFile

echo "--$mail_boundary" >> $mailFile

# Email Text Headers and body

/bin/echo "Content-Type: text/plain; charset=\"US-ASCII\"" >> $mailFile
/bin/echo "Content-Transfer-Encoding: 7bit" >> $mailFile
/bin/echo "Content-Disposition: inline" >> $mailFile

user=""

if [ ! -z $2 ];then
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

echo "--$mail_boundary" >> $mailFile


if [ -f "${jobName}.out" ];then
	# Attachment Headers and embedding
	
	mimetype=`file --mime-type "${jobName}.out" | sed 's/.*: //'`
	encoded_file=$(base64 < ${jobName}.out)
	/bin/echo "Content-Type: $mimetype" >> $mailFile
	/bin/echo "Content-Transfer-Encoding: base64" >> $mailFile
	/bin/echo "Content-Disposition: attachment; filename=\"${jobName}.out\"" >> $mailFile

	# Adding a new line
	/bin/echo "" >> $mailFile

	/bin/echo $encoded_file >> $mailFile
	
	/bin/echo "--$mail_boundary" >> $mailFile
fi

#/bin/echo $jobDetails

# Send the mail notification.
/usr/sbin/sendmail $mailto < $mailFile

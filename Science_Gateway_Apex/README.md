# Team Apex - Remote Job Runner
_Team Members:_
* _Gourav Shenoy_
* _Mangirish Wagle_
* _Erika Dsouza_

## Instructions Manual
The application has been programmed to submit jobs on any remote machine (eg: Karst), and monitor them. Below you will find a complete step-by-step guide to install the application and to get up and running.

### Prerequisites
* Oracle J2SE 1.7
* Apache Maven 3.3.9

### Installation Method: Installing from Source

__Step 1:__ Clone the repository from GitHub

```cmd
$ git clone https://github.com/airavata-courses/TeamApex.git -b milestone-1

$ cd Science_Gateway_Apex
```

__Step 2:__ Update the properties file

* Navigate to the _src/main/resources_ folder

	```cmd
	$ cd src/main/resources
	```

* Update the _config.properties_ file with the following details:
	
	__NOTE:__ For Windows, the directory path separator is double-backslash ('\\\\').
	
	* loginUser - Username to login to remote machine (Karst).
	* loginKey - Path to private key file on local machine.
	* knownHosts - Path to known_hosts file on local machine.
	* passPhrase - Passphrase for private key file.
	
	* destFileEmail - Path on the __REMOTE__ (Karst) machine, where script will be copied.
	* destFileEmailProp - Path on the __REMOTE__ (Karst) machine, where script will be copied.
	
	* tempJobScript - Path on the __LOCAL__ machine, where the script will be created.
	* destJobScript - Path on the __REMOTE__ machine, where the script will be copied.

* Navigate to _src/main/resources/scripts/karst_ directory and update the _sendemail.properties_ file with following details:
	
	* mailfrom - (Optional) - Email id of sender
	* mailto - (Required) - Email id where notifications would be sent (__Note:__ Currently supports IU email ID only).
	
__Step 3:__ Run the _maven_ build

* Navigate to the project home directory - _Science_Gateway_Apex_

* Build project using maven as follows:

	```cmd
	$ mvn -e clean install
	```

	__Note:__ The JAR is created in the _target_ directory.

__Step 4:__ Run the executable jar

* Navigate to the target directory

	```cmd
	$ cd target
	```

* Run the jar with following command(s):

	To submit a job to remote machine
	
	```cmd
	$ java -jar ScienceGatewayApex-0.1.0.jar submit
	```

	To monitor a job on the remote machine
	
	```cmd
	$ java -jar ScienceGatewayApex-0.1.0.jar submit <name_of_job>
	```
	
### Sample Output

```cmd
$ java -jar ScienceGatewayApex-0.1.0.jar submit
Please enter the number of nodes: 2
Please enter the processors per node: 2
Please enter the email id: mawagle@iu.edu
Please enter the job: mawagle04

Request Bean: SCPRequestBean [userName=mawagle, hostName=karst.uits.iu.edu, passPhrase=aq1sw2de, privateKeyFilePath=/home/mangirish/.ssh/id_rsa, KnownHostsFilePath=/home/mangirish/.ssh/known_hosts, sourceFilePath=/home/mangirish/temp.script, destFilePath=/N/u/mawagle/Karst/temp.script, sshPort=22]
Starting File Upload...
File upload succeeded!
Retrieving Uploaded file...

Request Bean: SCPRequestBean [userName=mawagle, hostName=karst.uits.iu.edu, passPhrase=aq1sw2de, privateKeyFilePath=/home/mangirish/.ssh/id_rsa, KnownHostsFilePath=/home/mangirish/.ssh/known_hosts, sourceFilePath=/tmp/sendEmail6493528668696500013.sh, destFilePath=/N/u/mawagle/Karst/sendEmail.sh, sshPort=22]
Starting File Upload...
File upload succeeded!
Retrieving Uploaded file...

Request Bean: SCPRequestBean [userName=mawagle, hostName=karst.uits.iu.edu, passPhrase=aq1sw2de, privateKeyFilePath=/home/mangirish/.ssh/id_rsa, KnownHostsFilePath=/home/mangirish/.ssh/known_hosts, sourceFilePath=/tmp/sendmail339337881862529657.properties, destFilePath=/N/u/mawagle/Karst/sendmail.properties, sshPort=22]
Starting File Upload...
File upload succeeded!
Retrieving Uploaded file...

Request Bean: SSHRequestBean [userName=mawagle, hostName=karst.uits.iu.edu, passPhrase=aq1sw2de, privateKeyFilePath=/home/mangirish/.ssh/id_rsa, KnownHostsFilePath=/home/mangirish/.ssh/known_hosts, sshPort=22, command=qsub temp.script]
Starting command execution...
Execution completed!

1179678.m2
```

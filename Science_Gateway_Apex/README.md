# Team Apex - Remote Job Runner
_Team Members:_
* _Gourav Shenoy_
* _Mangirish Wagle_
* _Erika Dsouza_

## Instructions Manual
The application has been programmed to submit jobs on any remote machine (eg: Karst), and monitor them. Below you will find a complete step-by-step guide to install the application and to get up and running.

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
	
	__NOTE:__ For Windows, the directory path separator is double-backslash ('\\').
	
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

* Run the jar with following command:

	To submit a job to remote machine
	
	```cmd
	$ java -jar ScienceGatewayApex-0.1.0.jar submit
	```

	To monitor a job on the remote machine
	
	```cmd
	$ java -jar ScienceGatewayApex-0.1.0.jar submit <name_of_job>
	```
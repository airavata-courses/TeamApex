/*
 * Script for SGA job management functions.
 * @author: Mangirish Wagle
 */

var baseURL = "sga/rest"

/*
 * Function to render Success message.
 */
function displayMessageOnSuccess(htmlString) {
	$( "#responseDiv" ).css( "color", "#0000FF" );
	$( "#responseDiv" ).html( htmlString );
}

/*
 * Function to render Error message.
 */
function displayOnError(htmlString) {
	$( "#responseDiv" ).css( "color", "#FF0000" );
	$( "#responseDiv" ).html( htmlString );
}

////////////SUCCESS Functions////////////

/*
 * Submit Job Success Function.
 */
function jobSubmitSuccess(response) {
	var htmlString = "<p>Job ID: " + response.submitJobResponse.jobId + "<br>Status: "
						+ response.submitJobResponse.status + "</p>"
	//alert(htmlString)
	displayMessageOnSuccess( htmlString );

	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
}

/*
 * Monitor Job Success Function.
 */
function jobMonitorSuccess(response) {
	var htmlString = "<p>" + response.simpleAPIResponse.message + "</p>"
	//alert(htmlString)
	displayMessageOnSuccess( htmlString );

	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
}

/*
 * Get Job Status Success Function.
 */
function jobGetStatusSuccess(response) {
	var htmlString = "<p>"
						+ "Job ID: " + response.jobResponse.jobId + "<br>"
						+ "Status: " + response.jobResponse.status + "<br>"
						+ "Queue: " + response.jobResponse.queue + "<br>"
						+ "Required Memory: " + response.jobResponse.requiredMemory + "<br>"
						+ "Number of Processors: " + response.jobResponse.numProcessors + "<br>"
						+ "Number of Nodes: " + response.jobResponse.numNodes + "<br>"
						+ "Required Time: " + response.jobResponse.requiredTime + "<br>"
						+ "User Name: " + response.jobResponse.userName + "<br>"
						+ "Job Name: " + response.jobResponse.jobName + "<br>"
						+ "Elapsed Time: " + response.jobResponse.elapsedTime + "<br>"
					 + "</p>"

	//alert(htmlString)
	displayMessageOnSuccess(htmlString);

	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
}

/*
 * Cancel Job Success Function.
 */
function jobCancelSuccess(response) {
	var htmlString = "<p>" + response.simpleAPIResponse.message + "</p>"
	//alert(htmlString)
	displayMessageOnSuccess( htmlString );

	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
}

/*
 * Output Download Success Function.
 * FIXME: This may not be really required.
 */
function outputDownloadSuccess(response) {
	var htmlString = "<p>" + response + "</p>"
	//alert(htmlString)
	displayMessageOnSuccess( htmlString );
}

////////////ERROR Functions////////////
//TODO: Implement Error functions and handle errors on UI.

/*
 * Submit Job function.
 */
function submitJob(procnum, email, nodenum, walltime, jobname) {

	// Show loading overlay
	$("#overlay").css("visibility", "visible");

	$.ajax({
		type: "POST",
		url: baseURL + "/job/submit",
		headers: {
			'Content-type': "application/json"
		},
		data: "{\"submitJobRequest\": {\"numProcessors\": "+ procnum 
		+ ", \"emailId\": \"" + email + "\""
		+ ", \"numNodes\": \"" + nodenum + "\""
		+ ", \"wallTime\": \"" + walltime + "\""
		+ ", \"jobName\": \"" + jobname + "\"}}",
		success: jobSubmitSuccess,
		dataType: "json"
	});
}

/*
 * Monitor Job Function.
 */
function monitorJob(jobID) {
	//alert("monitor job " + jobID);

	// Show loading overlay
	$("#overlay").css("visibility", "visible");

	$.get( baseURL + "/job/" + jobID + "/monitor", jobMonitorSuccess );
}

/*
 * Get Job Status function.
 */
function getStatus(jobID) {
	//alert("getstatus job " + jobID);

	// Show loading overlay
	$("#overlay").css("visibility", "visible");

	$.get( baseURL + "/job/" + jobID + "/status", jobGetStatusSuccess );
}

/*
 * Cancel Job function.
 */
function cancelJob(jobID) {
	//alert("cancel job " + jobID);

	// Show loading overlay
	$("#overlay").css("visibility", "visible");

	$.ajax({
		type: "DELETE",
		url: baseURL + "/job/" + jobID,
		success: jobCancelSuccess
	});
}

/*
 * Download Output function.
 */
function downloadOutput(jobName) {
	//alert("download output job " + jobName);

	$.get( baseURL + "/job/" + jobName + "/output", outputDownloadSuccess);

	window.open(baseURL + "/job/" + jobName + "/output", "_blank");
}

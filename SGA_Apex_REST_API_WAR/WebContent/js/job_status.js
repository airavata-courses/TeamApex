$(document).ready(function() {
	// hide buttons on load
	$("#refreshBtn").hide();
	$("#downloadBtn").hide();
	$("#cancelBtn").hide();
	
	// get job status
	jobID = getParameterByName("jobID");
	machineID = getParameterByName("machineID");
	
	//$.get( baseURL + "/job/" + machineID + "/" + jobID, renderJobStatus);
	$.ajax({
		type: "GET",
		url: baseURL + "/job/" + machineID + "/" + jobID,
		success: renderJobStatus,
		error: apiErrorResponse
	});
	
	$("#cancelBtn").click(function() {
		cancelJob(machineID, jobID);
	});
	
	$("#downloadBtn").click(function() {
		downloadOutput(machineID, jobID);
	});
	
	$("#refreshBtn").click(function() {
		location.reload();
	});
});


function renderJobStatus(response) {
	
	// set global variables
	machineID = response.experiment.machine.machineID;
	jobID = response.experiment.jobID;
	
	var htmlString = "<table class='table table-hover'>" +
						"<tr class='active'>" +
							"<td class='active'> Job ID </td>" +
							"<td> " + response.experiment.jobID + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Job Name </td>" +
							"<td> " + response.experiment.jobName + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Status </td>" +
							"<td> " + response.experiment.status + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> User Name </td>" +
							"<td> " + response.experiment.userName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Application </td>" +
							"<td> " + response.experiment.application.appName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Number of Processors per Node </td>" +
							"<td> " + response.experiment.numProcPerNode + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Number of Nodes </td>" +
							"<td> " + response.experiment.numNodes + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Wall Time </td>" +
							"<td> " + response.experiment.wallTime + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Machine </td>" +
							"<td> " + response.experiment.machine.machineID + "</td>" +
						"</tr>" +						
					"</table>";
						
	$("#jobStatus").html(htmlString);
	
	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
	
	// show buttons
	$("#refreshBtn").show();
	$("#cancelBtn").show();
	
	// show download button only if completed
	// and hide cancel button
	if(response.experiment.status.toLowerCase() == "completed") {
		$("#downloadBtn").show();
		$("#cancelBtn").hide();
	}
	
	// set the job name
	jobName = response.experiment.jobName;
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    url = url.toLowerCase(); // This is just to avoid case sensitiveness  
    name = name.replace(/[\[\]]/g, "\\$&").toLowerCase();// This is just to avoid case sensitiveness for query parameter name
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
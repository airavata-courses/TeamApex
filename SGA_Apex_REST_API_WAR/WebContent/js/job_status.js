$(document).ready(function() {
	// hide buttons on load
	$("#refreshBtn").hide();
	$("#downloadBtn").hide();
	$("#cancelBtn").hide();
	
	// get job status
	jobID = getParameterByName("jobID");
	$.get( baseURL + "/job/" + jobID + "/status", renderJobStatus);
	
	$("#cancelBtn").click(function() {
		cancelJob(jobID);
	});
	
	$("#downloadBtn").click(function() {
		downloadOutput(jobName);
	});
	
	$("#refreshBtn").click(function() {
		location.reload();
	});
	
	$("#monitorJob").submit(function(event) {
		window.location.href = jobStatusURL + $('#jobIDM').val();
		event.preventDefault();
	});
});


function renderJobStatus(response) {
	var htmlString = "<table class='table table-hover'>" +
						"<tr class='active'>" +
							"<td class='active'> Job ID </td>" +
							"<td> " + response.jobResponse.jobId + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Job Name </td>" +
							"<td> " + response.jobResponse.jobName + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Status </td>" +
							"<td> " + response.jobResponse.status + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> User Name </td>" +
							"<td> " + response.jobResponse.userName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Queue </td>" +
							"<td> " + response.jobResponse.queue + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Number of Processors </td>" +
							"<td> " + response.jobResponse.numProcessors + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Number of Nodes </td>" +
							"<td> " + response.jobResponse.numNodes + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Required Memory </td>" +
							"<td> " + response.jobResponse.requiredMemory + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Required Time </td>" +
							"<td> " + response.jobResponse.requiredTime + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Elapsed Time </td>" +
							"<td> " + response.jobResponse.elapsedTime + "</td>" +
						"</tr>" +
					"</table>";
						
	$("#jobStatus").html(htmlString);
	
	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
	
	// show buttons
	$("#refreshBtn").show();
	$("#cancelBtn").show();
	
	// show download buttong only if completed
	if(response.jobResponse.status.toLowerCase() == "completed") {
		$("#downloadBtn").show();
	}
	
	// set the job name
	jobName = response.jobResponse.jobName;
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
$(document).ready(function() {
	
	// hide buttons on load
	$("#monitorBtn").hide();
	$("#downloadBtn").hide();
	
	// get experiment list and render on page
	$.get( baseURL + "/user/jobs", renderExperimentList);
	
	$("#downloadBtn").click(function() {
		downloadOutput(machineID, jobID);
	});
	
	$("#monitorBtn").click(function() {
		window.location.href = jobStatusURL + jobID + "&machineID=" + machineID;
	});
});


function renderExperimentList(response) {
	var htmlString = "<table id='experimentListTable' class='table table-hover'>" +
					 		"<thead>" +
								"<tr class='active'>" +
									"<th data-field='srNo' data-sortable='true'> Sr No </th>" +
									"<th data-field='expName' data-sortable='true'> Experiment Name </th>" +
									"<th data-field='expID' data-sortable='true'> Experiment ID </th>" +
									"<th data-field='machine' data-sortable='true'> Machine </th>" +
									"<th data-field='createdAt' data-sortable='true'> Created Date </th>" +
								"</tr>" + 
							"</thead>" +
							"<tbody>";
	
	var experimentList = response.experimentListResponse.experimentList;
	var srNo = 1;
	
	$.each(experimentList, function(i, item) {
		
		var date = new Date(item.createdAt);
		
		htmlString += "<tr>" +
							"<td>" + (srNo++) + "</td>" +
							"<td>" + item.jobName + "</td>" +
							"<td>" + item.jobID + "</td>" +
							"<td>" + item.machine.machineID + "</td>" +
							"<td>" + date.toDateString() + "</td>" +
						"</tr>";
	});
		
	htmlString += "</tbody> </table>";
					
	// Render html
	$("#experimentList").html(htmlString);
	
	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
	
	$('#experimentListTable').dataTable();
	
	$('#experimentListTable tbody').on( 'click', 'tr', function () {
		var tableData = $(this).children("td").map(function() {
	        return $(this).text();
	    }).get();
		
		console.log('jobID: ' + tableData[2] + ', machineID: ' + tableData[3]);
		jobID = tableData[2];
		machineID = tableData[3];
		
		var experiment = $.grep(response.experimentListResponse.experimentList, function (element, index) {
		    return element.jobID == jobID && 
		    		element.machine.machineID == machineID;
		});
		
		// Show experiment on page
		renderExperimentDetails(experiment[0]);
		
	});
}

function renderExperimentDetails(experiment) {
	// Convert date to readable string 
	var date = new Date(experiment.createdAt);
	
	var htmlString = "<table class='table table-hover'>" +
						"<tr class='active'>" +
							"<td class='active'> Job ID </td>" +
							"<td> " + experiment.jobID + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Job Name </td>" +
							"<td> " + experiment.jobName + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Created Date </td>" +
							"<td> " + date.toDateString() + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Status </td>" +
							"<td> " + experiment.status + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> User Name </td>" +
							"<td> " + experiment.userName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Application </td>" +
							"<td> " + experiment.application.appName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Number of Processors per Node </td>" +
							"<td> " + experiment.numProcPerNode + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Number of Nodes </td>" +
							"<td> " + experiment.numNodes + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Wall Time </td>" +
							"<td> " + experiment.wallTime + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Machine </td>" +
							"<td> " + experiment.machine.machineID + "</td>" +
						"</tr>" +						
					"</table>";
						
	$("#experimentDetails").html(htmlString);
	
	// show buttons
	$("#monitorBtn").show();
	
	// show download button only if completed
	// and hide cancel button
	if(experiment.status.toLowerCase() == "completed") {
		$("#downloadBtn").show();
	}
}
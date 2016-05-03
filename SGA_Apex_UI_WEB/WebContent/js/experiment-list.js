$(document).ready(function() {
//	$.get( baseURL + "/user/jobs", renderExperimentList);

	var username = $.jStorage.get('username', 'admin');
	var password = $.jStorage.get('password', 'apex123');
	
	$.ajax({
		type: "GET",
		url: baseURL + "/user/jobs",
		beforeSend: function(xhr){
			xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
		},
		success: renderExperimentList
	});
	
	$("#monitorJob").submit(function(event) {
		window.location.href = jobStatusURL + $('#jobIDM').val() + "&machineID=" + $("#machineMonitor").val();
		event.preventDefault();
	});
});


function renderExperimentList(response) {
	var htmlString = "<div class='panel-group' id='accordion' role='tablist' aria-multiselectable='true'>";
	
	var experimentList = response.experimentListResponse.experimentList;
	
	$.each(experimentList, function(i, item) {
		
		var tableString = "<table class='table table-hover'>" +
							"<tr class='active'>" +
							"<td class='active'> Job ID </td>" +
							"<td> " + item.jobID + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Job Name </td>" +
							"<td> " + item.jobName + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Status </td>" +
							"<td> " + item.status + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> User Name </td>" +
							"<td> " + item.userName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Application </td>" +
							"<td> " + item.application.appName + "</td>" +
						"</tr>" +						
						"<tr class='active'>" +
							"<td class='active'> Number of Processors per Node </td>" +
							"<td> " + item.numProcPerNode + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Number of Nodes </td>" +
							"<td> " + item.numNodes + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Wall Time </td>" +
							"<td> " + item.wallTime + "</td>" +
						"</tr>" +
						"<tr class='active'>" +
							"<td class='active'> Machine </td>" +
							"<td> " + item.machine.machineID + "</td>" +
						"</tr>" +						
					"</table>";
		
		htmlString += "<div class='panel panel-default'>"
					+ "<div class='panel-heading' role='tab' id='" + item.jobID + "'>"
					+ "<h4 class='panel-title'>"
					+ "<a role='button' data-toggle='collapse' data-parent='#accordion' href='#collapse" + i + "' aria-expanded='true' aria-controls='collapseOne'>"
					+ "Job: " + item.jobName + " on Machine: " + item.machine.machineID
					+ "</a></h4>"
					+ "</div>"
					+ "<div id='collapse" + i + "' class='panel-collapse collapse in' role='tabpanel' aria-labelledby='headingOne'>"
					+ "<div class='panel-body'>"
					+ tableString
					+ "</div></div></div>";
	});
		
	htmlString += "</div>";
						
	$("#experimentList").html(htmlString);
	
	// Hide Loading overlay
	$("#overlay").css("visibility", "hidden");
}
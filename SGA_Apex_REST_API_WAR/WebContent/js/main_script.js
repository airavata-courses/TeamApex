$(document).ready(function() {
	console.log("document ready!");

	// render application list
	getApplicationList();
	
	$("#submitJob").submit(function(event) {
		console.log("Handler for .submit() called.");

		try {
			var file1 = $('#inputFile1')[0].files[0];
			var file2 = $('#inputFile2')[0].files[0];

			uploadFile(file1);
			uploadFile(file2);
		}
		catch(err) {
			console.log(err);
		}

		event.preventDefault();
	});

	$("#cancelJob").submit(function(event) {
		cancelJob($('#jobIDC').val());
		$("#cancelJob button[type='reset']").click();
		event.preventDefault();
	});

	$("#downloadOP").submit(function(event) {
		downloadOutput($('#jobNameD').val());
		$("#downloadOP button[type='reset']").click();
		event.preventDefault();
	});

	$("#monitorJob").submit(function(event) {
		window.location.href = jobStatusURL + $('#jobIDM').val() + "&machineID=" + $("#machineMonitor").val();
		event.preventDefault();
	});

	$("#myModal .modal-footer button").click(function() {
		window.location.href = jobStatusURL + jobID + "&machineID=" + machineID;
	});

	$("#machine").change(function() {
		  machineID = $("#machine").val();
	});
	
});

function uploadFile(fileObj) {
	var data = new FormData();
	data.append("file", fileObj);
	console.log(data);

	$.ajax({
		url: baseURL + '/file/upload',
		data: data,
		cache: false,
		contentType: false,
		processData: false,
		type: "POST",
		success: fileUploadSuccess,
		error: function(err) {
			console.log(err);
		}
	});
}

function fileUploadSuccess(data) {
	// log response object
	console.log(data);
	// add file contentUrl to array
	fileContentURLs.push(data.simpleAPIResponse.contentUrl);
	// increment upload count
	fileUploadCount++;

	// for karst, upload 2 files
	if(fileUploadCount == 2) {
		fileUploadCount = 0;
		console.log("2 files uploaded!");

		submitJob(	$('#numProc').val(), 
				$('#email').val(), 
				$('#numNodes').val(), 
				$('#wallTime').val(), 
				$('#jobName').val(),
				$("#application").val(),
				$("#machine").val());
	}
}

function getApplicationList() {
	// api call to get application list
	$.get( baseURL + "/application", applicationGetSuccess);
}

function applicationGetSuccess(response) {
	var appID = response.applicationListResponse.applicationList.appID;
	var appName = response.applicationListResponse.applicationList.appName;
	var appInputList = response.applicationListResponse.applicationList.appInputs;
	
	var appSelectHTML = "<option value='" + appID + "'> " + appName + "</option>";
	
	// set the application list on UI
	$("#experimentSelect select").html(appSelectHTML);
	
	var appInputHTML = "";
	
	// display app-input for each application
	$.each(appInputList, function(i, item) {
		appInputHTML += "<label for='inputFile"+ (i+1) + "'>" + item.input + "</label>"
					+ "<input type='file' id='inputFile"+ (i+1) + "' required>"
					+ "<p class='help-block'>" + item.description + "</p>";
	});
	
	// set the application input on UI
	$("#appInputDiv").html(appInputHTML);
}
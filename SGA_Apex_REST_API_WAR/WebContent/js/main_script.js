$(document).ready(function() {
	console.log("document ready!");

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
					$('#jobName').val());
	}
}
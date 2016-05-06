
jQuery(document).ready(function() {
	
	// base url
	var baseURL = "http://apex-api-host:8080/SGA_Apex/sga/rest";
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/backgrounds/1.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    });
    
    $('#registerBtn').on('click', function(e) {
    	// validate input textboxes
		if(validateForm() != true) {
			return;
		}
		
		// Construct JSON
		var jsonData = new Object();
		var userRequest = new Object();
		userRequest.userName = $('#form-username').val();
		userRequest.password = $('#form-password').val();
		
		jsonData.user = userRequest;
		console.log(JSON.stringify(jsonData));
		
		var username = $.jStorage.get('username', 'admin');
		var password = $.jStorage.get('password', 'apex123');

		
		// register user api
		$.ajax({
			type: "POST",
			url: baseURL + "/user/register",
			headers: {
				'Content-type': "application/json"
			},
			beforeSend: function(xhr){
				xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
			},
			data: JSON.stringify(jsonData),
			success: registerUserSuccess,
			error: apiErrorResponse,
			dataType: "json"
		});
	});
    
    $("#loginBtn").on('click', function() {
		// cache locally
		$.jStorage.set('username', $('#j_username').val());
		$.jStorage.set('password', $('#j_password').val());
	});
});

/**
 * Handler function for register user API
 * @param response
 */
function registerUserSuccess(response) {
	var htmlString = "<p>" + response.simpleAPIResponse.message + "</p>";

	// show the response on modal
	$("#userRegisterModal .modal-body").html(htmlString);
	$('#userRegisterModal').modal('show');
}

/**
 * Handler function for API error
 * @param response
 */
function apiErrorResponse(response) {
	response = response.responseJSON;
	var htmlString = "<p>" + response.apiErrorResponse.message + "</p>";

	// show the response on modal
	$("#userRegisterModal .modal-body").html(htmlString);
	$('#userRegisterModal').modal('show');
}

function validateForm() {
	var uName = false, pass = false;
	if( $('#form-username').val() == "" ) {
		$('#form-username').addClass('input-error');
	}
	else {
		$('#form-username').removeClass('input-error');
		uName = true;
	}
	if( $('#form-password').val() == "" ) {
		$('#form-password').addClass('input-error');
	}
	else {
		$('#form-password').removeClass('input-error');
		pass = true;
	}
	
	// return boolean
	return (uName && pass);
}

/**
 * This method uses AJAX for logging out an existing valid user. After response
 * comes from the server, it redirects the control to the login page.
 */
function logout() {

	var xmlHttp = null;

	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "logout";

	xmlHttp.open("GET", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {

		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

			var res = xmlHttp.responseText;

			window.location.href = "login.jsp";

		}
	};
}

/**
 * 
 * @returns {Boolean}
 * 
 * This method is meant to validate the data entered by the user. It generates
 * error message if any data violates the requireemnt.
 */
function validate() {
	var validated = true;

	var fromE = document.getElementById("from");
	var bE = document.getElementById("benef");
	
	var aE = document.getElementById("amount");
	var dE = document.getElementById("detail");

	if (fromE.value == "select") {
		var fError = document.getElementById("fError");
		fError.innerHTML = "Select a suitable value";
		validated = false;
	}
	if (bE.value == "") {
		var bError = document.getElementById("bError");
		bError.innerHTML = "Select a suitable value";
		validated = false;
	}
	if (aE.value == "") {
		var error = document.getElementById("aError");
		error.innerHTML = "Can't be empty";
		validated = false;
	}
	
	if (aE.value != "" && isNaN(aE.value)) {
		var error = document.getElementById("aError");
		error.innerHTML = "Should be numeric";
		aE.select();
		validated = false;
	}
	if (aE.value != "" && aE.value <=0) {
		var error = document.getElementById("aError");
		error.innerHTML = "Should be numeric";
		aE.select();
		validated = false;
	}
	if (dE.value == "") {

		var error = document.getElementById("dError");
		error.innerHTML = "Can't be empty";
		validated = false;
	}

	return validated;
}

/**
 * This method initializes the elements of HTML form.
 */
function initialize() {
	var fError = document.getElementById("fError");
	fError.innerHTML = "";
	var bError = document.getElementById("bError");
	bError.innerHTML = "";
	var dError = document.getElementById("dError");
	dError.innerHTML = "";
	var error = document.getElementById("aError");
	error.innerHTML = "";
}

/**
 * 
 * @param e
 * @returns {Boolean}
 * 
 * This method fetches account number for a given name. It uses AJAX to contact
 * to server.
 */
function fetchAccountNumber(e) {
	var name = e.value;

	var xmlHttp = null;

	var el = document.getElementById("msgAjax");

	if (name == "select") {
		mE.innerHTML = "";
		return false;
	}
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "getAccountNumber?name=" + name;

	xmlHttp.open("GET", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {

		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

			var res = xmlHttp.responseText;
			el.innerHTML = res;
		}
	};

}
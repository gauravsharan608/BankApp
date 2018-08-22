/**
 * This methhod resets the elements conatianing messages on the HTML file
 */
function initialize() {

	var uError = document.getElementById("idError");
	var pError = document.getElementById("pwdError");

	uError.innerHTML = "";
	pError.innerHTML = "";

	var element1 = document.getElementById("msg1");
	element1.innerHTML = "";
}
/**
 * 
 * @returns {Boolean}
 * 
 * This method validates the credentials enterd by the user to login, so taht
 * null or abrupt value doesn't goto the server
 */
function validate() {
	var validated = true;
	var uE = document.getElementById("userid");
	var pE = document.getElementById("password");
	var uError = document.getElementById("idError");
	var pError = document.getElementById("pwdError");
	var id = uE.value;
	var password = pE.value;
	if (id == "") {
		uError.innerHTML = "User ID can't be empty";
		validated = false;
	}
	if (password == "") {
		pError.innerHTML = "password can't be blank";
		validated = false;
	}
	return validated;
}

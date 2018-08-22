/**
 * This method is meant to initialize the elements on the HTML file
 */
function initialize() {
	var msgE = document.getElementById("report");
	var errordate = document.getElementById("dateError");
	errordate.innerHTML = "";
	msgE.innerHTML = "";
}

/**
 * 
 * @param e
 * @returns {Boolean}
 * 
 * This method uses AJAX to contact server to fetch transaction report for a
 * specific date.
 */
function showReport(e) {
	var xmlHttp = null;

	var date = document.getElementById("date").value;
	
	var msgE = document.getElementById("report");

	var errordate = document.getElementById("dateError");
	if (date == "") {

		errordate.innerHTML = "Date shouldn't be empty";
		return false;
	}
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "viewDetail?id=" + date;

	xmlHttp.open("GET", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {

		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

			var res = xmlHttp.responseText;

			if (res.charAt(0) == 'D') {
				errordate.innerHTML = res;
				return false;
			} else {
				msgE.innerHTML = res;
				return true;
			}
		}
	};

}
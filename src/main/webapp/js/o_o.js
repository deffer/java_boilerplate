(function () {

	var is_IE = function(){
		var myNav = navigator.userAgent.toLowerCase();
		return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	};

	var errorInIE = function(message, url, linenumber) {
		var errorbox = document.createElement('div');
		errorbox.className = 'fancyerror';
		errorbox.innerHTML = 'JS: <span class="errmsg"' +
			message.replace('<', '&lt;').replace('>', '&gt;') +
			'</span><br>line number: ' + linenumber +
			'<br>located: ' + url;
		document.body.appendChild(errorbox);
	};

	var reportError = function(message, file, line, column, errorObj) {
		var ie = is_IE();
		if (ie && ie < 9){	// cant console.log()
			errorInIE(message, file, line);
			return true;
		}else{
			console.log(message);
			if (file || line || column) {
				console.log("at: " + file + ":" + line + ":" + column);
			}
			if (errorObj !== undefined) //so it won't blow up in the rest of the browsers
				console.log('Error: ' + errorObj.stack);
			return false;
		}
	};

	var sendError = function(message, file, line, column, errorObj){
		console.log("Sending error");
		if (window.XMLHttpRequest) {
			console.log("Using XMLHttpRequest");
			var xhr = new XMLHttpRequest();
			var scripturl = '/reportjserror/log';
			xhr.open('POST', scripturl);
			xhr.setRequestHeader('Content-Type', 'text/plain;charset=UTF-8');
			var stack = null;
			if (errorObj) stack = errorObj.stack;
			xhr.send(JSON.stringify({message: message, file:file, line:line, column:column, errorObj:stack}));
		}
		return true;
	};

	window.onerror = sendError;
})();

var OOO = OOO || {};
OOO.makeError = function () {
	var a;
	a.u = "5"
};

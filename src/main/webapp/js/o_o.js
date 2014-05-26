(function () {

	/*
	 * Set up console if browser doesnt support it
	 * (taken from twitter's source code)
	 */

	var noop = function () {};
	var methods = [
		'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
		'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
		'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
		'timeStamp', 'trace', 'warn'
	];
	var length = methods.length;
	var console = (window.console = window.console || {});

	var method;
	while (length--) {
		method = methods[length];

		// Only stub undefined methods.
		if (!console[method]) {
			console[method] = noop;
		}
	}

	var devMode = true;
	if (devMode && console['log']===noop){
		console['log'] = function(message){
			var errorbox = document.createElement('div');
			errorbox.className = 'fancyerror';
			errorbox.innerHTML = 'JS: <span class="errmsg" >' + message.replace('<', '&lt;').replace('>', '&gt;') + '</span>';
			document.body.appendChild(errorbox);
		}
	}


	/*
	 * Functions here
	 */

	var is_IE = function(){
		var myNav = navigator.userAgent.toLowerCase();
		return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	};

	var reportError = function(message, file, line, column, errorObj) {
		var msg = message;
		if (file || line || column) {
			msg += "\nat: " + file + ":" + line + ":" + column;
		}
		if (errorObj !== undefined) //so it won't blow up in the rest of the browsers
			msg += '\nStacktrace:\n' + errorObj.stack;

		console.log(msg);
		return false;
	};

	var sendError = function(message, file, line, column, errorObj){
		//console.log("Sending error");
		if (window.XMLHttpRequest) {
			//console.log("Using XMLHttpRequest");
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

	//window.onerror = sendError;
	window.onerror = reportError;
})();

var OOO = OOO || {};
OOO.makeError = function () {
	var a;
	a.u = "5";
};

OOO.sendError = function (){
	try {
		OOO.makeError();
	}catch (e){
		if (window.XMLHttpRequest) {
			var xhr = new XMLHttpRequest();
			var scripturl = '/reportjserror/log';
			xhr.open('POST', scripturl);
			xhr.setRequestHeader('Content-Type', 'text/plain;charset=UTF-8');
			var bdy = JSON.stringify({message: e.message, file:"unknown", line:0, column:0, errorObj: e.stack});
			xhr.send(bdy);
		}
	}
};

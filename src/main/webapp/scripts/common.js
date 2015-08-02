var request = null;
var updatePanelIds = [];

function createRequest() {
	try {
		request = new XMLHttpRequest();
	} catch (trymicrosoft) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (othermicrosoft) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				alert("Error initializing XMLHttpRequest!");
			}
		}
	}
}

function __doPostBack(eventTarget, eventArgument) {
	var theForm = document.forms['cmmForm'];
	if (!theForm) {
		theForm = document.cmmForm;
	}
	if (!theForm.onsubmit || (theForm.onsubmit() !== false)) {
		theForm.EVENTTARGET.value = eventTarget.id;
		theForm.EVENTARGUMENT.value = eventArgument;
		var parentObj = eventTarget.parentNode;
		while (parentObj && parentObj.id != "cmmForm") {
			if (updatePanelIds.indexOf(parentObj.id)) {
				_ajaxSubmitForm(theForm, parentObj.id);
				return;
			}
			parentObj = parentObj.parentNode;
		}
		theForm.submit();
	}
}

function _ajaxSubmitForm(form, updatePanelId) {
	var elements = form.elements;// Enumeration the form elements
	var i;
	var postContent = "forUpdate=" + updatePanelId + "&";// Form contents need to submit
	for (i=0; i < elements.length; i++) {
		var element = elements[i];
		if(element.type == "text" || element.type == "textarea" || element.type == "hidden") {
			postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(element.value) + "&";
		}
		else if (element.type == "select-one" || element.type == "select-multiple") {
			var options = element.options;
			for(var j=0; j < options.length; j++){
				var item = options[j];
				if (item.selected) {
					postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(item.value) + "&";
				}
			}
		} else if (element.type == "checkbox" || element.type == "radio") {
			if (element.checked) {
				postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(element.value) + "&";
			}
		} else if (element.type == "file") {
			if (element.value != "") {
				postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(element.value) + "&";
			}
		} else {
			postContent += encodeURIComponent(element.name) + "=" + encodeURIComponent(element.value) + "&";
		}
	}
	//alert(postContent);
	_ajaxSubmit(form.action, form.method, postContent);
}

function _ajaxSubmit(url, method, postContent)            {
	//var loadingDiv = document.getElementById('loading');

	//window.setTimeout(function () {
	//		loadingDiv.innerText = "Loading.";
	//		loadingDiv.style.display = "";
	//	}, 1000);
	// code for Mozilla, etc.
	createRequest();
	if (request) {
		request.onreadystatechange = function() {
				// if xmlhttp shows "loaded"
				if (request.readyState==4) {
					if (request.status == 200) {
						//var result = document.createElement("DIV");
						//result.style.border="1px solid #363636";
						//result.innerHTML = request.responseText;
						//document.body.appendChild(result);
						var result = request.responseText;
						var json = JSON.parse(result);
						document.getElementById(json.updatePanelId).innerHTML = json.updateContent;
					}
				}
				//loadingDiv.innerHTML = "Submit finnished!";
			};
		if(method.toLowerCase() == "get") {
			request.open("GET", url + "?" + postContent, true);
			request.send(null);
		}
		else if(method.toLowerCase() == "post") {
			request.open("POST", url, true);
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//application/x-www-form-urlencoded
			request.send(postContent);
		}
	} else {
		//loadingDiv.innerHTML = "Can't create XMLHttpRequest object, please check your web browser.";
	}
}
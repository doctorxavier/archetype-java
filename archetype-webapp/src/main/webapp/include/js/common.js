window.onresize = function() {
	var element = document.getElementById('status');
	if(element != null)
	{
		element.style.height = document.documentElement.clientHeight + "px";
		element.style.width = document.documentElement.clientWidth + "px";
		
		element = document.getElementById('statusFrame');
		element.style.height = document.documentElement.clientHeight + "px";
		element.style.width = document.documentElement.clientWidth + "px";
	}
};

window.onscroll = function() {
	var element = document.getElementById('status');
	if(element != null)
	{
		element.style.top = document.documentElement.scrollTop + "px";
		element.style.left = document.documentElement.scrollLeft + "px";
		
		element = document.getElementById('statusFrame');
		element.style.top = document.documentElement.scrollTop + "px";
		element.style.left = document.documentElement.scrollLeft + "px";
	}
};

function init() {
	var name = arguments[0];
	var name2;
	if(name != undefined && name != ''){
		name2 = nombre + 'status';
	}else{
		name2	= 'status';
	}
	var element = document.getElementById(name2);
	if(element != null)
	{
		element.style.height = document.documentElement.clientHeight + "px";
		element.style.width = document.documentElement.clientWidth + "px";
		if(name != undefined && name != ''){
			name2 = nombre + 'statusFrame';
		}else{
			name2	= 'statusFrame';
		}
		element = document.getElementById(name2);
		element.style.height = document.documentElement.clientHeight + "px";
		element.style.width = document.documentElement.clientWidth + "px";
	}
}
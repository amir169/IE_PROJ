
//this jquery code below help us to change the selected and active elements who sho user wich tab he is there now
// and inside this jquery code we call js method who change the content of html desired div
$(document).ready(function(){
	$("#cmpt-list-selector-sidebar").click(function(){
		$(this).addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#cmpt-list-selector-nav").addClass("active").siblings().removeClass("active");
		$.get("htmlok/game-introducer-panel.html",function(data){
			for (var i = 5; i >= 0; i--) {
			$("#main-content").append(data);
		}
		});
});

	$("#cmpt-list-selector-nav").click(function(){
		$("#cmpt-list-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$(this).addClass("active").siblings().removeClass("active");
		$.get("htmlok/game-introducer-panel.html",function(data){
			for (var i = 5; i >= 0; i--) {
			$("#main-content").append(data);
		}
		});
	});
});

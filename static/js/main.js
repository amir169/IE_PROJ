
//this jquery code below help us to change the selected and active elements who sho user wich tab he is there now
// and inside this jquery code we call js method who change the content of html desired div
$(document).ready(function(){
	function cmptListSelect(){
		//we must change page apperance wich done below
		$("#cmpt-list-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#cmpt-list-selector-nav").addClass("active").siblings().removeClass("active");
		
		// then remove main content and add static element
		$("#main-content").empty();
		$("#main-content").load("htmlok/game-introducer.html");

		// and final step is to load dynamic element wich could be done in 2 way
		// 1. bulid whole contetn on server(with sending our desired data with post method and get whole page in response)
		// 2. get data and schema sepratly and mix in the client
		// but for now i just get schema with get method and fill the page KHIARlly
		$.get("htmlok/game-introducer-panel.html",function(data){
			for (var i = 5; i >= 0; i--) {
				$("#main-content").append(data);
			}
		});
	}
	$("#cmpt-list-selector-sidebar").click(cmptListSelect);
	$("#cmpt-list-selector-nav").click(cmptListSelect);


	function recordsListSelect(){
		$("#records-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#records-selector-nav").addClass("active").siblings().removeClass("active");

		$("#main-content").empty();
		$("#main-content").load("htmlok/total-records.html");
	}
	$("#records-selector-sidebar").click(recordsListSelect);
	$("#records-selector-nav").click(recordsListSelect);

	function userTeamsSelect(){
		$("#user-teams-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#user-teams-nav").addClass("active").siblings().removeClass("active");

		$("#main-content").empty();
		$("#main-content").load("htmlok/user-teams-panel.html");
	}
	$("#user-teams-sidebar").click(userTeamsSelect);
	$("#user-teams-nav").click(userTeamsSelect);

	function mainAnnouncmentSelect(){
		$("#main-announcment-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#main-announcment-nav").addClass("active").siblings().removeClass("active");

		$("#main-content").empty();
		//$("#main-content").load("htmlok/main-announcment.html");
	}
	$("#main-announcment-sidebar").click(mainAnnouncmentSelect);
	$("#main-announcment-nav").click(mainAnnouncmentSelect);

});
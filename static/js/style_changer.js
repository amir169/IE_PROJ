
//this jquery code below help us to change the selected and active elements who sho user wich tab he is there now
// and inside this jquery code we call js method who change the content of html desired div
$(document).ready(function(){
	function cmptListSelect(){
		//we must change page apperance wich done below
		$("#cmpt-list-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#cmpt-list-selector-nav").addClass("active").siblings().removeClass("active");
	}

	function recordsListSelect(){
		$("#records-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#records-selector-nav").addClass("active").siblings().removeClass("active");
	}

	function userTeamsSelect(){
		$("#user-teams-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#user-teams-nav").addClass("active").siblings().removeClass("active");
	}

	function mainAnnouncmentSelect(){
		$("#main-announcment-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#main-announcment-nav").addClass("active").siblings().removeClass("active");
	}
});
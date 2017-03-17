
//this jquery code below help us to change the selected and active elements who sho user wich tab he is there now
// and inside this jquery code we call js method who change the content of html desired div
$(document).ready(function(){
	$("#cmpt-list-selector-sidebar").click(function(){
		$(this).addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$("#cmpt-list-selector-nav").addClass("active").siblings().removeClass("active");
		changeContentOnCompListSelect();
	});

	$("#cmpt-list-selector-nav").click(function(){
		$("#cmpt-list-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
		$(this).addClass("active").siblings().removeClass("active");
		changeContentOnCompListSelect();
	});
});

function changeContentOnCompListSelect() {
	var mainContent = document.getElementById('main-content');
	//here we should get game info from server but for now i just make a random
	//and also get specefic div from htmlok folder with ajax and command below
	//$.get("htmlok/game-introducer.html",function(data){
	//});
	//and change it's value but also here i get it from another function
	var mainContentValue = getStaicHtmlok();
	for (var i = 5; i > 0; i--) {
		mainContentValue+=getHtmlokPanel(i);
	}
	mainContent.innerHTML=mainContentValue;
}


//codes below are PASHM u have not to read it 
// just know they are temp method who return us the html of the page 
function getHtmlokPanel(i){
	var ret="<div class=\"col-md-6 col-sm-12\" id=\"game-intoducer\">"+
  "<div class=\"panel panel-default\">"+
    "<div class=\"panel-body\">"+
      "<div class=\"col-md-6 col-xs-6\">"+
      "<h4 class=\" page-header\" style=\" margin-top:0px; margin-right:-10px;\"><span id=\"compt-name\">مسابقه "+
      i+
      "</span></h4>"+
      "<span id=\"other-info\">"+
        "برگزار کننده: دانشگاه شهید بهشتی<br/>"+
        "تیمی: 3 نفره<br/>"+
        "زمان: نامحدود<br/>"+
        "مکان: آنلاین<br/>"+
        "هزینه: تیمی 100 هزار تومان"+
      "</span>"+
      "</div>"+
      "<div class=\"col-md-6 col-xs-6\">"+ 
          "<img src=\"elements/Game_Logo.png\" class=\"img-rounded\" style=\" max-width:  100% ; max-height:  auto\"  >"+
      "</div>"+  
    "</div>"+
    "<div class=\"panel-footer\">"+
        "<button class=\"btn btn-primary\">شرکت در مسابقه</button>"+
        "<button class=\"btn btn-link\">صورت سوال</button>"+
    "</div>"+
  "</div> <!-- panel -->"+
"</div>";

	return ret;
}
function getStaicHtmlok() {
	return "<h3 class=\"page-header\" style=\" margin-top:0px\"  > لیست مسابقلات</h3>";
}

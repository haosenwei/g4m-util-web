function getMenu(data){
	var menuHtml="<li class=\"\">"+
					"<a href=\"home\">"+
					"	<i class=\"menu-icon fa fa-tachometer\"></i>"+
					"	<span class=\"menu-text\"> 主页 </span>"+
					"</a>"+
					"<b class=\"arrow\"></b>"+
				"</li>";
	menuHtml+=getMenuIteration(data);
	return menuHtml;
}
function getMenuIteration(data){
	var html="";
	/*
	<li class=\"\">
		<a href=\"#\" class=\"dropdown-toggle\">
			<i class=\"menu-icon fa fa-desktop\"></i>
			<span class=\"menu-text\">
				UI &amp; Elements
			</span>
	
			<b class=\"arrow fa fa-angle-down\"></b>
		</a>
	
		<b class=\"arrow\"></b>
		<ul class=\"submenu\">
			<li class=\"\">
				<a data-url=\"sys/table/main\" href=\"#sys/table/main\">
					<i class=\"menu-icon fa fa-caret-right\"></i>
					Elements
				</a>
	
				<b class=\"arrow\"></b>
			</li>
		</ul>
	</li>*/
	

	$.each(data, function(idx, obj) {
		//判断是否是模块
		if(obj.typeDic==1){
			//模块只加下拉
			html+="<li class=\"\">"+
			"<a href=\"#\" class=\"dropdown-toggle\">"+
				"<i class=\"menu-icon fa "+obj.icon+"\"></i>"+
				"<span class=\"menu-text\">"+
					obj.name+
				"</span>"+
				"<b class=\"arrow fa fa-angle-down\"></b>"+
			"</a>"+
			"<b class=\"arrow\"></b>"+
			"<ul class=\"submenu\">";
			html+=getMenuIteration(obj.list);
			html+="</ul>";
			html+="</li>";
		}
		//判断是否是菜单
		if(obj.typeDic==2){
			//菜单添加地址
			html+="<li class=\"\">"+
					"<a data-url=\""+obj.url+"\" href=\"#"+obj.url+"\">"+
					"<i class=\"menu-icon fa "+obj.icon+"\"></i>"+
					"<span class=\"menu-text\">"+obj.name+"</span>"+
					"</a>"+
				"</li>";
		}
	});
	return html;
}
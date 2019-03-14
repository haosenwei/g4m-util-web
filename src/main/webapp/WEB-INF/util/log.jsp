<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Log</title>
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="row clearfix" id="select">
			<div class="col-md-12 column">
				<form class="form-inline">
					<div class="form-group">
						<label for="ip">ip</label>
						<input type="text" class="form-control" id="ip" placeholder="ip">
					</div>
					<div class="form-group">
						<label for="port">端口</label>
						<input type="text" class="form-control" id="port" placeholder="端口">
					</div>
					<div class="form-group">
						<label for="key">关键字</label>
						<input type="text" class="form-control" id="key" placeholder="关键字">
					</div>
					<div class="checkbox">
						<label>
							<input id="rolling" type="checkbox">滚屏
						</label>
					</div>
					<button type="reset" class="btn btn-default">清空</button>
				</form>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column" id="log-container"
				style="height: 450px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
			</div>
		</div>
	</div>

	<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function() {
			var height = $(window).height() - $("#select").height();
			$("#log-container").css("height", height);
			var domain = window.location.host;
			var protocol = window.location.protocol;
			if(protocol=='https:'){
				protocol = s;
			}else{
				protocol = '';
			}
			var websocket = new WebSocket('ws'+protocol+'://' + domain + '/loglog');
			websocket.onmessage = function(event) {
				$("#log-container").append(event.data + "<br>");
				$("#log-container").scrollTop($("#log-container")[0].scrollHeight);
			};
		});
	</script>
</body>
</html>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Log</title>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
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
						<label for="inputPassword2" class="sr-only">Password</label>
						<select class="form-control">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<button type="submit" class="btn btn-default">Confirm identity</button>
				</form>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column" id="log-container" style="height: 450px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
			</div>
		</div>
	</div>

	<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

	<script>
	    $(document).ready(function() {
			var height = $(window).height()-$("#select").height();
	    	$("#log-container").css("height",height); 
	        var websocket = new WebSocket('ws://8080.b.bangmc.vip/loglog');
	        websocket.onmessage = function(event) {
	            $("#log-container").append(event.data);
	            $("#log-container").scrollTop($("#log-container")[0].scrollHeight);
	        };
	    });
	</script>
</body>
</html>


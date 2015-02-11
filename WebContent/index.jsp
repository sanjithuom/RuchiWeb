<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ruchi</title>
<!-- CSS -->
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/stylesheet-pure-css.css">
<link rel="stylesheet" href="css/examples.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">

<!-- JS -->
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src='js/hogan.js'></script>
<script src="js/jquery.typeahead.js"></script>
<script src="js/jquery-ui.js"></script>

<script type="text/javascript" class="init">
	$(document).ready(function() {
		$('#inputText').hide();
		$('#error_dialog').hide();
		$('#search').hide();
		$.getJSON("CityServlet", function(result) {
			$('#cityText').typeahead(result);
		});

		$("input[name='option']").click(function() {
			if ($('input:radio[name=option]:checked').val() == "restaurant") {
				$('#inputText').show();
				$('#search').show();
				$('#inputText').attr('placeholder', 'Restaurant');
			} else if ($('input:radio[name=option]:checked').val() == "food") {
				$('#inputText').show();
				$('#search').show();
				$('#inputText').attr('placeholder', 'Food');
			}
		});
		$("#searchForm").submit(function(event) {
			event.preventDefault();
			$('#table_content').html("");
			var option = $('input:radio[name=option]:checked').val();
			var city = $('#cityText').val();
			var search = $('#inputText').val();
			$.get("RuchiServlet", {
				city : city,
				option : option,
				search : search
			}).done(function(data) {
				var msg = data.substring(0, 5);
				if (msg == 'Error') {
					$('#error_dialog').show();
					$('#error_dialog').html(data);
					$("#error_dialog").dialog({
						modal : true,
						buttons : {
							Close : function() {
								$(this).dialog("close");
							}
						}
					});
				} else {
					$('#table_content').html(data);
					$('#rest_table').dataTable({
						"order" : [ [ 1, "desc" ] ],
						"scrollY" : 270,
						"paging" : false
					});
				}
			});
		});

	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<form id="searchForm" class="basic-grey" action="RuchiServlet"
				method="get">
				<input id="cityText" class="cities typeahead" type="text"
					placeholder="Cities"> <br> <input type="radio"
					name="option" value="restaurant" id="restaurant"> <label
					for="restaurant">Search by Restaurant</label> <input type="radio"
					name="option" value="food" id="food"> <label for="food">Search
					by Food</label> <br> <input id="inputText" class="typeahead"
					type="text" autocomplete="off"> <br> <input
					class="submit" id="search" value="Search" type="submit">
			</form>


		</div>
		<br>
		<div id="table_content"></div>
		<div id="error_dialog" title="Error"></div>
	</div>
</body>
</html>
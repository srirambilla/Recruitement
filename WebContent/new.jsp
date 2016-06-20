<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>


<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>


<style>
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

<
style>.row.content {
	height: 450px
}

.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

#last {
	color: red;
}
</style>
<style type="text/css">
td {
	padding: 5px 20px;
}

#params {
	display: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('.x').click(function() {

			var value = $(this).val();
			if ((this).checked) {

				$("." + value + "-param").show();
			} else {

				$("." + value + "-param").hide();
			}

		});
	});
</script>



<script type="text/javascript">
	$(document).ready(function() {
		$(".y").click(function() {
			var valuee = $(this).val();
			if ((this).checked) {
				$("#" + valuee + "-names").each(function() {
					$(this).removeAttr("disabled");

				});
			} else {
				$("#" + valuee + "-names").each(function() {
					$(this).prop("disabled", true);
				});

			}

		});
	});
</script>


<script>
	$(document).ready(function() {

		$('.z').keyup(function() {
			window.sum = 0;
			$('.z').each(function() {
				sum += Number($(this).val());

			});

			$('#weightagee').html("total weightage :" + sum + "  out of 100");
		});

	});
</script>
<script type="text/javascript">
	function submitform() {
		var title = $("#title").val();
		var desc = $("#description").val();

		if (sum < 100) {
			window.alert("weightage is less than 100..It must be equal to 100");
			return false;
		} else if (sum > 100) {
			window
					.alert("weightage is greater than 100..It must be equal to 100");
			return false;
		} else {
			return true;
		}
	}
</script>



</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Logo</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Projects</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-8 text-left">
				<h1>Post New Job</h1>
				<hr>
				<form action="JobController" method="post" role="form"
					onsubmit="return submitform()">

					<label>Job Title: </label> <input type="text" name="jobTitle"
						id="title" class="form-control" required="required"><span
						id="titlee"></span> <br> <label>Job Description: </label>
					<textarea rows="5" class="form-control" name="jobDescription"
						id="description" required="required"></textarea>
					<br> <label>Last Date: </label><input type="date"
						name="lastDate" required="required"> <br>
					<c:if test="${roundsList.size() gt 0 }">
						<label>Rounds:</label>
						<br>
						<c:forEach var="round" items="${roundsList}">
							<input type="checkbox" class="x" value="${round.roundId}"
								id="checkss" name="rounds">
							<label>${round.name}</label>
							<br>
							<div class="${round.roundId}-param" id="params"
								name="${round.name}-paramm">
								<c:forEach var="parameter" items="${round.parameterSet}">
									<input type="checkbox" name="${round.roundId}-names" class="y"
										id="${round.roundId}-names" value="${parameter.parameterId}">
									<label> ${parameter.name}</label>
									<input type="number" id="${parameter.parameterId}-names"
										min="1" class="z" onkeydown="return myFunction();"
										disabled="disabled" name="${parameter.parameterId}-names" />
									<br>
								</c:forEach>
							</div>
						</c:forEach>
					</c:if>
					<input type="submit" value="Submit">
					<div id="last"></div>

				</form>
			</div>
		</div>

	</div>
	<footer class="container-fluid text-center">
		<p>Footer Text</p>
	</footer>

</body>
</html>

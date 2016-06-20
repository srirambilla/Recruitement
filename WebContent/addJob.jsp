<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html lang="en">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="myStyle.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<style>
#params {
	display: none;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('.x').click(function() {

			var value = $(this).val();
			$("." + value + "-param").show();

		});

	});
	
	
});
	
</script>



<head>

</head>
<body>



	<form action="JobController" method="post">

		<input type="text" name="jobTitle"> <br>
		<textarea rows="10" cols="10" name="jobDescription"></textarea>
		<br> <input type="date" name="lastDate"> <br>

		<c:if test="${roundsList.size() gt 0 }">


			<c:forEach var="round" items="${roundsList}">

				<input type="checkbox" class="x" value="${round.roundId}"
					name="rounds">${round.name}
			<br>


				<div class="${round.roundId}-param" id="params"
					name="${round.name}-paramm">

					<c:forEach var="parameter" items="${round.parameterSet}">

						<input type="checkbox" name="${round.roundId}-names"
							id="${round.name}-names" value="${parameter.parameterId}">${parameter.name}
					<input type="text" id="textboxes" name="${parameter.parameterId}-names" />
						<br>

					</c:forEach>
				</div>


			</c:forEach>


		</c:if>
		<input type="submit" value="Submit">
	</form>


</body>
</html>
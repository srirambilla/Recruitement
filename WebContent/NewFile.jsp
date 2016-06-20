<html>
<body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#"+${round.name}+"-names").click(function () {
            if ($(this).is(":checked")) {
                $("#params").removeAttr("disabled");
                $("#params").focus();
            } else {
                $("#params").attr("disabled", "disabled");
            }
        });
    });
</script>
<label for="chkPassport">
    <input type="checkbox" id="chkPassport" />
    Do you have Passport?
</label>
<br />
Passport Number:
<input type="text" id="txtPassportNumber" disabled="disabled" />
 
 
Screenshot


</body>

</html>
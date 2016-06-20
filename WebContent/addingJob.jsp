<html>
<head>



</head>
<body>
<script>
function initAllButtons(variable, length)
{
   for(c = 0; c < length; c++)
   {
      clicks.push(true);
   }


   for(i = 0; i < length; ++i)
   {
      var label = document.createElement("label");
      var checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.checked = true;
      checkbox.value = btns[i];
      checkbox.class = "colorCoder";
      label.appendChild(checkbox);

      document.getElementById('Buttons').appendChild(checkbox);

      $('#Buttons').on('click', function(){updateData(i,clicks);});
   }
}
</script>
</body>

</html>
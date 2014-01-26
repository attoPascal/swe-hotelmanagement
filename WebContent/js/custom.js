$.ajaxSetup ({
    cache: false
});

$(document).ready(function() {
	$(document).on("change", "select.set-kategorie", function() {
		$.get(
			"ZimmerServlet",
			{
				action: "set",
				zimmer: $(this).attr("data-zimmer"),
				kategorie: $(this).val(),
				hotel: $("select.set-hotel").val()
			},
			function(response) {
				$("#response").text(response).slideDown().delay(2000).slideUp();
			}
		);
	});
	
	$("select.set-hotel").change(function() {
		$("#manage").load(
			"?hotel=" + $(this).val() +" #manage");
	});
	
	$("#response").hide();
});
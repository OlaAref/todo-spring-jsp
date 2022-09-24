$(document).ready(function(){
	
});



function completedTask(input){

	var id = input.getAttribute('data-id');
    if (input.checked) {
		updateCompletedStatus(true, id);
    } 
    else {
		updateCompletedStatus(false, id);
    }
}


function updateCompletedStatus(status, id){
	
	var url = id + "/completed/" + status;
	
	$.get(url, function(updateMessage) {
		$("#toast-message").text(updateMessage);
		$("#primaryToast").toast('show');
	})
	.done(function() {
		
	})
	.fail(function() {
		
	});
	
	return false;
}

function showModal(title, message, button){
	$("#modalLabel").text(title);
	$("#modalMessage").text(message);
	$("#modalButton").text(button);
	$("#confirmModal").modal('show');
}











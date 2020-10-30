var edit_mode = true;

function EditProfileTOGG(){
	console.log("EDIT_PROFILE_TOGGLED");
	console.log("SWITCHING FROM " + edit_mode + " TO " + !edit_mode);
	
	if(edit_mode){
		$("#profileDetails").css("display", "none");
		$("#profileEditDetails").css("display", "unset");
		edit_mode = false;
	}
	else{
		$("#profileDetails").css("display", "unset");
		$("#profileEditDetails").css("display", "none");
		edit_mode = true;
	}
	
}
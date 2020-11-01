/*
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

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
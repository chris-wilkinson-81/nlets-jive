$j(document).ready(function(){

	var deptField = {selectElemSelector:"select[id*='department']",allValue:"all~profile[department]~"};
	var locationField = {selectElemSelector:"select[id*='location']", allValue:"all~profile[location]~"};
		
	sortProfileFieldOptions(deptField.selectElemSelector, deptField.allValue);
	sortProfileFieldOptions(locationField.selectElemSelector, locationField.allValue);
	
	$j("div.j-browse-people").on("mouseenter",deptField.selectElemSelector,function(){
		//console.log("department filter clicked");
		sortProfileFieldOptions(deptField.selectElemSelector,deptField.allValue);
	});
	
	$j("div.j-browse-people").on("mouseenter",locationField.selectElemSelector,function(){
		//console.log("location filter clicked");
		sortProfileFieldOptions(locationField.selectElemSelector,locationField.allValue);
	});
});

var sortProfileFieldOptions = function(selector, allValue) {
	var selectOptions = $j(selector + ' option');
	if(!$j(selector).hasClass("nlets-sorted")) {
		//console.log("sorting "+selector+" filter options");
		selectOptions.sort(function(a,b) {
			
		    a = a.value;
		    b = b.value;
			
			if ( a < b )
				return -1;
			if ( a > b )
				return 1;
			return 0;
		});
		
		$j(selector).html(selectOptions);
		$j(selector).addClass("nlets-sorted");
		$j(selector).val(allValue);
	} else {
		//console.log("Filter options already sorted, nothing to do");
	}
}

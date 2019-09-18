$().ready(function(){
	var path=$("#path").val();
	configtype = 0;
	$(".modifySystemBtn").click(function(){
		var obj = $(this);
		$("#sid").val(obj.attr("sid"));
		$("#configName").val(obj.attr("sname"));
		configtype = obj.attr("configtype");
		if(configtype == 7 || configtype == 2){
			$("#modifyConfigValue").val(obj.attr("configvalue"));
		}
		if(configtype == 7){
			$("#modifyConfigTypeValue").val(obj.attr("configtypevalue"));
		}
		
		var select;
		if(obj.attr("isstart") == 1){
			select = "<select id='isstartselect'><option value='1' selected='selected'>启用</option><option value='2'>不启用</option></select>";
		}else{
			select = "<select id='isstartselect'><option value='1'>启用</option><option value='2' selected='selected'>不启用</option></select>";
		}
		$("#isstart").html('');
		$("#isstart").html(select);
		$("#addSystemdiv").slideUp(500);
		$("#modifySystemdiv").slideUp(500);
		$("#modifySystemdiv").slideDown(500);
	});
	$("#modifySystemConfigBtn").click(function(){
		var id = $("#sid").val();
		var configType = $.trim($("#modifyConfigType").val());
		var configName = $.trim($("#configName").val());
		var isstart = $("#isstartselect").val();
		var configvalue;
		var configTypevalue;
		var params;
		var ispeat = "nopeat";
		if(configName == '') 
			alert("类型名称不能为空！");
		else{
			$.post(path+"/isPeatConfig",{
				'id':id,
				'configType':configType,
				'configTypeName':configName
			},function(result){
				if("peat" == result){ 
					ispeat = "peat";
					alert("对不起，该类型名称已存在。");
				}else if("nopeat" == result){
					if(configtype == 7 || configtype == 2){
						configvalue = $.trim($("#modifyConfigValue").val());
						if(configvalue == '') 
							alert("类型数值不能为空！");
						else if(configtype == 7 && isInteger(configvalue) == false)
							alert("类型数值应为大于0的正整数！");
						else{
							if(configtype == 7){
								configTypevalue = $.trim($("#modifyConfigTypeValue").val());
								params = {
										'id':id,
										'configTypeName':configName,
										'configValue':configvalue,
										'configTypeValue':configTypevalue,
										'isStart':isstart};
							}else{
								params = {
										'id':id,
										'configTypeName':configName,
										'configValue':configvalue,
										'isStart':isstart};
							}
							if(configTypevalue == '') 
								alert("实际数值不能为空！");
							else if(configtype == 7 && isInteger(configTypevalue) == false)
								alert("实际数值应为大于0的正整数！");
							else{
								$.post(path+"/modifyconfig",params,function(result){
									if("success" == result){
										alert("恭喜您，修改成功。");
										window.location.reload(true);
									}else{
										humane.success("对不起，修改"+configName+"失败.");
									}
									
								},"html");
							}
						}
					}else{
						$.post(path+"/modifyconfig",{
							'id':id,
							'configTypeName':configName,
							'isStart':isstart
						},function(result){
							if("success" == result){
								alert("恭喜您，修改成功。");
								window.location.reload(true);
							}else{
								humane.success("对不起，修改"+configName+"失败.");
							}
							
						},"html");
					}
				}
			},"html");
		}
	});
	$("#cancleModifySystemConfigBtn").click(function(){
		$("#modifySystemdiv").slideUp(500);
	});
	$(".deleteSystemBtn").click(function(){
		var obj = $(this);
		if(confirm("您确定要删除【"+obj.attr("sname")+"】配置吗？")){
			//alert("Obj :" + obj.attr("sid")); deleteconfig
			$.post(path+"/deleteconfig",{
				'id':obj.attr("sid")
			},function(result){
				if("success" == result){
					alert("恭喜您，删除成功。");
					window.location.reload(true);
				}else{
					humane.success("对不起，删除"+configName+"失败.");
				}
				
			},"html");
		}
	});
	$("#addsystemconfig").click(function(){
		$("#modifySystemdiv").slideUp(500);
		$("#addSystemdiv").slideDown(500);
	});
	$("#cancleAddSystemConfigBtn").click(function(){
		$("#addSystemdiv").slideUp(500);
	});
	$("#addSystemConfigBtn").click(function(){
		var configType = $.trim($("#addConfigType").val());
		var configName = $.trim($("#addConfigName").val());
		var isstart = $("#addisStartSelect").val();
		var addParams;
		var ispeat = "nopeat";
		if(configType == '') 
			alert("配置类型不能为空！");
		else if(configName == '') 
			alert("类型名称不能为空！");
		else{
			$.post(path+"/isPeatConfig",{
				'configType':configType,
				'configTypeName':configName
			},function(result){
				if("peat" == result){ 
					ispeat = "peat";
					alert("对不起，该类型名称已存在。");
				}else if("nopeat" == result){
					if(configType == 7 || configType == 2){
						configvalue = $.trim($("#addConfigValue").val());
						if(configvalue == '') 
							alert("类型数值不能为空！");
						else if(configType == 7 && isInteger(configvalue) == false)
							alert("类型数值应为大于0的正整数！");
						else{
							if(configType == 7){
								var configTypevalue = $.trim($("#addConfigTypeValue").val());
								
								addParams = {
											'configType':configType,
											'configTypeName':configName,
											'configValue':configvalue,
											'configTypeValue':configTypevalue,
											'isStart':isstart
										};
							}else{
								addParams = {
										'configType':configType,
										'configTypeName':configName,
										'configValue':configvalue,
										'isStart':isstart
										};
							}
							if(configTypevalue == '') 
								alert("实际数值不能为空！");
							else if(configType == 7 && isInteger(configTypevalue) == false)
								alert("实际数值应为大于0的正整数！");
							else{
								$.post(path+"/addconfig",addParams,function(result){
									if("success" == result){
										alert("恭喜您，添加成功。");
										window.location.reload(true);
									}else{
										humane.success("对不起，失败"+configName+"失败.");
									}
									
								},"html");
							}
						}
					}else{
						$.post(path+"/addconfig",{
							'configType':configType,
							'configTypeName':configName,
							'isStart':isstart
						},function(result){
							if("success" == result){
								alert("恭喜您，添加成功。");
								window.location.reload(true);
							}else{
								humane.success("对不起，失败"+configName+"失败.");
							}
							
						},"html");
					}
				}
			},"html");
		}
		
	});
	
	$("#simpleBtn").click(function(){
		var simpTypeName = $.trim($("#simpTypeName").val());
		var simpConfigValue = $.trim($("#simpConfigValue").val()); 
		var configType = $.trim($("#configType").val()); 
		var simpleId = $("#simpleId").val();
		if(simpTypeName == '') 
			alert("配置名称不能为空！");
		else if(simpConfigValue == '') 
			alert("配置数值不能为空！");
		else if(configType == "4" && isURL(simpConfigValue) == false)
			alert("输入的URL格式不正确，请重新输入！");
		else if(configType == "3" && isInteger(simpConfigValue) == false)
			alert("输入的配置数值应为大于0的正整数！");
		else{
			$.post(path+"/modifyconfig",{
				'id':simpleId,
				'configTypeName':simpTypeName,
				'configValue':simpConfigValue,
				'isStart':1
			},function(result){
				if("success" == result){
					alert("恭喜您，修改成功。");
					window.location.reload(true);
				}else{
					humane.success("对不起，修改"+configName+"失败.");
				}
				
			},"html");
		}
	});
	mover(5);
});

function isInteger(simpConfigValue){
	var strRegex = "^[0-9]*[1-9][0-9]*$"; //正整数
	var re = new RegExp(strRegex);
	if (re.test(simpConfigValue))
		return true;
	else 
		return false;
}

function isURL(url){
	var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
	var re = new RegExp(strRegex);
	if (re.test(url))
		return true;
	else 
		return false;
}


/*$(function(){
	var path=$("#path").val();
	var sid;
	var sname;
	//修改操作
	//1.点击修改显示修改面板
	$(".modifySystemBtn").click(function(){
		
		
		sid=jQuery(this).attr("sid");
		sname=jQuery(this).attr("sname");
		$("#configName").attr("value",sname);//configTypeName 
		$("modifyisStartSelect").val(jQuery(this).attr("isStart"));
		
		$("#modifySystemdiv").slideDown(500);
	});
	//2.保存按钮的单击事件
	$("#modifySystemConfigBtn").click(function(){
		var configType=$("#modifyConfigType").val();
		var configTypeName=$("#configName").val();
		var isStart=$("#modifyisStartSelect").val();
		if(configType==''){
			humane.error("配置类型不能为空！");
		}else if(configTypeName==''){
			humane.error("配置类型名称不能为空！");
		}
		else{
			//提交到action处理
			$.post(path+"/modifyConfig",{"id":sid,"configType":configType,"configTypeName":configTypeName,"isStart":isStart},
			function(result){
				if(result=="peat"){
					humane.error("对不起，改类型名称以存在！");
				}else {
					humane.success("修改成功！");
					$("#modifySystemdiv").slideUp(500);
					window.location.reload(true);
				}
			},"html"		
			);
		}
	});
	//3.取消按钮
	$("#cancleModifySystemConfigBtn").click(function(){
		$("#modifySystemdiv").slideUp(500);
	});
	
	//删除
	$(".deleteSystemBtn").click(function(){
		var obj = $(this);
		if(confirm("您确定要删除【"+obj.attr("sname")+"】配置吗？")){
			//alert("Obj :" + obj.attr("sid")); deleteconfig
			$.post(path+"/deleteconfig",{
				'id':obj.attr("sid")
			},function(result){
				if("success" == result){
					alert("恭喜您，删除成功。");
					window.location.reload(true);
				}else{
					humane.success("对不起，删除"+configName+"失败.");
				}
				
			},"html");
		}
	});
	
});
*/
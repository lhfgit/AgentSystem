$().ready(function(){
	var path=$("#path").val();
	//查看
	$(".viewCustom").click(function(){
		var c = $(this);
		var c_id = c.attr('id');
		alert(c_id);
		window.location.href=path+"/viewcustom?id="+c_id;
	});
	//更新状态
	$(".modifyCustomStatus").click(function(){
		var c = $(this);
		var c_id = c.attr('id');
		var c_status = c.attr('customStatus');
		var m_status = c.attr('mStatus');
		var c_customname = c.attr('customName');
		if(c_status == '1'){
			if(confirm("确定要停用【"+c_customname+"】这个客户吗？")){
				$.post(path+"/modifycustomstatus",{'id':c_id,'customStatus':c_status},function(result){
					if("success" == result){
						alert("停用成功！");
						window.location.reload(true);
					}
				},'html');
			}
		}else{
			if(confirm("确定要启用【"+c_customname+"】这个客户吗？")){
				$.post(path+"/modifycustomstatus",{'id':c_id,'customStatus':c_status},function(result){
					if("success" == result){
						alert("启用成功！");
						window.location.reload(true);
					}
				},'html');
			}
		}
		
	});
	
	//修改
	$(".modifyCustom").click(function(){
		var c = $(this);
		var c_id = c.attr('id');
		var cname = c.attr('cname');
		window.location.href=path+"/modifycustom?id="+c_id+"&customName="+cname;
	});
	
	trBackground("tabletr");
});

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">用户管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="admin:insert">
						<a onclick="securityToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="${ctx}/user/add">添加</a>
					</@shiro.hasPermission>
				</div>
			</div>
			<div class="box-body">
				<div class="clearfix">
					<div class="col-md-4">
						<div class="input-group date ">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right" id="securityTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<input type="text" class="form-control" id="securityPremise" placeholder="根据账号搜索...">
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="user_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>账号</th>
								<th>昵称</th>
								<th>角色</th>
								<th>状态</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var user_tab;
$(function() {
	//初始化时间选择器
	$('#securityTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	user_tab=$('#user_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"lengthChange": true,
		"pagingType": "full_numbers",
		"language":{
            "emptyTable": "暂无数据",
            "lengthMenu": "每页显示 _MENU_ 条",
            "search": "搜索：",
            "info": "共 <b>_TOTAL_</b> 条，显示: [<b>_START_</b> -- <b>_END_</b>] 条",
            "infoFiltered": "(从_MAX_条记录中查询)",
            "paginate": {
                "first":      "首页",
                "last":       "尾页",
                "next":       "下一页",
                "previous":   "上一页"
            },
        },
		"ajax" : {"url":"${ctx}/user/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"userNo"},
			{"data":"nickName"},
			{"data":null},
			{"data":null},
			{"data":"createTime"},
			{"data":null} 
			],
		"columnDefs":[
			{
			    targets: 0,
			    data: null,
			    render: function (data) {
			    	No=No+1;
			        return No;
			    }
			},
			{
			    targets: 3,
			    data: null,
			    render: function (data) {
			    	var  listStr = "";
			    	var list = data.roleList;
			    	for(var i=0;i<list.length;i++){
			    		listStr = listStr+(i+1)+"."+list[i].roleName;
			    	}
			    	return listStr;
			    }
			},
			{
			    targets: 4,
			    data: null,
			    render: function (data) {
			    	if(data.status == "0"){
			    		return "不可用";
			    	}
			    	if(data.status == "1"){
			    		return "可用";
			    	}
			    	return "未知状态";
			    }
			},
            {
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
					var btn = "";
							if(data.userNo != 'super'){
								btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="${ctx}/user/view?id='+ data.id+ '">查看</a> &nbsp;'
								+'<@shiro.hasPermission name="admin:update">'
								+'<a class="btn btn-xs btn-info" onclick="securityToListAjax();" target="modal" modal="lg" href="${ctx}/user/edit?id='+ data.id+ '">修改</a> &nbsp;'
								+'</@shiro.hasPermission>'
								+'<@shiro.hasPermission name="admin:delete">'
								+'<a class="btn btn-xs btn-default" callback="securityReload();" data-body="确认要删除吗？" target="ajaxTodo" href="${ctx}/user/delete?id='+ data.id + '">删除</a>'
								+'</@shiro.hasPermission>';
							}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(user_tab,"#securityTime","#securityPremise");
	});
});

function securityReload(){
	reloadTable(user_tab,"#securityTime","#securityPremise");
}

function securityToListAjax(){
	list_ajax = user_tab;
	//console.log(list_ajax);
}
function securityUpdate(){
	$.ajax({
		url: '${ctx}/user/update',
	    type: 'post',
	    dataType: 'text',
	    data: $("#securityEditForm").serialize(),
	    success: function (data) {
	    	$("#lgModal").modal('hide');
	    	alertMsg("更新成功","success");
	    	reloadTable(list_ajax,"#securityTime","#securityPremise");
	    }
	});
}
</script>